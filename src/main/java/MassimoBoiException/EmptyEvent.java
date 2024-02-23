package MassimoBoiException;

<<<<<<<< HEAD:src/main/java/MassimoBoiException/EmptyEvent.java
public class EmptyEvent extends EventException{
========
public class emptyEvent extends EventException{
>>>>>>>> branch-Level-6:src/main/java/MassimoBoiException/emptyEvent.java
    @Override
    public void errorMessage(){
        System.out.println("Ma G! You got no description for event. Please re-add event with description.");
    }
}
