package FacadeImp;

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
import org.dozer.DozerBeanMapper;
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

    @Inject
    private DozerBeanMapper mapper = new DozerBeanMapper();

    @Inject
    public CourseFacadeImpl(CourseServiceImpl courseService)
    {
        this.courseService = courseService;
    }

    public CourseDTO createNewCourse(CourseDTO courseDTO)
    {
        Course entity = courseDtoToEntity(courseDTO);
        Course saved = courseService.createCourse(entity);

        return courseToCourseDto(saved);
    }

    public CourseDTO updateCourse(CourseDTO courseDTO)
    {
        Course entity = courseDtoToEntity(courseDTO);
        Course updated = courseService.updateCourse(entity);
        return courseToCourseDto(updated);
    }

    public void removeCourse(CourseDTO courseDTO)
    {
        Course entity = this.courseDtoToEntity(courseDTO);
        courseService.removeCourse(entity);
    }

    public List<CourseDTO> findCourseByLanguage(String language)
    {
        List<Course> entities = courseService.findCourseByLanguage(language);
        List<CourseDTO> courses = new ArrayList<>();
        for(Course entity : entities)
        {
            courses.add(courseToCourseDto(entity));
        }
        return courses;
    }

    private Course courseDtoToEntity(CourseDTO dto)
    {
        return mapper.map(dto,Course.class);
    }
    private CourseDTO courseToCourseDto(Course entity){
        return mapper.map(entity,CourseDTO.class);
    }
}
