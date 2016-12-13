package REST.Controllers;

import REST.Uri.ApiUris;
import ConfigMapper.BeanMapper;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import DTO.LecturerDTO;
import FacadeImp.LecturerFacadeImpl;
import ServiceImp.LecturerServiceImpl;
import REST.Exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * REST Controller for Lecturers
 *
 * @author Simon Hyben, 421112
 * @since 1.0
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_LECTURERS)
public class LecturersController {

    final static Logger logger = LoggerFactory.getLogger(LecturersController.class);

    @Inject BeanMapper mapper;

    @Inject LecturerServiceImpl lecturerService;

    private LecturerFacadeImpl lecturerFacade = new LecturerFacadeImpl(lecturerService, mapper);

    /**
     * Get all the lecturers
     * @return list of LecturerDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LecturerDTO> getLecturers() {

        logger.debug("REST getLecturers()");
        return lecturerFacade.getAllLecturers();
    }

    /**
     *
     * Get one lecturer specified by id
     *
     * @param id identifier for the lecturer
     * @return LecturerDTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final LecturerDTO getLecturer(@PathVariable("id") long id) throws Exception {

        logger.debug("REST getLecturer({})", id);

        LecturerDTO lecturerDTO = lecturerFacade.findById(id);
        if (lecturerDTO == null) {
            throw new ResourceNotFoundException();
        }

        return lecturerDTO;
    }
}
