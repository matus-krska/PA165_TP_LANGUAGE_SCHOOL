package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO.LecturerDAO;
import org.muni.fi.pa165.lang_school.org.muni.fi.pa165.lang_school.DAO.StudentDAO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Test class for LecturerDAO
 * Tests CRUD operations
 *
 * @author Matus Krska, 410073
 * @since 1.0
 */

@ContextConfiguration(classes = org.muni.fi.pa165.lang_school.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class LecturerDAOTest extends AbstractTestNGSpringContextTests
{
    @PersistenceContext
    private EntityManager em;

    @Inject
    private LecturerDAO lecturerDAO;

    private Lecturer lecturerA;
    private Lecturer lecturerB;
    private Lecturer lecturerC;

    @BeforeMethod
    public void init()
    {
        lecturerA = new Lecturer();
        lecturerA.setId(1L);
        lecturerA.setName("Lecturer");
        lecturerA.setSurname("One");

        lecturerB = new Lecturer();
        lecturerB.setId(1L);
        lecturerB.setName("Lecturer");
        lecturerB.setSurname("Duplicit");

        lecturerC = new Lecturer();
        lecturerC.setId(2L);
        lecturerC.setName("Lecturer");
        lecturerC.setSurname("Two");
    }

    @Test
    public void testCreateNullLecturer()
    {
        try {
            lecturerDAO.create(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testCreateLecturer()
    {
        lecturerDAO.create(lecturerA);
        Assert.assertNotNull(lecturerDAO.readById(lecturerA.getId()));
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getId(), (Long)1l);
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getName(), "Lecturer");
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getSurname(), "One");
        lecturerDAO.delete(lecturerA);
    }

    @Test
    public void testDuplicateId()
    {
        lecturerDAO.create(lecturerA);
        Assert.assertNotNull(lecturerDAO.readById(lecturerA.getId()));
        try {
            lecturerDAO.create(lecturerB);
            Assert.fail();
        } catch (javax.persistence.EntityExistsException e) {
            lecturerDAO.delete(lecturerA);
        }
    }

    @Test
    public void testReadById()
    {
        lecturerDAO.create(lecturerA);
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getId(), (Long)1l);
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getName(), "Lecturer");
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getSurname(), "One");
        lecturerDAO.delete(lecturerA);
    }

    @Test
    public void testReadByNullId()
    {
        try{
            lecturerDAO.readById(null);
            Assert.fail();
        } catch (javax.persistence.PersistenceException e) {
        }
    }

    @Test
    public void testUpdateLecturer()
    {
        lecturerDAO.create(lecturerA);
        lecturerA.setSurname("Updated");
        lecturerDAO.update(lecturerA);
        Assert.assertNotNull(lecturerDAO.readById(lecturerA.getId()));
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getId(), (Long)1l);
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getName(), "Lecturer");
        Assert.assertEquals(lecturerDAO.readById(lecturerA.getId()).getSurname(), "Updated");
        lecturerDAO.delete(lecturerA);
    }

    @Test
    public void testUpdateNullLecturer()
    {
        try{
            lecturerDAO.update(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testReadByColumn()
    {
        lecturerDAO.create(lecturerA);
        lecturerDAO.create(lecturerC);
        List<Lecturer> tmpRes = lecturerDAO.readByColumn("NAME", "Lecturer");
        Assert.assertEquals(tmpRes.size(), 2);
        Assert.assertEquals(tmpRes.get(0).getName(), "Lecturer");
        Assert.assertEquals(tmpRes.get(0).getName(), tmpRes.get(1).getName());
        Assert.assertNotEquals(tmpRes.get(0).getId(), tmpRes.get(1).getId());
        lecturerDAO.delete(lecturerA);
        lecturerDAO.delete(lecturerC);
    }

    @Test
    public void testDeleteLecturer()
    {
        lecturerDAO.create(lecturerA);
        Assert.assertNotNull(lecturerDAO.readById(lecturerA.getId()));
        lecturerDAO.delete(lecturerA);
        try {
            em.find(Student.class, lecturerA.getId()).getId();
            Assert.fail();
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testDeleteNullLecturer()
    {
        try{
            lecturerDAO.delete(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testFindByName()
    {
        lecturerDAO.create(lecturerA);
        lecturerDAO.create(lecturerC);

        List<Lecturer> tmpRes = lecturerDAO.findByName("Lecturer", "One");
        Assert.assertEquals(tmpRes.size(), 1);
        Assert.assertEquals(tmpRes.get(0).getName(), "Lecturer");

        lecturerDAO.delete(lecturerA);
        lecturerDAO.delete(lecturerC);
    }
}
