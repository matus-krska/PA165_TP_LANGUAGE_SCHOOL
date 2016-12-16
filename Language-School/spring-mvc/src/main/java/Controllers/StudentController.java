package Controllers;

import javax.inject.Inject;
import javax.validation.Valid;

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

import Facade.StudentFacadeInterface;
import java.util.Optional;

import DTO.StudentDTO;


/**
 * Student controller
 *
 * @author Simon Hyben, 421112
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Inject
    private StudentFacadeInterface studentFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("students", studentFacade.findAllStudents());
        return "student/studentsList";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("student", studentFacade.findById(id).get());
        return "student/studentView";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentFacade.findById(id).get());
        logger.debug("edit");
        return "student/studentEdit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, Model model, UriComponentsBuilder uriBuilder) {
        logger.debug("delete");
        studentFacade.removeStudent(studentFacade.findById(id).get());
        return "redirect:" + uriBuilder.path("/student/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newStudent(Model model) {
        logger.debug("new");
        model.addAttribute("student", new StudentDTO());
        return "student/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createStudent(@Valid @ModelAttribute("studentCreate") StudentDTO formBean,
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                                 UriComponentsBuilder uriBuilder) {
        logger.debug("create");
        Optional<StudentDTO> sdto = studentFacade.registerStudent(formBean);
        return "redirect:" + uriBuilder.path("/student/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") StudentDTO formBean,
                                 BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                                 UriComponentsBuilder uriBuilder) {
        logger.debug("update");
        Optional<StudentDTO> sdto = studentFacade.updateStudent(formBean);
        return "redirect:" + uriBuilder.path("/student/list").buildAndExpand().encode().toUriString();
    }

}

