package ServiceImp;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.muni.fi.pa165.lang_school.DAO.CourseDAO;
import org.muni.fi.pa165.lang_school.DAO.LectureDAO;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.fail;

/**
 * Test of lecture service layer
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class LectureServiceImplTest
{
    @Mock
    private LectureDAO lectureDAO;

    private Lecture lectureA;

    private LectureServiceImpl lectureServiceImpl;

    public LectureServiceImplTest()
    {
    }

    @BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        lectureServiceImpl = new LectureServiceImpl(lectureDAO);
    }

    @BeforeMethod
    public void init()
    {
        lectureA = new Lecture();
        lectureA.setCode("L1");
        lectureA.setTopic("Introduction");
        lectureA.setDescription("Introduction to the language");
        lectureA.setLectureTime(new Date());
    }

    /**
     * Testing creation of lecture
     */
    @Test
    public void testCreateLecture()
    {
        lectureServiceImpl.createLecture(lectureA);
        verify(lectureDAO, times(1)).create(lectureA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull()
    {
        lectureServiceImpl.createLecture(null);
        fail("Null has been created.");
    }

    /**
     * Testing update of lecture
     */
    @Test
    public void testUpdateLecture()
    {
        lectureServiceImpl.updateLecture(lectureA);
        verify(lectureDAO, times(1)).update(lectureA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateNull()
    {
        lectureServiceImpl.updateLecture(null);
        fail("Null has been updated.");
    }

    /**
     * Testing removal of course
     */
    @Test
    public void testRemoveLecture()
    {
        lectureServiceImpl.removeLecture(lectureA);
        verify(lectureDAO, times(1)).delete(lectureA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testRemoveNull()
    {
        lectureServiceImpl.removeLecture(null);
        fail("Null has been removed.");
    }

    /**
     * Testing find of course
     */
    @Test
    public void testFindLectureByCode()
    {
        lectureServiceImpl.findLectureByCode("L1");
        verify(lectureDAO, times(1)).readByColumn("CODE","L1");
    }

    @Test
    public void testFindLectureByCodeAndTopic()
    {
        lectureServiceImpl.findLectureByCodeAndTopic("L1", "Introduction");
        verify(lectureDAO,times(1)).findByCodeAndTopic("L1", "Introduction");
    }

    @Test
    public void testFindAllLectures()
    {
        lectureServiceImpl.findAllLectures();
        verify(lectureDAO,times(1)).findAllLectures();
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindLectureByCodeNull()
    {
        lectureServiceImpl.findLectureByCode(null);
        fail("Null has been found.");
    }

    /**
     * Testing find of course
     */
    @Test
    public void testFindLectureByLecturer()
    {
        Lecturer l = new Lecturer();
        l.setId(1L);
        lectureServiceImpl.findLecturesByLecturer(l);
        verify(lectureDAO, times(1)).readByColumn("TAUGHT_BY",1L);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindLectureByLecturerNull()
    {
        lectureServiceImpl.findLecturesByLecturer(null);
        fail("Null has been found.");
    }

    /**
     * Testing changing code
     */
    @Test
    public void testChangeCode()
    {
        lectureServiceImpl.changeLectureCode(lectureA, "L0");
        verify(lectureDAO, times(1)).update(lectureA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testChangeCodeNull()
    {
        lectureServiceImpl.changeLectureCode(lectureA, null);
        fail("Null has been changed.");
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testChangeCodeEmpty()
    {
        lectureServiceImpl.changeLectureCode(lectureA, "");
        fail("Null has been changed.");
    }
}
