package ServiceImp;

import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.muni.fi.pa165.lang_school.DAO.LecturerDAO;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;

/**
 * Tests for lecturer service layer
 * @author Simon Hyben, 421112
 * @since 1.0
 */
public class LecturerServiceImplTest {
    
    @Mock
    private LecturerDAO lecturerDao;

    private Lecturer lecturerA;
    private Lecturer lecturerB;
    
    private LecturerServiceImpl lecturerServiceImpl;
    
    public LecturerServiceImplTest() {
    }
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        lecturerServiceImpl = new LecturerServiceImpl(lecturerDao);
    }
    
    /**
     * Initialize 2 instances of Lecturer for test purposes
     */
    @BeforeMethod
    public void init() {            
        lecturerA = new Lecturer();
        lecturerA.setName("Simon");
        lecturerA.setSurname("Hyben");
        
        lecturerB = new Lecturer();
        lecturerB.setName("Michal");
        lecturerB.setSurname("Novak");
    }

    /**
     * Test of addLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testAddLecturer() {
        lecturerServiceImpl.addLecturer(lecturerA);
        verify(lecturerDao, times(1)).create(lecturerA);
    }

    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull(){
        lecturerServiceImpl.addLecturer(null);
        fail("Null has been created.");
    }
    
    /**
     * Test of updateLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testUpdateUser() {
        lecturerA.setName("123");
        lecturerServiceImpl.updateLecturer(lecturerA);
        verify(lecturerDao, times(1)).update(lecturerA);
    }
    
    /**
     * Test to create exception
     */
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testNullUpdate(){
        lecturerServiceImpl.updateLecturer(null);
        fail("Null has been updated.");
    }

    /**
     * Test of findById method, of class LecturerServiceImpl.
     */
    @Test
    public void testFindById() {
        lecturerServiceImpl.findById(1l);
        verify(lecturerDao, times(1)).readById(1l);
    }

    /**
     * Test of findByName method, of class LecturerServiceImpl.
     */
    @Test
    public void testFindByName() {
        lecturerServiceImpl.addLecturer(lecturerA);
        lecturerServiceImpl.findByName("Simon", "Hyben");
        verify(lecturerDao, times(1)).findByName("Simon", "Hyben");
    }  
}

