package MassimoBoiException;

<<<<<<<< HEAD:src/main/java/MassimoBoiException/EmptyToDo.java
public class EmptyToDo extends MassimoBoiException{
========
public class emptyToDo extends MassimoBoiException{
>>>>>>>> branch-Level-6:src/main/java/MassimoBoiException/emptyToDo.java
    @Override
    public void errorMessage(){
        System.out.println("Ma G! Your todo cannot have empty description! Rewrite the todo with description!");
    }
}
