package FacadeImp;

import DTO.LectureDTO;
import DTO.LecturerDTO;
import Exceptions.DAOdataAccessException;
import Facade.LectureFacadeInterface;
import ServiceImp.LectureServiceImpl;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.muni.fi.pa165.lang_school.entities.Lecture;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Inject
    private DozerBeanMapper mapper = new DozerBeanMapper();

    @Inject
    public LectureFacadeImpl(LectureServiceImpl lectureService)
    {
        this.lectureService = lectureService;
    }

    @Override
    public LectureDTO createNewLecture(LectureDTO lectureDTO)
    {
        Lecture entity = lectureDtoToEntity(lectureDTO);
        Lecture saved = lectureService.createLecture(entity);

        return lectureToLectureDto(saved);
    }

    @Override
    public LectureDTO updateLecture(LectureDTO lectureDTO)
    {
        Lecture entity = this.lectureDtoToEntity(lectureDTO);
        Lecture updated = lectureService.updateLecture(entity);
        return lectureToLectureDto(updated);
    }

    @Override
    public void removeLecture(LectureDTO lectureDTO)
    {
        Lecture entity = this.lectureDtoToEntity(lectureDTO);
        lectureService.removeLecture(entity);
    }

    @Override
    public LectureDTO findLectureByCode(String code)
    {
        Lecture entity = lectureService.findLectureByCode(code);
        return lectureToLectureDto(entity);
    }

    @Override
    public List<LectureDTO> findAllLectures()
    {
        List<LectureDTO> dtoLectures = new ArrayList<>();
        List<Lecture> lectures = lectureService.findAllLectures();
        for(Lecture lecture : lectures)
        {
            dtoLectures.add(lectureToLectureDto(lecture));
        }
        return dtoLectures;
    }

    @Override
    public LectureDTO findLectureByCodeAndTopic(String code, String topic)
    {
        Lecture lecture = lectureService.findLectureByCodeAndTopic(code, topic);
        return lectureToLectureDto(lecture);
    }

    @Override
    public List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer)
    {
        List<LectureDTO> dtoLectures = new ArrayList<>();
        List<Lecture> lectures = lectureService.findLecturesByLecturer(lecturerDtoToEntity(lecturer));
        for(Lecture lecture : lectures)
        {
            dtoLectures.add(lectureToLectureDto(lecture));
        }
        return dtoLectures;
    }

    @Override
    public void changeLectureCode(LectureDTO lectureDTO, String newCode) throws DAOdataAccessException
    {
        LectureDTO lecture = lectureToLectureDto(lectureService.changeLectureCode(lectureDtoToEntity(lectureDTO),newCode));
    }

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
}
