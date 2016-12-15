package org.muni.fi.pa165.lang_school.DAO;

import java.util.List;
import javax.persistence.Query;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.springframework.stereotype.Component;

/**
 * Implementation of DAO for entity Student
 * Extends basic implementation GenericDAOImplementation
 * @author Richard Zan, 396380
 * @since 1.0
 */
@Component
public class StudentDAO extends GenericDAOImplementation<Student, Long>
{
    /**
     * Method for finding Student by name and surname
     * @param name name of student
     * @param surname surname of student
     * @return List of Students that match the query, can be empty
     */
    public List<Student> findByNameAndSurname(String name, String surname)
    {
        String query = " FROM " + entityClass.getName() + " WHERE NAME = :NAME AND SURNAME = :SURNAME";
        Query q = em.createQuery(query);
        q.setParameter("NAME",name);
        q.setParameter("SURNAME",surname);
        return q.getResultList();
    }

    /**
     * Finds and returns list of all existing students
     * @return list of all students
     */
    public List<Student> findAllStudents()
    {
        String query = " FROM " + entityClass.getName();
        Query q = em.createQuery(query);
        return q.getResultList();
    }
}
