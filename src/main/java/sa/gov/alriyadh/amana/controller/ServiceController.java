package sa.gov.alriyadh.amana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.entity.VrsRole;
import sa.gov.alriyadh.amana.services.VrsRolesService;

import java.util.List;

@RestController
@RequestMapping("/vrs/api/services/")
@Validated
public class ServiceController {

    @Autowired
    VrsRolesService rolesService;


    @GetMapping("/userRoles")
    public GenericApiResponse<?> getVrsUserRoles() {
        List<VrsRole> roles = rolesService.getUserRoles();
        return GenericApiResponse.returnJsonTemp("0", null, roles);
    }


}
