package org.muni.fi.pa165.lang_school.DAO;

import java.util.List;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO.StudentDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for StudentDAO
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

    @Autowired
    private StudentDAO studentDAO;

    Student studentA;
    Student studentB;
    Student studentC;

    @BeforeMethod
    public void createTestingSubject() 
    {            
        studentA = new Student();
        studentB.setId(1l);
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
        
    @Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testNullEntity() 
    {
        studentDAO.create(null);
        Assert.fail("Trying to create NULL entity!");                
    }  
        
    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testNullName() 
    {          
        studentA.setName(null);
        studentDAO.create(studentA);
        Assert.fail("Can't insert student with NULL name!");            
    }
       
    @Test(expectedExceptions = javax.validation.ConstraintViolationException.class)
    public void testNullSurname() 
    {       
        studentA.setSurname(null);
        studentDAO.create(studentA);
        Assert.fail("Can't insert student with NULL surname!");            
    }
    
    @Test
    public void testCreateValidStudent() 
    {        
        studentDAO.create(studentA);
        Assert.assertNotNull(em.find(Student.class, studentA.getId())); 
    }      
    
    @Test(expectedExceptions = org.springframework.orm.jpa.JpaSystemException.class)
    public void testIdDuplicate() 
    {
        studentDAO.create(studentA);
        studentDAO.create(studentB);
        Assert.fail("Student with duplicate id inserted!");            
    }    
        
    @Test
    public void testReadById() 
    {        
        em.persist(studentA);
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getId(), "1");
                
    }

    @Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testReadByNullId() 
    {
        studentDAO.readById(null);
        Assert.fail("Trying to find student by NULL id!");                
    }

    @Test
    public void testUpdateStudent() 
    { 
	em.persist(studentA);
	studentA.setSurname("Mover");
	studentDAO.update(studentA);
	Assert.assertEquals(studentDAO.readById(studentA.getId()).getSurname(), "Mover");
    }

    @Test(expectedExceptions = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testUpdateNull() 
    {
	studentDAO.update(null);
        Assert.fail("Trying to update NULL student!");               
    }

    @Test
    public void testReadByColumn() 
    {        
	em.persist(studentA);
	em.persist(studentC);
        List<Student> tmpRes = studentDAO.readByColumn("NAME", "Simon");
	Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getName(), "Simon");
        Assert.assertEquals(tmpRes.get(0).getName(), tmpRes.get(1).getName());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveNullStudent() 
    {
    	studentDAO.delete(null);
        Assert.fail("Trying to delete NULL student!");                
    }
    
    @Test
    public void testDeleteStudent() 
    {        
	em.persist(studentA);
	Assert.assertNotNull(studentDAO.readById(studentA.getId()));
	studentDAO.delete(studentA);
	Assert.assertNull(studentDAO.readById(studentA.getId()));        
    }   
}
