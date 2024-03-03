package exceptions;

public class UnknownInputException extends Exception{

    @Override
    public String getMessage() {
        return "\t I've not seen this input before. Please tell me something else I can help you with.";
    }
}
