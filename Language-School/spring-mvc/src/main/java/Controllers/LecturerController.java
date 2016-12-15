package Controllers;

import DTO.LecturerDTO;
import Facade.LecturerFacadeInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

/**
 * MVC Controller for managing lecturers
 * @author Simon Hyben, 421112
 * @since 1.0
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController
{
    final static Logger log = LoggerFactory.getLogger(LecturerController.class);

    @Inject
    private LecturerFacadeInterface lecturerFacade;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        LecturerDTO lecturer = lecturerFacade.findById(id);
        lecturerFacade.removeLecturer(lecturer);
        log.debug("delete({})", id);
        redirectAttributes.addFlashAttribute("alert_success", "Lecturer \"" + lecturer.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/lecturer").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("lecturer", lecturerFacade.findById(id));
        return "lecturer/view";
    }
}

