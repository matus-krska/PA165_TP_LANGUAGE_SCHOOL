package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.genericDAO.GenericDAOImplementation;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of DAO for entity Lecture
 * Extends basic implementation GenericDAOImplementation
 * @author Simon Hyben, 421112
 * @since 1.0
 */
@Component
public class LectureDAO extends GenericDAOImplementation<Lecture, Long>
{
    /**
     * Method for finding Lecture by code and topic
     * @param code code of lecture
     * @param topic topic of lecture
     * @return List of Lectures that match the query, can be empty
     */
    public List<Lecture> findByCodeAndTopic(String code, String topic)
    {
        String query = " FROM " + entityClass.getName() + " WHERE CODE = :CODE AND TOPIC = :TOPIC";
        Query q = em.createQuery(query);
        q.setParameter("CODE",code);
        q.setParameter("TOPIC", topic);
        return q.getResultList();
    }
}

