package MassimoBoiException;

public class emptyToDo extends MassimoBoiException{
    @Override
    public void errorMessage(){
        System.out.println("Ma G! Your todo cannot have empty description! Rewrite the todo with description!");
    }
}
