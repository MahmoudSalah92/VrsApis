package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RoleActionView;

import java.util.List;

public interface IVrsPhaseActionService {

    List<RoleActionView[]> getActions(Long role);

    PhaseActionDetailView getRoleActionDetail(Long role, Long actionId);

}
