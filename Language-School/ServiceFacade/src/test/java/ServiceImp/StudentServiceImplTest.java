/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceImp;

import org.muni.fi.pa165.lang_school.entities.Student;
import org.muni.fi.pa165.lang_school.DAO.StudentDAO;


import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;


/**
 *
 * @author zanri
 */
public class StudentServiceImplTest {
    
    @Mock
    private StudentDAO studentDao;

    private Student studentA;
    private Student studentB;
    
    private StudentServiceImpl studentServiceImpl;
    
    public StudentServiceImplTest() {
    }
    
    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
        studentServiceImpl = new StudentServiceImpl(studentDao);
    }
    
    @BeforeMethod
    public void init() {            
        studentA = new Student();
        studentA.setName("Simon");
        studentA.setSurname("Hyben");
        
        studentB = new Student();
        studentB.setName("Albert");
        studentB.setSurname("Wonter");
    }

    /**
     * Test of addStudent method, of class StudentServiceImpl.
     */
    @Test
    public void testAddStudent() {
        Student temp = studentServiceImpl.addStudent(studentA);
        assertEquals(temp.getName(), studentA.getName());
    }

    /**
     * Test of updateUser method, of class StudentServiceImpl.
     */
    @Test
    public void testUpdateUser() {
        
    }

    /**
     * Test of findById method, of class StudentServiceImpl.
     */
    @Test
    public void testFindById() {
        
    }

    /**
     * Test of findByNameSurname method, of class StudentServiceImpl.
     */
    @Test
    public void testFindByNameSurname() {
        
    }

    /**
     * Test of findByIdNameAndSurname method, of class StudentServiceImpl.
     */
    @Test
    public void testFindByIdNameAndSurname() {
       
    }
    
}
