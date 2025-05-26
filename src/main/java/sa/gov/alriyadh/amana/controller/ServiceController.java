package sa.gov.alriyadh.amana.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sa.gov.alriyadh.amana.dto.response.GenericApiResponse;
import sa.gov.alriyadh.amana.entity.VrsRole;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestPhaseDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestWorkTeamDto;
import sa.gov.alriyadh.amana.pojo.*;
import sa.gov.alriyadh.amana.services.VrsRolesService;
import sa.gov.alriyadh.amana.services.VrsPhaseActionService;
import sa.gov.alriyadh.amana.services.VrsRequestService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vrs/api/services/")
@Validated
@SecurityRequirement(name = "bearerAuth")
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
    @Operation(summary = "Returns Roles", description = "Returns Roles")
    public GenericApiResponse<?> getVrsRoles() {
        List<VrsRole> roles = vrsRolesService.getUserRoles();
        return GenericApiResponse.returnJsonTemp("0", null, roles);
    }

    @GetMapping("/roleActions/{role}")
    @Operation(summary = "Returns Role Actions For Specified Role", description = "Returns Role Actions For Specified Role")
    public GenericApiResponse<?> getVrsRoleActions(@PathVariable(required = true) Long role) {
        List<RoleActionView> roleActions = vrsPhaseActionService.getActions(role);
        return GenericApiResponse.returnJsonTemp("0", null, roleActions);
    }

    @GetMapping("/dirTypes")
    @Operation(summary = "Returns Directorate Types", description = "Returns Directorate Types")
    public GenericApiResponse<?> getVrsDirTypes() {
        Map<Integer, String> dirTypes = dirTypeData.getTypes();
        return GenericApiResponse.returnJsonTemp("0", null, dirTypes);
    }

    @GetMapping("/directorates/{dirType}")
    @Operation(summary = "Returns Directorate Under Specified Type", description = "Returns Directorate Under Specified Type")
    public GenericApiResponse<?> getDirectorates(@PathVariable(required = true) Long dirType) {
        List<Object[]> directorates = vrsRequestService.getDirectorates(dirType);
        return GenericApiResponse.returnJsonTemp("0", null, directorates);
    }

    @PostMapping("/addNewRequest")
    @Operation(summary = "Add New Request", description = "Add New Request")
    public GenericApiResponse<?> addNewRequest(@Valid @RequestBody VrsRequestDto vrsRequestDto) {
        Map<String, Object> output = vrsRequestService.addNewRequest(vrsRequestDto);
        return GenericApiResponse.returnJsonTemp("0", null, output);
    }

    @PostMapping("/addRequestPhase")
    @Operation(summary = "Add Request Phase", description = "Add Request Phase")
    public GenericApiResponse<?> addRequestPhase(@Valid @RequestBody RequestPhase requestPhase) {
        Map<String, Object> output = vrsRequestService.addRequestPhase(requestPhase);
        return GenericApiResponse.returnJsonTemp("0", null, output);
    }


    @PostMapping("/findRequests")
    @Operation(summary = "Returns Request By roleId || userCode || requestNo", description = "Returns Request By roleId || userCode || requestNo")
    public GenericApiResponse<?> getRequestByFilter(@Valid @RequestBody VrsRequestFilter filter) {
        System.out.println(filter.getUserCode());
        List<VrsRequestDto> requests = vrsRequestService.findRequestsByFilter(filter);
        return GenericApiResponse.returnJsonTemp("0", null, requests);
    }

    @GetMapping("/statusList")
    @Operation(summary = "Returns Request Status List", description = "Returns Request Status List")
    public GenericApiResponse<?> getRequestStatusList() {
        List<Object[]> statusList = vrsRequestService.getRequestStatusList();
        return GenericApiResponse.returnJsonTemp("0", null, statusList);
    }

    @GetMapping("/requestPhases/{requestNo}")
    @Operation(summary = "Returns Request Phases", description = "Returns Request Phases")
    public GenericApiResponse<?> getRequestPhases(@PathVariable(required = true) Long requestNo) {
        List<VrsRequestPhaseDto> requestPhases = vrsRequestService.getRequestPhases(requestNo);
        return GenericApiResponse.returnJsonTemp("0", null, requestPhases);
    }

    @GetMapping("/employees/{dirCode}")
    @Operation(summary = "Returns Employees List Under Directorate", description = "Returns Employees List Under Directorate")
    public GenericApiResponse<?> getEmployeesByDir(@PathVariable(required = true) Long dirCode) {
        List<Object[]> employees = vrsRequestService.getEmployeesByDir(dirCode);
        return GenericApiResponse.returnJsonTemp("0", null, employees);
    }

    @PostMapping("/addEmployee")
    @Operation(summary = "Add Employee To Request Work Team", description = "Add Employee To Request Work Team")
    public GenericApiResponse<?> addEmployeeToWorkTeam(@Valid @RequestBody VrsRequestWorkTeamDto employee) {
        Map<String, Object> output = vrsRequestService.addEmployeeToWorkTeam(employee);
        return GenericApiResponse.returnJsonTemp("0", null, output);
    }

    @DeleteMapping("/deleteEmployee/{workTeamId}")
    @Operation(summary = "Delete Employee From Request Work Team", description = "Delete Employee From Request Work Team")
    public GenericApiResponse<?> deleteEmployeeFromWorkTeam(@PathVariable(required = true) Long workTeamId) {
        Map<String, Object> output = vrsRequestService.deleteEmployeeFromWorkTeam(workTeamId);
        return GenericApiResponse.returnJsonTemp("0", null, output);
    }

}
