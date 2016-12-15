package REST.Controllers;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping(ApiUris.ROOT_URI_LECTURES)
public class LecturesController {

	@Inject
	private LectureFacadeInterface lectureFacade;
                
        @Inject
        private LectureResourceAssembler lectureResourceAssembler;

        @Inject
        private LecturerResourceAssembler lecturerResourceAssembler;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LectureDTO>>> getLectures(WebRequest webRequest) {

                
            Collection<LectureDTO> lecturesDTO = lectureFacade.findAllLectures();
            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();

            for (LectureDTO lect : lecturesDTO) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }

            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
	}

        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<LectureDTO>> getLecture(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<LectureDTO> lectureDTO = lectureFacade.findById(id);
            if(!lectureDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<LectureDTO> resource = lectureResourceAssembler.toResource(lectureDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lectureDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteLecture(@PathVariable("id") long id)  {

            Optional<LectureDTO> lecture = lectureFacade.findById(id);
            if(!lecture.isPresent())
                throw new ResourceNotFoundException();

            lectureFacade.removeLecture(lecture.get());
        }
}
