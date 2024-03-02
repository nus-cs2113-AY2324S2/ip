public class SamException extends Exception{
    public SamException(String errorMessage){
        if(errorMessage != null)
        {
            System.out.println(errorMessage);
        }
        System.out.println("Please try again.");
    }
}
