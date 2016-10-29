package org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;

import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of DAO for entity Course
 * Extends basic implementation GenericDAOImplementation
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CourseDAO extends GenericDAOImplementation<Course, Long>
{
    /**
     * Method for finding Course by language and language level
     * @param language language
     * @param languageLevel language level
     * @return List of Courses that match the query, can be empty
     */
    public List<Course> findByLanguageAndLanguageLevel(String language, String languageLevel)
    {
        String query = "SELECT * FROM " + entityClass.getName() + " WHERE LANGUAGE = :language AND LANGUAGE_LEVEL = :languageLevel";
        Query q = em.createQuery(query);
        q.setParameter("LANGUAGE",language);
        q.setParameter("LANGUAGE_LEVEL", languageLevel);
        return q.getResultList();
    }
}
