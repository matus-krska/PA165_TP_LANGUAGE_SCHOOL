package FacadeImp;

import ConfigMapper.BeanMapper;
import ConfigMapper.BeanMappingConfiguration;
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
@ContextConfiguration(classes = BeanMappingConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LecturerFacadeImplTest {

    @Mock
    private BeanMapper mapper;

    @Mock
    private LecturerServiceImpl lecturerService;

    private LecturerFacadeImpl lecturerFacade;

    private Lecturer lecturer;
    private Lecturer lecturerB;

    @BeforeClass
    public void beforeClass()
    {
//        when(lecturerService.addLecturer(lecturer)).thenReturn(lecturer);
//        when(lecturerService.updateLecturer(lecturerB)).thenReturn(lecturerB);
        

        
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Lecturer())).when(mapper).mapTo(any(LecturerDTO.class), eq(Lecturer.class));
        doReturn(Optional.of(new LecturerDTO())).when(mapper).mapTo(any(Lecturer.class), eq(LecturerDTO.class));
        doReturn(Optional.of(new Lecture())).when(mapper).mapTo(any(LectureDTO.class), eq(Lecture.class));
        
        
        lecturerFacade = new LecturerFacadeImpl(lecturerService, mapper);
    }

    /**
     * Initialize instance of Lecturer and Lecture for test purposes
     */
    @BeforeMethod
    public void init()
    {
        lecturer = new Lecturer();
        lecturer.setId(1l);
        lecturer.setName("Simon");
        lecturer.setSurname("Hyben");
        
        lecturerB = new Lecturer();
        lecturerB.setId(2l);
        lecturerB.setName("Simon");
        lecturerB.setSurname("Mover"); 
    }

   
    
    /**
     * Tests for registerLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testRegisterLecturer() {
        Optional<LecturerDTO> lecturerDto = mapper.mapTo(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto.get());
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
        Optional<LecturerDTO> lecturerBDto = mapper.mapTo(lecturerB, LecturerDTO.class);
        lecturerFacade.updateLecturer(lecturerBDto.get());        
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
        Optional<LecturerDTO> lecturerDto = mapper.mapTo(lecturerB, LecturerDTO.class);
        lecturerFacade.removeLecturer(lecturerDto.get());
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
        Optional<LecturerDTO> lecturerDto = mapper.mapTo(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto.get());
        List<LecturerDTO> newList = lecturerFacade.filterByName("Simon", "Hyben");
        verify(lecturerService, times(1)).findByName("Simon", "Hyben");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFilterByNullName() {
        Optional<LecturerDTO> lecturerDto = mapper.mapTo(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto.get());
        List<LecturerDTO> newList = lecturerFacade.filterByName(null, "Hyben");
        fail("Expected IllegalArgumentException");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFilterByNullSurname() {
        Optional<LecturerDTO> lecturerDto = mapper.mapTo(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto.get());
        List<LecturerDTO> newList = lecturerFacade.filterByName("Simon", null);
        fail("Expected IllegalArgumentException");
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFilterByNameNullBoth() {
        Optional<LecturerDTO> lecturerDto = mapper.mapTo(lecturer, LecturerDTO.class);
        lecturerFacade.registerLecturer(lecturerDto.get());
        List<LecturerDTO> newList = lecturerFacade.filterByName(null, null);
        fail("Expected IllegalArgumentException");
    }
}

