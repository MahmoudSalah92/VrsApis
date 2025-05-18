package sa.gov.alriyadh.amana.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link sa.gov.alriyadh.amana.entity.VrsRequestPhase}
 */
@Value
public class VrsRequestPhaseDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long requestPhaseId;

    @NotNull
    private Long requestId;

    private Long requestPhaseSerial;

    private Long fromRoleId;

    private Long toRoleId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long fromPhaseId;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long toPhaseId;

    @Size(max = 4000)
    private String notes;

    @NotNull
    private String createdDate; // As String in the format yyyy/MM/dd HH:mm:ss

    @NotNull
    @Size(max = 20)
    private String createdUser;
}