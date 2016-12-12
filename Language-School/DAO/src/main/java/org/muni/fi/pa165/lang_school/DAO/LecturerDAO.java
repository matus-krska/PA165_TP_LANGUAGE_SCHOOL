package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of DAO for entity Lecturer
 * Extends the implementation GenericDAOImplementation
 *
 * @author Petra Kamenickova, 396179
 * @since 1.0
 */
@Component
public class LecturerDAO extends GenericDAOImplementation<Lecturer, Long>{
    /**
     * Method for finding Lecturer by name and surname
     * @param name name of lecturer
     * @param surname surname of lecturer
     * @return List of lecturers that match the query, can be empty
     */
    public List<Lecturer> findByName(String name, String surname)
    {
        String query = " FROM " + entityClass.getName() + " WHERE NAME = :NAME AND SURNAME = :SURNAME";
        Query nQ = em.createQuery(query);
        nQ.setParameter("NAME",name);
        nQ.setParameter("SURNAME",surname);
        return nQ.getResultList();
    }
    
    /**
     * Finds and returns list of all existing lecturers
     * @return list of all lecturers
     */
    public List<Lecturer> findAllLecturers()
    {
        String query = " FROM " + entityClass.getName();
        Query q = em.createQuery(query);
        return q.getResultList();
    }
}

