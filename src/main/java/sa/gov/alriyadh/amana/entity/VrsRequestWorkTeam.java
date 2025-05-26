package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "VRS_REQUEST_WORK_TEAM")
public class VrsRequestWorkTeam {
    @Id
    @Column(name = "WORK_TEAM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VRS_REQUEST_WORK_TEAM_SEQ")
    @SequenceGenerator(name = "VRS_REQUEST_WORK_TEAM_SEQ", sequenceName = "VRS_REQUEST_WORK_TEAM_SEQ", allocationSize = 1, schema = "VRS")
    private Long workTeamId;

    @NotNull
    @Column(name = "REQUEST_ID", nullable = false)
    private Long requestId;

    @Size(max = 20)
    @NotNull
    @Column(name = "EMPLOYEE_CODE", nullable = false, length = 20)
    private String employeeCode;

    @Size(max = 200)
    @Column(name = "EMPLOYEE_NAME", length = 200)
    private String employeeName;

    @Size(max = 200)
    @Column(name = "JOB_TITLE", length = 200)
    private String jobTitle;

    @Column(name = "DIR_CODE")
    private Long dirCode;

    @NotNull
    @Column(name = "EMPLOYEE_SERIAL", nullable = false)
    private Long employeeSerial;

}