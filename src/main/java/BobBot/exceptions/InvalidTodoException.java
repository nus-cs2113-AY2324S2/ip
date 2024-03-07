package BobBot.exceptions;
public class InvalidTodoException extends BobBotExceptions {

    @Override
    public void displayExceptionMessage() {
        System.out.println("\tIt seems that you have not entered the task information. "
                + "\n\tUsage: todo {description of task}"
                + "\n\tPlease try again, or enter /help if you need it");
    }
    
}
