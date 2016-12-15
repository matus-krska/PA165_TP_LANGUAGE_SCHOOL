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
    Optional<StudentDTO> registerStudent(StudentDTO studentDTO);

    /**
     * Updates existing student
     * @param studentDTO updated room
     * @return StudentDTO 
     */
    Optional<StudentDTO> updateStudent(StudentDTO studentDTO);

    /**
     * Removes existing student
     * @param studentDTO - student to be removed
     * @return true, if successfully removed
     */
    void removeStudent(StudentDTO studentDTO);

    /**
     * finds student by id
     * @param id unique id
     * @return StudentDTO 
     */
    Optional<StudentDTO> findById(Long id);

    /**
     * Returns filtered users
     * @param name specifies room criteria
     * @param surname page info
     * @return filtered list of studentsDTO
     */
    List<StudentDTO> filterByNameSurname(String name, String surname) ;

    /**
     * Returns all existing students
     * @return list of all existing students
     */
    List<StudentDTO> findAllStudents();

}
