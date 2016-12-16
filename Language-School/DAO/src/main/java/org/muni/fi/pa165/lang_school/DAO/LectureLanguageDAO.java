package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.LectureLanguage;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;


@Component
public class LectureLanguageDAO extends GenericDAOImplementation<LectureLanguage, Long>
{

    public List<LectureLanguage> findAllLectureLanguage()
    {
        String query = " FROM " + entityClass.getName();
        Query q = em.createQuery(query);
        return q.getResultList();
    }
}
