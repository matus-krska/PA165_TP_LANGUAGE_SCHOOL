package Facade;

import java.util.List;
import java.util.Optional;

import DTO.UserDTO;
import DTO.UserCreateDTO;

/**
 * User facade interface implementation
 *
 * @author Simon Hyben, 421112
 *
 */
public interface UserFacadeInterface {

    /**
     * Finds user by id
     * @param id of a user
     * @return user
     */
    Optional<UserDTO> getUserById(Long id);

    /**
     * Updates existing user
     * @param userId user id
     * @return updated user
     */
    Optional<UserDTO> update(Long userId);

    /**
     * Removes existing user
     * @param userId user id
     * @return true, if successfully removed
     */
    Boolean deleteUser(Long userId);

    /**
     * Returns all existing users
     * @return list of users
     */
    List<UserDTO> getAllUsers();

    /**
     * Find user by email
     * @param email user email
     * @return user
     */
    Optional<UserDTO> getUserByEmail(String email);

    /**
     * Registers new user
     * @param u user to register
     * @param unencryptedPassword password
     * @return true if success
     */
    public Boolean registerUser(UserCreateDTO u, String unencryptedPassword);

    /**
     * Try to authenticate a user. Return true only if the hashed password
     * matches the records.
     * @param u user to authenticate
     * @return true if success
     */
    public Boolean authenticate(UserDTO u);

}
