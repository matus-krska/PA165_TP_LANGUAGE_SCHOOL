package Exceptions;

/**
 * Exception used when trying to use a code for lecture which is already taken by another lecture
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class DAOdataAccessException extends org.springframework.dao.DataAccessException{
    public DAOdataAccessException(String msg)
    {
        super(msg);
    }
    
    public DAOdataAccessException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }
    
}
