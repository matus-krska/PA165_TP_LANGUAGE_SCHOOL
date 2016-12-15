package REST.Controllers;

import javax.inject.Inject;

import DTO.*;
import Facade.*;
import REST.Uri.ApiUris;
import REST.Assemblers.*;
import REST.Exceptions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(ApiUris.ROOT_URI_COURSES)
public class CoursesController {

	@Inject
	private CourseFacadeInterface courseFacade;
        
        @Inject
        private CourseResourceAssembler courseResourceAssembler;
        

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<CourseDTO>>> getCourses(WebRequest webRequest) {
   
            Collection<CourseDTO> coursesDTO = courseFacade.findAllCourses();
            Collection<Resource<CourseDTO>> courseResourceCollection = new ArrayList<>();

            for (CourseDTO c : coursesDTO) {
                courseResourceCollection.add(courseResourceAssembler.toResource(c));
            }

            Resources<Resource<CourseDTO>> coursesResources = new Resources<>(courseResourceCollection);
            coursesResources.add(linkTo(this.getClass()).withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(coursesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(coursesResources);
	}

        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<CourseDTO>> getCourse(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<CourseDTO> courseDTO = courseFacade.findById(id);
            if(!courseDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<CourseDTO> resource = courseResourceAssembler.toResource(courseDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(courseDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteCourse(@PathVariable("id") long id)  {
            Optional<CourseDTO> course = courseFacade.findById(id);
            if(!course.isPresent())
                throw new ResourceNotFoundException();

            courseFacade.removeCourse(course.get());
        }
}
