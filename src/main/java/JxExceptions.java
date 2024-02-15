import java.lang.Throwable;
public class JxExceptions extends Exception{
    public JxExceptions(String s, Throwable err) {
        super(s, err);
    }
    public JxExceptions(String s) {
        super(s);
    }
}
