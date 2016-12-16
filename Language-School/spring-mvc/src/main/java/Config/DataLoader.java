package Config;

import Facade.CourseFacadeInterface;
import ServiceImp.CourseServiceImpl;
import ServiceImp.LectureServiceImpl;
import ServiceImp.LecturerServiceImpl;
import ServiceImp.StudentServiceImpl;
import ServiceImp.UserServiceImpl;
import org.muni.fi.pa165.lang_school.entities.*;
import Enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Data loader of MVC
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Component
@Transactional
public class DataLoader
{

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private LectureServiceImpl lectureService;

    @Autowired
    private LecturerServiceImpl lecturerService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserServiceImpl userService;

    public void loadData()
    {
        //////////// Users for testing ////////////
        User user1 = new User();
        user1.setEmail("admin@mail.cz");
        user1.setPasswordHash("admin");
        user1.setUserRole(UserRoles.ROLE_ADMIN.name());
        userService.registerUser(user1, user1.getPasswordHash());

        User user2 = new User();
        user2.setEmail("student@email.cz");
        user2.setPasswordHash("student");
        user2.setUserRole(UserRoles.ROLE_STUDENT.name());
        userService.registerUser(user2, user2.getPasswordHash());

        //////////// Courses for testing ////////////
        course("English B1","English for intermediate students","English","B1");
        course("Spanish A1", "Spanish for beginners", "Spanish", "A1");

        //////////// Students for testing ////////////
        student("Michal", "Ziak");
        student("Andrej", "Neznal");

        //////////// Lecturers for testing ////////////
        lecturer("Martin", "Vseznal");
        lecturer("Tomas", "Nezapoctovy");

        //////////// Lectures for testing ////////////
        lecture("ENG_B1_I", "Introduction", "First lecture of course English B1 aiming to tell students what should they expect from it");
        lecture("SPA_A1_II", "Verbs", "Second lecture of course Spanish A1. Goal of this lecture is to complete chapter 2: Verbs in course textbook");
    }

    private Course course(String name, String description, String language, String languageLevel)
    {
        Course c = new Course();
        c.setDescription(description);
        c.setLanguage_level(languageLevel);
        c.setLanguage(language);
        c.setName(name);
        return courseService.createCourse(c);
    }

    private Student student(String name, String surname)
    {
        Student s = new Student();
        s.setName(name);
        s.setSurname(surname);
        return studentService.addStudent(s);
    }

    private Lecturer lecturer(String name, String surname)
    {
        Lecturer l = new Lecturer();
        l.setName(name);
        l.setSurname(surname);
        return lecturerService.addLecturer(l);
    }

    private Lecture lecture(String code, String topic, String description)
    {
        Lecture l = new Lecture();
        l.setCode(code);
        l.setTopic(topic);
        l.setDescription(description);
        return lectureService.createLecture(l);
    }
}
