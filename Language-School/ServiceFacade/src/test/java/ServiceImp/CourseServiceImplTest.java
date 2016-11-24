package ServiceImp;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;
import org.muni.fi.pa165.lang_school.DAO.CourseDAO;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.fail;

/**
 * Test of course service layer
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CourseServiceImplTest
{
    @Mock
    private CourseDAO courseDAO;

    private Course courseA;

    private CourseServiceImpl courseServiceImpl;

    public CourseServiceImplTest()
    {
    }

    @BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        courseServiceImpl = new CourseServiceImpl(courseDAO);
    }

    @BeforeMethod
    public void init()
    {
        courseA = new Course();
        courseA.setName("Anglictina pre zaciatocnikov");
        courseA.setLanguage("ENG");
        courseA.setLanguage_level("A1");
    }

    /**
     * Testing creation of course
     */
    @Test
    public void testCreateCourse()
    {
        courseServiceImpl.createCourse(courseA);
        verify(courseDAO, times(1)).create(courseA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull()
    {
        courseServiceImpl.createCourse(null);
        fail("Null has been created.");
    }

    /**
     * Testing update of course
     */
    @Test
    public void testUpdateCourse()
    {
        courseServiceImpl.updateCourse(courseA);
        verify(courseDAO, times(1)).update(courseA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateNull()
    {
        courseServiceImpl.updateCourse(null);
        fail("Null has been updated.");
    }

    /**
     * Testing removal of course
     */
    @Test
    public void testRemoveCourse()
    {
        courseServiceImpl.removeCourse(courseA);
        verify(courseDAO, times(1)).delete(courseA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveNull()
    {
        courseServiceImpl.removeCourse(null);
        fail("Null has been removed.");
    }

    /**
     * Testing finding of course
     */
    @Test
    public void testFindCourseByLanguage()
    {
        courseServiceImpl.findCourseByLanguage("ENG");
        verify(courseDAO, times(1)).readByColumn("LANGUAGE", "ENG");
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindCourseByLanguageNull()
    {
        courseServiceImpl.findCourseByLanguage(null);
        fail("Null has been found.");
    }
}
