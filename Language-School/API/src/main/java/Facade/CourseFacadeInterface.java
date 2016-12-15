package Facade;

import DTO.CourseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Facade interface for access to courses
 * @author Matus Krska, 410073
 * @since 1.0
 */
public interface CourseFacadeInterface
{
    /**
     * Create new course
     * @param course
     * @return
     */
    Optional<CourseDTO> createNewCourse(CourseDTO course);

    /**
     * Update course
     * @param course
     * @return
     */
    Optional<CourseDTO> updateCourse(CourseDTO course);

    /**
     * Remove course
     * @param course
     */
    void removeCourse(CourseDTO course);

    /**
     * Find course by id
     * @param id
     * @return
     */
    Optional<CourseDTO> findById(Long id);

    /**
     * Find course by taught language
     * @param language
     * @return
     */
    List<CourseDTO> findCourseByLanguage(String language);

    /**
     * Return list of all existing courses
     * @return
     */
    List<CourseDTO> findAllCourses();
}
