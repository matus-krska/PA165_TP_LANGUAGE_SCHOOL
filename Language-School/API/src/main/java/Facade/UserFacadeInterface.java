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
     * finds specific user by id
     *
     * @param id
     *            of a user that would be returned
     * @return specific user by id
     */
    public Optional<UserDTO> getUserById(Long id);

    /**
     * updates given user
     *
     * @param userId
     *            user that has to be updated
     * @return updated user
     */
    public Optional<UserDTO> update(Long userId);

    /**
     * removes given user
     *
     * @param userId
     *            user that has to be removed
     * @return true, if successfully removed
     */
    public Boolean deleteUser(Long userId);

    /**
     * Returns all courses in language school
     *
     * @return List of courses which are in language school
     */
    public List<UserDTO> getAllUsers();

    /**
     * Find specific user by his email
     *
     * @param email
     *            email to search in String format
     * @return return specific user by his email
     */
    public Optional<UserDTO> getUserByEmail(String email);

    /**
     * Register the given user with the given unencrypted password.
     * @param u
     * @param unencryptedPassword
     * @return true, if successful removed
     */
    public Boolean registerUser(UserCreateDTO u, String unencryptedPassword);

    /**
     * Try to authenticate a user. Return true only if the hashed password
     * matches the records.
     * @param u
     * @return true, if successful
     */
    public Boolean authenticate(UserDTO u);

}
