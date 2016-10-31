package org.muni.fi.pa165.lang_school.DAO;

//import java.util.ArrayList;
import java.util.List;
import org.muni.fi.pa165.lang_school.entities.Student;
//import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO.StudentDAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
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
    
    /*
    @Test
    public void testStudentLectures() 
    { 
        Lecture lectureA = new Lecture();
        lectureA.setId(1l);
        lectureA.setCode("PA165");
        
        Lecture lectureB = new Lecture();
        lectureB.setId(2l);
        lectureB.setCode("PA154");
        
        Lecture lectureC = new Lecture();
        lectureC.setId(3l);
        lectureC.setCode("MB103");
        
        List<Lecture> listA = new ArrayList();
        listA.add(lectureA);
        listA.add(lectureB);
        
        List<Lecture> listB = new ArrayList();
        listB.add(lectureC);
        
        studentA.setLecture(listA);
        studentC.setLecture(listB);
        
	studentDAO.create(studentA);
        studentDAO.create(studentC);
        
        Assert.assertNotNull(studentDAO.readById(studentA.getId())); 
        Assert.assertNotNull(studentDAO.readById(studentC.getId()));
        
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getName(), "Simon");
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getSurname(), "Hyben");
        Assert.assertEquals(studentDAO.readById(studentC.getId()).getName(), "Simon");
        Assert.assertEquals(studentDAO.readById(studentC.getId()).getSurname(), "Lehotsky");
        
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getLecture().get(0).getId(), (Long)1l);
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getLecture().get(1).getId(), (Long)2l);
        Assert.assertEquals(studentDAO.readById(studentC.getId()).getLecture().get(0).getId(), (Long)3l);
        
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getLecture().get(0).getCode(), "PA165");
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getLecture().get(1).getCode(), "PA154");
        Assert.assertEquals(studentDAO.readById(studentC.getId()).getLecture().get(0).getCode(), "MB103");
        
        Assert.assertEquals(studentDAO.readById(studentA.getId()).getLecture(), listA);
        Assert.assertEquals(studentDAO.readById(studentC.getId()).getLecture(), listB);
        
        studentDAO.delete(studentA);
        studentDAO.delete(studentC);
    } 
    */
}
