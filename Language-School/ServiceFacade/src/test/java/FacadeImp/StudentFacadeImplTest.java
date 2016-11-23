/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeImp;

import DTO.StudentDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zanri
 */
public class StudentFacadeImplTest {
    
    public StudentFacadeImplTest() {
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
     * Test of registerStudent method, of class StudentFacadeImpl.
     */
    @Test
    public void testRegisterStudent() {
        System.out.println("registerStudent");
        StudentDTO studentDTO = null;
        StudentFacadeImpl instance = new StudentFacadeImpl();
        StudentDTO expResult = null;
        StudentDTO result = instance.registerStudent(studentDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStudent method, of class StudentFacadeImpl.
     */
    @Test
    public void testUpdateStudent() {
        System.out.println("updateStudent");
        StudentDTO studentDTO = null;
        StudentFacadeImpl instance = new StudentFacadeImpl();
        StudentDTO expResult = null;
        StudentDTO result = instance.updateStudent(studentDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class StudentFacadeImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        StudentFacadeImpl instance = new StudentFacadeImpl();
        StudentDTO expResult = null;
        StudentDTO result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterByNameSurname method, of class StudentFacadeImpl.
     */
    @Test
    public void testFilterByNameSurname() {
        System.out.println("filterByNameSurname");
        String name = "";
        String surname = "";
        StudentFacadeImpl instance = new StudentFacadeImpl();
        List<StudentDTO> expResult = null;
        List<StudentDTO> result = instance.filterByNameSurname(name, surname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdNameAndSurname method, of class StudentFacadeImpl.
     */
    @Test
    public void testFindByIdNameAndSurname() {
        System.out.println("findByIdNameAndSurname");
        Long id = null;
        String name = "";
        String surname = "";
        StudentFacadeImpl instance = new StudentFacadeImpl();
        StudentDTO expResult = null;
        StudentDTO result = instance.findByIdNameAndSurname(id, name, surname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
