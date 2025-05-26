package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.srinterface.VrsRequestRepositoryCustom;

import java.util.List;

public interface VrsRequestRepository extends JpaRepository<VrsRequest, Long>, VrsRequestRepositoryCustom {

    @Query(value = "SELECT DIR_CODE, DIR_DESC FROM CMNV3.CMN_DIRECTORATES WHERE VIEW_INDICATOR = :dirType  AND ACTIV = 1 ORDER BY 1", nativeQuery = true)
    List<Object[]> getDirectorates(@Param("dirType") Long dirType);

    @Query(value = "SELECT PHASE_ID, PHASE_DESC FROM VRS.VRS_PHASES ORDER BY 1", nativeQuery = true)
    List<Object[]> getRequestStatusList();

//  default List<VrsRequest> findByRequestId(Long requestNo) {
//      return null;
//  }

    @Query(value = "SELECT USER_CODE, USER_NAME FROM CMNV3.CMN_USERS WHERE DIR_CODE = :dirCode ORDER BY 1", nativeQuery = true)
    List<Object[]> getEmployeesByDir(@Param("dirCode") Long dirCode);

//  SELECT USER_CODE, USER_NAME FROM CMNV3.CMN_USERS WHERE DIR_CODE = 8; موظفين الادارة العامة للمراجعة الداخلية
}
