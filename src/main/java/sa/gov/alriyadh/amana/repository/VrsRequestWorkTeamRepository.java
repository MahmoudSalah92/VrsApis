package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.gov.alriyadh.amana.entity.VrsRequestWorkTeam;

public interface VrsRequestWorkTeamRepository extends JpaRepository<VrsRequestWorkTeam, Long> {

    @Query(value = "SELECT COALESCE(MAX(EMPLOYEE_SERIAL), 0) + 1 AS employeeNextSerial " +
            "FROM VRS.VRS_REQUEST_WORK_TEAM WHERE REQUEST_ID = :requestNo", nativeQuery = true)
    Long getNextSerial(@Param("requestNo") Long requestNo);

}
