package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.StudentDTO;
import Facade.StudentFacadeInterface;
import ServiceImp.StudentServiceImpl;

import java.util.*;

import org.muni.fi.pa165.lang_school.entities.Student;
import javax.inject.Inject;
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
    public Optional<StudentDTO> registerStudent(StudentDTO studentDTO) {
        if (studentDTO == null)
            throw new IllegalArgumentException("Student is null");
        try {
            Optional<Student> student = Optional.ofNullable(studentService.addStudent(mapper.mapTo(studentDTO, Student.class).get()));
            return student.isPresent() ? mapper.mapTo(student.get(), StudentDTO.class) : Optional.empty();
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    /**
     * Updates existing student
     * @param studentDTO updated room
     * @return StudentDTO 
     */
    @Override
    public Optional<StudentDTO> updateStudent(StudentDTO studentDTO) {
        if (studentDTO == null)
            throw new IllegalArgumentException("Student is null");
        try {
            Optional<Student> student = Optional.ofNullable(studentService.updateStudent(mapper.mapTo(studentDTO, Student.class).get()));
            return student.isPresent() ? mapper.mapTo(student.get(), StudentDTO.class) : Optional.empty();
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }


    public void removeStudent(StudentDTO studentDTO) {
        if (studentDTO == null)
            throw new IllegalArgumentException("Student is null");
        try {
            this.studentService.removeStudent(studentService.findById(studentDTO.getId()));
            return;
        } catch (NoSuchElementException ex) {
            return;
        }
    }

    /**
     * Finds existing student by id
     * @param id unique id
     * @return StudentDTO 
     */
    @Override
    public Optional<StudentDTO> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id is null");
        try {
            Optional<Student> student = Optional.ofNullable(studentService.findById(id));
            return student.isPresent() ? mapper.mapTo(student.get(), StudentDTO.class) : Optional.empty();
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
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

    @Override
    public List<StudentDTO> findAllStudents() {
        try {
            return mapper.mapTo(studentService.findAllStudents(), StudentDTO.class);
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
    }
}
