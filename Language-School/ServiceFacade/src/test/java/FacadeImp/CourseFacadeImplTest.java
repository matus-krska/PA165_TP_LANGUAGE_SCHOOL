package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.CourseDTO;
import ServiceImp.CourseServiceImpl;
import ServiceImp.LecturerServiceImpl;
import org.dozer.DozerBeanMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test of Course facade implementation
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CourseFacadeImplTest
{
    @Autowired
    private BeanMapper mapper;

    @Mock
    private CourseServiceImpl courseService;

    private CourseFacadeImpl courseFacade;

    private Course courseA;
    private Course courseB;
    private List<Course> engCourses;

    @org.testng.annotations.BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        courseFacade = new CourseFacadeImpl(courseService, mapper);
    }

    /**
     * Initialize 2 instances of Course for test purposes
     */
    @BeforeMethod
    public void init()
    {
        //mapper = new DozerBeanMapper();

        courseA = new Course();
        courseA.setName("Course1");
        courseA.setId(1L);
        courseA.setLanguage("ENG");
        courseA.setLanguage_level("B1");

        courseB = new Course();
        courseB.setName("Course2");
        courseB.setId(2L);
        courseB.setLanguage("ENG");
        courseB.setLanguage_level("A1");

        engCourses = new ArrayList<>();
        engCourses.add(courseA);
        engCourses.add(courseB);
    }

    @BeforeMethod
    public void initServiceBehaviour()
    {
        when(courseService.findCourseByLanguage("ENG")).thenReturn(engCourses);
        when(courseService.createCourse(courseA)).thenReturn(courseA);
        when(courseService.updateCourse(courseB)).thenReturn(courseB);
    }

    @Test
    public void testCreateNewCourse()
    {
        CourseDTO courseADTO = mapper.map(courseA,CourseDTO.class);

        courseFacade.createNewCourse(courseADTO);
        verify(courseService,times(1)).createCourse(courseA);
    }

    @Test
    public void testUpdateCourse()
    {
        CourseDTO courseBDTO = mapper.map(courseB,CourseDTO.class);

        courseFacade.updateCourse(courseBDTO);
        verify(courseService,times(1)).updateCourse(courseB);
    }

    @Test
    public void testRemoveCourse()
    {
        CourseDTO courseBDTO = mapper.map(courseB,CourseDTO.class);

        courseFacade.removeCourse(courseBDTO);
        verify(courseService,times(1)).removeCourse(courseB);
    }

    @Test
    public void testFindCourseByLanguage()
    {
        List<CourseDTO> enCourse = courseFacade.findCourseByLanguage("ENG");
        verify(courseService,times(1)).findCourseByLanguage("ENG");
        assert(enCourse.size() == 2);
        assert(enCourse.get(0).getName().equals(courseA.getName()));
        assert(enCourse.get(1).getName().equals(courseB.getName()));
    }



}
