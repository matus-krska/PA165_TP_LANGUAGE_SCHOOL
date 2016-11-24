package ServiceImp;

import Exceptions.DAOdataAccessException;
import org.muni.fi.pa165.lang_school.DAO.CourseDAO;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Service
public class CourseServiceImpl
{
    private CourseDAO courseDAO;

    @Inject
    public CourseServiceImpl(CourseDAO courseDAO)
    {

        this.courseDAO = courseDAO;
    }

    public Course createCourse(Course course)
    {
        try
        {
            course = courseDAO.create(course);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error creating course");
        }

        return course;
    }

    public Course updateCourse(Course course)
    {
        try
        {
            courseDAO.update(course);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error updating course");
        }
        return course;
    }

    public void removeCourse(Course course)
    {
        try
        {
            courseDAO.delete(course);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error removing course");
        }
    }

    public List<Course> findCourseByLanguage(String language)
    {
        List<Course> courses;
        if(language == null || language.isEmpty())
        {
            throw new DAOdataAccessException("Error finding course by language: parameter language cannot be empty");
        }
        try
        {
            courses = courseDAO.readByColumn("LANGUAGE", language);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error removing course");
        }
        return courses;
    }
}
