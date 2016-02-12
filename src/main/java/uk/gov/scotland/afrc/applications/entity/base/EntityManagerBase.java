/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.entity.base;

import java.util.List;

import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * EntityManagerBase is a generic DAO interface which provides services for basic CRUD operations.
 *
 * <p/>Please standardize generic database operations on this DAO, and not in the specific DAO implementations for each
 * entity type. For example, if adding a delete by primary key method, add it here, so that the code can be re-used
 * across all entity types. This will keep our DAO code consistent. Consistency is important because JPA development can
 * be very awkward to get right, therefore it is important to document conditions such as whether entities are returned
 * in attached or detached states, and so on.
 *
 * <p/>To add to the comment above, finder methods specific to particular entity types are expected to go in the
 * specific DAO implementations, as is other behaviour which is specific to a particular entity.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th><th> Collaborations </th></tr>
 * <tr><td> Provides services for basic CRUD operations </td></tr>
 * </table></pre>
 */
public interface EntityManagerBase<E, K> {
    /**
     * Creates a new instance of the entity in the database.
     *
     * <p/><em>Note: The entity instance returned by this will be attached.</em>
     *
     * @param   entity  The entity to create.
     *
     * @return  A new instance of the entity (attached).
     */
    E create(E entity);

    /**
     * Removes an entity instance from the database.
     *
     * @param  entity  The entity to remove.
     */
    void delete(E entity) throws ConcurrentAccessException;

    /**
     * Merges changes to an entity instance into the database.
     *
     * <p/><em>Note: The entity instance returned by this will be attached.</em>
     *
     * <p/><em>Note: The entity instance supplied to this can be attached or detached. See documentation on JPA
     * EntityManager for a description of how it behaves differently when the entity is attached or detached.</em>
     *
     * @param   entity  The entity to merge into the database.
     *
     * @return  The modified entity (attached).
     */
    E update(E entity) throws ConcurrentAccessException;

    /**
     * Inserts list of entities into database.
     *
     * @param   entities  The list of entities to create.
     */
    void createList(List<E> entities);
    
    /**
     * Obtains an entity instance from the database using its primary key.
     *
     * <p/><em>Note: The entity instance returned by this will be attached, provided this is invoked in a transaction,
     * which is the usual case.</em>
     *
     * @param   id  The primary key.
     *
     * @return  The entity for the key if it exists (attached), or <tt>null</tt> if no matching entity can be found.
     */
    E findById(K id);


    /**
     * find by Id qualified by version
     * @param id The primary key.
     * @param version entity JPA version number 
     * @return entity or null 
     */
	E findByIdAndVersion(K id, Long version);
    
    
    /**
     * Obtains list of entity instances from the database using named query.
     *
     * <p/><em>Note: The entity instance returned by this will be attached, provided this is invoked in a transaction,
     * which is the usual case.</em>
     *
     * @param   String  The named query to run.
     *
     * @return  The List of entities (attached), or <tt>empty list</tt> if no matching entity can be found.
     */
    List<E> queryAll(String namedQuery);
    
    /**
     * Obtains list of entity instances from the database using named query with given Key.
     *
     * <p/><em>Note: The entity instance returned by this will be attached, provided this is invoked in a transaction,
     * which is the usual case.</em>
     *
     * @param   String  The named query to run.
     * @param   String  The key name.
     * @param   Long  The key id.
     *
     * @return  The List of entities (attached), or <tt>empty list</tt> if no matching entity can be found.
     */
    List<E> queryByKey(String namedQuery, String keyName, Long key);

    /**
     * Obtains a single entity instance from the database using named query with given Key.
     *
     * <p/><em>Note: The entity instance returned by this will be attached, provided this is invoked in a transaction,
     * which is the usual case.</em>
     * 
     * @param   namedQuery The named query to run.
     * @param   keyName    The key name.
     * @param   key        The key id.
     * 
     * @return  The entity or null if no matching entity can be found.
     */
    E queryByKey(String namedQuery, String keyName, String key);
}
