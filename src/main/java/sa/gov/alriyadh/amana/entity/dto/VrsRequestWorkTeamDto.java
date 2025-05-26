package sa.gov.alriyadh.amana.entity.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VrsRequestWorkTeamDto {

    //@NotNull
    private Long workTeamId;

    @NotNull
    private Long requestId;

    @NotNull
    private String employeeCode;

    private String employeeName;
    private String jobTitle;
    private Long dirCode;

    //@NotNull
    private Long employeeSerial;

}
