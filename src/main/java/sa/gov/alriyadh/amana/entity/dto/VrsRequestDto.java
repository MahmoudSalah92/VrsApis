package sa.gov.alriyadh.amana.entity.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VrsRequestDto {

    private Long requestId;

    private String requestDate;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    private Long requestPhaseId;

    @NotNull
    private Long dirType;

    @NotNull
    private Long dirCode;

    @NotNull
    private String dirEmployeeCode;

    private String notes;

}
