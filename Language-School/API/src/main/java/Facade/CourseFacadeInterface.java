package Facade;

import DTO.CourseDTO;

import java.util.List;

/**
 * Facade interface for access to courses
 * @author Matus Krska, 410073
 * @since 1.0
 */
public interface CourseFacadeInterface
{
    public CourseDTO createNewCourse(CourseDTO course);

    public CourseDTO updateCourse(CourseDTO course);

    public void removeCourse(CourseDTO course);

    public List<CourseDTO> findCourseByLanguage(String language);
}
