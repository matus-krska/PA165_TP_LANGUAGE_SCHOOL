package REST.Controllers;

import DTO.CourseDTO;
import Facade.CourseFacadeInterface;
import Facade.LecturerFacadeInterface;
import REST.Uri.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * REST controller for Course
 * @author Matus Krska, 410073
 * @since 1.0
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COURSES)
public class CoursesController
{
    final static Logger logger = LoggerFactory.getLogger(CoursesController.class);

    @PostConstruct
    public void initCourses() {
        CourseDTO course1 = new CourseDTO();
        course1.setName("Intermediate English");
        course1.setLanguage("English");
        course1.setLanguage_level("B1");
        course1.setDescription("English for intermediate students");
        courseFacade.createNewCourse(course1);

        CourseDTO course2 = new CourseDTO();
        course2.setName("Beginner Spanish");
        course2.setLanguage("Spanish");
        course2.setLanguage_level("A1");
        course2.setDescription("Spanish for beginner students");
        courseFacade.createNewCourse(course2);
    }

    @Inject
    private CourseFacadeInterface courseFacade;

    @RequestMapping(value = "/open/{id}", method = RequestMethod.GET)
    public String openCourseById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseFacade.findById(id));
        return "courses/openCourse";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCourse(Model model) {
        logger.debug("new");
        model.addAttribute("course", new CourseDTO());
        return "course/createCourse";
    }
}
