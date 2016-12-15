package REST.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;


@RestController
public class MainController {
    
    final static Logger logger = LoggerFactory.getLogger(MainController.class);
    

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource> getHome(WebRequest webRequest) {

        logger.debug("rest getHome()");

        Resource resource = new Resource("REST Home");
        resource.add(linkTo(this.getClass()).withSelfRel());
        resource.add(linkTo(StudentsController.class).withRel("Students"));
        resource.add(linkTo(LecturersController.class).withRel("Lecturers"));
        resource.add(linkTo(CoursesController.class).withRel("Courses"));
        resource.add(linkTo(LecturesController.class).withRel("Lectures"));

        return ResponseEntity.ok().body(resource);
    }

}
