package Facade;

import DTO.LectureDTO;
import DTO.LecturerDTO;

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
     * Return lecture with corresponding id
     * @param id of desired lecture
     * @return
     */
    public LectureDTO findById(Long id);

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

    /**
     * Finds list of lectures which are taught by the lecturer
     * @param lecturer to search for
     * @return list of lectures taught by the lecturer
     */
    public List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer);

    /**
     * Changes code of lecture, the code must be unique and unused
     * @param lecture lecture which should be updated
     * @param newCode desired new code
     */
    public void changeLectureCode(LectureDTO lecture, String newCode);

    /**
     * Finds all created lectures
     */
    public List<LectureDTO> findAllLectures();

    /**
     * Finds a lecture with desired code and topic
     * @param code desired code
     * @param topic desired topic
     * @return lecture with desired parameters
     */
    public LectureDTO findLectureByCodeAndTopic(String code, String topic);
}
