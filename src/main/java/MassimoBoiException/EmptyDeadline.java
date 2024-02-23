package MassimoBoiException;




public class EmptyDeadline extends DeadlineException{
    @Override
    public void errorMessage(){
        System.out.println("Ma G! You got no description for deadline. Please re-add deadline with description.");
    }
}
