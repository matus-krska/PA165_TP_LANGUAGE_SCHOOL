/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImp;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.muni.fi.pa165.lang_school.entities.Student;

/**
 *
 * @author zanri
 */
public class StudentServiceImplTest {
    
    public StudentServiceImplTest() {
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
     * Test of addStudent method, of class StudentServiceImpl.
     */
    @Test
    public void testAddStudent() {
        System.out.println("addStudent");
        Student entity = null;
        StudentServiceImpl instance = new StudentServiceImpl();
        Student expResult = null;
        Student result = instance.addStudent(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class StudentServiceImpl.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        Student entity = null;
        StudentServiceImpl instance = new StudentServiceImpl();
        Student expResult = null;
        Student result = instance.updateUser(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class StudentServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        StudentServiceImpl instance = new StudentServiceImpl();
        Student expResult = null;
        Student result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByNameSurname method, of class StudentServiceImpl.
     */
    @Test
    public void testFindByNameSurname() {
        System.out.println("findByNameSurname");
        String name = "";
        String surname = "";
        StudentServiceImpl instance = new StudentServiceImpl();
        List<Student> expResult = null;
        List<Student> result = instance.findByNameSurname(name, surname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByIdNameAndSurname method, of class StudentServiceImpl.
     */
    @Test
    public void testFindByIdNameAndSurname() {
        System.out.println("findByIdNameAndSurname");
        Long id = null;
        String name = "";
        String surname = "";
        StudentServiceImpl instance = new StudentServiceImpl();
        Student expResult = null;
        Student result = instance.findByIdNameAndSurname(id, name, surname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
