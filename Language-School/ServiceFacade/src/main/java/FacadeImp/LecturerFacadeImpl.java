package FacadeImp;

import ConfigMapper.BeanMapper;
import DTO.LecturerDTO;
import Facade.LecturerFacadeInterface;
import ServiceImp.LecturerServiceImpl;

import java.util.*;

import org.muni.fi.pa165.lang_school.entities.Lecturer;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Optional<LecturerDTO> registerLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
            throw new IllegalArgumentException("LecturerDTO is null");

        Optional<Lecturer> entity = mapper.mapTo(lecturerDTO, Lecturer.class);
        try {
            Lecturer created = lecturerService.addLecturer(entity.get());
            return mapper.mapTo(created, LecturerDTO.class);
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    /**
     * Updates existing lecturer
     * @param lecturerDTO updated room
     * @return LecturerDTO 
     */
    @Override
    public Optional<LecturerDTO> updateLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
            throw new IllegalArgumentException("LecturerDTO is null");

        Optional<Lecturer> entity = mapper.mapTo(lecturerDTO, Lecturer.class);
        try {
            Lecturer updated = lecturerService.updateLecturer(entity.get());
            return mapper.mapTo(updated, LecturerDTO.class);
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    /**
     * Finds lecturer by id
     * @param id unique id
     * @return LecturerDTO 
     */
    @Override
    public Optional<LecturerDTO> findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id is null");

        try {
            Lecturer entity = lecturerService.findById(id);
            Optional<LecturerDTO> lecturerDTO = mapper.mapTo(entity, LecturerDTO.class);
            return lecturerDTO;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    /**
     * Removes lecturer
     * @param lecturerDTO 
     */
    @Override
    public void removeLecturer(LecturerDTO lecturerDTO) {
        if (lecturerDTO == null)
                throw new IllegalArgumentException("LecturerDTO is null");

        Optional<Lecturer> entity = mapper.mapTo(lecturerDTO, Lecturer.class);
        try {
            lecturerService.removeLecturer(entity.get());
            return;
        } catch (NoSuchElementException ex) {
            return;
        }
    }
    
    /**
     * Finds and returns all existing lecturers
     * @return list of all lecturers
     */
    @Override
    public List<LecturerDTO> getAllLecturers() {
        try {
            List<Lecturer> entities = lecturerService.findAllLecturers();
            return mapper.mapTo(entities, LecturerDTO.class);
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
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
        try {
            List<Lecturer> lecturers = lecturerService.findAllLecturers();
            return mapper.mapTo(lecturers, LecturerDTO.class);
        } catch (NoSuchElementException ex) {
            return Collections.emptyList();
        }
    }

    
}
