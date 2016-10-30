package org.muni.fi.pa165.lang_school.genericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Generic implementation of Interface GenericDAOInterface
 * Implements basic CRUD operations
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class GenericDAOImplementation<T, PK extends Serializable> implements GenericDAOInteface<T,PK>
{
    @PersistenceContext
    protected EntityManager em;

    protected Class<T> entityClass;

    public GenericDAOImplementation()
    {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    public T create(T o)
    {
        em.persist(o);
        return o;
    }

    public void update(T o)
    {
        em.merge(o);
        return;
    }

    public T readById(PK id)
    {
        //String query = "SELECT * FROM " + entityClass.getName() + " WHERE ID = :id";
        String query = " FROM " + entityClass.getName() + " WHERE ID = :id";
        Query q = em.createQuery(query);
        q.setParameter("ID",id);
        return (T) q.getSingleResult();
    }

    public List<T> readByColumn(String column, Object value)
    {
        //String query = "SELECT * FROM " + entityClass.getName() + " WHERE "+ column +" = " + ":value";
        String query = " FROM " + entityClass.getName() + " WHERE "+ column +" = " + ":value";
        Query q = em.createQuery(query);
        q.setParameter("value",value);
        return q.getResultList();
    }


    public void delete(T o)
    {
        o = em.merge(o);
        em.remove(o);
    }
}
