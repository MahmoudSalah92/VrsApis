package sa.gov.alriyadh.amana.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "VRS_PHASE_ACTIONS", schema = "VRS")
public class VrsPhaseAction {
    @Id
    @Column(name = "PHASE_ACTION_SERIAL", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "PARENT_PHASE_ID", nullable = false)
    private Long parentPhaseId;

    @NotNull
    @Column(name = "CHILD_PHASE_ID", nullable = false)
    private Long childPhaseId;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Column(name = "ACTION_ORDER")
    private Long actionOrder;

    @Size(max = 4000)
    @Column(name = "NOTES", length = 4000)
    private String notes;

}