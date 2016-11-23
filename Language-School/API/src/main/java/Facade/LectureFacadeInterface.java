package Facade;

import DTO.LectureDTO;
import Exceptions.CodeUsedException;

import java.util.List;

/**
 * Facade interface for access to lectures
 * @author Matus Krska, 410073
 * @since 1.0
 */
public interface LectureFacadeInterface
{
    /**
     * Creates new lecture
     * @param lecture new lecture  to create
     * @return created lecture
     */
    public LectureDTO createNewLecture(LectureDTO lecture);

    /**
     * Updates lecture
     * @param lecture lecture to update
     * @return updated lecture
     */
    public LectureDTO updateLecture(LectureDTO lecture);

    /**
     * Removes a lecture
     * @param lecture to be removed
     */
    public void removeLecture(LectureDTO lecture);

    /**
     * Finds lecture by unique code
     * @param code
     * @return
     */
    public LectureDTO findLectureByCode(String code);

    //TODO public List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer);

    /**
     * Changes code of lecture, the code must be unique and unused
     * @param lecture lecture which should be updated
     * @param newCode desired new code
     */
    public void changeLectureCode(LectureDTO lecture, String newCode) throws CodeUsedException;

    //TODO public List<LecturerDTO> getAvailableLecturersForLecture(LectureDTO lecture);
}
