package ServiceImp;

import org.muni.fi.pa165.lang_school.DAO.LectureDAO;
import org.muni.fi.pa165.lang_school.entities.Lecture;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class LectureServiceImpl
{
    @Inject
    private LectureDAO lectureDAO;

    public Lecture createLecture(Lecture lecture)
    {
        return lectureDAO.create(lecture);
    }

    public Lecture updateLecture(Lecture lecture)
    {
        lectureDAO.update(lecture);
        return lecture;
    }

    public void removeLecture(Lecture lecture)
    {
        lectureDAO.delete(lecture);
    }

    public Lecture findLectureByCode(String code)
    {
        List<Lecture> lectures = lectureDAO.readByColumn("CODE",code);
        if(lectures.size() > 1 || lectures.size() == 0)
        {
            //TODO error
        }
        else
        {
            return lectures.get(0);
        }
        return null;
    }
}
