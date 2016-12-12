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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.testng.Assert.fail;

/**
 * Test of Course facade implementation
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CourseFacadeImplTest
{
    @Mock
    private BeanMapper mapper;

    @Mock
    private CourseServiceImpl courseService;

    private CourseFacadeImpl courseFacade;

    private CourseDTO course;
    //private Course courseB;
    private List<Course> engCourses;

    @org.testng.annotations.BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Course())).when(mapper).mapTo(any(CourseDTO.class), eq(Course.class));
        doReturn(Optional.of(new CourseDTO())).when(mapper).mapTo(any(Course.class), eq(CourseDTO.class));
        //doReturn(Optional.of(new Course())).when(mapper).mapTo(any(LectureDTO.class), eq(Lecture.class));

        courseFacade = new CourseFacadeImpl(courseService, mapper);
    }

    /**
     * Initialize 2 instances of Course for test purposes
     */
    @BeforeMethod
    public void init()
    {
        course = new CourseDTO();

    }

    @AfterMethod
    public void afterMethod() {
        reset(courseService);
    }

    /*
    @BeforeMethod
    public void initServiceBehaviour()
    {
        when(courseService.findCourseByLanguage("ENG")).thenReturn(engCourses);
        when(courseService.createCourse(courseA)).thenReturn(courseA);
        when(courseService.updateCourse(courseB)).thenReturn(courseB);
    }
    */

    @Test
    public void testCreateNewCourse()
    {
        courseFacade.createNewCourse(course);
        verify(courseService, times(1)).createCourse(any(Course.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNewCourseNull() {
        courseFacade.createNewCourse(null);
        fail("Expected IllegalArgumentException");
    }

    @Test
    public void testUpdateCourse()
    {
        courseFacade.updateCourse(course);
        verify(courseService, times(1)).updateCourse(any(Course.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateCourseNull() {
        courseFacade.updateCourse(null);
        fail("Expected IllegalArgumentException");
    }

    @Test
    public void testFindById() {
        courseFacade.findById(Long.MAX_VALUE);
        verify(courseService, times(1)).findById(any(Long.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByNullId() {
        courseFacade.findById(null);
        fail("Expected IllegalArgumentException");
    }

    @Test
    public void testRemoveCourse()
    {
        courseFacade.removeCourse(course);
        verify(courseService, times(1)).removeCourse(any(Course.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveCourseNull() {
        courseFacade.removeCourse(null);
        fail("Expected IllegalArgumentException");
    }

    @Test
    public void testFindCourseByLanguage()
    {
        /*
        List<CourseDTO> enCourse = courseFacade.findCourseByLanguage("ENG");
        verify(courseService,times(1)).findCourseByLanguage("ENG");
        assert(enCourse.size() == 2);
        assert(enCourse.get(0).getName().equals(courseA.getName()));
        assert(enCourse.get(1).getName().equals(courseB.getName()));
        */

        courseFacade.findCourseByLanguage("ENG");
        verify(courseService, times(1)).findCourseByLanguage(any(String.class));
    }



}
