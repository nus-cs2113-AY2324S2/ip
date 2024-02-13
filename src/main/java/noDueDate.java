public class noDueDate extends DeadlineException{
    public void errorMessage(){
        System.out.println("No due date mentioned! After your description type /by {date}");
    }
}
