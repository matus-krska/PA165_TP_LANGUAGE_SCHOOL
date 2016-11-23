package FacadeImp;

import DTO.LectureDTO;
import Exceptions.CodeUsedException;
import Facade.LectureFacadeInterface;
import ServiceImp.LectureServiceImpl;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.muni.fi.pa165.lang_school.entities.Lecture;

import javax.inject.Inject;

/**
 * Implementation of facade layer for entity Lecture
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class LectureFacadeImpl implements LectureFacadeInterface
{
    @Inject
    private LectureServiceImpl lectureService;

    @Inject
    private Mapper mapper;


    @Override
    public LectureDTO createNewLecture(LectureDTO lectureDTO)
    {
        Validate.isTrue(lectureDTO.getId() == null);

        Lecture entity = lectureDtoToEntity(lectureDTO);
        Lecture saved = lectureService.createLecture(entity);

        return lectureToLectureDto(saved);
    }

    @Override
    public LectureDTO updateLecture(LectureDTO lectureDTO)
    {
        Validate.notNull(lectureDTO.getId());
        Lecture entity = this.lectureDtoToEntity(lectureDTO);
        Lecture updated = lectureService.updateLecture(entity);
        return lectureToLectureDto(updated);
    }

    @Override
    public void removeLecture(LectureDTO lectureDTO)
    {
        Validate.notNull(lectureDTO.getId());
        Lecture entity = this.lectureDtoToEntity(lectureDTO);
        lectureService.removeLecture(entity);
    }

    @Override
    public LectureDTO findLectureByCode(String code)
    {
        Validate.notEmpty(code);
        Lecture entity = lectureService.findLectureByCode(code);
        return lectureToLectureDto(entity);
    }

    //TODO public List<LectureDTO> findLecturesByLecturer(LecturerDTO lecturer);

    @Override
    public void changeLectureCode(LectureDTO lectureDTO, String newCode) throws CodeUsedException
    {
        Validate.notEmpty(newCode);
        Validate.notNull(lectureDTO.getId());
        if(lectureDTO.getCode().equals(newCode))
        {
            return;
        }
        LectureDTO lecture = findLectureByCode(newCode);
        if(lecture != null)
        {
            throw new CodeUsedException("Code is alrady used.");
        }
        else
        {
            lectureDTO.setCode(newCode);
            updateLecture(lectureDTO);
        }
    }

    //TODO public List<LecturerDTO> getAvailableLecturersForLecture(LectureDTO lecture);

    private Lecture lectureDtoToEntity(LectureDTO dto)
    {
        return mapper.map(dto,Lecture.class);
    }
    private LectureDTO lectureToLectureDto(Lecture entity){
        return mapper.map(entity,LectureDTO.class);
    }
}
