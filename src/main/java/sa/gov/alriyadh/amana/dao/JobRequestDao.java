package sa.gov.alriyadh.amana.dao;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;


@Component
public class JobRequestDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String SEND_SMS = "?=call CMNV3.SEND_PERSONS_MSG.ADD_SMS_MESSAGE(?,?,?,?,?,?,to_date(?,'dd-mm-yyyy hh24:mi:ss'),?,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)}";

	public Object sendSms(Map<String, Object> datafields) {
		Object result = null;
		Map<String, Object> outParams = new HashMap<String, Object>();
		try {
			jdbcTemplate.setResultsMapCaseInsensitive(true);
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("CMNV3")
					.withCatalogName("SEND_PERSONS_MSG").withFunctionName("ADD_SMS_MESSAGE");
			MapSqlParameterSource params = new MapSqlParameterSource(datafields);
			outParams.putAll(simpleJdbcCall.execute(params));
			if (outParams != null && outParams.size() > 0) {
				result = outParams.get("return");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
