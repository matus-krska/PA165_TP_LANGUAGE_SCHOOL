package REST.Controllers;

import Facade.UserFacadeInterface;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DTO.UserDTO;
import FacadeImp.UserFacadeImpl;
import REST.Uri.ApiUris;

/**
 * @author Simon Hyben, 421112
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UsersController {

    final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Inject
    private UserFacadeInterface userFacade;

    /**
     * get all the users
     *
     * @return list of UserDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDTO> getUsers() {

        return userFacade.getAllUsers();
    }

}
