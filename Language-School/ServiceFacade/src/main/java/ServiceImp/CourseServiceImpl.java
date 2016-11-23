package ServiceImp;

import org.muni.fi.pa165.lang_school.DAO.CourseDAO;
import org.muni.fi.pa165.lang_school.entities.Course;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CourseServiceImpl
{
    @Inject
    private CourseDAO courseDAO;

    public Course createCourse(Course course)
    {
        return courseDAO.create(course);
    }

    public Course updateCourse(Course course)
    {
        courseDAO.update(course);
        return course;
    }

    public void removeCourse(Course course)
    {
        courseDAO.delete(course);
    }

    public List<Course> findCourseByLanguage(String language)
    {
        return courseDAO.readByColumn("LANGUAGE", language);
    }
}
