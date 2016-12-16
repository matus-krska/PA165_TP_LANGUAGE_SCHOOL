package Config;

import Facade.CourseFacadeInterface;
import ServiceImp.CourseServiceImpl;
import ServiceImp.LectureServiceImpl;
import ServiceImp.LecturerServiceImpl;
import ServiceImp.StudentServiceImpl;
import ServiceImp.UserServiceImpl;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.entities.Lecture;
import org.muni.fi.pa165.lang_school.entities.User;
import Enums.UserRoles;
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

    @Inject
    private CourseServiceImpl courseService;

    @Inject
    private LectureServiceImpl lectureService;

    @Inject
    private LecturerServiceImpl lecturerService;

    @Inject
    private StudentServiceImpl studentService;

    @Inject
    private UserServiceImpl userService;

    public void loadData()
    {
        //////// MY tmp TestData ////////////
        User user1 = new User();
        user1.setEmail("admin@mail.cz");
        user1.setPasswordHash("admin");
        //user1.setUserRole(UserRoles.ROLE_ADMIN.name());
        userService.registerUser(user1, user1.getPasswordHash());

        /////////////////////////////////////
        course("English B1","English for intermediate students","English","B1");
        course("Spanish A1", "Spanish for beginners", "Spanish", "A1");
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
    // TODO add this for lecturer / other
}
