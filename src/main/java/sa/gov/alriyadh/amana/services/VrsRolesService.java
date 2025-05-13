package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.gov.alriyadh.amana.entity.VrsRole;
import sa.gov.alriyadh.amana.repository.VrsRoleRepository;
import sa.gov.alriyadh.amana.srinterface.IVrsRolesService;

import java.util.List;

@Service
public class VrsRolesService implements IVrsRolesService {

    @Autowired
    VrsRoleRepository vrsRoleRepository;

    @Override
    public List<VrsRole> getUserRoles() {
        return vrsRoleRepository.findAll();
    }

}
