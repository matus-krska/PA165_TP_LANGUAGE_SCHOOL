package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.CourseDTO;
import DTO.LectureDTO;
import Facade.CourseFacadeInterface;
import ServiceImp.CourseServiceImpl;
import ServiceImp.LectureServiceImpl;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.entities.Lecture;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

import org.springframework.stereotype.Service;

/**
 * Implementation of facade layer for entity Course
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Service
@Transactional
public class CourseFacadeImpl implements CourseFacadeInterface
{
    private CourseServiceImpl courseService;

    private BeanMapper mapper;

    @Inject
    public CourseFacadeImpl(CourseServiceImpl courseService, BeanMapper mapper)
    {
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @Override
    public Optional<CourseDTO> createNewCourse(CourseDTO courseDTO)
    {
        if (courseDTO == null)
            throw new IllegalArgumentException("CourseDTO parameter is null");
        try {
            Optional<Course> course = Optional.ofNullable(courseService.createCourse(mapper.mapTo(courseDTO, Course.class).get()));
            return course.isPresent() ? mapper.mapTo(course.get(), CourseDTO.class) : Optional.empty();
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseDTO> updateCourse(CourseDTO courseDTO)
    {
        if (courseDTO == null)
            throw new IllegalArgumentException("CourseDTO parameter is null");
        try {
            Optional<Course> toUpdate = mapper.mapTo(courseDTO,Course.class);
            Optional<Course> course = Optional.ofNullable(courseService.updateCourse(toUpdate.get()));
            return course.isPresent() ? mapper.mapTo(course.get(), CourseDTO.class) : Optional.empty();
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseDTO> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id parameter is null");
        try {
            Optional<Course> course = Optional.ofNullable(courseService.findById(id));
            return course.isPresent() ? mapper.mapTo(course.get(), CourseDTO.class) : Optional.empty();
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void removeCourse(CourseDTO courseDTO)
    {
        if (courseDTO == null)
            throw new IllegalArgumentException("courseId parameter is null in deleteCourse method");
        try {
            courseService.removeCourse(courseService.findById(courseDTO.getId()));
        } catch (NoSuchElementException ex) {
            return;
        }
    }

    @Override
    public List<CourseDTO> findCourseByLanguage(String language)
    {
        if (language == null)
            throw new IllegalArgumentException("Language is null!");

        List<Course> courses = courseService.findCourseByLanguage(language);
        return mapper.mapTo(courses, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> findAllCourses() {
        try {
            return mapper.mapTo(courseService.findAllCourses(), CourseDTO.class);
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
    }
}
