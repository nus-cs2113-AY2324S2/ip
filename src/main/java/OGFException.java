public class OGFException extends Exception{
    private boolean isFatal;
    OGFException(String message, boolean isFatal){
        super(message);
        this.isFatal = isFatal;
    }
    public boolean getFatal(){
        return isFatal;
    }
}
