package sa.gov.alriyadh.amana.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPhase {

    @NotNull(message = "{REQUEST_NO_REQUIRED}")
    private Long requestNO;
    @NotNull(message = "{ISSUE_USER_REQUIRED}")
    private String issueUser;
    @NotNull(message = "{CURRENT_ROLE_REQUIRED}")
    private Long currentRole;
    @NotNull(message = "{ACTION_ID_REQUIRED}")
    private Long actionId;
    private String notes;

}
