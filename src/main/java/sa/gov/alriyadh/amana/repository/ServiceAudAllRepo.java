package sa.gov.alriyadh.amana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.gov.alriyadh.amana.entity.ServiceAudAll;

import java.math.BigDecimal;

public interface ServiceAudAllRepo extends JpaRepository<ServiceAudAll, BigDecimal> {

}
