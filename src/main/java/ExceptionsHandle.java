public class ExceptionsHandle {
    protected final int STRING_ISNOT_INTEGER = -35019;
    public ExceptionsHandle(){
    }
    public int checkIfStringIsInteger(String s){
        try{
            return Integer.parseInt(s);
        } catch(NumberFormatException e){
            return -35019;
        }
    }
    public int getStringIsNotInteger(){
        return STRING_ISNOT_INTEGER;
    }
}
