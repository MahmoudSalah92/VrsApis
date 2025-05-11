package sa.gov.alriyadh.amana.srinterface;

import java.util.Map;
import sa.gov.alriyadh.amana.dto.RequestPojo;

public interface IJobRequestService {

	Map<String, Object> sendSms(RequestPojo request);

}
