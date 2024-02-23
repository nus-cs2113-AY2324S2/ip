package MassimoBoiException;

<<<<<<<< HEAD:src/main/java/MassimoBoiException/NoDueDate.java
public class NoDueDate extends DeadlineException{
========
public class noDueDate extends DeadlineException{
>>>>>>>> branch-Level-6:src/main/java/MassimoBoiException/noDueDate.java
    @Override
    public void errorMessage(){
        System.out.println("No due date mentioned! After your description type /by {date}");
    }
}
