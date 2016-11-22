package FacadeImp;

//import 

import DTO.StudentDTO;
import Facade.StudentFacadeInterface;
import ServiceImp.StudentServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.muni.fi.pa165.lang_school.entities.Student;

import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;



/**
 * @author Richard Zan, 396380
 * @since 1.0
 */
public class StudentFacadeImpl implements StudentFacadeInterface{
    @Inject
    private StudentServiceImpl studentService;

    @Inject
    private Mapper mapper;
    
    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        Validate.isTrue(studentDTO.getId() == null);

        Student entity = studentDtoToEntity(studentDTO);
        Student saved = studentService.addStudent(entity);

        return studentToStudentDto(saved);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Validate.notNull(studentDTO.getId());
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
