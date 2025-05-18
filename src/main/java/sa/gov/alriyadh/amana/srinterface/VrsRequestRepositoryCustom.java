package sa.gov.alriyadh.amana.srinterface;

import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.pojo.VrsRequestFilter;

import java.util.List;

public interface VrsRequestRepositoryCustom {

    List<VrsRequest> findRequestsByFilter(VrsRequestFilter filter);

}