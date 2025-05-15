package sa.gov.alriyadh.amana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.entity.VrsRequestPhase;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;
import sa.gov.alriyadh.amana.mapper.VrsRequestMapper;
import sa.gov.alriyadh.amana.pojo.PhaseActionDetailView;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.repository.VrsPhaseActionRepository;
import sa.gov.alriyadh.amana.repository.VrsRequestRepository;
import sa.gov.alriyadh.amana.repository.VrsRequestPhaseRepository;
import sa.gov.alriyadh.amana.srinterface.IVrsRequestService;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Long nextRequestPhaseSerial = vrsRequestPhaseRepository.getNextSerial(savedRequestDto.getRequestSerial());

        VrsRequestPhase vrsRequestPhase = new VrsRequestPhase();
        vrsRequestPhase.setRequestId(savedRequestDto.getRequestSerial());
        vrsRequestPhase.setPhaseId(nextRequestPhaseSerial);
        vrsRequestPhase.setCreatedDate(LocalDate.now());
        vrsRequestPhase.setCreatedUser(savedRequestDto.getUserCode());
        vrsRequestPhase.setFromPhaseId(actionDetail.getFromPhaseId());
        vrsRequestPhase.setToPhaseId(actionDetail.getToPhaseId());
        vrsRequestPhase.setFromRoleId(actionDetail.getFromRoleNo());
        vrsRequestPhase.setToRoleId(actionDetail.getToRoleNo());
        vrsRequestPhase.setNotes(actionDetail.getFromPhaseDesc());
        vrsRequestRepository.save(request);
        vrsRequestPhaseRepository.save(vrsRequestPhase);
        output.put("output", "Operation successful.");
        output.put("RequestNO", savedRequestDto.getRequestSerial());
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
            request.get().setRequestPhaseId(actionDetail.getToPhaseId());
            VrsRequestPhase vrsRequestPhase = new VrsRequestPhase();
            vrsRequestPhase.setRequestId(requestPhase.getRequestNO());
            vrsRequestPhase.setPhaseId(nextRequestPhaseSerial);
            vrsRequestPhase.setCreatedDate(LocalDate.now());
            vrsRequestPhase.setCreatedUser(requestPhase.getIssueUser());
            vrsRequestPhase.setFromPhaseId(actionDetail.getFromPhaseId());
            vrsRequestPhase.setToPhaseId(actionDetail.getToPhaseId());
            vrsRequestPhase.setFromRoleId(actionDetail.getFromRoleNo());
            vrsRequestPhase.setToRoleId(actionDetail.getToRoleNo());
            vrsRequestPhase.setNotes(requestPhase.getNotes());
            vrsRequestRepository.save(request.get());
            vrsRequestPhaseRepository.save(vrsRequestPhase);
            output.put("output", "Operation successful.");
            output.put("RequestNO", requestPhase.getRequestNO());
            output.put("RequestStatus", actionDetail.getToPhaseDesc());
        } else {
            output.put("output", "Operation fail (Request not found).");
            output.put("RequestNO", requestPhase.getRequestNO());
        }

        return output;
    }
}
