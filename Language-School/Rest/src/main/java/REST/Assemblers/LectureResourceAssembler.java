package REST.Assemblers;

import DTO.LectureDTO;
import REST.Controllers.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Lukáš Daubner (410034)
 *
 */
@Component
public class LectureResourceAssembler implements ResourceAssembler<LectureDTO, Resource<LectureDTO>> {

    @Override
    public Resource<LectureDTO> toResource(LectureDTO lectureDTO) {
        Resource<LectureDTO> lectureResource = new Resource<>(lectureDTO);

        try {
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).withSelfRel());
            lectureResource.add(linkTo(LecturesController.class).slash(lectureDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(LectureResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturesController", ex);
        }

        return lectureResource;
    }
}
