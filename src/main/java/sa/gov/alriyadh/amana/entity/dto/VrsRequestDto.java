package sa.gov.alriyadh.amana.entity.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VrsRequestDto {

    private Long requestSerial;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Long requestPhaseId;

    @NotNull
    private Long dirType;

    @NotNull
    private Long dirCode;

    @NotNull
    private String userCode;

    private String notes;

}
