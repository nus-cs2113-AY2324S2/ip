package MassimoBoiException;

<<<<<<<< HEAD:src/main/java/MassimoBoiException/EmptyDeadline.java
public class EmptyDeadline extends DeadlineException{
========
>>>>>>>> branch-Level-6:src/main/java/MassimoBoiException/emptyDeadline.java
public class emptyDeadline extends DeadlineException{
    @Override
    public void errorMessage(){
        System.out.println("Ma G! You got no description for deadline. Please re-add deadline with description.");
    }
}
