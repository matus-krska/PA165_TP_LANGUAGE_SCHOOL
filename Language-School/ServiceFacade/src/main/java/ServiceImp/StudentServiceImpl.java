package ServiceImp;

import Exceptions.DAOdataAccessException;
import java.util.List;
import javax.inject.Inject;
import org.muni.fi.pa165.lang_school.DAO.StudentDAO;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author Richard Zan, 396380
 * @since 1.0
 */
@Service
public class StudentServiceImpl {
    
    @Inject
    private StudentDAO studentDAO;

    @Inject
    public StudentServiceImpl(StudentDAO studentDAO){
        
    	this.studentDAO = studentDAO;
    }
    
    
    public Student addStudent(Student entity) {
        Student student;
        try {
            student = studentDAO.create(entity);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating student"); 
        }
        return student;
    }

    public Student updateUser(Student entity) {
        Student student;
        try {
            studentDAO.update(entity);
            student = studentDAO.readById(entity.getId());
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating student"); 
        }
        return student;
    }

    public Student findById(Long id) {
        Student student;
        if (id != null){
            throw new DAOdataAccessException("Error creating student"); 
        }
        try {
            student = studentDAO.readById(id);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating student"); 
        }
        return student;
    }

    public List<Student> findByNameSurname(String name, String surname) {
        List<Student>  students;
        if (name!= null && surname != null){
            throw new DAOdataAccessException("Error creating student"); 
        }
        try {
            students = studentDAO.findByNameAndSurname(name, surname);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating student"); 
        }
        return students;
    }

    public Student findByIdNameAndSurname(Long id, String name, String surname) {
        Student student;
        if (name!= null && surname != null && id != null){
            throw new DAOdataAccessException("Error creating student"); 
        }
        try {
            student = (Student) studentDAO.findByIdNameAndSurname(id, name, surname);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating student"); 
        }
        return student;
    }
    
}
