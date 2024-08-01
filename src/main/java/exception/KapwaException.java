package exception;


/**
 * Represents the exception in Kapwa
 * @see Kapwa
 * @see TaskManager
 * @see Ui
 * 
 * @author yyangdaa
 * @version 0.1
 * @since 2024-03-03
 * 
 */
public class KapwaException extends Exception{
    public KapwaException(String message) {
        super(message);
    }
}
