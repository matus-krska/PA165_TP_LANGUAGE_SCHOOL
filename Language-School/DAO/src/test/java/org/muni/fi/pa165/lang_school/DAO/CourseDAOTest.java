package org.muni.fi.pa165.lang_school.DAO;

import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for CourseDAO
 * Tests CRUD operations
 * 
 * @author Richard Zan, 396380
 * @since 1.0
 */

@ContextConfiguration(classes = org.muni.fi.pa165.lang_school.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourseDAOTest extends AbstractTestNGSpringContextTests{
    @PersistenceContext
    private EntityManager em;

    @Inject
    private CourseDAO courseDAO;

    Course courseA;
    Course courseB;

    @BeforeMethod
    public void createTestingSubject() 
    {             
        courseA = new Course();
        //courseA.setId((Long)165l);
        courseA.setName("PA165");
        courseA.setDescription("JavaEE");
        courseA.setLanguage("English");
        courseA.setLanguage_level("B2");
        courseA.setCreated(new Date());
      
        courseB = new Course();
        //courseB.setId((Long)162l);
        courseB.setName("PB162");
        courseB.setDescription("JavaSE");
        courseB.setLanguage("Czech");
        courseB.setLanguage_level("C2");
        courseB.setCreated(new Date());
       
    }

   @Test
    public void testCreateNullCourse() 
    {        	
        try {
            courseDAO.create(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }          
    } 
       
    @Test
    public void testCreateCourse() 
    {        
        courseDAO.create(courseA);
        Assert.assertNotNull(courseDAO.readById(courseA.getId())); 
        //Assert.assertEquals(courseDAO.readById(courseA.getId()).getId(), (Long)165l);
        Assert.assertEquals(courseDAO.readById(courseA.getId()).getName(), "PA165");
        Assert.assertEquals(courseDAO.readById(courseA.getId()).getDescription(), "JavaEE");
        courseDAO.delete(courseA);
    } 
    
    @Test
    public void testCreateId() 
    {        
	courseDAO.create(courseA);
	Assert.assertNotNull(courseDAO.readById(courseA.getId()));
        /*try {
            courseB.setId((Long)165l);
            courseDAO.create(courseB);
            
            Assert.fail();
        } catch (javax.persistence.EntityExistsException e) {   
            courseDAO.delete(courseA);
        } */         
    } 
    
    @Test
    public void testReadById() 
    {        
        courseDAO.create(courseA);
        //Assert.assertEquals(courseDAO.readById(courseA.getId()).getId(), (Long)165l); 
        Assert.assertEquals(courseDAO.readById(courseA.getId()).getName(), "PA165");
        Assert.assertEquals(courseDAO.readById(courseA.getId()).getDescription(), "JavaEE");
        courseDAO.delete(courseA);
    }    
    
    @Test
    public void testReadByNullId() 
    {
        try{
            courseDAO.readById(null);
            Assert.fail();
        } catch (javax.persistence.PersistenceException e) {   
        }                   
    }
    
    @Test
    public void testUpdateCourse() 
    { 
	courseDAO.create(courseA);
	courseA.setDescription("Mover");
	courseDAO.update(courseA);
        Assert.assertNotNull(courseDAO.readById(courseA.getId())); 
        //Assert.assertEquals(courseDAO.readById(courseA.getId()).getId(), (Long)165l);
        Assert.assertEquals(courseDAO.readById(courseA.getId()).getName(), "PA165");
        Assert.assertEquals(courseDAO.readById(courseA.getId()).getDescription(), "Mover");
        courseDAO.delete(courseA);
    }   
    
    @Test
    public void testUpdateNullCourse() 
    {
        try{
            courseDAO.update(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }                   
    }

    @Test
    public void testDeleteCourse() 
    {        
	courseDAO.create(courseA);
	//Assert.assertNotNull(courseDAO.readById(courseDAO.getId()));
        courseDAO.delete(courseA);
        /*try {
            em.find(Course.class, courseA.getId()).getId();
            Assert.fail();
        } catch (NullPointerException e) {   
        } */         
    } 
    
    @Test
    public void testDeleteNullCourse() 
    {
        try{
            courseDAO.delete(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {   
        }                   
    }        
}
