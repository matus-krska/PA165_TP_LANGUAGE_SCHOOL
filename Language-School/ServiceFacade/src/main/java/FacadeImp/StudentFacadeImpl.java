package FacadeImp;

//import 

import DTO.StudentDTO;
import Facade.StudentFacadeInterface;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.muni.fi.pa165.lang_school.DAO.StudentDAO;


/**
 * @author Richard Zan, 396380
 * @since 1.0
 */
public class StudentFacadeImpl implements StudentFacadeInterface{
    //@Inject
    //private Student;
    
    
    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StudentDTO> filterByNameSurname(String name, String surname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentDTO findByIdNameAndSurname(Long id, String name, String surname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
