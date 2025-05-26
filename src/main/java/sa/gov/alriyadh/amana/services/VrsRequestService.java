package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.entity.VrsRequestPhase;
import sa.gov.alriyadh.amana.entity.VrsRequestWorkTeam;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestWorkTeamDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestPhaseDto;

import sa.gov.alriyadh.amana.mapper.VrsRequestMapper;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.pojo.VrsRequestFilter;
import sa.gov.alriyadh.amana.repository.VrsPhaseActionRepository;
import sa.gov.alriyadh.amana.repository.VrsRequestRepository;
import sa.gov.alriyadh.amana.repository.VrsRequestPhaseRepository;
import sa.gov.alriyadh.amana.repository.VrsRequestWorkTeamRepository;
import sa.gov.alriyadh.amana.srinterface.IVrsRequestService;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VrsRequestService implements IVrsRequestService {

    @Autowired
    VrsPhaseActionRepository vrsPhaseActionRepository;
    @Autowired
    VrsRequestPhaseRepository vrsRequestPhaseRepository;
    @Autowired
    VrsRequestRepository vrsRequestRepository;

    @Autowired
    VrsRequestMapper mapper;
    @Autowired
    private VrsRequestWorkTeamRepository vrsRequestWorkTeamRepository;

    @Override
    public List<Object[]> getDirectorates(Long dirType) {
        return vrsRequestRepository.getDirectorates(dirType);
    }

    @Override
    public Map<String, Object> addNewRequest(VrsRequestDto vrsRequestDto) {
        Map<String, Object> output = new LinkedHashMap<>();

        VrsRequest entity = mapper.toEntity(vrsRequestDto); // sets requestDate = now
        entity.setRequestPhaseId(1L);
        VrsRequest request = vrsRequestRepository.save(entity);
        VrsRequestDto savedRequestDto = mapper.toDto(request); // return full DTO with generated requestNo and formatted dates

        PhaseActionDetailView actionDetail = vrsPhaseActionRepository.getRoleActionDetails(1L, 1L);
        Long nextRequestPhaseSerial = vrsRequestPhaseRepository.getNextSerial(savedRequestDto.getRequestId());

        VrsRequestPhase vrsRequestPhase = new VrsRequestPhase();
        vrsRequestPhase.setRequestId(savedRequestDto.getRequestId());
        vrsRequestPhase.setRequestPhaseSerial(nextRequestPhaseSerial);
        vrsRequestPhase.setCreatedDate(LocalDateTime.now());
        vrsRequestPhase.setCreatedUser(savedRequestDto.getCreatedUser());
        vrsRequestPhase.setFromPhaseId(actionDetail.getFromPhaseId());
        vrsRequestPhase.setToPhaseId(actionDetail.getToPhaseId());
        vrsRequestPhase.setFromRoleId(actionDetail.getFromRoleNo());
        vrsRequestPhase.setToRoleId(actionDetail.getToRoleNo());
        vrsRequestPhase.setNotes(actionDetail.getFromPhaseDesc());

        request.setNotes(actionDetail.getFromPhaseDesc());

        vrsRequestRepository.save(request);
        vrsRequestPhaseRepository.save(vrsRequestPhase);

        //output.put("output", "Operation successful.");
        output.put("RequestNO", savedRequestDto.getRequestId());
        output.put("RequestStatus", actionDetail.getToPhaseDesc());

        return output;
    }


    @Override
    @Transactional
    public Map<String, Object> addRequestPhase(RequestPhase requestPhase) {
        Map<String, Object> output = new LinkedHashMap<>();
        PhaseActionDetailView actionDetail = vrsPhaseActionRepository.getRoleActionDetails(requestPhase.getCurrentRole(), requestPhase.getActionId());
        Long nextRequestPhaseSerial = vrsRequestPhaseRepository.getNextSerial(requestPhase.getRequestNO());
        Optional<VrsRequest> request = vrsRequestRepository.findById(requestPhase.getRequestNO());
        if (request.isPresent()) {

            if ((requestPhase.getCurrentRole() == 1 && requestPhase.getActionId() == 2 && request.get().getRequestPhaseId() == 1)
                    || request.get().getRequestPhaseId() < actionDetail.getToPhaseId()) {

                if (requestPhase.getCurrentRole() == 2 && (requestPhase.getDirEmployeeCode() == null || requestPhase.getDirEmployeeCode().isEmpty())) {
                    output.put("output", "Operation Failed (Dir Employee Code Required for this role).");
                    output.put("RequestNO", requestPhase.getRequestNO());
                    return output;
                }
                request.get().setRequestPhaseId(actionDetail.getToPhaseId());
                VrsRequestPhase vrsRequestPhase = new VrsRequestPhase();
                vrsRequestPhase.setRequestId(requestPhase.getRequestNO());
                vrsRequestPhase.setRequestPhaseSerial(nextRequestPhaseSerial);
                vrsRequestPhase.setCreatedDate(LocalDateTime.now());
                vrsRequestPhase.setCreatedUser(requestPhase.getIssueUser());
                vrsRequestPhase.setFromPhaseId(actionDetail.getFromPhaseId());
                vrsRequestPhase.setToPhaseId(actionDetail.getToPhaseId());
                vrsRequestPhase.setFromRoleId(actionDetail.getFromRoleNo());
                vrsRequestPhase.setToRoleId(actionDetail.getToRoleNo());
                vrsRequestPhase.setNotes(requestPhase.getNotes());

                request.get().setNotes(requestPhase.getNotes());

                if (requestPhase.getCurrentRole() != null && requestPhase.getCurrentRole().equals(2L)) {
                    request.get().setDirEmployeeCode(requestPhase.getDirEmployeeCode());
                }

                if (requestPhase.getCurrentRole() != null && requestPhase.getCurrentRole().equals(3L)) {
                    request.get().setDirEmployeeStatement(requestPhase.getNotes());
                } else if (requestPhase.getCurrentRole() != null && requestPhase.getCurrentRole().equals(4L)) {
                    request.get().setDirManagerVisions(requestPhase.getNotes());
                } else if (requestPhase.getCurrentRole() != null && requestPhase.getCurrentRole().equals(5L)) {
                    request.get().setFinalApproverVisions(requestPhase.getNotes());
                } else if (requestPhase.getCurrentRole() != null && requestPhase.getCurrentRole().equals(6L)) {
                    request.get().setDirEmployeeStatementConfirm(requestPhase.getNotes());
                }

                vrsRequestRepository.save(request.get());
                vrsRequestPhaseRepository.save(vrsRequestPhase);
                //output.put("output", "Operation Successful.");
                output.put("RequestNO", requestPhase.getRequestNO());
                output.put("RequestStatus", actionDetail.getToPhaseDesc());


            } else {
                output.put("output", "Operation Failed (Action has already been taken - the action cannot be repeated at the same phase).");
                output.put("RequestNO", requestPhase.getRequestNO());
            }

        } else {
            output.put("output", "Operation Failed (Request not found).");
            output.put("RequestNO", requestPhase.getRequestNO());
        }

        return output;
    }

    @Override
    public List<Object[]> getRequestStatusList() {
        return vrsRequestRepository.getRequestStatusList();
    }

    @Override
    public List<VrsRequestDto> findRequestsByFilter(VrsRequestFilter filter) {
        return vrsRequestRepository.findRequestsByFilter(filter).stream()
                .map(vrsRequest -> mapper.toDto(vrsRequest))
                .collect(Collectors.toList());
    }

    @Override
    public List<VrsRequestPhaseDto> getRequestPhases(Long requestNo) {
        return vrsRequestPhaseRepository.findByRequestId(requestNo).stream()
                .map(vrsRequestPhase -> mapper.toDto(vrsRequestPhase))
                .collect(Collectors.toList());
    }

    @Override
    public List<Object[]> getEmployeesByDir(Long dirCode) {
        return vrsRequestRepository.getEmployeesByDir(dirCode);
    }

    @Transactional
    @Override
    public Map<String, Object> addEmployeeToWorkTeam(VrsRequestWorkTeamDto requestWorkTeamDto) {
        Map<String, Object> output = new LinkedHashMap<>();
        Long nextRequestWorkTeamSerial = vrsRequestWorkTeamRepository.getNextSerial(requestWorkTeamDto.getRequestId());
        Optional<VrsRequest> request = vrsRequestRepository.findById(requestWorkTeamDto.getRequestId());
        if (request.isPresent()) {
            VrsRequestWorkTeam requestWorkTeam = mapper.toEntity(requestWorkTeamDto);

            requestWorkTeam.setRequestId(requestWorkTeamDto.getRequestId());
            requestWorkTeam.setEmployeeSerial(nextRequestWorkTeamSerial);
            requestWorkTeam.setEmployeeCode(requestWorkTeamDto.getEmployeeCode());
            requestWorkTeam.setEmployeeName(requestWorkTeamDto.getEmployeeName());
            requestWorkTeam.setDirCode(requestWorkTeamDto.getDirCode());

            vrsRequestWorkTeamRepository.save(requestWorkTeam);

            //output.put("output", "Operation Successful.");
            output.put("RequestNO", requestWorkTeamDto.getRequestId());
            output.put("EmployeeCode", requestWorkTeamDto.getEmployeeCode());
        } else {
            output.put("output", "Operation Failed (Request not found).");
            output.put("RequestNO", requestWorkTeamDto.getRequestId());
        }

        return output;
    }

    @Override
    public Map<String, Object> deleteEmployeeFromWorkTeam(Long workTeamId) {
        Map<String, Object> output = new LinkedHashMap<>();
        vrsRequestWorkTeamRepository.deleteById(workTeamId);
        output.put("output", "Operation Successful.");
        return output;
    }

}
