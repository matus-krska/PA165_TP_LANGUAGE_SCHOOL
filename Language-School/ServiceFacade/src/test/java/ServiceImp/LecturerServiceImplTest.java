package ServiceImp;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.muni.fi.pa165.lang_school.entities.Lecturer;

/**
 * Tests for lecturer's service layer
 * @author Simon Hyben, 421112
 */
public class LecturerServiceImplTest {
    
    public LecturerServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testCreateLecturer() {
        System.out.println("createLecturer");
        Lecturer entity = null;
        LecturerServiceImpl instance = new LecturerServiceImpl();
        Lecturer expResult = null;
        Lecturer result = instance.createLecturer(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLecturer method, of class LecturerServiceImpl.
     */
    @Test
    public void testUpdateLecturer() {
        System.out.println("updateLecturer");
        Lecturer entity = null;
        LecturerServiceImpl instance = new LecturerServiceImpl();
        Lecturer expResult = null;
        Lecturer result = instance.updateLecturer(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class LecturerServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        LecturerServiceImpl instance = new LecturerServiceImpl();
        Lecturer expResult = null;
        Lecturer result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLecturerByName method, of class LecturerServiceImpl.
     */
    @Test
    public void testFindLecturerByName() {
        System.out.println("findByName");
        String name = "";
        String surname = "";
        LecturerServiceImpl instance = new LecturerServiceImpl();
        List<Lecturer> expResult = null;
        List<Lecturer> result = instance.findLecturerByName(name, surname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}

