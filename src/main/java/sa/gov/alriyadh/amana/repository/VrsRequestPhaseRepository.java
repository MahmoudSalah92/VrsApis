package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.VrsRequestPhase;

import java.util.List;

public interface VrsRequestPhaseRepository extends JpaRepository<VrsRequestPhase, Long> {

    @Query(value = "SELECT COALESCE(MAX(REQUEST_PHASE_SERIAL), 0) + 1 AS requestNextSerial " +
            "FROM VRS.VRS_REQUEST_PHASES WHERE REQUEST_ID = :requestNo", nativeQuery = true)
    Long getNextSerial(@Param("requestNo") Long requestNo);

    List<VrsRequestPhase> findByRequestId(Long requestNo);

}
