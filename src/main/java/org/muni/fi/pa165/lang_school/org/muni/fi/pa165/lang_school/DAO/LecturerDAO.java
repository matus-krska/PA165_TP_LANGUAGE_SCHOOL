package org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.muni.fi.pa165.lang_school.entities.Lecturer;

import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of DAO for entity Lecturer
 * Extends the implementation GenericDAOImplementation
 *
 * @author Petra Kamenickova, 396179
 * @since 1.0
 */

public class LecturerDAO extends GenericDAOImplementation<Lecturer, Long>{
    /**
     * Method for finding Lecturer by name and surname
     * @param name name of lecturer
     * @param surname surname of lecturer
     * @return List of lecturers that match the query, can be empty
     */
    public List<Lecturer> findByName(String name, String surname)
    {
        String query = "SELECT * FROM " + entityClass.getName() + " WHERE NAME = :name AND SURNAME = :surname";
        Query q = em.createQuery(query);
        q.setParameter("NAME",name);
        q.setParameter("SURNAME",surname);
        return q.getResultList();
    }

}
