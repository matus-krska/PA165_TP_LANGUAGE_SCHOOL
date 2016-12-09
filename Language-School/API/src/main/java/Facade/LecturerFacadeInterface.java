package Facade;

import DTO.LecturerDTO;
import java.util.List;

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
    public LecturerDTO findById(Long id);
    
    /**
     * Registers new lecturer
     * @param lecturer new lecturer  to register
     * @return LecturerDTO
     */
    public LecturerDTO registerLecturer(LecturerDTO lecturer);

    /**
     * Updates existing lecturer
     * @param lecturer lecturer to update
     * @return LecturerDTO
     */
    public LecturerDTO updateLecturer(LecturerDTO lecturer);

    /**
     * Removes a lecturer
     * @param lecturer to be removed
     */
    public void removeLecturer(LecturerDTO lecturer);

    /** 
     * Finds lecturer by his full name
     * @param name
     * @param surname
     * @return list of lecturers with matching name
     */
    public List<LecturerDTO> filterByName(String name, String surname);
    
    /**
     * Finds and returns all existing lecturers
     * @return list of all lecturers
     */
    public List<LecturerDTO> getAllLecturers();

}

