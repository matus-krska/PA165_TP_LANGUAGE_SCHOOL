package ServiceImp;

import Exceptions.DAOdataAccessException;
import java.util.List;
import javax.inject.Inject;
import org.muni.fi.pa165.lang_school.DAO.LecturerDAO;
import org.muni.fi.pa165.lang_school.entities.Lecturer;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Lecturer facade
 * 
 * @author Simon Hyben, 421112
 * @since 1.0
 */
@Service
public class LecturerServiceImpl {
    
    @Inject
    private LecturerDAO lecturerDAO;

    @Inject
    public LecturerServiceImpl(LecturerDAO lecturerDAO){
        
    	this.lecturerDAO = lecturerDAO;
    }
    
    
    /**
     * Add lecturer entity
     * @param entity lecturer entity
     * @return lecturer entity
     */
    public Lecturer addLecturer(Lecturer entity) {
        Lecturer lecturer;
        if (entity == null){
            throw new IllegalArgumentException("Error creating lecturer"); 
        }
        try {
            lecturer = lecturerDAO.create(entity);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating lecturer"); 
        }
        return lecturer;
    }

    /**
     * Update lecturer entity
     * @param entity lecturer entity
     * @return lecturer entity
     */
    public Lecturer updateLecturer(Lecturer entity) {
        Lecturer lecturer;
        if (entity == null){
            throw new IllegalArgumentException("Error updating lecturer"); 
        }
        
        try {
            lecturerDAO.update(entity);
            lecturer = lecturerDAO.readById(entity.getId());
        } catch (Exception e) {
            throw new DAOdataAccessException("Error updating lecturer"); 
        }
        return lecturer;
    }
    
        public void removeLecturer(Lecturer lecturer)
    {
        if(lecturer == null)        
            throw new IllegalArgumentException("Error removing null lecturer");
        
        try
        {
            lecturerDAO.delete(lecturer);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error deleting lecturer");
        }
    }

    /**
     * Find lecturer entity
     * @param id id of lecturer
     * @return lecturer entity
     */
    public Lecturer findById(Long id) {
        Lecturer lecturer;
        if (id == null){
            throw new IllegalArgumentException("Error findById"); 
        }
        try {
            lecturer = lecturerDAO.readById(id);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findById"); 
        }
        return lecturer;
    }

    /**
     * Find lecturer entity
     * @param name name of lecturer
     * @param surname surname of lecturer
     * @return list of found lecturers
     */
    public List<Lecturer> findByName(String name, String surname) {
        List<Lecturer>  lecturers;
        if (name == null || surname == null){
            throw new IllegalArgumentException("Error findByName"); 
        }
        try {
            lecturers = lecturerDAO.findByName(name, surname);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findByName"); 
        }
        return lecturers;
    }
    
    /**
     * Find all existing lecturers
     * @return list of all existing lecturers
     */
    public List<Lecturer> findAllLecturers() {
        List<Lecturer>  lecturers;
        try {
            lecturers = lecturerDAO.findAllLecturers();
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findAllLecturers"); 
        }
        return lecturers;
    }
}