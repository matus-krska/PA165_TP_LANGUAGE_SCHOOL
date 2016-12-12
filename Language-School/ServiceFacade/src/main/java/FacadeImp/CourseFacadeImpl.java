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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public CourseDTO createNewCourse(CourseDTO courseDTO)
    {
        if (courseDTO == null)
            throw new IllegalArgumentException("CourseDTO parameter is null");

        Optional<Course> entity = mapper.mapTo(courseDTO, Course.class);
        Course saved = courseService.createCourse(entity.get());

        return mapper.mapTo(saved, CourseDTO.class).get();
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO)
    {
        if (courseDTO == null)
            throw new IllegalArgumentException("CourseDTO parameter is null");

        Optional<Course> entity = mapper.mapTo(courseDTO, Course.class);
        Course updated = courseService.updateCourse(entity.get());
        return mapper.mapTo(updated, CourseDTO.class).get();
    }

    @Override
    public CourseDTO findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id parameter is null");

        return mapper.mapTo(courseService.findById(id), CourseDTO.class).get();

    }

    @Override
    public void removeCourse(CourseDTO courseDTO)
    {
        if (courseDTO == null)
            throw new IllegalArgumentException("CourseDTO parameter is null");

        Optional<Course> entity = mapper.mapTo(courseDTO, Course.class);
        courseService.removeCourse(entity.get());
    }

    @Override
    public List<CourseDTO> findCourseByLanguage(String language)
    {
        if (language == null)
            throw new IllegalArgumentException("Language is null!");

        List<Course> courses = courseService.findCourseByLanguage(language);
        return mapper.mapTo(courses, CourseDTO.class);
    }

    /*
    private Course courseDtoToEntity(CourseDTO dto)
    {
        return mapper.map(dto,Course.class);
    }
    private CourseDTO courseToCourseDto(Course entity){
        return mapper.map(entity,CourseDTO.class);
    }
    */
}
