package com.lawencon.base;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lawencon05
 */
public class BaseCoreService<T extends BaseEntity> {

	@Autowired
	private AbstractJpaDao<T> abstractJpaDao;

	protected void begin() {
		ConnHandler.begin();
	}

	protected void commit() {
		ConnHandler.commit();
	}

	protected void rollback() {
		ConnHandler.rollback();
	}

	protected void clear() {
		ConnHandler.clear();
	}

	@SuppressWarnings("unchecked")
	protected <A> A getAuthPrincipal() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null)
			throw new Exception("Invalid Login");

		return (A) auth.getPrincipal().toString();
	}

	protected T save(T entity) throws Exception {
		saveData(entity, getAuthPrincipal());
		return abstractJpaDao.save(entity);
	}

	protected T saveNonLogin(T entity, Supplier<String> getIdFunc) throws Exception {
		saveData(entity, getIdFunc.get());
		return abstractJpaDao.save(entity);
	}

	private void saveData(T entity, String id) {
		if (entity.getId() != null) {
			entity.setUpdatedBy(id);
		} else {
			entity.setCreatedBy(id);
		}
	}
}
