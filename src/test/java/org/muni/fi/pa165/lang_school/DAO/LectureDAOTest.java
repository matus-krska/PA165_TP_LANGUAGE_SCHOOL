package org.muni.fi.pa165.lang_school.DAO;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO.LectureDAO;
import org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO.StudentDAO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Petra Kamenickova, 396179
 * @since 1.0
 */

@ContextConfiguration(classes = org.muni.fi.pa165.lang_school.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LectureDAOTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private LectureDAO lectureDAO;

    Lecture lectureA;
    Lecture lectureB;


    @BeforeMethod
    public void init() 
    {            
        lectureA = new Lecture();
        lectureA.setId(1l);
        lectureA.setCode("X");
        lectureA.setDescription("Y");
        
        lectureB = new Lecture();
        lectureB.setId(2l);
        lectureA.setCode("Y");
        lectureA.setDescription("X");   
    }
    
    @Test
    public void testCreateNullLecture() 
    {        	
        try {
            lectureDAO.create(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }          
    } 
       
    @Test
    public void testCreateLecture() 
    {        
        lectureDAO.create(lectureA);
        Assert.assertNotNull(lectureDAO.readById(lectureA.getId())); 
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getId(), (Long)1l);
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getName(), "Simon");
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getSurname(), "Hyben");
        lectureDAO.delete(lectureA);
    } 
    
    @Test
    public void testDuplicateId() 
    {        
	lectureDAO.create(lectureA);
	Assert.assertNotNull(lectureDAO.readById(lectureA.getId()));
        try {
            lectureDAO.create(lectureB);
            Assert.fail();
        } catch (javax.persistence.EntityExistsException e) {   
            lectureDAO.delete(lectureA);
        }          
    } 
    
    @Test
    public void testReadById() 
    {        
        lectureDAO.create(lectureA);
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getId(), (Long)1l); 
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getName(), "Simon");
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getSurname(), "Hyben");
        lectureDAO.delete(lectureA);
    }    
    
    @Test
    public void testReadByNullId() 
    {
        try{
            lectureDAO.readById(null);
            Assert.fail();
        } catch (javax.persistence.PersistenceException e) {   
        }                   
    }
    
    @Test
    public void testUpdateLecture() 
    { 
	lectureDAO.create(lectureA);
	lectureA.setSurname("Mover");
	lectureDAO.update(lectureA);
        Assert.assertNotNull(lectureDAO.readById(lectureA.getId())); 
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getId(), (Long)1l);
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getName(), "Simon");
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getSurname(), "Mover");
        lectureDAO.delete(lectureA);
    }   
    
    @Test
    public void testUpdateNullLecture() 
    {
        try{
            lectureDAO.update(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }                   
    }
    
    @Test
    public void testReadByColumn() 
    {        
        lectureDAO.create(lectureA);
	lectureDAO.create(studentC);
        List<Student> tmpRes = lectureDAO.readByColumn("NAME", "Simon");
	Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getName(), "Simon");
        Assert.assertEquals(tmpRes.get(0).getName(), tmpRes.get(1).getName());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
        lectureDAO.delete(lectureA);
        lectureDAO.delete(studentC);
    }
    
    @Test
    public void testDeleteLecture() 
    {        
	lectureDAO.create(lectureA);
	Assert.assertNotNull(lectureDAO.readById(lectureA.getId()));
        lectureDAO.delete(lectureA);
        try {
            em.find(Student.class, lectureA.getId()).getId();
            Assert.fail();
        } catch (NullPointerException e) {   
        }          
    } 
    
    @Test
    public void testDeleteNullLecture() 
    {
        try{
            lectureDAO.delete(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }                   
    }        
        
    @Test
    public void testFindByNameAndSurname() 
    {        
        lectureDAO.create(lectureA);
        studentC.setSurname("Hyben");
        lectureDAO.create(studentC);
        
        List<Student> tmpRes = lectureDAO.findByNameAndSurname("Simon", "Hyben");
	Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getName(), "Simon");
        Assert.assertEquals(tmpRes.get(0).getName(), tmpRes.get(1).getName());
        Assert.assertEquals(tmpRes.get(0).getSurname(), tmpRes.get(1).getSurname());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
        
        lectureDAO.delete(lectureA);
        lectureDAO.delete(studentC);
    }
    
    @Test
    public void testFindByIdNameAndSurname() 
    {        
        lectureDAO.create(lectureA);
        Object tmpObject = lectureDAO.findByIdNameAndSurname(1l, "Simon", "Hyben");
        Student tmpStudent = (Student)tmpObject;
        
        Assert.assertEquals(tmpStudent.getId(), (Long)1l); 
        Assert.assertEquals(tmpStudent.getName(), "Simon");
        Assert.assertEquals(tmpStudent.getSurname(), "Hyben");
        lectureDAO.delete(lectureA);
    } 
}
