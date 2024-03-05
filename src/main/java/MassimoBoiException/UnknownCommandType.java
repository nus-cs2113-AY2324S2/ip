package MassimoBoiException;

public class UnknownCommandType extends MassimoBoiException{
    @Override
    public void errorMessage(){
        System.out.println("I don't know what that means homie! Use one of the commands listed below: ");
    }
}
