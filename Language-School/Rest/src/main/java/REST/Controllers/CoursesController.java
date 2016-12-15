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
        
        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        
	/**
	 * get all the courses (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/courses
	 * 
         * @param webRequest
	 * @return list of CourseDTOs
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<CourseDTO>>> getCourses(WebRequest webRequest) {
   
            Collection<CourseDTO> coursesDTO = courseFacade.getAllCourses();
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
        
        /**
         * get course by id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/courses/{id}
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<CourseDTO>> getCourse(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<CourseDTO> courseDTO = courseFacade.getCourseById(id);
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
        
        /**
         * get course lectures by course id (with HTTP caching)
         * curl -i -X GET http://localhost:8080/pa165/rest/courses/{id}/lectures
         * 
         * @param id
         * @param webRequest
         * @return 
         */
        @RequestMapping(value = "/{id}/lectures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LectureDTO>>> geCourseLectures(@PathVariable("id") long id, WebRequest webRequest) {
            Optional<CourseDTO> courseDTO = courseFacade.getCourseById(id);
            if(!courseDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();
            for (LectureDTO lect : courseDTO.get().getListOfLectures()) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }
            
            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).slash(courseDTO.get().getId()).slash("lectures").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
        }
        
        /**
         * delete course
         * curl -i -X DELETE http://localhost:8080/pa165/rest/courses/{id}
         * 
         * @param id 
         */
        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteCourse(@PathVariable("id") long id)  {
            Optional<CourseDTO> course = courseFacade.getCourseById(id);
            if(!course.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = courseFacade.deleteCourse(course.get().getId());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
}
