package Config;

import Facade.CourseFacadeInterface;
import ServiceImp.CourseServiceImpl;
import ServiceImp.LectureServiceImpl;
import ServiceImp.LecturerServiceImpl;
import ServiceImp.StudentServiceImpl;
import org.muni.fi.pa165.lang_school.entities.Course;
import org.muni.fi.pa165.lang_school.entities.Lecture;
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

    public void loadData()
    {
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
}
