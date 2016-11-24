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

import static org.mockito.Mockito.*;
import static org.testng.Assert.fail;

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
        studentServiceImpl.addStudent(studentA);
        verify(studentDao, times(1)).create(studentA);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testCreateNull(){
        studentServiceImpl.addStudent(null);
        fail("Null has been created.");
    }
    
    /**
     * Test of updateStudent method, of class StudentServiceImpl.
     */
    @Test
    public void testUpdateUser() {
        studentA.setName("123");
        studentServiceImpl.updateStudent(studentA);
        verify(studentDao, times(1)).update(studentA);
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testNullUpdate(){
        studentServiceImpl.updateStudent(null);
        fail("Null has been updated.");
    }

    /**
     * Test of findById method, of class StudentServiceImpl.
     */
    @Test
    public void testFindById() {
        studentServiceImpl.findById(1l);
        verify(studentDao, times(1)).readById(1l);
    }

    /**
     * Test of findByNameSurname method, of class StudentServiceImpl.
     */
    @Test
    public void testFindByNameSurname() {
        studentServiceImpl.addStudent(studentA);
        studentServiceImpl.findByNameSurname("Simon", "Hyben");
        verify(studentDao, times(1)).findByNameAndSurname("Simon", "Hyben");
    }

    /**
     * Test of findByIdNameAndSurname method, of class StudentServiceImpl.
     */
    @Test
    public void testFindByIdNameAndSurname() {
       studentServiceImpl.addStudent(studentA);
       studentServiceImpl.findByIdNameAndSurname(1l, "Simon", "Hyben");
       verify(studentDao, times(1)).findByIdNameAndSurname(1l, "Simon", "Hyben");
    }
}
