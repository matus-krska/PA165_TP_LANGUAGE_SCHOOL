package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;


@Component
public class LectureLanguageDAO extends GenericDAOImplementation<Course, Long>
{

    public List<Course> findAllLectureLanguage()
    {
        String query = " FROM " + entityClass.getName();
        Query q = em.createQuery(query);
        return q.getResultList();
    }
}
