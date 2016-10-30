package org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO;

import java.util.List;
import javax.persistence.Query;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;

/**
 * Implementation of DAO for entity Student
 * Extends basic implementation GenericDAOImplementation
 * @author Richard Zan, 396380
 * @since 1.0
 */
public class StudentDAO extends GenericDAOImplementation<Lecture, Long>{
    /**
     * Method for finding Student by id
     * @param id id/uco of student
     * @return List of Students that match the query, can be empty
     */
    public List<Lecture> findByID(String id)
    {
        String query = "SELECT * FROM " + entityClass.getName() + " WHERE ID = :id";
        Query q = em.createQuery(query);
        q.setParameter("ID",id);
        return q.getResultList();
    }
}
