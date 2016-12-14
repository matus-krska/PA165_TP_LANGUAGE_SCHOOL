package Controllers;

import DTO.CourseDTO;
import Facade.CourseFacadeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * MVC Controller for managing courses
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Controller
@RequestMapping("/course")
public class CourseController
{
    final static Logger log = LoggerFactory.getLogger(CourseController.class);

    @Inject
    private CourseFacadeInterface courseFacade;

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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        CourseDTO course = courseFacade.findById(id);
        courseFacade.removeCourse(course);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Course \"" + course.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/course").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("course", courseFacade.findById(id));
        return "course/view";
    }
}
