package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.LecturerDTO;
import Facade.LecturerFacadeInterface;
import ServiceImp.LecturerServiceImpl;
import java.util.ArrayList;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
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
    private BeanMapper mapper;
    
     
    @Inject
    public LecturerFacadeImpl(LecturerServiceImpl lecturerService, BeanMapper mapper) {
        this.lecturerService = lecturerService;
        this.mapper = mapper;
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
        
         Optional<Lecturer> entity = mapper.mapTo(lecturerDTO, Lecturer.class);
        Lecturer saved = lecturerService.addLecturer(entity.get());

        return mapper.mapTo(saved, LecturerDTO.class).get();
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
        
        Optional<Lecturer> entity = mapper.mapTo(lecturerDTO, Lecturer.class);
        Lecturer updated = lecturerService.updateLecturer(entity.get());
        
        return mapper.mapTo(updated, LecturerDTO.class).get();
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
        
        return mapper.mapTo(lecturerService.findById(id), LecturerDTO.class).get();
        
    }

    /**
     * Removes lecturer
     * @param lecturerDTO 
     */
    @Override
    public void removeLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
                throw new IllegalArgumentException("LecturerDTO parameter is null");

        Optional<Lecturer> entity = mapper.mapTo(lecturerDTO, Lecturer.class);
        lecturerService.removeLecturer(entity.get());
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
            Optional<LecturerDTO> lecturerDTO = mapper.mapTo(lecturer, LecturerDTO.class);
            lecturersDTO.add(lecturerDTO.get());
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
            //LecturerDTO lecturerDTO = lecturerToLecturerDto(lecturer);
            Optional<LecturerDTO> lecturerDTO = mapper.mapTo(lecturer, LecturerDTO.class);
            lecturersDTO.add(lecturerDTO.get());
        }
        return lecturersDTO;
    }

//    /**
//     * Bean mapping method
//     * @param dto DTO lecturer
//     * @return lecturer entity
//     */
//    private Lecturer lecturerDtoToEntity(LecturerDTO dto){
//        return mapper.map(dto, Lecturer.class);
//    }
//    
//    /**
//     * Bean mapping method
//     * @param entity lecturer entity
//     * @return lecturerDTO
//     */
//    private LecturerDTO lecturerToLecturerDto(Lecturer entity){
//        return mapper.map(entity, LecturerDTO.class);
//    }
    
}
