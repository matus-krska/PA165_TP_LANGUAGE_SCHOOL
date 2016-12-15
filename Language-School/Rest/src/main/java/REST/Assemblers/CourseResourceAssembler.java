package REST.Assemblers;

import DTO.CourseDTO;
import DTO.LecturerDTO;
import REST.Controllers.CoursesControllerHateoas;
import REST.Controllers.LecturersControllerHateoas;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * This class shows a resource assembler for a HATEOAS REST Service we are
 * mapping the DTO to a resource that can provide links to the different parts
 * of the API See
 * http://docs.spring.io/spring-hateoas/docs/current/reference/html/
 * @author Matus Krska, 410073
 * @since 1.0
 */
@Component
public class CourseResourceAssembler implements ResourceAssembler<CourseDTO, Resource<CourseDTO>>
{
    @Override
    public Resource<CourseDTO> toResource(CourseDTO courseDTO) {
        long id = courseDTO.getId();
        Resource<CourseDTO> courseResource = new Resource<CourseDTO>(courseDTO);

        try {
            courseResource.add(linkTo(CoursesControllerHateoas.class).slash(courseDTO.getId()).withSelfRel());
            courseResource.add(linkTo(CoursesControllerHateoas.class).slash(courseDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(LecturerResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from LecturersControllerHateoas", ex);
        }

        return courseResource;
    }
}
