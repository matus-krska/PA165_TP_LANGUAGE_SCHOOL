package FacadeImp;

//import 

import DTO.StudentDTO;
import Facade.StudentFacadeInterface;
import ServiceImp.StudentServiceImpl;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.dozer.Mapper;


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

        Student entity = convert(studentDTO);
        Student saved = hotelService.registerHotel(entity);

        return convert(saved);
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Validate.notNull(user.getId());
        UserEntity entity = this.studentDtoToEntity(user);
        UserEntity updated = userService.updateUser(entity);
        return this.studentToStudentDto(updated);
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        return userService.findByEmail(email).map(this::studentToStudentDto);
    }

    @Override
    public List<StudentDTO> filterByNameSurname(String name, String surname) {
        return userService.findByEmail(email).map(this::studentToStudentDto);
    }

    @Override
    public StudentDTO findByIdNameAndSurname(Long id, String name, String surname) {
        return userService.findByEmail(email).map(this::studentToStudentDto);
    }

    private Student studentDtoToEntity(StudentDTO dto){
        return mapper.map(dto,Student.class);
    }
    private StudentDTO studentToStudentDto(Student entity){
        return mapper.map(entity,StudentDTO.class);
    }
    
}
