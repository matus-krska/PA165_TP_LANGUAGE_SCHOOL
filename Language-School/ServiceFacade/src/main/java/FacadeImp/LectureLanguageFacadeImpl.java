package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.LectureDTO;
import DTO.LecturerDTO;
import DTO.LecturerLanguageDTO;
import Facade.LectureLanguageFacadeInterface;
import ServiceImp.LectureLanguageServiceImpl;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.muni.fi.pa165.lang_school.entities.LectureLanguage;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Implementation of facade layer for entity Lecture
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Service
@Transactional
public class LectureLanguageFacadeImpl implements LectureLanguageFacadeInterface
{
    private LectureLanguageServiceImpl lectureLanguageService;

    private BeanMapper mapper;

    @Inject
    public LectureLanguageFacadeImpl(LectureLanguageServiceImpl lectureLanguageService, BeanMapper mapper)
    {
        this.lectureLanguageService = lectureLanguageService;
        this.mapper = mapper;
    }



    @Override
    public Optional<LecturerLanguageDTO> createNewLanguage(LecturerLanguageDTO lecturerLanguageDTO) {
        if (lecturerLanguageDTO == null)
            throw new IllegalArgumentException("Param can not be null!");

        try {
            Optional<LectureLanguage> entity = Optional.ofNullable(lectureLanguageService.createLectureLanguage(mapper.mapTo(lecturerLanguageDTO, LectureLanguage.class).get()));
            LectureLanguage lectureNew = lectureLanguageService.createLectureLanguage(entity.get());
            return mapper.mapTo(lectureNew, LecturerLanguageDTO.class);

        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<LecturerLanguageDTO> updateLanguage(LecturerLanguageDTO lecturerLanguageDTO) {
        if (lecturerLanguageDTO == null)
            throw new IllegalArgumentException("LectureDTO is null");

        Optional<LectureLanguage> entity = mapper.mapTo(lecturerLanguageDTO, LectureLanguage.class);
        try {
            LectureLanguage updated = lectureLanguageService.updateLectureLanguage(entity.get());
            return mapper.mapTo(updated, LecturerLanguageDTO.class);
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void removeLanguage(LecturerLanguageDTO lectureLanguageDTO) {
        if (lectureLanguageDTO == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");

        Optional<LectureLanguage> entity = mapper.mapTo(lectureLanguageDTO, LectureLanguage.class);
        try {
            lectureLanguageService.removeLectureLanguage(entity.get());
        } catch (NoSuchElementException ex) {
            return;
        }
    }

    @Override
    public Optional<LecturerLanguageDTO> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id is null");

        try {
            LectureLanguage entity = lectureLanguageService.findById(id);
            Optional<LecturerLanguageDTO> lecturerLangugeDTO = mapper.mapTo(entity, LecturerLanguageDTO.class);
            return lecturerLangugeDTO;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }


    @Override
    public List<LecturerLanguageDTO> findAllLectureLanguages() {
        try {
            List<LectureLanguage> entities = lectureLanguageService.findAllLectureLanguages();
            return mapper.mapTo(entities, LecturerLanguageDTO.class);
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
    }


}
