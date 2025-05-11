package sa.gov.alriyadh.amana.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sa.gov.alriyadh.amana.dao.AuditDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.entity.ServiceAudAll;
import sa.gov.alriyadh.amana.repository.ServiceAudAllRepo;

@Aspect
@Component
public class LoggingAspect {

	@Autowired(required = false)
	private HttpServletRequest request;
	
	@Autowired
    AuditDao auditDao;
	@Autowired
	ServiceAudAllRepo serviceAudAllRepo;

	@Value("${spring.application.name}")
	private String serviceName;
	@Value("${application.code}")
	private String applCode;
	@Value("${service.id}")
	private Integer serviceId;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void postAction() {
	}

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
	}

	@Pointcut("execution(* sa.gov.alriyadh.amana.controller.*.*(..))")
	public void anyApplicationService() {

	}

	@Around("anyApplicationService()")
	public Object applicationLogger(ProceedingJoinPoint joinPoint) throws Throwable{
		String payload = null;
		Object response = null;
		Map<String, Object> inputParams = new HashMap<String, Object>();
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			URI uri = new URI(request.getRequestURL().toString());
			if (joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] != null) {
				payload = ow.writeValueAsString(joinPoint.getArgs()[0]);
			}else {
				payload = "No Input Received";
			}
			log.info(" for the Method : " + uri.getPath() + " Request received : {}", payload);
			Date beforeDate = new Date();
			response = joinPoint.proceed();
			log.info(" for the Method :" + joinPoint.getSignature().getName() + " Response received : {}", response);

			//set audit method parameters.
			String clientIp = request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");  //Client IP Address
			InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());

			String f5Ip = inetAddress.getHostAddress(); //F5Ip IP Address
			String serverIp = InetAddress.getLocalHost().getHostAddress(); //Server IP Address
			GenericApiResponse<?> responseEntity = (GenericApiResponse<?>) response;
			Map<String,Object> responseStatus = (HashMap<String,Object>) responseEntity.getStatusDetails();
			String statusCode = (String) responseStatus.get("StatusCode");
			String statusMsg = (String) responseStatus.get("StatusMessage");
			String processId = (String) responseStatus.get("ProcessId");
			String notes = "Status is " + statusCode + ", "
					+ statusMsg+ " for call service " + joinPoint.getSignature().getName()
					+ " - Service URL: " + uri.getPath();
			ServiceAudAll serviceAudAll = new ServiceAudAll();
			//serviceAudAll.setServiceSerial(new BigDecimal(0));
			serviceAudAll.setServiceName(joinPoint.getSignature().getName());
			serviceAudAll.setAppCode(applCode);
			serviceAudAll.setClientIp(request.getRemoteAddr());
			serviceAudAll.setPinput(payload);
			serviceAudAll.setPoutput(response.toString());
			serviceAudAll.setExecuteDate(new Date());
			serviceAudAll.setStatusCode(new BigDecimal(HttpStatus.OK.value()));
			serviceAudAll.setStatusDesc(HttpStatus.OK.name());
			serviceAudAll.setFullStatusDesc(statusMsg);
			serviceAudAll.setNote(notes);
			serviceAudAll.setServId(new BigDecimal(serviceId));
			serviceAudAll.setServerIp(serverIp);
			serviceAudAll.setF5Ip(f5Ip);
			serviceAudAll.setSourceType(getDeviceType(request));
			serviceAudAll.setCallDate(new Date());
			serviceAudAll.setBeforeCallDate(beforeDate);
			Date afterDate = new Date();
			serviceAudAll.setAfterCallDate(afterDate);
			serviceAudAll.setUserCode("");
			serviceAudAll.setDirCode(null);
			serviceAudAll.setProcessId(processId);
			auditDao.addApiAudit(serviceAudAll);
			//ServiceAudAll nn= serviceAudAllRepo.save(serviceAudAll);
			//log.info("aduitSer : "+nn.getServiceSerial());
			//responseStatus.put("ServiceSerial",nn.getServiceSerial());
		} finally {
			log.info("@Around applicationLogger done...");
		}
		return response;
	}

	@AfterThrowing(pointcut = "anyApplicationService() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				e.getCause() != null ? e.getMessage() : "NULL");
	}

	public String getDeviceType(HttpServletRequest request) {
		String deviceType = "curl/Web-Browser";
		if(request != null && request.getHeader("User-Agent") != null) {
			String userAgent = request.getHeader("User-Agent");
			String[] agentVals = userAgent.split("/");
			if(userAgent.contains("Mobi")) {
				deviceType = agentVals[0]+ "/Mobile";
			}else {
				deviceType = agentVals[0]+ "/Web-Browser";
			}
		}
		return deviceType;
	}

}
