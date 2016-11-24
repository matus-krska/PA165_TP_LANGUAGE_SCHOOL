package FacadeImp;

import org.muni.fi.pa165.lang_school.entities.Student;
import DTO.StudentDTO;
import com.fi.ls.config.BeanMappingConfiguration;
import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.dozer.classmap.DozerClass;
import org.dozer.inject.Inject;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Richard Zan, 396380
 */
public class MapperTest {

    @Autowired
    private Mapper mapper;
    
    @Mock
    Student studentA;
    @Mock
    StudentDTO studentADTO;

    @BeforeClass
    public void init() {
    }

    @Test
    public void testMapStudentToStudentDTO() {

        studentA = new Student();
        
        studentA.setName("Simon");
        studentA.setSurname("Hyben");

        StudentDTO dto = mapper.map(studentA, StudentDTO.class);

        assertEquals(studentA.getName(), dto.getName());
        assertEquals(studentA.getSurname(), dto.getSurname());
    }

    @Test
    public void testMapStudentDTOToStudent() {
        studentADTO = new StudentDTO();
        
        studentADTO.setName("Simon");
        studentADTO.setSurname("Hyben");

        Student entity = mapper.map(studentADTO, Student.class);

        assertEquals(studentADTO.getName(), entity .getName());
        assertEquals(studentADTO.getSurname(), entity .getSurname());
    }

	

}

