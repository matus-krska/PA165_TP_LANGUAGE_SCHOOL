package ServiceImp;

import java.util.List;
import org.apache.commons.lang3.Validate;
import org.muni.fi.pa165.lang_school.DAO.StudentDAO;
import org.muni.fi.pa165.lang_school.entities.Student;

/**
 * @author Richard Zan, 396380
 * @since 1.0
 */
public class StudentServiceImpl {
    
    private StudentDAO studentDAO;

    public Student addStudent(Student entity) {
        Validate.isTrue(entity.getId() == null);
        studentDAO.create(entity);
        return studentDAO.readById(entity.getId());
    }

    public Student updateUser(Student entity) {
        Validate.isTrue(entity.getId() == null);
        studentDAO.update(entity);
        return studentDAO.readById(entity.getId());
    }

    public Student findById(Long id) {
        Validate.notNull(id);
        return studentDAO.readById(id);
    }

    public List<Student> findByNameSurname(String name, String surname) {
        Validate.notEmpty(name);
        Validate.notEmpty(surname);
        return studentDAO.findByNameAndSurname(name, surname);
    }

    public Student findByIdNameAndSurname(Long id, String name, String surname) {
        Validate.notNull(id);
        Validate.notEmpty(name);
        Validate.notEmpty(surname);
        return (Student) studentDAO.findByIdNameAndSurname(id, name, surname);
    }
    
}
