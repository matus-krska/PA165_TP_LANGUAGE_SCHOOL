package FacadeImp;

import DTO.LectureDTO;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import DTO.LecturerDTO;
import ServiceImp.LecturerServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;

import org.dozer.Mapper;
import org.dozer.classmap.DozerClass;
import org.dozer.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for lecturer facade layer
 * @author Simon Hyben, 421112
 */
//@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LecturerFacadeImplTest {
    
    @Mock
    LecturerServiceImpl lecturerService;
    
    //@Mock
    //BeanMapping beanMapping;
    
    LecturerFacadeImpl lecturerFacade;
    
    Lecturer lecturer;
    
    LecturerDTO lecturerDto;
    //LecturerCreateDTO lecturerCreate;
    LectureDTO lectureDto;
    
    public LecturerFacadeImplTest() {
    }
    
    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        lecturerFacade = new LecturerFacadeImpl(lecturerService);
        //lecturerFacade = new LecturerFacadeImpl(lecturerService, beanMapping);
    }
    
    @BeforeMethod
    public void init() {
        lectureDto = new LectureDTO();
        lectureDto.setId(2l);
        lectureDto.setCode("PA165");
        lectureDto.setDescription("3rd lecture of java programming");
        //lectureDto.setLectureTime(lectureTime);
        lectureDto.setTaughtBy(lecturerDto);
        lectureDto.setTopic("java");
        
        List<LectureDTO> lessons = new ArrayList<>();
        lessons.add(lectureDto);
        
        lecturerDto = new LecturerDTO();
        lecturerDto.setId(1l);        
        lecturerDto.setLessons(lessons);
        lecturerDto.setName("Simon");
        lecturerDto.setSurname("Hyben");
        
        //lecturerDto.setTaughtLanguages(taughtLanguages);        
    }
    
    @AfterMethod
    public void afterMethod() {
        reset(lecturerService);
    }
    
    /**
     * Tests for registerLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testRegisterLecturer() {
        lecturerFacade.registerLecturer(lecturerDto);
        
        verify(lecturerService, times(1)).addLecturer(any(Lecturer.class));
    }    
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateLecturerNull() {
        
        lecturerFacade.registerLecturer(null);
        
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for updateLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testUpdateLecturer() {        
        lecturerFacade.updateLecturer(lecturerDto);        
        verify(lecturerService, times(1)).updateLecturer(any(Lecturer.class));
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testUpdateLecturerNull() {
        
        lecturerFacade.updateLecturer(null);
        
        fail("Expected IllegalArgumentException");
    }

    /**
     * Tests for findById method, of class LecturerFacadeImpl.
     */
    @Test
    public void testFindById() {
        
        lecturerFacade.findById(1l);
        
        verify(lecturerService, times(1)).findById(1l);
    }    
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testGetLecturerByIdNull() {
        
        lecturerFacade.findById(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    /**
     * Tests for deleteLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testDeleteLecturer() {
        
        lecturerFacade.removeLecturer(lecturerDto);
        
        verify(lecturerService, times(1)).removeLecturer(any(Lecturer.class));
    }    
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testDeleteLecturerNull() {
        
        lecturerFacade.removeLecturer(null);
        
        fail("Expected IllegalArgumentException");
    }
    
    /**
     * Tests for filterByName method, of class LecturerFacadeImpl.
     */
    @Test
    public void testFilterByName() {
        lecturerFacade.registerLecturer(lecturerDto);
        lecturerFacade.filterByName("Simon", "Hyben");
        verify(lecturerService, times(1)).findByName("Simon", "Hyben");
    }
    public void testFilterByNullName() {
        lecturerFacade.registerLecturer(lecturerDto);
        lecturerFacade.filterByName(null, "Hyben");
        fail("Expected IllegalArgumentException");
    }
    public void testFilterByNullSurname() {
        lecturerFacade.registerLecturer(lecturerDto);
        lecturerFacade.filterByName("Simon", null);
        fail("Expected IllegalArgumentException");
    }
    public void testFilterByNameNullBoth() {
        lecturerFacade.registerLecturer(lecturerDto);
        lecturerFacade.filterByName(null, null);
        fail("Expected IllegalArgumentException");
    }
    
    
}

