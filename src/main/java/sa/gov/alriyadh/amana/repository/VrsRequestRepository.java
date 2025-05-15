package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.VrsRequest;

import java.util.List;

public interface VrsRequestRepository extends JpaRepository<VrsRequest, Long> {

    @Query(value = "SELECT DIR_CODE,DIR_DESC FROM CMNV3.CMN_DIRECTORATES\n" + "WHERE VIEW_INDICATOR = :dirType  AND ACTIV = 1 ORDER BY 1", nativeQuery = true)
    List<Object[]> getDirectorates(@Param("dirType") Long dirType);

}
