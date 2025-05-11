package sa.gov.alriyadh.amana.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import sa.gov.alriyadh.amana.config.language.LangLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonTempUtil {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LangLocaleResolver langLocaleResolver;

    // returnJsonDataTemp --> (main template of return json data success of fail)
    public Map<String, Object> returnJsonDataTemp(int flage, Map<String, Object> outputData, String errorCode,
                                                  String errorMsg) {
        Map<String, Object> tempJsonData = new HashMap<String, Object>();
        Map<String, Object> statusDetails = new HashMap<String, Object>();
        if (flage == 1) { // success Process
            statusDetails.put("StatusCode", "0");
            statusDetails.put("StatusMessage", "Success Process");
            tempJsonData.put("OutputData", outputData);
        } else { // 2 fail
            statusDetails.put("StatusCode", errorCode);
            statusDetails.put("StatusMessage", errorMsg);
        }
        tempJsonData.put("StatusDetails", statusDetails);
        return tempJsonData;
    }

    public String getMessageFromResourceBundle(String key){
        return messageSource.getMessage(key, null, langLocaleResolver.resolveLocale(request));
    }
}
