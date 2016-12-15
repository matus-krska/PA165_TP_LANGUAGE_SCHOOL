package REST.Controllers;

import DTO.LectureDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping(ApiUris.ROOT_URI_STUDENTS)
public class StudentsController {

	@Inject
	private StudentFacadeInterface studentFacade;
        
        @Inject
        private StudentResourceAssembler studentResourceAssembler;

        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<StudentDTO>>> getLecturers(WebRequest webRequest) {
                
            Collection<StudentDTO> studentsDTO = studentFacade.getAllStudents();
            Collection<Resource<StudentDTO>> studentResourceCollection = new ArrayList<>();

            for (StudentDTO s : studentsDTO) {
                studentResourceCollection.add(studentResourceAssembler.toResource(s));
            }

            Resources<Resource<StudentDTO>> studentsResources = new Resources<>(studentResourceCollection);
            studentsResources.add(linkTo(this.getClass()).withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(studentsResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(studentsResources);
	}
        

        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<StudentDTO>> getStudent(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<StudentDTO> studentDTO = studentFacade.getStudentById(id);
            if(!studentDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<StudentDTO> resource = studentResourceAssembler.toResource(studentDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(studentDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }
        

        @RequestMapping(value = "/{id}/lectures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LectureDTO>>> getStudentLectures(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<StudentDTO> studentDTO = studentFacade.getStudentById(id);
            if(!studentDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();
            for (LectureDTO lect : studentDTO.get().getListOfLectures()) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }
            
            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).slash(studentDTO.get().getId()).slash("lectures").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
        }
        

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteStudent(@PathVariable("id") long id)  {

            Optional<StudentDTO> student = studentFacade.getStudentById(id);
            if(!student.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = studentFacade.deleteStudent(student.get());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
}
