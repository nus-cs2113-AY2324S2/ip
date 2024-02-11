public class WrongArgumentsException extends Exception{
    public WrongArgumentsException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "OOPS!!! " + super.getMessage();
    }
}
