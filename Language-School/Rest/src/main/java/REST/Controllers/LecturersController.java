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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping(ApiUris.ROOT_URI_LECTURERS)
public class LecturersController {


	@Inject
	private LecturerFacadeInterface lecturerFacade;
        
        @Inject
        private LecturerResourceAssembler lecturerResourceAssembler;

        
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LecturerDTO>>> getLecturers(WebRequest webRequest) {
                
            Collection<LecturerDTO> lecturersDTO = lecturerFacade.getAllLecturers();
            Collection<Resource<LecturerDTO>> languageResourceCollection = new ArrayList<>();

            for (LecturerDTO l : lecturersDTO) {
                languageResourceCollection.add(lecturerResourceAssembler.toResource(l));
            }

            Resources<Resource<LecturerDTO>> lecturersResources = new Resources<>(languageResourceCollection);
            lecturersResources.add(linkTo(this.getClass()).withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturersResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturersResources);
	}

        @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resource<LecturerDTO>> getLecturer(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.findById(id);
            if(!lecturerDTO.isPresent())
                throw new ResourceNotFoundException();

            Resource<LecturerDTO> resource = lecturerResourceAssembler.toResource(lecturerDTO.get());
            
            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturerDTO.get().hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(resource);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteLecturer(@PathVariable("id") long id)  {

            Optional<LecturerDTO> lecturer = lecturerFacade.findById(id);
            if(!lecturer.isPresent())
                throw new ResourceNotFoundException();

            lecturerFacade.removeLecturer(lecturer.get());
        }
}
