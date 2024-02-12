public class CasperEmptyTodoException extends Exception {
    public CasperEmptyTodoException(){
        super();
    }

    public CasperEmptyTodoException(String errorMessage){
        super(errorMessage);
    }
}
