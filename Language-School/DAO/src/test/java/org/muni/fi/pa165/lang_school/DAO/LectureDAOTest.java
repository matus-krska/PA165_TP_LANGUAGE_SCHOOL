package org.muni.fi.pa165.lang_school.DAO;

import org.muni.fi.pa165.lang_school.entities.Lecture;
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
    private LectureDAO lectureDao;

    Lecture dataA;
    Lecture dataB;

    String descriptionUpdate = "English A1 family";

    String dataCodeNoMatch = "EA1F15";
    String dataTopicNoMatch = "Animals";


    @BeforeMethod
    public void createTestingSubject() {
        dataA = new Lecture();
        dataA.setId(1l);
        dataA.setCode("EA1F16");
        dataA.setDescription("English A1 food");
        dataA.setTopic("Food");

        dataB = new Lecture();
        dataB.setId(2l);
        dataB.setCode("EA1T16");
        dataB.setDescription("English A1 travel");
        dataB.setTopic("Travel");
    }

    @Test
    public void testCreateNullCourse() {
        try {
            lectureDao.create(null); //TODO: dodelat odchytavani vyjimek primo do create (a dalsich metod)
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testCreate() {
        lectureDao.create(dataA);
        try {
            Assert.assertNotNull(lectureDao.readById(dataA.getId()));
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getId(), dataA.getId());
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getCode(), dataA.getCode());
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getDescription(), dataA.getDescription());
        } finally {
            lectureDao.delete(dataA);
        }
    }

    @Test
    public void testReadById()
    {
        lectureDao.create(dataA);
        try {
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getId(), dataA.getId());
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getTopic(), dataA.getTopic());
        } finally {
            lectureDao.delete(dataA);
        }
    }

    @Test
    public void testReadByNullId()
    {
        try{
            lectureDao.readById(null);
            Assert.fail();
        } catch (javax.persistence.PersistenceException e) {
        }
    }

    @Test
    public void testUpdateCourse()
    {
        lectureDao.create(dataA);
        try {
            dataA.setDescription(descriptionUpdate);
            lectureDao.update(dataA);
            Assert.assertNotNull(lectureDao.readById(dataA.getId()));
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getId(), dataA.getId());
            Assert.assertEquals(lectureDao.readById(dataA.getId()).getDescription(), descriptionUpdate);
        } finally {
            lectureDao.delete(dataA);
        }
    }

    @Test
    public void testUpdateNullCourse()
    {
        try{
            lectureDao.update(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    //TODO: predelat tuhle divnou konstrukci
    @Test
    public void testDeleteCourse()
    {
        lectureDao.create(dataA);
        try {
            Assert.assertNotNull(lectureDao.readById(dataA.getId()));
        }
        finally {
            lectureDao.delete(dataA);
            try {
                em.find(Lecture.class, dataA.getId()).getId();
                Assert.fail();
            } catch (NullPointerException e) {
            }
        }
    }

    @Test
    public void testDeleteNullCourse()
    {
        try{
            lectureDao.delete(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    //TODO:tohle
    @Test
    public void testReadByColumn()
    {
        lectureDao.create(dataA);
        dataB.setCode(dataA.getCode());
        lectureDao.create(dataB);
        List<Lecture> tmpList = lectureDao.readByColumn("CODE", dataA.getCode());
        Assert.assertEquals(tmpList.size(), 2);
        Assert.assertEquals(tmpList.get(0).getCode(), dataA.getCode());
        Assert.assertEquals(tmpList.get(0).getCode(), tmpList.get(1).getCode());
        Assert.assertNotEquals(tmpList.get(0).getId(), tmpList.get(1).getId());
        lectureDao.delete(dataA);
        lectureDao.delete(dataB);
    }

    @Test
    public void testFindByCodeAndTopicNoMatchCode()
    {
        lectureDao.create(dataA);
        try {
            List<Lecture> tmpList = lectureDao.findByCodeAndTopic(dataCodeNoMatch, dataA.getTopic());
            Assert.assertEquals(tmpList.size(), 0);
        } finally {
            lectureDao.delete(dataA);
        }
    }

    @Test
    public void testFindByCodeAndTopicNoMatchTopic()
    {
        lectureDao.create(dataA);
        try {
            List<Lecture> tmpList = lectureDao.findByCodeAndTopic(dataA.getCode(), dataTopicNoMatch);
            Assert.assertEquals(tmpList.size(), 0);
        } finally {
            lectureDao.delete(dataA);
        }
    }

    @Test
    public void testFindByCodeAndTopicOneMatch()
    {
        lectureDao.create(dataA);
        dataB.setCode(dataA.getCode()); //ciste pro odzkouseni, jestli pri shode jednoho parametru nevrati vsechno
        lectureDao.create(dataB);
        try {
            List<Lecture> tmpList = lectureDao.findByCodeAndTopic(dataA.getCode(), dataA.getTopic());
            Assert.assertEquals(tmpList.size(), 1);
            Assert.assertEquals(tmpList.get(0).getId(), dataA.getId());
            Assert.assertEquals(tmpList.get(0).getCode(), dataA.getCode());
            Assert.assertEquals(tmpList.get(0).getTopic(), dataA.getTopic());
        } finally {
            lectureDao.delete(dataA);
            lectureDao.delete(dataB);
        }
    }

    @Test
    public void testFindByCodeAndTopicMatchMultiple()
    {
        lectureDao.create(dataA);
        //TODO:udelat lepsi sadu testovacich dat, at to takhle sproste neupravuju
        dataB.setCode(dataA.getCode());
        dataB.setTopic(dataA.getTopic());
        lectureDao.create(dataB);
        try {
            List<Lecture> tmpList = lectureDao.findByCodeAndTopic(dataA.getCode(), dataA.getTopic());
            Assert.assertEquals(tmpList.size(), 2);
            Assert.assertEquals(tmpList.get(0).getCode(), dataA.getCode());
            Assert.assertEquals(tmpList.get(1).getCode(), dataA.getCode());
            Assert.assertEquals(tmpList.get(0).getTopic(), dataA.getTopic());
            Assert.assertEquals(tmpList.get(1).getTopic(), dataA.getTopic());
            Assert.assertNotEquals(tmpList.get(0).getId(), tmpList.get(1).getId());
        } finally {
            lectureDao.delete(dataA);
            lectureDao.delete(dataB);
        }
    }


}
