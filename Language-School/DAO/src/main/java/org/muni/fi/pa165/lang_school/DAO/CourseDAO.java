package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of DAO for entity Course
 * Extends basic implementation GenericDAOImplementation
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Component
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
        String query = " FROM " + entityClass.getName() + " WHERE LANGUAGE = :LANGUAGE AND LANGUAGE_LEVEL = :LANGUAGE_LEVEL";
        Query q = em.createQuery(query);
        q.setParameter("LANGUAGE",language);
        q.setParameter("LANGUAGE_LEVEL", languageLevel);
        return q.getResultList();
    }

    /**
     * Finds and returns list of all existing courses
     * @return list of all courses
     */
    public List<Course> findAllCourses()
    {
        String query = " FROM " + entityClass.getName();
        Query q = em.createQuery(query);
        return q.getResultList();
    }
}
