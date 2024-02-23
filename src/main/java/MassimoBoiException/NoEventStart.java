package MassimoBoiException;

public class noEventStart extends EventException{
    @Override
    public void errorMessage(){
        System.out.println("No start date mentioned! After your description type /from {date}");
    }
}
