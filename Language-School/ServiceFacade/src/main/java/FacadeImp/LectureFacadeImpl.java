package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.LectureDTO;
import DTO.LecturerDTO;
import Exceptions.DAOdataAccessException;
import Facade.LectureFacadeInterface;
import ServiceImp.LectureServiceImpl;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
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
public class LectureFacadeImpl implements LectureFacadeInterface
{
    private LectureServiceImpl lectureService;

    private BeanMapper mapper;

    @Inject
    public LectureFacadeImpl(LectureServiceImpl lectureService, BeanMapper mapper)
    {
        this.lectureService = lectureService;
        this.mapper = mapper;
    }

    @Override
    public Optional<LectureDTO> createNewLecture(LectureDTO lecture) {

        if (lecture == null)
            throw new IllegalArgumentException("Param can not be null!");

        try {
            Optional<Lecture> entity = Optional.ofNullable(lectureService.createLecture(mapper.mapTo(lecture, Lecture.class).get()));
            Lecture lectureNew = lectureService.createLecture(entity.get());
            return mapper.mapTo(lectureNew, LectureDTO.class);

        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<LectureDTO> updateLecture(LectureDTO lectureDTO) {
        if (lectureDTO == null)
            throw new IllegalArgumentException("LectureDTO is null");

        Optional<Lecture> entity = mapper.mapTo(lectureDTO, Lecture.class);
        try {
            Lecture updated = lectureService.updateLecture(entity.get());
            return mapper.mapTo(updated, LectureDTO.class);
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<LectureDTO> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id parameter is null");
        try {
            Lecture entity = lectureService.findById(id);
            Optional<LectureDTO> dto = mapper.mapTo(entity, LectureDTO.class);
            return dto;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void removeLecture(LectureDTO lectureDTO)
    {
        if (lectureDTO == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");

        Optional<Lecture> entity = mapper.mapTo(lectureDTO, Lecture.class);
        try {
            lectureService.removeLecture(entity.get());
        } catch (NoSuchElementException ex) {
            return;
        }
    }

    @Override
    public Optional<LectureDTO> findLectureByCode(String code)
    {
        if (code == null)
            throw new IllegalArgumentException("Code parameter is null");
        try {
            Lecture entity = lectureService.findLectureByCode(code);
            Optional<LectureDTO> dto = mapper.mapTo(entity, LectureDTO.class);
            return dto;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<LectureDTO> findAllLectures()
    {
        try {
            List<Lecture> entities = lectureService.findAllLectures();
            return mapper.mapTo(entities, LectureDTO.class);
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<LectureDTO> findLectureByCodeAndTopic(String code, String topic)
    {
        if (code == null || topic == null)
            throw new IllegalArgumentException("Code parameter is null");
        try {
            Lecture entity = lectureService.findLectureByCodeAndTopic(code, topic);
            Optional<LectureDTO> dto = mapper.mapTo(entity, LectureDTO.class);
            return dto;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer)
    {
        if(lecturer == null || lecturer.getId() == null)
        {
            throw new IllegalArgumentException("Error finding lecture by lecturer which is null");
        }

        List<LectureDTO> dtoLectures = new ArrayList<>();
        List<Lecture> lectures = lectureService.findLecturesByLecturer(mapper.mapTo(lecturer,Lecturer.class).get());
        for(Lecture lecture : lectures)
        {
            dtoLectures.add(mapper.mapTo(lecture,LectureDTO.class).get());
        }
        return dtoLectures;
    }

    @Override
    public void changeLectureCode(LectureDTO lectureDTO, String newCode) throws DAOdataAccessException
    {
        if (lectureDTO == null || newCode == null)
            throw new IllegalArgumentException("LectureDTO  or newCode parameter is null");


        lectureService.changeLectureCode(mapper.mapTo(lectureDTO, Lecture.class).get(),newCode);
    }
}
