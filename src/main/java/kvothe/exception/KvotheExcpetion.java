package kvothe.exception;

public class KvotheExcpetion extends Exception{
    public KvotheExcpetion(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "OOPS!!! " + super.getMessage();
    }
}
