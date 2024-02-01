import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String chatbotName = "Noriaki";
    public static void printLine(){
        int lineUnderscore = 30;
        for(int i = 0; i < lineUnderscore; i++){
            System.out.print("_");
        }
        System.out.println();
    }

    public static void printMessage(String message){
        printLine();
        System.out.println(message);
        printLine();
    }

    public static void greet(){
        String greetMessage = "Hello! I'm " + chatbotName + "\nWhat can I do for you?";
        String logo =
                " _______               .__        __   .__ \n" +
                " \\      \\   ___________|__|____  |  | _|__|\n" +
                " /   |   \\ /  _ \\_  __ \\  \\__  \\ |  |/ /  |\n" +
                "/    |    (  <_> )  | \\/  |/ __ \\|    <|  |\n" +
                "\\____|__  /\\____/|__|  |__(____  /__|_ \\__|\n" +
                "        \\/                     \\/     \\/   \n";

        System.out.println("Hello from\n" + logo);
        printMessage(greetMessage);
    }

    public static void goodbye(){
        String goodbyeMessage = "Bye! Hope to see you again soon! MEGANE!!";

        printMessage(goodbyeMessage);
    }

    public static String readInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void echo(){
        boolean isRunning = true;
        while(isRunning){
            String input = readInput();
            if(input.equalsIgnoreCase("bye")) isRunning = false;
            else printMessage(input);
        }
    }

    public static void printList(List<Task> taskList){
        printLine();
        for(int i = 0; i < taskList.size(); i++){
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        printLine();
    }

    public static void addToList(List<Task> taskList, String description){
        Task newTask = new Task(description);
        taskList.add(newTask);
        printMessage("added: " + description);
    }

    public static void startList(){
        List<Task> taskList = new ArrayList<>();
        while(true){
            String input = readInput();

            if (input.equalsIgnoreCase("bye")) return;
            if (input.equalsIgnoreCase("list")) {
                printList(taskList);
            } else {
                addToList(taskList, input);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        startList();
        goodbye();
    }
}
