package FacadeImp;

import Facade.UserFacadeInterface;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import DTO.UserCreateDTO;
import DTO.UserDTO;
import org.muni.fi.pa165.lang_school.entities.User;
//import com.fi.ls.exceptions.ServiceLayerException;
import ConfigMapper.BeanMapper;
import ServiceImp.UserServiceImpl;

/**
 * @author Simon Hyben, 421112
 *
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacadeInterface {

    private final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

    private UserServiceImpl userService;
    private BeanMapper beanMapper;

    @Inject
    public UserFacadeImpl(UserServiceImpl userService, BeanMapper beanMapper) {
        this.userService = userService;
        this.beanMapper = beanMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            return beanMapper.mapTo(userService.findAllUsers(), UserDTO.class);
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("getAllUsers method invokes exception: " + ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id parameter is null");
        try {
            Optional<User> user = Optional.ofNullable(userService.findById(id));
            return user.isPresent() ? beanMapper.mapTo(user.get(), UserDTO.class) : Optional.empty();
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("getUserById method invokes exception: " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("email parameter is null or empty");
        try {
            Optional<User> user = Optional.ofNullable(userService.findByEmail(email));
            return user.isPresent() ? beanMapper.mapTo(user.get(), UserDTO.class) : Optional.empty();
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("getUserByEmail method invokes exception: " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> update(Long userId) {
        if (userId == null)
            throw new IllegalArgumentException("userId parameter is null in update method");
        try {
            Optional<User> user = Optional.ofNullable(userService.update(userService.findById(userId)));
            return user.isPresent() ? beanMapper.mapTo(user.get(), UserDTO.class) : Optional.empty();
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("update method invokes exception: " + ex);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteUser(Long userId) {
        if (userId == null)
            throw new IllegalArgumentException("userId parameter is null in deleteUser method");
        try {
            userService.remove(userService.findById(userId));
            return true;
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("deleteUser method invokes exception: " + ex);
            return true;
        }
    }

    @Override
    public Boolean registerUser(UserCreateDTO u, String unencryptedPassword) {
        if (u == null || unencryptedPassword == null || unencryptedPassword.isEmpty())
            throw new IllegalArgumentException(
                    "u parameter is null or unencryptedPassword is null or unencryptedPassword is empty in registerUser method");
        try {
            User userEntity = beanMapper.mapTo(u, User.class).get();
            userService.registerUser(userEntity, unencryptedPassword);
            u.setId(userEntity.getId());
            return true;
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("registerUser method invokes exception: " + ex);
            return false;
        }
    }

    @Override
    public Boolean authenticate(UserDTO u) {
        if (u == null)
            throw new IllegalArgumentException("UserDTO u parametr is null in authenticate method");
        try {
            return userService.authenticate(userService.findById(u.getId()), u.getPasswordHash());
        } catch (/*ServiceLayerException |*/ NoSuchElementException ex) {
            logger.warn("authenticate method invokes exception: " + ex);
            return false;
        }
    }
}

