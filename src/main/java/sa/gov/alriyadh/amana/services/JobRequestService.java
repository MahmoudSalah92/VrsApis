package sa.gov.alriyadh.amana.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sa.gov.alriyadh.amana.dao.JobRequestDao;
import sa.gov.alriyadh.amana.dto.RequestPojo;
import sa.gov.alriyadh.amana.srinterface.IJobRequestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobRequestService implements IJobRequestService {

	@Autowired
	JobRequestDao jobRequestDao;

	// Send SMS
	@Override
	public Map<String, Object> sendSms(RequestPojo request) {
		Map<String, Object> dataSet = new HashMap<String, Object>();
		Map<String, Object> outputData = new HashMap<String, Object>();
		Map<String, String> resultSet = new HashMap<String, String>();
		BigDecimal result = (BigDecimal) jobRequestDao.sendSms(request.getDatafields());
		if (result != null && result.intValue() != 0) {
			resultSet.put("StatusCode", result.toString());
			resultSet.put("StatusMessage", "SMS has been sent successfully");
		} else {
			resultSet.put("StatusCode", "0");
			resultSet.put("StatusMessage", "Failed to send SMS");
		}
		outputData.put("ResultSet", resultSet);
		if (outputData.get("ResultSet") != null) {
			dataSet = returnJsonDataTemp(1, outputData, null, null);
		} else {
			dataSet = returnJsonDataTemp(2, null, "E0002", "No Requests Data Found.");
		}

		return dataSet;
	}

	// returnJsonDataTemp --> (main template of return json data success of fail)
	public Map<String, Object> returnJsonDataTemp(int flage, Map<String, Object> outputData, String errorCode,
			String errorMsg) {
		Map<String, Object> tempJsonData = new HashMap<String, Object>();
		Map<String, Object> statusDetails = new HashMap<String, Object>();
		if (flage == 1) { // success Process
			statusDetails.put("StatusCode", "200");
			statusDetails.put("StatusMessage", "Success Process");
			tempJsonData.put("OutputData", outputData);
		} else { // 2 fail
			statusDetails.put("StatusCode", errorCode);
			statusDetails.put("StatusMessage", errorMsg);
		}
		tempJsonData.put("StatusDetails", statusDetails);
		return tempJsonData;
	}

}
