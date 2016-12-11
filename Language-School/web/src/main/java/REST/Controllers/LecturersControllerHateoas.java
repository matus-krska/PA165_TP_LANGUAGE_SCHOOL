package REST.Controllers;

import DTO.LecturerDTO;

//import cz.fi.muni.pa165.dto.UserDTO;
import FacadeImp.LecturerFacadeImpl;
import REST.Exceptions.ResourceNotFoundException;
import REST.Assemblers.LecturerResourceAssembler;
import REST.Exceptions.ResourceNotModifiedException;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.WebRequest;

/**
 * REST HATEOAS Controller for Lecturers
 * This class shows Spring support for full HATEOAS REST services
 * it uses the {@link REST.Assemblers.LecturerResourceAssembler} to create
 * resources to be returned as ResponseEntities
 *
 * @author Simon Hyben, 421112
 */
@RestController
@RequestMapping("/lecturers_hateoas")
public class LecturersControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(LecturersControllerHateoas.class);

    @Inject
    private LecturerFacadeImpl lecturerFacade;

    @Inject
    private LecturerResourceAssembler lecturerResourceAssembler;

    /**
     *
     * Get list of lecturers
     * 
     * @return HttpEntity<Resources<Resource<LecturerDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<LecturerDTO>>> getLecturers() {
        
        logger.debug("REST getLecturers({}) hateoas");

        Collection<LecturerDTO> lecturersDTO = lecturerFacade.getAllLecturers();
        Collection<Resource<LecturerDTO>> lecturerResourceCollection = new ArrayList();

        for (LecturerDTO l : lecturersDTO) {
            lecturerResourceCollection.add(lecturerResourceAssembler.toResource(l));
        }

        Resources<Resource<LecturerDTO>> lecturersResources = new Resources<Resource<LecturerDTO>>(lecturerResourceCollection);
        lecturersResources.add(linkTo(LecturersControllerHateoas.class).withSelfRel());

        return new ResponseEntity<Resources<Resource<LecturerDTO>>>(lecturersResources, HttpStatus.OK);

    }
    
    /**
     *
     * Get list of lecturers - this method also supports HTTP caching
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
     * 
     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
     * 
     * The conditional request can be sent with
     * curl -i -X GET http://localhost:8080/eshop-rest/products_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
     * 
     * @return HttpEntity<Resources<Resource<LecturerDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resources<Resource<LecturerDTO>>> getLecturersCached(WebRequest webRequest) {
        
        logger.debug("REST getLecturers({}) hateoas cached version");
       
        final Collection<LecturerDTO> lecturersDTO = lecturerFacade.getAllLecturers();
        final Collection<Resource<LecturerDTO>> lecturerResourceCollection = new ArrayList();

        for (LecturerDTO l : lecturersDTO) {
            lecturerResourceCollection.add(lecturerResourceAssembler.toResource(l));
        }

        Resources<Resource<LecturerDTO>> lecturersResources = new Resources(lecturerResourceCollection);
        lecturersResources.add(linkTo(LecturersControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(lecturersResources.hashCode()));
        eTag.append('\"');
        
        if (webRequest.checkNotModified(eTag.toString())){
            throw new ResourceNotModifiedException();
        }
                
        return ResponseEntity.ok().eTag(eTag.toString()).body(lecturersResources);
    }

    /**
     *
     * Get one lecturer according to id
     * 
     * @param id identifier for lecturer
     * @return HttpEntity<Resource<LecturerDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<Resource<LecturerDTO>> getLecturer(@PathVariable("id") long id) throws Exception {
        
        logger.debug("REST getLecturer({}) hateoas", id);

        try {
            LecturerDTO lecturerDTO = lecturerFacade.findById(id);
            Resource<LecturerDTO> resource = lecturerResourceAssembler.toResource(lecturerDTO);
            return new ResponseEntity<Resource<LecturerDTO>>(resource, HttpStatus.OK);
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * Delete one lecturer by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/products/1
     *
     * @param id identifier for lecturer
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteLecturer(@PathVariable("id") long id) throws Exception {
        logger.debug("REST deleteLecturer({}) hateoas", id);
        try {
            lecturerFacade.removeLecturer(lecturerFacade.findById(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
