package Facade;

import DTO.LecturerDTO;
import java.util.List;
import java.util.Optional;

/**
 * Facade interface for access to lecturers
 * @author Simon Hyben, 421112
 * @since 1.0
 */
public interface LecturerFacadeInterface
{
    /**
     * Finds Lecturer by id
     * @param id of searched lecturer 
     * @return LecturerDTO if exists
     */
    Optional<LecturerDTO> findById(Long id);
    
    /**
     * Registers new lecturer
     * @param lecturer new lecturer  to register
     * @return LecturerDTO
     */
    Optional<LecturerDTO> registerLecturer(LecturerDTO lecturer);

    /**
     * Updates existing lecturer
     * @param lecturer lecturer to update
     * @return LecturerDTO
     */
    Optional<LecturerDTO> updateLecturer(LecturerDTO lecturer);

    /**
     * Removes existing lecturer
     * @param lecturer to be removed
     */
    void removeLecturer(LecturerDTO lecturer);

    /** 
     * Finds lecturer by his full name
     * @param name
     * @param surname
     * @return list of lecturers with matching name
     */
    List<LecturerDTO> filterByName(String name, String surname);
    
    /**
     * Finds and returns all existing lecturers
     * @return list of all lecturers
     */
    List<LecturerDTO> getAllLecturers();

}

