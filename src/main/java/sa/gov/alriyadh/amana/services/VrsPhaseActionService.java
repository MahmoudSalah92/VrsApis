package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RoleActionView;
import sa.gov.alriyadh.amana.repository.VrsPhaseActionRepository;
import sa.gov.alriyadh.amana.srinterface.IVrsPhaseActionService;

import java.util.List;

@Service
public class VrsPhaseActionService implements IVrsPhaseActionService {

    @Autowired
    VrsPhaseActionRepository vrsPhaseActionRepository;

    @Override
    public List<RoleActionView[]> getActions(Long role) {
        return vrsPhaseActionRepository.getRoleActions(role);
    }

    @Override
    public PhaseActionDetailView getRoleActionDetail(Long role, Long actionId) {
        return vrsPhaseActionRepository.getRoleActionDetails(role, actionId);
    }

}
