package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.VrsPhaseAction;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RoleActionView;

import java.util.List;

public interface VrsPhaseActionRepository extends JpaRepository<VrsPhaseAction, Long> {

    @Query(value = "SELECT AC.PHASE_ACTION_ID phaseActionId,\n" +
            "         PH_CHILD.PHASE_NAME      actionName,\n" +
            "         ph_child.phase_desc      statusDesc,\n" +
            "         PH_CHILD.FROM_ROLE_NO    roleNo\n" +
            "    FROM VRS.VRS_PHASE_ACTIONS AC,\n" +
            "         VRS.VRS_PHASES       PH_CHILD\n" +
            "   WHERE    \n" +
            "         AC.CHILD_PHASE_ID = PH_CHILD.PHASE_ID\n" +
            "         AND PH_CHILD.FROM_ROLE_NO = :role\n" +
            "ORDER BY AC.ACTION_ORDER ASC", nativeQuery = true)
    List<RoleActionView[]> getRoleActions(@Param("role") Long role);

    @Query(value = "SELECT AC.PHASE_ACTION_ID phaseActionId,\n" +
            "         PH_PARENT.PHASE_ID       fromPhaseId,\n" +
            "         PH_PARENT.PHASE_NAME     fromPhaseName,\n" +
            "         PH_PARENT.phase_desc     fromPhaseDesc,\n" +
            "         PH_CHILD.PHASE_ID        toPhaseId,\n" +
            "         PH_CHILD.PHASE_NAME      toPhaseName,\n" +
            "         PH_CHILD.phase_desc      toPhaseDesc,\n" +
            "         PH_CHILD.FROM_ROLE_NO    fromRoleNo,\n" +
            "         PH_CHILD.TO_ROLE_NO      toRoleNo\n" +
            "    FROM VRS.VRS_PHASE_ACTIONS AC,\n" +
            "         VRS.VRS_PHASES       PH_PARENT,\n" +
            "         VRS.VRS_PHASES       PH_CHILD\n" +
            "   WHERE     AC.PARENT_PHASE_ID = PH_PARENT.PHASE_ID\n" +
            "         AND AC.CHILD_PHASE_ID = PH_CHILD.PHASE_ID\n" +
            "         AND PH_CHILD.FROM_ROLE_NO = :role\n" +
            "         AND AC.PHASE_ACTION_ID =:actionId\n" +
            "ORDER BY AC.ACTION_ORDER ASC", nativeQuery = true)
    PhaseActionDetailView getRoleActionDetails(@Param("role") Long role, @Param("actionId") Long actionId);

}
