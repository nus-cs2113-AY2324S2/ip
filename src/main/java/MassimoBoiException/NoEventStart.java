package MassimoBoiException;

public class NoEventStart extends EventException{
    @Override
    public void errorMessage(){
        System.out.println("No start date mentioned! After your description type /from {date}");
    }
}
