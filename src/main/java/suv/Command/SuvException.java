package suv.Command;

public class SuvException extends Exception{
    public String warning;

    public SuvException(String warning) {
        this.warning = warning;
    }
}
