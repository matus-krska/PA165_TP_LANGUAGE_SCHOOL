package org.muni.fi.pa165.lang_school.DAO;

import java.util.List;
import org.muni.fi.pa165.lang_school.entities.Student;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for StudentDAO
 * Tests CRUD operations
 * 
 * @author Simon Hyben, 421112
 * @since 1.0
 */

@ContextConfiguration(classes = org.muni.fi.pa165.lang_school.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StudentDAOTest extends AbstractTestNGSpringContextTests {
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private StudentDAO studentDAO;

    Student studentA;
    Student studentB;
    Student studentC;

    @BeforeMethod
    public void init() 
    {            
        studentA = new Student();
        studentA.setId(1l);
        studentA.setName("Simon");
        studentA.setSurname("Hyben");
        
        studentB = new Student();
        studentB.setId(1l);
        studentB.setName("Albert");
        studentB.setSurname("Wonter");
        
        studentC = new Student();
        studentC.setId(3l);
        studentC.setName("Simon");
        studentC.setSurname("Lehotsky");            
    }
    
    @Test
    public void testCreateNullStudent() 
    {        	
        try {
            studentDAO.create(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }          
    } 
       
    @Test
    public void testCreateStudent() 
    {        
        studentDAO.create(studentA);
        Assert.assertNotNull(studentDAO.readById(studentA.getId())); 
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getId(), (Long)1l);
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getName(), "Simon");
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getSurname(), "Hyben");
        studentDAO.delete(studentA);
    } 
    
    @Test
    public void testDuplicateId() 
    {        
	studentDAO.create(studentA);
	Assert.assertNotNull(studentDAO.readById(studentA.getId()));
        try {
            studentDAO.create(studentB);
            Assert.fail();
        } catch (javax.persistence.EntityExistsException e) {   
            studentDAO.delete(studentA);
        }          
    } 
    
    @Test
    public void testReadById() 
    {        
        studentDAO.create(studentA);
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getId(), (Long)1l); 
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getName(), "Simon");
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getSurname(), "Hyben");
        studentDAO.delete(studentA);
    }    
    
    @Test
    public void testReadByNullId() 
    {
        try{
            studentDAO.readById(null);
            Assert.fail();
        } catch (javax.persistence.PersistenceException e) {   
        }                   
    }
    
    @Test
    public void testUpdateStudent() 
    { 
	studentDAO.create(studentA);
	studentA.setSurname("Mover");
	studentDAO.update(studentA);
        Assert.assertNotNull(studentDAO.readById(studentA.getId())); 
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getId(), (Long)1l);
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getName(), "Simon");
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getSurname(), "Mover");
        studentDAO.delete(studentA);
    }   
    
    @Test
    public void testUpdateNullStudent() 
    {
        try{
            studentDAO.update(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }                   
    }
    
    @Test
    public void testReadByColumn() 
    {        
        studentDAO.create(studentA);
	studentDAO.create(studentC);
        List<Student> tmpRes = studentDAO.readByColumn("NAME", "Simon");
	Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getName(), "Simon");
        Assert.assertEquals(tmpRes.get(0).getName(), tmpRes.get(1).getName());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
        studentDAO.delete(studentA);
        studentDAO.delete(studentC);
    }
    
    @Test
    public void testDeleteStudent() 
    {        
	studentDAO.create(studentA);
	Assert.assertNotNull(studentDAO.readById(studentA.getId()));
        studentDAO.delete(studentA);
        try {
            em.find(Student.class, studentA.getId()).getId();
            Assert.fail();
        } catch (NullPointerException e) {   
        }          
    } 
    
    @Test
    public void testDeleteNullStudent() 
    {
        try{
            studentDAO.delete(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }                   
    }        
        
    @Test
    public void testFindByNameAndSurname() 
    {        
        studentDAO.create(studentA);
        studentC.setSurname("Hyben");
        studentDAO.create(studentC);
        
        List<Student> tmpRes = studentDAO.findByNameAndSurname("Simon", "Hyben");
	Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getName(), "Simon");
        Assert.assertEquals(tmpRes.get(0).getName(), tmpRes.get(1).getName());
        Assert.assertEquals(tmpRes.get(0).getSurname(), tmpRes.get(1).getSurname());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
        
        studentDAO.delete(studentA);
        studentDAO.delete(studentC);
    }
    
    @Test
    public void testFindByIdNameAndSurname() 
    {        
        studentDAO.create(studentA);
        Object tmpObject = studentDAO.findByIdNameAndSurname(1l, "Simon", "Hyben");
        Student tmpStudent = (Student)tmpObject;
        
        Assert.assertEquals(tmpStudent.getId(), (Long)1l); 
        Assert.assertEquals(tmpStudent.getName(), "Simon");
        Assert.assertEquals(tmpStudent.getSurname(), "Hyben");
        studentDAO.delete(studentA);
    } 
}
