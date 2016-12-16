package Controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import DTO.CourseDTO;
import DTO.LectureDTO;
import DTO.LecturerDTO;
import Facade.CourseFacadeInterface;
import Facade.LecturerFacadeInterface;
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

import Facade.LectureFacadeInterface;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Lecture controller
 *
 * @author Simon Hyben, 421112
 */
@Controller
@RequestMapping("/lecture")
public class LectureController {

    private final static Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Inject
    private LectureFacadeInterface lectureFacade;

    @Inject
    private CourseFacadeInterface courseFacade;

    @Inject
    private LecturerFacadeInterface lecturerFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("lectures", lectureFacade.findAllLectures());
        return "lecture/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCourse(Model model) {
        logger.debug("new");
        model.addAttribute("lecture", new LectureDTO());
        return "lecture/new";
    }

    @ModelAttribute("courses")
    public List<CourseDTO> courses() {
        return courseFacade.findAllCourses();
    }

    @ModelAttribute("lecturers")
    public List<LecturerDTO> lecturers() {
        return lecturerFacade.getAllLecturers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCourse(@Valid @ModelAttribute("lecture") LectureDTO formBean,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder) {
        logger.debug("create");
        formBean.setLectureTime(new Date());
        Optional<LectureDTO> cdto = lectureFacade.createNewLecture(formBean);
        return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("lecture", lectureFacade.findById(id).get());
        return "lecture/view";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editLecture(@PathVariable Long id, Model model) {
        model.addAttribute("lecture", lectureFacade.findById(id).get());
        logger.debug("edit");
        return "lecture/edit";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("lecture") LectureDTO formBean,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder) {
        logger.debug("update");
        Optional<LectureDTO> cdto = lectureFacade.updateLecture(formBean);
        return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteLecture(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
        logger.debug("delete");
        lectureFacade.removeLecture(lectureFacade.findById(id).get());
        return "redirect:" + uriBuilder.path("/lecture/list").buildAndExpand().encode().toUriString();
    }
}
