/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.entity.base.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessHandler;

/**
 * <p/>
 * EntityManagerBaseImpl provides a base implementation for basic CRUD services.
 * 
 * <pre>
 * <p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th></tr>
 * <tr><td> Allow an entity manager to be injected. </td></tr>
 * </table>
 * </pre>
 * 
 * @param <E>
 *            The type of the entity that this DAO manages.
 * @param <K>
 *            The type of the primary key of the entity.
 */
public abstract class EntityManagerBaseImpl<E, K> implements
		EntityManagerBase<E, K> {
	/** Used for debugging purposes. */
	private final Logger log = LoggerFactory.getLogger(getClass());

	/** The JPA entity manager. */
	private EntityManager entityManager;

	/** The transaction manager. */
	private TransactionManager txManager;

	/** The class of entity that this DAO manages. */
	private Class<E> type;
	
	private String idFieldName;
	private String versionFieldName;

	public EntityManagerBaseImpl(Class<E> type) {
		this.type = type;
		idFieldName=getFieldNameForAnnotation(Id.class);
		versionFieldName=getFieldNameForAnnotation(Version.class);
	}
	
	private String getFieldNameForAnnotation(Class<? extends Annotation> annotationClass) {
		
		final Field[] fields=type.getDeclaredFields();
		String result=null;
		
		for(Field f: fields) {
			if(f.isAnnotationPresent(annotationClass)) {
				result = f.getName();
				break;
			}
		}
		
		return result;
	}

	/** {@inheritDoc} */
	public E create(E entity) {
		log.debug("Persisting {}", entity);

		entityManager.persist(entity);

		entityManager.flush();
		return entity;
	}

	/** {@inheritDoc} */
	public void delete(final E entity) throws ConcurrentAccessException {
		ConcurrentAccessHandler<E> jpaHandler = new ConcurrentAccessHandler<E>(
				entityManager, txManager) {
			protected E modify(EntityManager entityManager) {
				log.debug("Deleting {}", entity);

				entityManager.remove(entity);

				return null;
			}
		};

		jpaHandler.apply();
	}

	/** {@inheritDoc} */
	public E update(final E entity) throws ConcurrentAccessException {
		ConcurrentAccessHandler<E> jpaHandler = new ConcurrentAccessHandler<E>(
				entityManager, txManager) {
			protected E modify(EntityManager entityManager) {
				log.debug("Updating {}", entity);

				E merged = entityManager.merge(entity);

				entityManager.flush();
				return merged;
			}
		};

		return jpaHandler.apply();
	}

	/** {@inheritDoc} */
	public void createList(List<E> entities) {
		for (E e : entities) {
			create(e);
		}
	}

	/** {@inheritDoc} */
	public E findById(K id) {
		log.debug("Retrieving {}:{}", type, id);

		return entityManager.find(type, id);
	}

	
	/** {@inheritDoc} */
	public E findByIdAndVersion(K id, Long version) {
		log.debug("Retrieving {}:{}", type, id);
		
		if(idFieldName==null || versionFieldName==null) {
			throw new UnsupportedOperationException("call this method only on entities annotated @Id and @Version");
		}

		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<E> q = cb.createQuery(type);
		final Root<E> c = q.from(type);

		q.select(c).where(
				cb.and(cb.equal(c.get(idFieldName), cb.parameter(Long.class)),
				cb.equal(c.get(versionFieldName), cb.parameter(Long.class))));

		final TypedQuery<E> query = getEntityManager().createQuery(q);
		query.setParameter(1, id);
		query.setParameter(2, version);

		E result;
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		return result;
	}

	/** {@inheritDoc} */
	public List<E> queryAll(String namedQuery) {
		log.debug("Querying {}:{}", type, namedQuery);

		TypedQuery<E> query = getEntityManager().createNamedQuery(namedQuery,
				type);

		return new ArrayList<E>(query.getResultList());
	}

	/** {@inheritDoc} */
	public List<E> queryByKey(String namedQuery, String keyName, Long key) {
		log.debug("Querying {}:{}:{}:{}", type, namedQuery, keyName, key);

		TypedQuery<E> query = getEntityManager().createNamedQuery(namedQuery,
				type);

		return new ArrayList<E>(query.setParameter(keyName, key)
				.getResultList());
	}

	/** {@inheritDoc} */
	public E queryByKey(String namedQuery, String keyName, String key) {
		log.debug("Querying {}:{}:{}:{}", type, namedQuery, keyName, key);

		TypedQuery<E> query = getEntityManager().createNamedQuery(namedQuery,
				type);

		return query.setParameter(keyName, key).getSingleResult();
	}

	/**
	 * Establishes the EntityManager to use to control persistence through.
	 * 
	 * @param entityManager
	 *            The EntityManager to use to control persistence through.
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Establishes the transaction manager.
	 * 
	 * @param txManager
	 *            The JTA transaction manager.
	 */
	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	/**
	 * Returns entity manager to use to control persistence.
	 * 
	 * @return {@link EntityManager}
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
