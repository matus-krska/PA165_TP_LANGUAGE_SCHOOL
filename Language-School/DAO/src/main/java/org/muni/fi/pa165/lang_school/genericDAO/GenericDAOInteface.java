package org.muni.fi.pa165.lang_school.genericDAO;

import java.io.Serializable;
import java.util.List;

/**
 * Generic Interface for DAO implementations
 * Interface contains CRUD operations:
 * create for creating object T
 * readById for reading object T by its primary key
 * readByColumn for reading by a column value
 * update for updating object T
 * delete for removing object T
 *
 * @author Matus Krska, 410073
 * @since 1.0
 */
public interface GenericDAOInteface<T,PK extends Serializable>
{

    /**
     * Method for creating object T in database
     * @param o object to be created
     * @return created object which is created in database with added primary key or null if object wasn't created
     */
    public T create(T o);

    /**
     * Method for reading object T from databse based on its primary key
     * @param id primary key of object T
     * @return Object T with primary key id or null if object wasnt found
     */
    public T readById(PK id);

    /**
     * Method for reading object T from database based on a column value
     * @param column name of the column
     * @param value value of the column
     * @return returns a list of entries that have the column value, list can be empty if no entry is found
     */
    public List<T> readByColumn(String column, Object value);

    /**
     * Method for updating object T in database
     * @param o object to be updated
     */
    public void update(T o);

    /**
     * Method for removing object o from database
     * @param o object to be removed
     */
    public void delete(T o);

}
