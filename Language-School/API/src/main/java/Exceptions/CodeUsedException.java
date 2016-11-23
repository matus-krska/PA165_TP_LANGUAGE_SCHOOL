package Exceptions;

/**
 * Exception used when trying to use a code for lecture which is already taken by another lecture
 * @author Matus Krska, 410073
 * @since 1.0
 */
public class CodeUsedException extends Exception
{
    public CodeUsedException(String msg)
    {
        super(msg);
    }
}
