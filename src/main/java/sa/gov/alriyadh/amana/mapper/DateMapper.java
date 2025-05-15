package sa.gov.alriyadh.amana.mapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Named("localDateToString")
    public String asString(LocalDate date) {
        return date != null ? date.atStartOfDay().format(FORMATTER) : null;
    }

    @Named("stringToLocalDate")
    public LocalDate asLocalDate(String date) {
        return date != null ? LocalDate.parse(date, FORMATTER) : null;
    }

}
