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
     * @return lecturer if exists
     */
    public LecturerDTO findById(Long id);
    
    /**
     * Creates new lecturer
     * @param lecturer new lecturer  to create
     * @return created lecturer
     */
    public LecturerDTO createNewLecturer(LecturerDTO lecturer);

    /**
     * Updates lecturer
     * @param lecturer lecturer to update
     * @return updated lecturer
     */
    public LecturerDTO updateLecturer(LecturerDTO lecturer);

    /**
     * Removes a lecturer
     * @param lecturer to be removed
     */
    public void removeLecturer(LecturerDTO lecturer);

    /** !!!!!
     * Finds lecturer by his full name
     * @param name
     * @param surname
     * @return Lecturer with matching name
     */
    public List<LecturerDTO> findLecturerByName(String name, String surname);
}

