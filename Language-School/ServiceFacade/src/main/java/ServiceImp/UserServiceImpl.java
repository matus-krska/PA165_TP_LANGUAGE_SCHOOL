package ServiceImp;

import Exceptions.DAOdataAccessException;
import Security.UserPasswordEncryption;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import org.muni.fi.pa165.lang_school.DAO.UserRepository;
import org.muni.fi.pa165.lang_school.entities.User;
//import com.fi.ls.exceptions.ServiceLayerException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

/**
 * Implementation of user service
 *
 * @author Simon Hyben, 421112
 *
 */
@Service
public class UserServiceImpl {

    private UserRepository userDao;

    private UserPasswordEncryption userPasswordEncryption;

    @Inject
    public UserServiceImpl(UserRepository userDao, UserPasswordEncryption userPasswordEncryption){
        this.userDao = userDao;
        this.userPasswordEncryption = userPasswordEncryption;
    }

    public void registerUser(User u, String unencryptedPassword) {
        if (u == null)
            throw new IllegalArgumentException("User u parameter is null");
        if (unencryptedPassword == null)
            throw new IllegalArgumentException("String unencryptedPassword parameter is null");

        try {
            u.setPasswordHash(userPasswordEncryption.createHash(unencryptedPassword));
            userDao.save(u);
        }
        catch(Exception ex) {
            throw new DAOdataAccessException("Error creating user");
        }
    }

    public boolean authenticate(User u, String password) {
        if (u == null)
            throw new IllegalArgumentException("LSUser u parameter is null");
        if (password == null)
            throw new IllegalArgumentException("String password parameter is null");

        try {
            return userPasswordEncryption.validatePassword(password, u.getPasswordHash());
        }
        catch(RuntimeException ex) {
            throw new DAOdataAccessException("Error authenticationg user");        }
    }

    public User findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Long id parameter is null");

        try {
            return userDao.findOne(id);
        }
        catch(DataAccessException | ConstraintViolationException ex) {
            throw new DAOdataAccessException("Error findById user");
        }
    }

    public User update(User c) {
        if (c == null)
            throw new IllegalArgumentException("User c parameter is null");

        try {
            return userDao.save(c);
        }
        catch(DataAccessException | ConstraintViolationException ex) {
            throw new DAOdataAccessException("Error updating user");
        }
    }

    public void remove(User c) {
        if (c == null)
            throw new IllegalArgumentException("User c parameter is null");

        try {
            userDao.delete(c);
        }
        catch(DataAccessException | ConstraintViolationException ex) {
            throw new DAOdataAccessException("Error remove user");
        }
    }

    public List<User> findAllUsers() {
        try {
            return userDao.findAll();
        }
        catch(DataAccessException | ConstraintViolationException ex) {
            throw new DAOdataAccessException("Error findAllUsers");
        }
    }

    public User findByEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException("String email parameter is null");

        try {
            return userDao.findByEmail(email);
        }
        catch(DataAccessException | PersistenceException | ConstraintViolationException ex) {
            throw new DAOdataAccessException("Error findByEmail");
        }
    }

}
