package ServiceImp;

import Exceptions.DAOdataAccessException;
import org.muni.fi.pa165.lang_school.DAO.LectureLanguageDAO;
import org.muni.fi.pa165.lang_school.entities.LectureLanguage;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Implementation of service layer for Course
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Service
public class LectureLanguageServiceImpl
{
    private LectureLanguageDAO lectureLanguageDAO;

    @Inject
    public LectureLanguageServiceImpl(LectureLanguageDAO lectureLanguageDAO)
    {

        this.lectureLanguageDAO = lectureLanguageDAO;
    }

    public LectureLanguage createLectureLanguage(LectureLanguage lectureLanguage)
    {
        if(lectureLanguage == null)
        {
            throw new IllegalArgumentException("Cannot create null course");
        }
        try
        {
            lectureLanguage = lectureLanguageDAO.create(lectureLanguage);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error creating course");
        }

        return lectureLanguage;
    }

    public LectureLanguage updateLectureLanguage(LectureLanguage lectureLanguage)
    {
        if(lectureLanguage == null)
        {
            throw new IllegalArgumentException("Cannot update null course");
        }
        try
        {
            lectureLanguageDAO.update(lectureLanguage);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error updating course");
        }
        return lectureLanguage;
    }

    public void removeLectureLanguage(LectureLanguage lectureLanguage)
    {
        if(lectureLanguage == null)
        {
            throw new IllegalArgumentException("Cannot remove null course");
        }
        try
        {
            lectureLanguageDAO.delete(lectureLanguage);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error removing course");
        }
    }

    public LectureLanguage findById(Long id) {
        LectureLanguage lectureLanguage;
        if (id == null){
            throw new IllegalArgumentException("Error findById");
        }
        try {
            lectureLanguage = lectureLanguageDAO.readById(id);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findById");
        }
        return lectureLanguage;
    }


    public List<LectureLanguage> findAllLectureLanguages() {
        try {
            return lectureLanguageDAO.findAllLectureLanguage();
        } catch (PersistenceException | DataAccessException ex) {
            throw new DAOdataAccessException("Error finding all courses", ex);
        }
    }
}
