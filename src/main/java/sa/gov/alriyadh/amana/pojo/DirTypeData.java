package sa.gov.alriyadh.amana.pojo;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DirTypeData {

    private final Map<Integer, String> types;// = new LinkedHashMap<>();

    public DirTypeData() {
        types = new LinkedHashMap<>();
        types.put(1, "بلدية فرعية");
        types.put(2, "إدارة عامة");
        types.put(3, "وكالة");
    }

    public Map<Integer, String> getTypes() {
        return types;
    }

    public String getTypeName(Integer type) {
        return types.get(type);
    }

}
