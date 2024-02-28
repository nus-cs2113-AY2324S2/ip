import java.lang.Throwable;
public class JxExceptions extends Exception{

    /**
     * Constructor for JxExceptions, invoked when
     * user gives an undefined input
     *
     */
    public JxExceptions(String s, Throwable err) {
        super(s, err);
    }

    /**
     * Constructor for JxExceptions, invoked when
     * user gives an undefined input
     *
     */
    public JxExceptions(String s) {
        super(s);
    }
}
