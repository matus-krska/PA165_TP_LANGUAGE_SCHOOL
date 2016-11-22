package Facade;

import DTO.StudentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Facade interface for access to the students
 * @author Richard Zan, 396380
 * @since 1.0
 */
public interface StudentFacadeInterface {
   

    /**
     * Registers new student
     * @param studentDTO new room
     * @return StudentDTO 
     */
    public StudentDTO registerStudent(StudentDTO studentDTO);

    /**
     * Updates existing student
     * @param studentDTO updated room
     * @return StudentDTO 
     */
    public StudentDTO updateStudent(StudentDTO studentDTO);

    /**
     * finds student by id
     * @param id unique id
     * @return StudentDTO 
     */
    public Optional<StudentDTO> findById(Long id);

    /**
     * Returns filtered users
     * @param name specifies room criteria
     * @param surname page info
     * @return filtered list of studentsDTO
     */
    public List<StudentDTO> filterByNameSurname(String name, String surname) ;
    
     /**
      * Return StudentDTO
      * @param id unique id
      * @param name name of student
      * @param surname surname of student
      * @return StudentDTO
      */
    public StudentDTO findByIdNameAndSurname(Long id, String name, String surname);
}
