package FacadeImp;

import DTO.StudentDTO;
import Facade.StudentFacadeInterface;
import ServiceImp.StudentServiceImpl;
import java.util.ArrayList;
import org.muni.fi.pa165.lang_school.entities.Student;
import javax.inject.Inject;
import java.util.List;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;



/**
 * @author Richard Zan, 396380
 * @since 1.0
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacadeInterface{
    
    private StudentServiceImpl studentService;
    private DozerBeanMapper mapper = new DozerBeanMapper();
    
    @Inject
    public StudentFacadeImpl(StudentServiceImpl courseService) {
        this.studentService = courseService;
    }
    
    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        
        Student entity = studentDtoToEntity(studentDTO);
        Student saved = studentService.addStudent(entity);

        return studentToStudentDto(saved);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        
        Student entity = this.studentDtoToEntity(studentDTO);
        Student updated = studentService.updateUser(entity);
        
        return this.studentToStudentDto(updated);
    }

    @Override
    public StudentDTO findById(Long id) {
        return studentToStudentDto(studentService.findById(id));
    }

    @Override
    public List<StudentDTO> filterByNameSurname(String name, String surname) {
        List<Student> students = studentService.findByNameSurname(name, surname);
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for(Student student : students){
            StudentDTO studentDTO = studentToStudentDto(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }

    @Override
    public StudentDTO findByIdNameAndSurname(Long id, String name, String surname) {
        return studentToStudentDto(studentService.findByIdNameAndSurname(id, name, surname));
    }

    private Student studentDtoToEntity(StudentDTO dto){
        return mapper.map(dto,Student.class);
    }
    private StudentDTO studentToStudentDto(Student entity){
        return mapper.map(entity,StudentDTO.class);
    }
    
}
