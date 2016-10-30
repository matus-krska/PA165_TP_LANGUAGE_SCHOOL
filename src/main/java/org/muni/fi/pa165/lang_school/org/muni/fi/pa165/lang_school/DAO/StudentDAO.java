package org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO;

import java.util.List;
import javax.persistence.Query;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;

/**
 * Implementation of DAO for entity Student
 * Extends basic implementation GenericDAOImplementation
 * @author Richard Zan, 396380
 * @since 1.0
 */
public class StudentDAO extends GenericDAOImplementation<Student, Long>{
    /**
     * Method for finding Student by id, name and surname
     * @param id id/uco of student
     * @param name name of student
     * @param surname surname of student
     * @return List of Students that match the query, can be empty
     */
    public List<Student> findByID(String id, String name, String surname)
    {
        String query = "SELECT * FROM " + entityClass.getName() + " WHERE ID = :id AND NAME = :name AND SURNAME = :surname";
        Query q = em.createQuery(query);
        q.setParameter("ID",id);
        q.setParameter("NAME",name);
        q.setParameter("SURNAME",surname);
        return q.getResultList();
    }
}
