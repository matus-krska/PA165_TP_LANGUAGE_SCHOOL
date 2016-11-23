/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeImp;

import DTO.LecturerDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for lecturer's facade layer
 * @author Simon Hyben, 421112
 */
public class LecturerFacadeImplTest {
    
    public LecturerFacadeImplTest() {
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
     * Test of createNewLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testCreateNewLecturer() {
        System.out.println("createNewLecturer");
        LecturerDTO lecturerDTO = null;
        LecturerFacadeImpl instance = new LecturerFacadeImpl();
        LecturerDTO expResult = null;
        LecturerDTO result = instance.createNewLecturer(lecturerDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateLecturer method, of class LecturerFacadeImpl.
     */
    @Test
    public void testUpdateLecturer() {
        System.out.println("updateLecturer");
        LecturerDTO lecturerDTO = null;
        LecturerFacadeImpl instance = new LecturerFacadeImpl();
        LecturerDTO expResult = null;
        LecturerDTO result = instance.updateLecturer(lecturerDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class LecturerFacadeImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        LecturerFacadeImpl instance = new LecturerFacadeImpl();
        LecturerDTO expResult = null;
        LecturerDTO result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLecturerByName method, of class LecturerFacadeImpl.
     */
    @Test
    public void testFindLecturerByName() {
        System.out.println("findLecturerByName");
        String name = "";
        String surname = "";
        LecturerFacadeImpl instance = new LecturerFacadeImpl();
        List<LecturerDTO> expResult = null;
        List<LecturerDTO> result = instance.findLecturerByName(name, surname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }    
}

