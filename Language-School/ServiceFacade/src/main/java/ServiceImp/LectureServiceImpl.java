package ServiceImp;

import Exceptions.DAOdataAccessException;
import org.muni.fi.pa165.lang_school.DAO.LectureDAO;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implemantation of service layer for Lecture
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
        if(lecture == null)
        {
            throw new IllegalArgumentException("Error creating lecture null");
        }
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
        if(lecture == null)
        {
            throw new IllegalArgumentException("Error updating lecture null");
        }
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
        if(lecture == null)
        {
            throw new IllegalArgumentException("Error removing lecture null");
        }
        try
        {
            lectureDAO.delete(lecture);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error deleting lecture");
        }
    }

    public Lecture findById(Long id) {
        Lecture lecture;
        if (id == null){
            throw new IllegalArgumentException("Error findById");
        }
        try {
            lecture = lectureDAO.readById(id);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findById");
        }
        return lecture;
    }

    public Lecture findLectureByCode(String code)
    {
        if(code == null)
        {
            throw new IllegalArgumentException("Error finding lecture by code");
        }
        List<Lecture> lectures = lectureDAO.readByColumn("CODE",code);
        if(lectures.size() > 1)
        {
            throw new DAOdataAccessException("Error finding lecture by code: Found count that is not 1");
        }
        else
        {
            if(lectures.size() != 0)
            {
                return lectures.get(0);
            }
            else
            {
                return null;
            }
        }
    }

    public List<Lecture> findAllLectures()
    {
        List<Lecture> lectures = lectureDAO.findAllLectures();
        return lectures;
    }

    public Lecture findLectureByCodeAndTopic(String code, String topic)
    {
        if(code == null || code.isEmpty() || topic == null || topic.isEmpty())
        {
            throw new IllegalArgumentException("Error finding lecture by code or topic which is null or empty");
        }

        List<Lecture> lectures = lectureDAO.findByCodeAndTopic(code, topic);
        if(lectures.size() == 0)
        {
            return null;
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
            throw new IllegalArgumentException("Error finding lecture by lecturer which is null");
        }

        List<Lecture> lectures = lectureDAO.readByColumn("TAUGHT_BY",lecturer.getId());
        return lectures;
    }

    public Lecture changeLectureCode(Lecture lecture, String code)
    {
        if(code == null || code.isEmpty())
        {
            throw new IllegalArgumentException("Error changing lecture code: code cannot be empty");
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
