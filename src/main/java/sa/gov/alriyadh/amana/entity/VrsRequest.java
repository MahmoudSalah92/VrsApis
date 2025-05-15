package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "VRS_REQUESTS", schema = "VRS")
public class VrsRequest {
    @Id
    @Column(name = "REQUEST_SERIAL", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VRS_REQUESTS_SEQ")
    @SequenceGenerator(name = "VRS_REQUESTS_SEQ", sequenceName = "VRS_REQUESTS_SEQ", allocationSize = 1, schema = "VRS")
    private Long requestSerial;

    @Column(name = "REQUEST_DATE")
    private LocalDate requestDate;

    @Column(name = "REQUEST_STATUS")
    private Long requestStatus;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DIR_TYPE")
    private Long dirType;

    @Column(name = "DIR_CODE")
    private Long dirCode;

    @Column(name = "WORK_TEAM")
    private Long workTeam;

    @Size(max = 2000)
    @Column(name = "TRANSACTION_DETAILS", length = 2000)
    private String transactionDetails;

    @Size(max = 2000)
    @Column(name = "NOTES", length = 2000)
    private String notes;

    @Column(name = "DIR_EMPLOYEE_CODE")
    private Long dirEmployeeCode;

    @Size(max = 2000)
    @Column(name = "DIR_EMPLOYEE_STATEMENT", length = 2000)
    private String dirEmployeeStatement;

    @Size(max = 2000)
    @Column(name = "DIR_MANAGER_VISIONS", length = 2000)
    private String dirManagerVisions;

    @Size(max = 2000)
    @Column(name = "FINAL_APPROVER_VISIONS", length = 2000)
    private String finalApproverVisions;

    @Size(max = 2000)
    @Column(name = "DIR_EMPLOYEE_STATEMENT_CONFIRM", length = 2000)
    private String dirEmployeeStatementConfirm;

    @Column(name = "TRANSACTION_NO")
    private Long transactionNo;

    @Column(name = "REQUEST_PHASE_ID")
    private Long requestPhaseId;

}