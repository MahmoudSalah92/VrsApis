package sa.gov.alriyadh.amana.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Long transactionNo;

    private String transactionDetails;

    private String createdUser;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String requestStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String requestNotes;

}
