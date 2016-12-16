package Controllers;

import DTO.CourseDTO;
import Facade.CourseFacadeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * MVC Controller for managing courses
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Controller
@RequestMapping("/course")
public class CourseController
{
    private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Inject
    private CourseFacadeInterface courseFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("courses", courseFacade.findAllCourses());
        return "course/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        logger.debug("view: ", id);

        CourseDTO course = courseFacade.findById(id).get();

        model.addAttribute("course", course);
        return "course/view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCourse(Model model) {
        logger.debug("new");
        model.addAttribute("courseCreate", new CourseDTO());
        return "course/courseNew";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCourse(@Valid @ModelAttribute("courseCreate") CourseDTO formBean,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder) {
        logger.debug("create");
        Optional<CourseDTO> cdto = courseFacade.createNewCourse(formBean);
        return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseFacade.findById(id).get());
        logger.debug("edit");
        return "course/courseEdit";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("course") CourseDTO formBean,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder) {
        logger.debug("update");
        Optional<CourseDTO> toUpdate = courseFacade.findById(id);
        Optional<CourseDTO> cdto = courseFacade.updateCourse(courseFacade.findById(id).get());
        return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteCourse(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
        logger.debug("delete");
        courseFacade.removeCourse(courseFacade.findById(id).get());
        return "redirect:" + uriBuilder.path("/course/list").buildAndExpand().encode().toUriString();
    }
}
