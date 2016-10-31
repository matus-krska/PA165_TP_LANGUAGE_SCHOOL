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
    Lecture lectureC;


    @BeforeMethod
    public void init() 
    {            
        lectureA = new Lecture();
        lectureA.setId(1L);
        lectureA.setCode("X");
        lectureA.setDescription("Y");
        
        lectureB = new Lecture();
        lectureB.setId(1L);
        lectureB.setCode("Y");
        lectureB.setDescription("X");

        lectureC = new Lecture();
        lectureC.setId(2L);
        lectureC.setCode("A");
        lectureC.setDescription("Y");
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
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getCode(), "X");
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getDescription(), "Y");
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
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getCode(), "X");
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getDescription(), "Y");
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
        lectureA.setDescription("Z");
        lectureDAO.update(lectureA);
        Assert.assertNotNull(lectureDAO.readById(lectureA.getId())); 
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getId(), (Long)1l);
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getCode(), "X");
        Assert.assertEquals(lectureDAO.readById(lectureA.getId()).getDescription(), "Z");
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
	lectureDAO.create(lectureC);
        List<Lecture> tmpRes = lectureDAO.readByColumn("DESCRIPTION", "Y");
	Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getDescription(), "Y");
        Assert.assertEquals(tmpRes.get(0).getDescription(), tmpRes.get(1).getDescription());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
        lectureDAO.delete(lectureA);
        lectureDAO.delete(lectureC);
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
        lectureA.setTopic("T");
        lectureDAO.create(lectureA);
        
        List<Lecture> tmpRes = lectureDAO.findByCodeAndTopic("X","T");
	    Assert.assertEquals(tmpRes.size(), 1);
        Assert.assertEquals(tmpRes.get(0).getCode(), "X");
        Assert.assertEquals(tmpRes.get(0).getTopic(), "T");
        
        lectureDAO.delete(lectureA);
    }
}
