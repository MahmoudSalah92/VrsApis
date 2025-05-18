package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "VRS_REQUEST_PHASES", schema = "VRS")
public class VrsRequestPhase {
    @Id
    @Column(name = "REQUEST_PHASE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VRS_REQUEST_PHASES_SEQ")
    @SequenceGenerator(name = "VRS_REQUEST_PHASES_SEQ", sequenceName = "VRS_REQUEST_PHASES_SEQ", allocationSize = 1, schema = "VRS")
    private Long requestPhaseId; // pk for table

    @NotNull
    @Column(name = "REQUEST_ID", nullable = false)
    private Long requestId;

    @Column(name = "REQUEST_PHASE_SERIAL")
    private Long requestPhaseSerial; // serial max+1 for each request

    @Column(name = "FROM_ROLE_ID")
    private Long fromRoleId;

    @Column(name = "TO_ROLE_ID")
    private Long toRoleId;

    @NotNull
    @Column(name = "FROM_PHASE_ID", nullable = false)
    private Long fromPhaseId;

    @NotNull
    @Column(name = "TO_PHASE_ID", nullable = false)
    private Long toPhaseId;

    @Size(max = 4000)
    @Column(name = "NOTES", length = 4000)
    private String notes;

    @NotNull
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDate createdDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "CREATED_USER", nullable = false, length = 20)
    private String createdUser;

}