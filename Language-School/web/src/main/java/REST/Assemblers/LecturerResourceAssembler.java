package REST.Assemblers;

import DTO.LecturerDTO;
import REST.Controllers.LecturersControllerHateoas;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * This class shows a resource assembler for a HATEOAS REST Service we are
 * mapping the DTO to a resource that can provide links to the different parts
 * of the API See
 * http://docs.spring.io/spring-hateoas/docs/current/reference/html/
 *
 * @author Simon Hyben, 421112
 */
@Component
public class LecturerResourceAssembler implements ResourceAssembler<LecturerDTO, Resource<LecturerDTO>> {

    @Override
    public Resource<LecturerDTO> toResource(LecturerDTO lecturerDTO) {
        long id = lecturerDTO.getId();
        Resource<LecturerDTO> lecturerResource = new Resource<LecturerDTO>(lecturerDTO);

        try {
            lecturerResource.add(linkTo(LecturersControllerHateoas.class).slash(lecturerDTO.getId()).withSelfRel());
            lecturerResource.add(linkTo(LecturersControllerHateoas.class).slash(lecturerDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(LecturerResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturersControllerHateoas", ex);
        }

        return lecturerResource;
    }
}
