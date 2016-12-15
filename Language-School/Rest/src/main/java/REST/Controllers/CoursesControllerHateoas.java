package REST.Controllers;

import DTO.CourseDTO;
import DTO.LecturerDTO;
import Facade.CourseFacadeInterface;
import Facade.LecturerFacadeInterface;
import REST.Assemblers.CourseResourceAssembler;
import REST.Assemblers.LecturerResourceAssembler;
import REST.Exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author Matus Krska, 410073
 * @since 1.0
 */
@RestController
@RequestMapping("/courses_hateoas")
public class CoursesControllerHateoas
{

    final static Logger logger = LoggerFactory.getLogger(LecturersControllerHateoas.class);


    @Inject
    private CourseFacadeInterface courseFacade;

    @Inject
    private CourseResourceAssembler courseResourceAssembler;

    /**
     *
     * Get one course according to id
     *
     * @param id identifier for course
     * @return HttpEntity<Resource<LecturerDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource<CourseDTO>> getCourse(@PathVariable("id") long id) throws Exception {

        logger.debug("REST getLecturer({}) hateoas", id);

        try {
            CourseDTO courseDTO = courseFacade.findById(id);
            Resource<CourseDTO> resource = courseResourceAssembler.toResource(courseDTO);
            return new ResponseEntity<Resource<CourseDTO>>(resource, HttpStatus.OK);
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Delete one course by id
     *
     * @param id identifier for course
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteCourse(@PathVariable("id") long id) throws Exception {
        logger.debug("REST deleteCourse({}) hateoas", id);
        try {
            courseFacade.removeCourse(courseFacade.findById(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
