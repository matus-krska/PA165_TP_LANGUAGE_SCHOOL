package ServiceImp;

import org.muni.fi.pa165.lang_school.DAO.LecturerDAO;
import org.muni.fi.pa165.lang_school.entities.Lecturer;

import javax.inject.Inject;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Implementation of service layer for entity Lecturer
 * @author Simon Hyben, 421112
 * @since 1.0
 */
public class LecturerServiceImpl
{
    @Inject
    private LecturerDAO lecturerDAO;

    public Lecturer findById(Long id) 
    {
        return lecturerDAO.readById(id);
    }
    
    public Lecturer createLecturer(Lecturer lecturer)
    {
        return lecturerDAO.create(lecturer);
    }

    public Lecturer updateLecturer(Lecturer lecturer)
    {
        lecturerDAO.update(lecturer);
        return lecturer;
    }

    public void removeLecturer(Lecturer lecturer)
    {
        lecturerDAO.delete(lecturer);
    }

    public List<Lecturer> findLecturerByName(String name, String surname)
    {
        Validate.notEmpty(name);
        Validate.notEmpty(surname);
        return lecturerDAO.findByName(name, surname);
    }
}

