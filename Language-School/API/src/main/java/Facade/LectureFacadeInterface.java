package Facade;

import DTO.LectureDTO;
import DTO.LecturerDTO;

import java.util.List;
import java.util.Optional;

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
    Optional<LectureDTO> createNewLecture(LectureDTO lecture);

    /**
     * Updates lecture
     * @param lecture lecture to update
     * @return updated lecture
     */
    Optional<LectureDTO> updateLecture(LectureDTO lecture);

    /**
     * Return lecture with corresponding id
     * @param id of desired lecture
     * @return
     */
    Optional<LectureDTO> findById(Long id);

    /**
     * Removes a lecture
     * @param lecture to be removed
     */
    void removeLecture(LectureDTO lecture);

    /**
     * Finds lecture by unique code
     * @param code
     * @return
     */
    Optional<LectureDTO> findLectureByCode(String code);

    /**
     * Finds list of lectures which are taught by the lecturer
     * @param lecturer to search for
     * @return list of lectures taught by the lecturer
     */
    List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer);

    /**
     * Changes code of lecture, the code must be unique and unused
     * @param lecture lecture which should be updated
     * @param newCode desired new code
     */
    void changeLectureCode(LectureDTO lecture, String newCode);

    /**
     * Finds all created lectures
     */
    List<LectureDTO> findAllLectures();

    /**
     * Finds a lecture with desired code and topic
     * @param code desired code
     * @param topic desired topic
     * @return lecture with desired parameters
     */
    Optional<LectureDTO> findLectureByCodeAndTopic(String code, String topic);
}
