package sa.gov.alriyadh.amana.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericApiResponse<T> {
    @JsonProperty(value = "StatusDetails")
    T statusDetails;
    @JsonProperty(value = "OutputData")
    T outputData;

    // Static helper methods
    public static <T> GenericApiResponse<T> returnJsonTemp(String status, String message, T outputData) {
        Map<String, Object> statusDetails = new HashMap<String, Object>();
        // Map<String, Object> outData = new HashMap<String, Object>();
        String processId = UUID.randomUUID().toString();
        if ("0".equals(status)) { // returnJsonTemp Process
            statusDetails.put("StatusCode", "0");
            statusDetails.put("StatusMessage", "Success Process");
            statusDetails.put("ProcessId", processId);
            statusDetails.put("processTime", new Date().toString());
        } else { // 2 fail
            statusDetails.put("StatusCode", status);
            statusDetails.put("StatusMessage", message);
            statusDetails.put("ProcessId", processId);
            statusDetails.put("processTime", new Date().toString());
        }
        return new GenericApiResponse<T>((T) statusDetails, outputData);
    }

}
