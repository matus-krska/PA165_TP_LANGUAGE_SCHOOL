package Controllers;

import DTO.LecturerDTO;
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

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Optional;

/**
 * MVC Controller for managing lecturers
 * @author Simon Hyben, 421112
 * @since 1.0
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController
{
    private final static Logger logger = LoggerFactory.getLogger(LecturerController.class);

    @Inject
    private LecturerFacadeInterface lecturerFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("lecturers", lecturerFacade.getAllLecturers());
        return "lecturer/lecturersList";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("lecturer", lecturerFacade.findById(id).get());
        return "lecturer/lecturerView";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("lecturer", lecturerFacade.findById(id).get());
        logger.debug("edit");
        return "lecturer/lecturerEdit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder) {
        logger.debug("delete");
        lecturerFacade.removeLecturer(lecturerFacade.findById(id).get());
        return "redirect:" + uriBuilder.path("/lecturer/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLecturer(Model model) {
        logger.debug("new");
        model.addAttribute("lecturer", new LecturerDTO());
        return "lecturer/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createLecturer(@Valid @ModelAttribute("lecturerCreate") LecturerDTO formBean,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder) {
        logger.debug("create");
        Optional<LecturerDTO> ldto = lecturerFacade.registerLecturer(formBean);
        return "redirect:" + uriBuilder.path("/lecturer/list").buildAndExpand().encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateLecturer(@PathVariable Long id, @Valid @ModelAttribute("lecturer") LecturerDTO formBean,
                               BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                               UriComponentsBuilder uriBuilder) {
        logger.debug("update");
        Optional<LecturerDTO> ldto = lecturerFacade.updateLecturer(formBean);
        return "redirect:" + uriBuilder.path("/lecturer/list").buildAndExpand().encode().toUriString();
    }
}

