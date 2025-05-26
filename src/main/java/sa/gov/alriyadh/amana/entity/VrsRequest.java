package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "VRS_REQUESTS", schema = "VRS")
public class VrsRequest {
    @Id
    @Column(name = "REQUEST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VRS_REQUESTS_SEQ")
    @SequenceGenerator(name = "VRS_REQUESTS_SEQ", sequenceName = "VRS_REQUESTS_SEQ", allocationSize = 1, schema = "VRS")
    private Long requestId;

    @Column(name = "REQUEST_DATE")
    private LocalDateTime requestDate;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "DIR_TYPE")
    private Long dirType;

    @Column(name = "DIR_CODE")
    private Long dirCode;

    @Size(max = 20)
    @Column(name = "CREATED_USER")
    private String createdUser;

    @Size(max = 2000)
    @Column(name = "TRANSACTION_DETAILS", length = 2000)
    private String transactionDetails;

    @Size(max = 2000)
    @Column(name = "NOTES", length = 2000)
    private String notes;

    @Size(max = 20)
    @Column(name = "DIR_EMPLOYEE_CODE", length = 20)
    private String dirEmployeeCode;

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

    @Formula("(SELECT p.phase_desc FROM VRS.vrs_phases p WHERE p.phase_id = REQUEST_PHASE_ID)")
    //@Transient
    private String requestStatus;

    //@Formula("(SELECT p.NOTES FROM VRS.vrs_request_phases p WHERE p.request_id = REQUEST_ID AND p.to_phase_id = REQUEST_PHASE_ID)")
    @Transient
    private String requestNotes;

}