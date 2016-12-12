package FacadeImp;

import ConfigMapper.BeanMapper;
import ConfigMapper.BeanMappingConfiguration;
import DTO.LectureDTO;
import DTO.LecturerDTO;
import ServiceImp.LectureServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;

/**
 * Test of Lecture facade implementation
 * @author Matus Krska, 410073
 * @since 1.0
 */
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LectureFacadeImplTest
{
    @Mock
    private BeanMapper mapper;

    @Mock
    private LectureServiceImpl lectureService;

    private LectureFacadeImpl lectureFacade;

    private LectureDTO lectureA;
    private LecturerDTO lecturerB;


    @BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Lecture())).when(mapper).mapTo(any(LectureDTO.class), eq(Lecture.class));
        doReturn(Optional.of(new LectureDTO())).when(mapper).mapTo(any(Lecture.class), eq(LectureDTO.class));
        doReturn(Optional.of(new Lecturer())).when(mapper).mapTo(any(LecturerDTO.class), eq(Lecturer.class));
        doReturn(Optional.of(new LecturerDTO())).when(mapper).mapTo(any(Lecturer.class), eq(LecturerDTO.class));

        lectureFacade = new LectureFacadeImpl(lectureService, mapper);
    }

    /**
     * Initialize instance of Lecture for test purposes
     */
    @BeforeMethod
    public void init() {
        lectureA = new LectureDTO();
        lecturerB = new LecturerDTO();
    }

    @AfterMethod
    public void afterMethod() {
        reset(lectureService);
    }

    /**
     * Tests for createNewLecture method, of class LectureFacadeImpl.
     */
    @Test
    public void testCreateNewLecture()
    {
        lectureFacade.createNewLecture(lectureA);
        verify(lectureService, times(1)).createLecture(any(Lecture.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateLecturerNull()
    {
        lectureFacade.createNewLecture(null);
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for updateLecture method, of class LectureFacadeImpl.
     */
    @Test
    public void testUpdateLecture()
    {
        lectureFacade.updateLecture(lectureA);
        verify(lectureService, times(1)).updateLecture(any(Lecture.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateLectureNull()
    {
        lectureFacade.updateLecture(null);
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for removeLecture method, of class LectureFacadeImpl.
     */
    @Test
    public void testRemoveLecture()
    {
        lectureFacade.removeLecture(lectureA);
        verify(lectureService, times(1)).removeLecture(any(Lecture.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturerNull()
    {
        lectureFacade.removeLecture(null);
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for findLectureByCode method, of class LectureFacadeImpl.
     */
    @Test
    public void testFindLectureByCode()
    {
        lectureFacade.findLectureByCode("code");
        verify(lectureService,times(1)).findLectureByCode(eq("code"));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindLectureByCodeNull()
    {
        lectureFacade.findLectureByCode(null);
        fail("Expected IllegalArgumentException");
    }

    /**
     * Test for findAllLectures method, of class LectureFacadeImpl.
     */
    @Test
    public void testFindAllLectures()
    {
        lectureFacade.findAllLectures();
        verify(lectureService,times(1)).findAllLectures();
    }

    /**
     * Tests for findLectureByCodeAndTopic method, of class LectureFacadeImpl.
     */
    @Test
    public void testFindLectureByCodeAndTopic()
    {
        lectureFacade.findLectureByCodeAndTopic("code","topic");
        verify(lectureService,times(1)).findLectureByCodeAndTopic(eq("code"),eq("topic"));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindLectureByCodeNullAndTopic()
    {
        lectureFacade.findLectureByCodeAndTopic(null, "topic");
        fail("Expected IllegalArgumentException");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindLectureByCodeAndTopicNull()
    {
        lectureFacade.findLectureByCodeAndTopic("code", null);
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for findLectureByLecturer method, of class LectureFacadeImpl
     */
    @Test
    public void testFindLectureByLecturer()
    {
        //ID is needed
        lecturerB.setId(1L);
        lectureFacade.findLecturesByLecturer(lecturerB);
        verify(lectureService,times(1)).findLecturesByLecturer(any(Lecturer.class));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindLectureByLecturerNull()
    {
        lectureFacade.findLecturesByLecturer(null);
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for changeLectureCode metod , of class LectureFacadeImpl
     */
    @Test
    public void testChangeLectureCode()
    {
        lectureFacade.changeLectureCode(lectureA,"code");
        verify(lectureService,times(1)).changeLectureCode(any(Lecture.class),eq("code"));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testChangeLectureCodeNull()
    {
        lectureFacade.changeLectureCode(lectureA,null);
        fail("Expected IllegalArgumentException");
    }
}
