package ServiceImp;

import Exceptions.DAOdataAccessException;
import java.util.List;
import javax.inject.Inject;
import org.muni.fi.pa165.lang_school.DAO.StudentDAO;
import org.muni.fi.pa165.lang_school.entities.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Student facade
 * 
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
    
    
    /**
     * Add student entity
     * @param entity student entity
     * @return student entity
     */
    public Student addStudent(Student entity) {
        Student student;
        if (entity == null){
            throw new IllegalArgumentException("Error creating student"); 
        }
        try {
            student = studentDAO.create(entity);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error creating student"); 
        }
        return student;
    }

    /**
     * Update student entity
     * @param entity student entity
     * @return student entity
     */
    public Student updateStudent(Student entity) {
        Student student;
        if (entity == null){
            throw new IllegalArgumentException("Error updating student"); 
        }
        
        try {
            studentDAO.update(entity);
            student = studentDAO.readById(entity.getId());
        } catch (Exception e) {
            throw new DAOdataAccessException("Error updating student"); 
        }
        return student;
    }

    /**
     * Remove existing student
     * @param student
     */
    public void removeStudent(Student student)
    {
        if(student == null)
            throw new IllegalArgumentException("Error removing null student");

        try
        {
            studentDAO.delete(student);
        }
        catch (Exception e)
        {
            throw new DAOdataAccessException("Error removing student");
        }
    }

    /**
     * Find student entity
     * @param id id of student
     * @return student entity
     */
    public Student findById(Long id) {
        Student student;
        if (id == null){
            throw new IllegalArgumentException("Error by findById"); 
        }
        try {
            student = studentDAO.readById(id);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error by findById"); 
        }
        return student;
    }

    /**
     * Find student entity
     * @param name name of student
     * @param surname surname of student
     * @return list of found students
     */
    public List<Student> findByNameSurname(String name, String surname) {
        List<Student>  students;
        if (name == null || surname == null){
            throw new IllegalArgumentException("Error by findByNameSurname"); 
        }
        try {
            students = studentDAO.findByNameAndSurname(name, surname);
        } catch (Exception e) {
            throw new DAOdataAccessException("Error by findByNameSurname"); 
        }
        return students;
    }

    /**
     * Find all students
     * @return list of students
     */
    public List<Student> findAllStudents() {
        List<Student>  students;
        try {
            students = studentDAO.findAllStudents();
        } catch (Exception e) {
            throw new DAOdataAccessException("Error findAllStudents");
        }
        return students;
    }


}
