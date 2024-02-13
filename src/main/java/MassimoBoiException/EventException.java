package MassimoBoiException;

public class EventException extends MassimoBoiException{
    @Override
    public void errorMessage(){
        System.out.println("You have an error related to adding an event.");
    }
}
