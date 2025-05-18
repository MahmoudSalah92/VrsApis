package sa.gov.alriyadh.amana.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.entity.VrsRole;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestPhaseDto;
import sa.gov.alriyadh.amana.pojo.DirTypeData;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.pojo.RoleActionView;
import sa.gov.alriyadh.amana.pojo.VrsRequestFilter;
import sa.gov.alriyadh.amana.services.VrsRolesService;
import sa.gov.alriyadh.amana.services.VrsPhaseActionService;
import sa.gov.alriyadh.amana.services.VrsRequestService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vrs/api/services/")
@Validated
public class ServiceController {

    @Autowired
    VrsRolesService vrsRolesService;

    @Autowired
    VrsPhaseActionService vrsPhaseActionService;

    @Autowired
    VrsRequestService vrsRequestService;

    @Autowired
    DirTypeData dirTypeData;

    @GetMapping("/roles")
    public GenericApiResponse<?> getVrsRoles() {
        List<VrsRole> roles = vrsRolesService.getUserRoles();
        return GenericApiResponse.returnJsonTemp("0", null, roles);
    }

    @GetMapping("/roleActions/{role}")
    public GenericApiResponse<?> getVrsRoleActions(@PathVariable(required = true) Long role) {
        List<RoleActionView[]> roleActions = vrsPhaseActionService.getActions(role);
        return GenericApiResponse.returnJsonTemp("0", null, roleActions);
    }

    @GetMapping("/dirTypes")
    public GenericApiResponse<?> getVrsDirTypes() {
        Map<Integer, String> dirTypes = dirTypeData.getTypes();
        return GenericApiResponse.returnJsonTemp("0", null, dirTypes);
    }

    @GetMapping("/directorates/{dirType}")
    public GenericApiResponse<?> getDirectorates(@PathVariable(required = true) Long dirType) {
        List<Object[]> directorates = vrsRequestService.getDirectorates(dirType);
        return GenericApiResponse.returnJsonTemp("0", null, directorates);
    }

    @PostMapping("/addNewRequest")
    public GenericApiResponse<?> addNewRequest(@Valid @RequestBody VrsRequestDto vrsRequestDto) {
        Map<String, Object> output = vrsRequestService.addNewRequest(vrsRequestDto);
        return GenericApiResponse.returnJsonTemp("0", null, output);
    }

    @PostMapping("/addRequestPhase")
    public GenericApiResponse<?> addRequestPhase(@Valid @RequestBody RequestPhase requestPhase) {
        Map<String, Object> output = vrsRequestService.addRequestPhase(requestPhase);
        return GenericApiResponse.returnJsonTemp("0", null, output);
    }


    @PostMapping("/findRequests")
    public GenericApiResponse<?> getRequestByFilter(@Valid @RequestBody VrsRequestFilter filter) {
        System.out.println(filter.getUserCode());
        List<VrsRequestDto> requests = vrsRequestService.findRequestsByFilter(filter);
        return GenericApiResponse.returnJsonTemp("0", null, requests);
    }

    @GetMapping("/statusList")
    public GenericApiResponse<?> getRequestStatusList() {
        List<Object[]> statusList = vrsRequestService.getRequestStatusList();
        return GenericApiResponse.returnJsonTemp("0", null, statusList);
    }

    @GetMapping("/requestPhases/{requestNo}")
    public GenericApiResponse<?> getRequestPhases(@PathVariable(required = true) Long requestNo) {
        List<VrsRequestPhaseDto> requestPhases = vrsRequestService.getRequestPhases(requestNo);
        return GenericApiResponse.returnJsonTemp("0", null, requestPhases);
    }

    @GetMapping("/employees/{dirCode}")
    public GenericApiResponse<?> getEmployeesByDir(@PathVariable(required = true) Long dirCode) {
        List<Object[]> employees = vrsRequestService.getEmployeesByDir(dirCode);
        return GenericApiResponse.returnJsonTemp("0", null, employees);
    }

}
