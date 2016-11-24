package ServiceImp;

import Exceptions.DAOdataAccessException;
import org.muni.fi.pa165.lang_school.DAO.LectureDAO;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Service
public class LectureServiceImpl
{
    private LectureDAO lectureDAO;

    @Inject
    public LectureServiceImpl(LectureDAO lectureDAO){

        this.lectureDAO = lectureDAO;
    }

    public Lecture createLecture(Lecture lecture)
    {
        try
        {
            lecture = lectureDAO.create(lecture);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error creating lecture");
        }

        return lecture;
    }

    public Lecture updateLecture(Lecture lecture)
    {
        try
        {
            lectureDAO.update(lecture);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error updating lecture");
        }
        return lecture;
    }

    public void removeLecture(Lecture lecture)
    {
        try
        {
            lectureDAO.delete(lecture);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error deleting lecture");
        }
    }

    public Lecture findLectureByCode(String code)
    {
        if(code == null)
        {
            throw new DAOdataAccessException("Error finding lecture by code");
        }
        List<Lecture> lectures = lectureDAO.readByColumn("CODE",code);
        if(lectures.size() > 1 || lectures.size() == 0)
        {
            throw new DAOdataAccessException("Error finding lecture by code: Found count that is not 1");
        }
        else
        {
            return lectures.get(0);
        }
    }

    public List<Lecture> findLecturesByLecturer(Lecturer lecturer)
    {
        if(lecturer == null || lecturer.getId() == null)
        {
            throw new DAOdataAccessException("Error finding lecture by lecturer which is null");
        }

        List<Lecture> lectures = lectureDAO.readByColumn("TAUGHT_BY",lecturer.getId());
        return lectures;
    }

    public Lecture changeLectureCode(Lecture lecture, String code)
    {
        if(code == null || code.isEmpty())
        {
            throw new DAOdataAccessException("Error changing lecture code: code cannot be empty");
        }
        if(code.equals(lecture.getCode()))
        {
            return lecture;
        }
        lecture.setCode(code);
        lectureDAO.update(lecture);
        return lecture;
    }
}
