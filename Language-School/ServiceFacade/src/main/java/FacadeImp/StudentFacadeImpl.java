package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.StudentDTO;
import Facade.StudentFacadeInterface;
import ServiceImp.StudentServiceImpl;
import java.util.ArrayList;
import org.muni.fi.pa165.lang_school.entities.Student;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;



/**
 * Facade interface for access to the students
 * @author Richard Zan, 396380
 * @since 1.0
 */
@Service
@Transactional
public class StudentFacadeImpl implements StudentFacadeInterface{
    
    private StudentServiceImpl studentService;
    private BeanMapper mapper;
    
     
    @Inject
    public StudentFacadeImpl(StudentServiceImpl courseService, BeanMapper mapper) {
        this.studentService = courseService;
        this.mapper = mapper;
    }
    
    /**
     * Registers new student
     * @param studentDTO new room
     * @return StudentDTO 
     */
    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        
        Optional<Student> entity =  mapper.mapTo(studentDTO, Student.class);
        Student saved = studentService.addStudent(entity.get());

        return mapper.mapTo(saved, StudentDTO.class).get();
    }

    /**
     * Updates existing student
     * @param studentDTO updated room
     * @return StudentDTO 
     */
    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        
        Optional<Student> entity =  mapper.mapTo(studentDTO, Student.class);
        Student updated = studentService.updateStudent(entity.get());
        
        return  mapper.mapTo(updated, StudentDTO.class).get();
    }

    /**
     * finds student by id
     * @param id unique id
     * @return StudentDTO 
     */
    @Override
    public StudentDTO findById(Long id) {
        return mapper.mapTo(studentService.findById(id), StudentDTO.class).get();
    }

    /**
     * Returns filtered users
     * @param name specifies room criteria
     * @param surname page info
     * @return filtered list of studentsDTO
     */
    @Override
    public List<StudentDTO> filterByNameSurname(String name, String surname) {
        List<Student> students = studentService.findByNameSurname(name, surname);
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for(Student student : students){
            Optional<StudentDTO> studentDTO =  mapper.mapTo(student, StudentDTO.class);
            studentsDTO.add(studentDTO.get());
        }
        return studentsDTO;
    }

    /**
      * Return StudentDTO
      * @param id unique id
      * @param name name of student
      * @param surname surname of student
      * @return StudentDTO
      */
    @Override
    public StudentDTO findByIdNameAndSurname(Long id, String name, String surname) {
        return mapper.mapTo(studentService.findByIdNameAndSurname(id, name, surname), StudentDTO.class).get();
    }
}
