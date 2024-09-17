package humi;

public class HumiException extends Exception{
    String message;
    HumiException(String message) {
        this.message = message;
    }
}
