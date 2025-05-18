package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.entity.dto.VrsRequestDto;
import sa.gov.alriyadh.amana.entity.dto.VrsRequestPhaseDto;
import sa.gov.alriyadh.amana.pojo.RequestPhase;
import sa.gov.alriyadh.amana.pojo.VrsRequestFilter;

import java.util.List;
import java.util.Map;

public interface IVrsRequestService {

    List<Object[]> getDirectorates(Long dirType);

    Map<String, Object> addNewRequest(VrsRequestDto vrsRequestDto);

    Map<String, Object> addRequestPhase(RequestPhase requestPhase);

    List<Object[]> getRequestStatusList();

    List<VrsRequestDto> findRequestsByFilter(VrsRequestFilter filter);

    List<VrsRequestPhaseDto> getRequestPhases(Long requestNo);

    List<Object[]> getEmployeesByDir(Long dirCode);

}
