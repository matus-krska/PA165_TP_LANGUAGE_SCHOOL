package FacadeImp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import DTO.StudentDTO;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.dozer.Mapper;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


/**
 *
 * @author Richard Zan, 396380
 */
@ContextConfiguration(classes = org.muni.fi.pa165.lang_school.ApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MapperTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private Mapper mapper;
    
    @Inject
    Student studentA;
    @Inject
    StudentDTO studentADTO;

    @BeforeClass
    public void init() {
    }

    @Test
    public void testMapStudentToStudentDTO() {

        Student studentA = new Student();
        studentA.setId(1l);
        studentA.setName("Simon");
        studentA.setSurname("Hyben");

        StudentDTO dto = mapper.map(studentA, StudentDTO.class);

        assertEquals(studentA.getId(), dto.getId());
        assertEquals(studentA.getName(), dto.getName());
        assertEquals(studentA.getSurname(), dto.getSurname());
    }

    @Test
    public void testMapStudentDTOToStudent() {
        StudentDTO studentADTO = new StudentDTO();
        studentADTO.setId(1l);
        studentADTO.setName("Simon");
        studentADTO.setSurname("Hyben");

        Student entity = mapper.map(studentADTO, Student.class);

        assertEquals(studentADTO.getId(), entity .getId());
        assertEquals(studentADTO.getName(), entity .getName());
        assertEquals(studentADTO.getSurname(), entity .getSurname());
    }

	

}

