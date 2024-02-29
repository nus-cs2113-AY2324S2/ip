package Casper;

public class CasperInvalidInputException extends Exception{
    public String exceptionNote;
    public CasperInvalidInputException(String e){
        super();
        this.exceptionNote = e;
    }
    public String getExceptionNote(){
        return exceptionNote;
    }
}
