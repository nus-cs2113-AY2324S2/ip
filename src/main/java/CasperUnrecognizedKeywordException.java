public class CasperUnrecognizedKeywordException extends Exception {
    private String unrecognizedKeyword;
    public CasperUnrecognizedKeywordException(){
        super();
    }
    public CasperUnrecognizedKeywordException(String errorMessage){
        super("\n     ERROR: \"" + errorMessage + "\" is an unrecognized keyword.");
        this.unrecognizedKeyword = errorMessage;
    }
    public String getUnrecognizedKeyword(){
        return this.unrecognizedKeyword;
    }
}
