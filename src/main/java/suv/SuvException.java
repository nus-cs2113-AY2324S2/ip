package suv;

public class SuvException extends Exception{
    String warning;

    SuvException(String warning) {
        this.warning = warning;
    }
}
