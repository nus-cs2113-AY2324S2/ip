package MassimoBoiException;

public class DeadlineException extends MassimoBoiException {
    @Override
    public void errorMessage(){
        System.out.println("You have an error related to adding a deadline.");
    }
}
