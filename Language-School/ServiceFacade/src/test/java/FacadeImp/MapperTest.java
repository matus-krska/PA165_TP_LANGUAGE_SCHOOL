package FacadeImp;

import org.muni.fi.pa165.lang_school.entities.Student;
import DTO.StudentDTO;
import org.dozer.DozerBeanMapper;

import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Richard Zan, 396380
 */
public class MapperTest {

    @Autowired
    private DozerBeanMapper mapper;
    
    @Mock
    Student studentA;
    @Mock
    StudentDTO studentADTO;

    @BeforeClass
    public void init() {
        mapper = new DozerBeanMapper();
        
        studentA = new Student();
        
        studentA.setName("Simon");
        studentA.setSurname("Hyben");
        
        studentADTO = new StudentDTO();
        
        studentADTO.setName("Simon");
        studentADTO.setSurname("Hyben");
    }

    @Test
    public void testMapStudentToStudentDTO() {

        StudentDTO dto = mapper.map(studentA, StudentDTO.class);

        assertEquals(studentA.getName(), dto.getName());
        assertEquals(studentA.getSurname(), dto.getSurname());
    }

    @Test
    public void testMapStudentDTOToStudent() {

        Student entity = mapper.map(studentADTO, Student.class);

        assertEquals(studentADTO.getName(), entity .getName());
        assertEquals(studentADTO.getSurname(), entity .getSurname());
    }

	

}

