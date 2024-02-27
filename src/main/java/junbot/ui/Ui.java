package junbot.ui;
import junbot.error.InvalidInputException;
import junbot.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected static String DIVIDER = "____________________________________________________________";
    protected static String GREETING = "Hello! I'm JunBot\nWhat can I do for you?";
    protected static String GOODBYE = "Bye. Hope to see you again soon!";

    protected static Scanner userInputScanner;

    public Ui(){
        userInputScanner = new Scanner(System.in);
    }

    public void printDivider(){
        System.out.println(DIVIDER);
    }
    public void printWelcomeMessage(){
        printDivider();
        System.out.println(GREETING);
        printDivider();
    }

    public void printGoodbyeMessage(){
        printDivider();
        System.out.println(GOODBYE);
        printDivider();
    }

    public void printDeleteSuccessMessage(Task userTask){
        printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println(userTask);
        printDivider();
    }

    public void printAddSuccessMessage(Task userTask){
        System.out.println("Got it. I've added this tasks:");
        System.out.println(userTask);
    }

    public void printUnmarkSuccessMessage(Task userTask){
        printDivider();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(userTask);
        printDivider();
    }

    public void printMarkSuccessMessage(Task userTask){
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(userTask);
        printDivider();
    }

    public void printNumTasks(ArrayList<Task> tasks){
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public String readCommand() {
        return userInputScanner.nextLine();
    }

}
