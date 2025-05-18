package sa.gov.alriyadh.amana.repository;

import org.springframework.stereotype.Repository;
import sa.gov.alriyadh.amana.entity.VrsRequest;
import sa.gov.alriyadh.amana.pojo.VrsRequestFilter;
import sa.gov.alriyadh.amana.srinterface.VrsRequestRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VrsRequestRepositoryImpl implements VrsRequestRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VrsRequest> findRequestsByFilter(VrsRequestFilter filter) {
        StringBuilder sql = new StringBuilder("SELECT REQ.*, (SELECT P.PHASE_DESC FROM VRS.VRS_PHASES P " +
                "WHERE P.PHASE_ID = REQ.REQUEST_PHASE_ID) REQUEST_STATUS" +
                " FROM VRS.VRS_REQUESTS REQ, VRS.VRS_REQUEST_PHASES PH ");
        sql.append("WHERE REQ.REQUEST_ID = PH.REQUEST_ID ");
        sql.append("AND REQ.REQUEST_PHASE_ID = PH.TO_PHASE_ID ");

        Map<String, Object> params = new HashMap<>();

        if (filter.getRoleId() != null) {
            sql.append("AND (PH.TO_ROLE_ID = :roleId) ");
            params.put("roleId", filter.getRoleId());
        }
        if (filter.getUserCode() != null) {
            sql.append("AND (PH.CREATED_USER = :userCode) ");
            params.put("userCode", filter.getUserCode());
        }
        if (filter.getRequestNo() != null) {
            sql.append("AND (REQ.REQUEST_ID = :requestNo) ");
            params.put("requestNo", filter.getRequestNo());
        }
        if (filter.getDirCode() != null) {
            sql.append("AND (REQ.DIR_CODE = :dirCode) ");
            params.put("dirCode", filter.getDirCode());
        }

        sql.append("ORDER BY REQ.REQUEST_ID");

        System.out.println("SQL query: " + sql.toString() + "   " + filter.getUserCode());

        Query query = entityManager.createNativeQuery(sql.toString(), VrsRequest.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
