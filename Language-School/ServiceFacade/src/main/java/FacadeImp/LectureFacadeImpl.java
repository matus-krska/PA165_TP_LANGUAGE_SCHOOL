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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public LectureDTO createNewLecture(LectureDTO lectureDTO)
    {
        if (lectureDTO == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");

        Optional<Lecture> entity = mapper.mapTo(lectureDTO, Lecture.class);
        Lecture saved = lectureService.createLecture(entity.get());

        return mapper.mapTo(saved, LectureDTO.class).get();
    }

    @Override
    public LectureDTO updateLecture(LectureDTO lectureDTO)
    {
        if (lectureDTO == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");

        Optional<Lecture> entity = mapper.mapTo(lectureDTO, Lecture.class);
        Lecture updated = lectureService.updateLecture(entity.get());

        return mapper.mapTo(updated, LectureDTO.class).get();
    }

    @Override
    public LectureDTO findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id parameter is null");

        return mapper.mapTo(lectureService.findById(id), LectureDTO.class).get();

    }

    @Override
    public void removeLecture(LectureDTO lectureDTO)
    {
        if (lectureDTO == null)
            throw new IllegalArgumentException("LectureDTO parameter is null");

        Optional<Lecture> entity = mapper.mapTo(lectureDTO, Lecture.class);
        lectureService.removeLecture(entity.get());
    }

    @Override
    public LectureDTO findLectureByCode(String code)
    {
        if (code == null)
            throw new IllegalArgumentException("Code parameter is null");

        Lecture entity = lectureService.findLectureByCode(code);
        return mapper.mapTo(entity, LectureDTO.class).get();
    }

    @Override
    public List<LectureDTO> findAllLectures()
    {
        List<Lecture> lectures = lectureService.findAllLectures();
        return mapper.mapTo(lectures, LectureDTO.class);
    }

    @Override
    public LectureDTO findLectureByCodeAndTopic(String code, String topic)
    {
        if (code == null || topic == null)
            throw new IllegalArgumentException("Code or topic is null!");

        Lecture lecture = lectureService.findLectureByCodeAndTopic(code, topic);
        return mapper.mapTo(lecture, LectureDTO.class).get();

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

    /*
    private Lecture lectureDtoToEntity(LectureDTO dto)
    {
        return mapper.map(dto,Lecture.class);
    }
    private LectureDTO lectureToLectureDto(Lecture entity){
        return mapper.map(entity,LectureDTO.class);
    }

    private Lecturer lecturerDtoToEntity(LecturerDTO dto)
    {
        return mapper.map(dto, Lecturer.class);
    }

    private LecturerDTO lecturerToLecturerDto(Lecturer entity){
        return mapper.map(entity, LecturerDTO.class);
    }
    */
}
