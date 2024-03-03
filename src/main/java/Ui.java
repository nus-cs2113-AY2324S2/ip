import java.util.ArrayList;
import java.util.Scanner;

// Includes methods for messages to show to the user, i.e. intro, error messages, confirmation messages
public class Ui  {
    public void printIntroMessage(){
        String logo = "\n" +
                " _____  _    _                   _____ ______ \n" +
                "|  _  || |  | |                 |  __ \\|  ___|\n" +
                "| | | || |_ | |__    ___  _ __  | |  \\/| |_   \n" +
                "| | | || __|| '_ \\  / _ \\| '__| | | __ |  _|  \n" +
                "\\ \\_/ /| |_ | | | ||  __/| |    | |_\\ \\| |    \n" +
                " \\___/  \\__||_| |_| \\___||_|     \\____/\\_|    \n" +
                "                                              \n" +
                "                                              \n";
        System.out.println("Welcome! I'm your \n" + logo + "Nice to meet you!");
    }

    public void printBreakLine(){
        System.out.println(("____________________________________________________________"));
    }

    public String getInput(){
        System.out.println("What can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        return inputScanner.nextLine();
    }

    public void printList(TaskList taskList){
        ArrayList<Task> tasks = taskList.getTaskList();
        System.out.println("Here are your tasks: ");
        for (int i = 0; i < tasks.size(); i++){
            System.out.printf("%d. ", i+1);
            System.out.println(tasks.get(i).toString());
        }
        printBreakLine();
    }
    public void printByeMessage(){
        System.out.println("Bye bye now!");
        System.out.println(("____________________________________________________________"));

    }

    public void printMarkMessage(boolean isMark, Task task){
        if (isMark){
            System.out.println("Good Job! I'm setting this task as done: ");
            }
        else{
            System.out.println("Oop! I'm setting this task as undone: ");
        }
        System.out.println(task);
        printBreakLine();
    }
    public void printIOException(){
        System.out.println("Encountered an error trying to access hard drive file. Closing program.");
    }

    public void printTaskAdded(Task task, TaskList taskList){
        System.out.println("Alright, adding this task to the list: ");
        System.out.println(task);
        System.out.printf("You have %d tasks in the list.%n", taskList.getSize());
        printBreakLine();
    }

    public void printOGFException(OGFException error){
        System.out.println(error.getMessage());
        printBreakLine();
    }
}
