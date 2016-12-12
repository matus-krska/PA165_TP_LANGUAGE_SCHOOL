package ServiceImp;

import Exceptions.DAOdataAccessException;
import org.muni.fi.pa165.lang_school.DAO.CourseDAO;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of service layer for Course
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
        if(course == null)
        {
            throw new IllegalArgumentException("Cannot create null course");
        }
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
        if(course == null)
        {
            throw new IllegalArgumentException("Cannot update null course");
        }
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
        if(course == null)
        {
            throw new IllegalArgumentException("Cannot remove null course");
        }
        try
        {
            courseDAO.delete(course);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error removing course");
        }
    }

    public Course findById(Long id) {
        Course course;
        if (id == null){
            throw new IllegalArgumentException("Error findById");
        }
        try {
            course = courseDAO.readById(id);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findById");
        }
        return course;
    }

    public List<Course> findCourseByLanguage(String language)
    {
        List<Course> courses;
        if(language == null || language.isEmpty())
        {
            throw new IllegalArgumentException("Error finding course by language: parameter language cannot be empty");
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
