package com.lawencon.community.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.community.model.PollingHdr;
import com.lawencon.community.model.PollingOption;

@Repository
public class PollingOptionDao extends AbstractJpaDao<PollingOption> {

	public List<PollingOption> findByThreadId(String id, Integer startPage, Integer maxPage) throws Exception {
		StringBuilder sql = new StringBuilder().append(" SELECT po.* ").append(" FROM comm_polling_option po ")
				.append(" INNER JOIN comm_polling_hdr ph ON ph.id = po.polling_hdr ")
				.append(" INNER JOIN comm_thread_hdr th ON th.polling_id = ph.id ").append(" WHERE th.id = :id ");

		List<PollingOption> options = new ArrayList<>();
		try {

			Query q = createNativeQuery(sql.toString()).setParameter("id", id);

			if (startPage != null && maxPage != null) {
				q.setFirstResult(startPage).setMaxResults(maxPage);
			}

			List<?> result = q.getResultList();

			result.forEach(obj -> {
				Object[] objArr = (Object[]) obj;
				PollingOption option = new PollingOption();
				option.setId(objArr[0].toString());

				PollingHdr pollingHdr = new PollingHdr();
				pollingHdr.setId(objArr[1].toString());
				option.setPollingHdr(pollingHdr);

				option.setOptionName(objArr[2].toString());
				option.setCreatedBy(objArr[3].toString());

				option.setCreatedBy(objArr[3].toString());
				option.setCreatedAt(((Timestamp) objArr[4]).toLocalDateTime());
				if (objArr[5] != null)
					option.setUpdatedBy(objArr[5].toString());
				if (objArr[6] != null)
					option.setUpdatedAt(((Timestamp) objArr[6]).toLocalDateTime());

				option.setIsActive(Boolean.valueOf(objArr[7].toString()));
				option.setVersion(Integer.valueOf(objArr[8].toString()));

				options.add(option);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}
}