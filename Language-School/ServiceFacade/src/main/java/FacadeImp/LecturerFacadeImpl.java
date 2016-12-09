package FacadeImp;

import DTO.LecturerDTO;
import Facade.LecturerFacadeInterface;
import ServiceImp.LecturerServiceImpl;
import java.util.ArrayList;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import javax.inject.Inject;
import java.util.List;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;



/**
 * Facade interface for access to the lecturers
 * @author Simon Hyben
 * @since 1.0
 */
@Service
@Transactional
public class LecturerFacadeImpl implements LecturerFacadeInterface {
    
    private LecturerServiceImpl lecturerService;
    private DozerBeanMapper mapper = new DozerBeanMapper();
    
     
    @Inject
    public LecturerFacadeImpl(LecturerServiceImpl lecturerService) {
        this.lecturerService = lecturerService;
    }
    
    /**
     * Registers new lecturer
     * @param lecturerDTO new room
     * @return LecturerDTO 
     */
    @Override
    public LecturerDTO registerLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
                throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Lecturer entity = lecturerDtoToEntity(lecturerDTO);
        Lecturer saved = lecturerService.addLecturer(entity);

        return lecturerToLecturerDto(saved);
    }

    /**
     * Updates existing lecturer
     * @param lecturerDTO updated room
     * @return LecturerDTO 
     */
    @Override
    public LecturerDTO updateLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
                throw new IllegalArgumentException("LecturerDTO parameter is null");
        
        Lecturer entity = this.lecturerDtoToEntity(lecturerDTO);
        Lecturer updated = lecturerService.updateLecturer(entity);
        
        return this.lecturerToLecturerDto(updated);
    }

    /**
     * Finds lecturer by id
     * @param id unique id
     * @return LecturerDTO 
     */
    @Override
    public LecturerDTO findById(Long id) {
        if (id == null)
                throw new IllegalArgumentException("Id parameter is null");
        
        return lecturerToLecturerDto(lecturerService.findById(id));
    }

    /**
     * Removes lecturer
     * @param lecturerDTO 
     */
    @Override
    public void removeLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
                throw new IllegalArgumentException("LecturerDTO parameter is null");
        Lecturer entity = this.lecturerDtoToEntity(lecturerDTO);
        lecturerService.removeLecturer(entity);
    }
    
    /**
     * Finds and returns all existing lecturers
     * @return list of all lecturers
     */
    @Override
    public List<LecturerDTO> getAllLecturers() {
        List<Lecturer> lecturers = lecturerService.findAllLecturers();
        List<LecturerDTO> lecturersDTO = new ArrayList<>();
        for(Lecturer lecturer : lecturers){
            LecturerDTO lecturerDTO = lecturerToLecturerDto(lecturer);
            lecturersDTO.add(lecturerDTO);
        }
        return lecturersDTO;
    }
        
    /**
     * Returns filtered users
     * @param name specifies room criteria
     * @param surname page info
     * @return filtered list of LecturerDTO
     */
    @Override
    public List<LecturerDTO> filterByName(String name, String surname) {
        if (name == null || surname == null)
            throw new IllegalArgumentException("Name or surname is null!");
        
        List<Lecturer> lecturers = lecturerService.findByName(name, surname);
        List<LecturerDTO> lecturersDTO = new ArrayList<>();
        for(Lecturer lecturer : lecturers){
            LecturerDTO lecturerDTO = lecturerToLecturerDto(lecturer);
            lecturersDTO.add(lecturerDTO);
        }
        return lecturersDTO;
    }

    /**
     * Bean mapping method
     * @param dto DTO lecturer
     * @return lecturer entity
     */
    private Lecturer lecturerDtoToEntity(LecturerDTO dto){
        return mapper.map(dto, Lecturer.class);
    }
    
    /**
     * Bean mapping method
     * @param entity lecturer entity
     * @return lecturerDTO
     */
    private LecturerDTO lecturerToLecturerDto(Lecturer entity){
        return mapper.map(entity, LecturerDTO.class);
    }
    
}
