public class unknownCommandType extends MassimoBoiException{
    @Override
    public void errorMessage(){
        System.out.println("I don't know what that means homie! Use on of the commands listed below: ");
    }
}
