package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "VRS_PHASES", schema = "VRS")
public class VrsPhase {
    @Id
    @Column(name = "PHASE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long phaseId;

    @Size(max = 500)
    @NotNull
    @Column(name = "PHASE_NAME", nullable = false, length = 500)
    private String phaseName;

    @Size(max = 500)
    @Column(name = "PHASE_DESC", length = 500)
    private String phaseDesc;

    @NotNull
    @Column(name = "FROM_ROLE_NO", nullable = false)
    private Long fromRoleNo;

    @NotNull
    @Column(name = "TO_ROLE_NO", nullable = false)
    private Long toRoleNo;

    @Size(max = 2000)
    @Column(name = "BENEFICIARY_MESSAGE", length = 2000)
    private String beneficiaryMessage;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Size(max = 200)
    @Column(name = "NOTES", length = 200)
    private String notes;

}