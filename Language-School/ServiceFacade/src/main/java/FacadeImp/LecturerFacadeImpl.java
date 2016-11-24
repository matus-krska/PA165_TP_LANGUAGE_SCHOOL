package FacadeImp;

import DTO.LecturerDTO;
import Exceptions.DAOdataAccessException;
import Facade.LecturerFacadeInterface;
import ServiceImp.LecturerServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.dozer.Mapper;
import org.muni.fi.pa165.lang_school.entities.Lecturer;

import javax.inject.Inject;
import org.dozer.DozerBeanMapper;

/**
 * Implementation of facade layer for entity Lecturer
 * @author Simon Hyben, 421112
 * @since 1.0
 */
public class LecturerFacadeImpl implements LecturerFacadeInterface
{
    @Inject
    private LecturerServiceImpl lecturerService;

    @Inject
    private DozerBeanMapper mapper = new DozerBeanMapper();

    @Override
    public LecturerDTO findById(Long id) 
    {
        return lecturerToLecturerDto(lecturerService.findById(id));
    }
    
    @Override
    public LecturerDTO createNewLecturer(LecturerDTO lecturerDTO)
    {
        Validate.isTrue(lecturerDTO.getId() == null);

        Lecturer entity = lecturerDtoToEntity(lecturerDTO);
        Lecturer saved = lecturerService.createLecturer(entity);

        return lecturerToLecturerDto(saved);
    }

    @Override
    public LecturerDTO updateLecturer(LecturerDTO lecturerDTO)
    {
        Validate.notNull(lecturerDTO.getId());
        Lecturer entity = this.lecturerDtoToEntity(lecturerDTO);
        Lecturer updated = lecturerService.updateLecturer(entity);
        return lecturerToLecturerDto(updated);
    }

    @Override
    public void removeLecturer(LecturerDTO lecturerDTO)
    {
        Validate.notNull(lecturerDTO.getId());
        Lecturer entity = this.lecturerDtoToEntity(lecturerDTO);
        lecturerService.removeLecturer(entity);
    }
    
    @Override
    public List<LecturerDTO> findLecturerByName(String name, String surname)
    {
        List<Lecturer> lecturers = lecturerService.findLecturerByName(name, surname);
        List<LecturerDTO> lecturersDTO = new ArrayList<>();
        for(Lecturer lecturer : lecturers){
            LecturerDTO lecturerDTO = lecturerToLecturerDto(lecturer);
            lecturersDTO.add(lecturerDTO);
        }
        return lecturersDTO;
    }
    
    private Lecturer lecturerDtoToEntity(LecturerDTO dto)
    {
        return mapper.map(dto, Lecturer.class);
    }
    
    private LecturerDTO lecturerToLecturerDto(Lecturer entity){
        return mapper.map(entity, LecturerDTO.class);
    }
}
