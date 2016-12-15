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
        
        @Inject
        private LanguageResourceAssembler languageResourceAssembler;
        
        @Inject
        private LectureResourceAssembler lectureResourceAssembler;
        
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpEntity<Resources<Resource<LecturerDTO>>> getLecturers(WebRequest webRequest) {

            logger.debug("rest getLecturers()");
                
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

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.getLecturerById(id);
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

        @RequestMapping(value = "/{id}/languages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LanguageDTO>>> getLecturerLanguages(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.getLecturerById(id);
            if(!lecturerDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LanguageDTO>> languageResourceCollection = new ArrayList<>();
            for (LanguageDTO lang : lecturerDTO.get().getListOfLanguages()) {
                languageResourceCollection.add(languageResourceAssembler.toResource(lang));
            }
            
            Resources<Resource<LanguageDTO>> languagesResources = new Resources<>(languageResourceCollection);
            languagesResources.add(linkTo(this.getClass()).slash(lecturerDTO.get().getId()).slash("languages").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(languagesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(languagesResources);
        }

        @RequestMapping(value = "/{id}/lectures", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public final HttpEntity<Resources<Resource<LectureDTO>>> getLecturerLectures(@PathVariable("id") long id, WebRequest webRequest) {

            Optional<LecturerDTO> lecturerDTO = lecturerFacade.getLecturerById(id);
            if(!lecturerDTO.isPresent())
                throw new ResourceNotFoundException();

            Collection<Resource<LectureDTO>> lectureResourceCollection = new ArrayList<>();
            for (LectureDTO lect : lecturerDTO.get().getListOfLectures()) {
                lectureResourceCollection.add(lectureResourceAssembler.toResource(lect));
            }
            
            Resources<Resource<LectureDTO>> lecturesResources = new Resources<>(lectureResourceCollection);
            lecturesResources.add(linkTo(this.getClass()).slash(lecturerDTO.get().getId()).slash("lectures").withSelfRel());

            final StringBuffer eTag = new StringBuffer("\"");
            eTag.append(Integer.toString(lecturesResources.hashCode()));
            eTag.append('\"');

            if (webRequest.checkNotModified(eTag.toString())){
                throw new ResourceNotModifiedException();
            }

            return ResponseEntity.ok().eTag(eTag.toString()).body(lecturesResources);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
        public final void deleteLecturer(@PathVariable("id") long id)  {

            Optional<LecturerDTO> lecturer = lecturerFacade.getLecturerById(id);
            if(!lecturer.isPresent())
                throw new ResourceNotFoundException();

            Boolean deleted = lecturerFacade.deleteLecturer(lecturer.get());

            if(!deleted)
                throw new ResourceNotFoundException();
        }
}
