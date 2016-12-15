package Controllers;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import Facade.StudentFacadeInterface;

/**
 * Student controller
 *
 * @author Simon Hyben, 421112
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private final static Logger logger = LoggerFactory.getLogger(CourseController.class);

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

}
}
