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
