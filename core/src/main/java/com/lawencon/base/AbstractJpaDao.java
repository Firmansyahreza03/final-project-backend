package com.lawencon.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import com.lawencon.model.SearchQuery;

/**
 * 
 * @author Agung Damas Saputra
 * 
 */
@Repository
public class AbstractJpaDao<T extends BaseEntity> {

	public Class<T> clazz;

	@SuppressWarnings("unchecked")
	public AbstractJpaDao() {
		this.clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractJpaDao.class);
	}

	public T getById(final String id) {
		T data = em().find(clazz, id);
		em().detach(data);
		return data;
	}

	public T getByIdWithoutDetach(final String id) {
		T data = em().find(clazz, id);
		return data;
	}

	public List<T> getAll() {
		return em().createQuery("FROM " + clazz.getName(), clazz)
				.getResultList();
	}

	public Long countAll() {
		return (Long) em().createQuery("SELECT COUNT(id) FROM " + clazz.getName())
				.getSingleResult();
	}

	public List<T> getAll(int startPage, int maxPage) {
		return em().createQuery("FROM " + clazz.getName(), clazz)
				.setFirstResult(startPage)
				.setMaxResults(maxPage)
				.getResultList();
	}

	public SearchQuery<T> searchQueryTable(String textQuery, 
			Integer startPosition, Integer limit, 
			String... fields) throws Exception{
		if (textQuery != null) {
			return searchQuery(textQuery, startPosition, limit, fields);
		} else {
			return findAll(textQuery, startPosition, limit, fields);
		}
	}
	
	public SearchQuery<T> searchQuery( 
			String textQuery, 
			int startPosition, int limit,
			String... fields) {

		EntityManager em = em();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<T> criteriaQueryData = criteriaBuilder.createQuery(clazz);

		Root<T> rootData = criteriaQueryData.from(clazz);

		String[] querySplit = extractQuery(textQuery);

		List<Predicate> predicates = new ArrayList<>();

		Map<String, Join<Object, Object>> joinMap = new HashMap<>();
		
		for (String field : fields) {
			String fieldTrim = field.trim();
			if (fieldTrim.contains(".")) {
				doJoin(criteriaBuilder, rootData, fieldTrim, querySplit, predicates, joinMap);
			} else {
				for (String subQuery : querySplit) {
					Predicate condition = criteriaBuilder.like(
							criteriaBuilder.lower(rootData.get(field)), "%" + subQuery.toLowerCase() + "%"
					);
					predicates.add(condition);
				}
			}
		}

		Predicate[] predicateArr = new Predicate[predicates.size()];
		for (int i = 0; i < predicates.size(); i++) {
			predicateArr[i] = predicates.get(i);
		}
		Predicate predicate = criteriaBuilder.or(predicateArr);

		//TODO : query count not yet works :(
		//criteriaQueryCount
		//	.select(criteriaBuilderCount.count(rootCount))
		//	.where(predicate);

		//Long resultCount = em().createQuery(criteriaQueryCount)
		//		.getSingleResult();
		
		criteriaQueryData
			.where(predicate)
			.orderBy(criteriaBuilder.asc(rootData.get("createdAt")));


		List<T> resultData = em().createQuery(criteriaQueryData)
				.setFirstResult(startPosition)
				.setMaxResults(limit)
				.getResultList();

		int resultCount = em().createQuery(criteriaQueryData)
				.getResultList()
				.size();

		SearchQuery<T> data = new SearchQuery<>();
		data.setData(resultData);
		data.setCount(resultCount);

		return data;
	}
	
	private void doJoin(CriteriaBuilder criteriaBuilder, 
			Root<T> rootData, 
			String field, String[] extractQuery,
			List<Predicate> predicates,
			Map<String, Join<Object, Object>> joinMap) {
		
		Join<Object, Object> joinData = null;
		
		String[] fieldSplit = field.split("\\.");

		for (int i = 0; i < fieldSplit.length - 1; i++) {
			if (!joinMap.containsKey(fieldSplit[i])) {
				if (i == 0) {
					joinData = rootData.join(fieldSplit[i]);
				} else {
					joinData = joinData.join(fieldSplit[i]);
				}
				joinMap.put(fieldSplit[i], joinData);
			} else {
				joinData = joinMap.get(fieldSplit[i]);
			}
		}
		
		for (String subQuery : extractQuery) {
			Predicate condition = criteriaBuilder.like(
					criteriaBuilder.lower(joinData.get(fieldSplit[fieldSplit.length - 1])), "%" + subQuery.toLowerCase() + "%"
			);
			predicates.add(condition);
		}
	}

	private String[] extractQuery(String textQuery) {
		String[] result = textQuery.split(" ");
		return result;
	}

	
	public SearchQuery<T> findAll(String query, Integer startPage, Integer maxPage, String... fields) throws Exception {
		SearchQuery<T> sq = new SearchQuery<>();
		List<T> data = null;

		if (startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		} else {
			if (query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();

				sq.setData(data);
				sq.setCount(count);
			} else {
				return searchQuery(query, startPage, maxPage, fields);
			}
		}

		return sq;
	}

	public T save(T entity) throws Exception {
		if (entity.getId() != null) {
			entity = em().merge(entity);
			em().flush();
		} else {
			em().persist(entity);
		}

		return entity;
	}

	public void delete(final T entity) throws Exception {
		em().remove(entity);
	}

	public boolean deleteById(final Object entityId) throws Exception {
		T entity = null;
		if (entityId != null && entityId instanceof String) {
			entity = em().find(clazz, (String) entityId);
		}

		if (entity != null) {
			delete(entity);
			return true;
		}

		return false;
	}

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	protected <C> TypedQuery<C> createQuery(String sql, Class<C> clazz) {
		return em().createQuery(sql, clazz);
	}

	protected Query createNativeQuery(String sql) {
		return em().createNativeQuery(sql);
	}

}