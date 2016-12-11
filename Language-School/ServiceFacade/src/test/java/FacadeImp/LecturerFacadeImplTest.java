package FacadeImp;

import DTO.LectureDTO;
import org.muni.fi.pa165.lang_school.entities.Lecture;
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
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
//@Transactional
public class LecturerFacadeImplTest {

    @Autowired
    private DozerBeanMapper mapper;

    @Mock
    private LecturerServiceImpl lecturerService;

    private LecturerFacadeImpl lecturerFacade;

    private Lecturer lecturer;
    private Lecturer lecturerB;

    @BeforeClass
    public void beforeClass()
    {
        MockitoAnnotations.initMocks(this);
        lecturerFacade = new LecturerFacadeImpl(lecturerService);
    }

    /**
     * Initialize instance of Lecturer and Lecture for test purposes
     */
    @BeforeMethod
    public void init()
    {
        mapper = new DozerBeanMapper();

        lecturer = new Lecturer();
        lecturer.setId(1l);
        lecturer.setName("Simon");
        lecturer.setSurname("Hyben");
        
        lecturerB = new Lecturer();
        lecturerB.setId(2l);
        lecturerB.setName("Simon");
        lecturerB.setSurname("Mover"); 
    }

    @BeforeMethod
    public void initServiceBehaviour()
    {
        when(lecturerService.addLecturer(lecturer)).thenReturn(lecturer);
        when(lecturerService.updateLecturer(lecturerB)).thenReturn(lecturerB);
    }
    
    /**
     * Tests for registerLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testRegisterLecturer() {
        LecturerDTO lecturerDto = mapper.map(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto);
        verify(lecturerService, times(1)).addLecturer(lecturer);
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
        LecturerDTO lecturerBDto = mapper.map(lecturerB, LecturerDTO.class);
        lecturerFacade.updateLecturer(lecturerBDto);        
        verify(lecturerService, times(1)).updateLecturer(lecturerB);
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
        LecturerDTO tmpLec = lecturerFacade.findById(1l);
        assert(tmpLec.getId() == 1l);
    }    
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindByNullId() {
        lecturerFacade.findById(null);
        fail("Expected IllegalArgumentException");
    }
    
    /**
     * Tests for deleteLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testDeleteLecturer() {
        LecturerDTO lecturerDto = mapper.map(lecturerB, LecturerDTO.class);
        lecturerFacade.removeLecturer(lecturerDto);
        verify(lecturerService, times(1)).removeLecturer(lecturerB);
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
        LecturerDTO lecturerDto = mapper.map(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto);
        List<LecturerDTO> newList = lecturerFacade.filterByName("Simon", "Hyben");
        verify(lecturerService, times(1)).findByName("Simon", "Hyben");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFilterByNullName() {
        LecturerDTO lecturerDto = mapper.map(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto);
        List<LecturerDTO> newList = lecturerFacade.filterByName(null, "Hyben");
        fail("Expected IllegalArgumentException");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFilterByNullSurname() {
        LecturerDTO lecturerDto = mapper.map(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto);
        List<LecturerDTO> newList = lecturerFacade.filterByName("Simon", null);
        fail("Expected IllegalArgumentException");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFilterByNameNullBoth() {
        LecturerDTO lecturerDto = mapper.map(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto);
        List<LecturerDTO> newList = lecturerFacade.filterByName(null, null);
        fail("Expected IllegalArgumentException");
    }
}

