package Facade;

import DTO.LecturerLanguageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Facade interface for access to courses
 * @author Matus Krska, 410073
 * @since 1.0
 */
public interface LectureLanguageFacadeInterface
{
    /**
     * Create new course
     * @param course
     * @return
     */
    Optional<LecturerLanguageDTO> createNewLanguage(LecturerLanguageDTO course);

    /**
     * Update course
     * @param course
     * @return
     */
    Optional<LecturerLanguageDTO> updateLanguage(LecturerLanguageDTO course);

    /**
     * Remove course
     * @param course
     */
    void removeLanguage(LecturerLanguageDTO course);

    /**
     * Find course by id
     * @param id
     * @return
     */
    Optional<LecturerLanguageDTO> findById(Long id);

    /**
     * Return list of all existing courses
     * @return
     */
    List<LecturerLanguageDTO> findAllLectureLanguages();
}
