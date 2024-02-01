import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String chatbotName = "Noriaki";

    /**
     * Prints line of 30 underscores.
     */
    public static void printLine(){
        int lineUnderscore = 30;
        for(int i = 0; i < lineUnderscore; i++){
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Prints line, followed by message, followed by line.
     *
     * @param message Message to be printed.
     */
    public static void printMessage(String message){
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints greeting.
     */
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

    /**
     * Prints goodbye message.
     */
    public static void goodbye(){
        String goodbyeMessage = "Bye! Hope to see you again soon! MEGANE!!";

        printMessage(goodbyeMessage);
    }

    /**
     * Reads user input.
     *
     * @return String User input.
     */
    public static String readInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    /**
     * Reads user input and prints user input.
     */
    public static void echo(){
        boolean isRunning = true;
        while(isRunning){
            String input = readInput();
            if(input.equalsIgnoreCase("bye")) {
                isRunning = false;
            }
            else printMessage(input);
        }
    }

    /**
     * Prints list of tasks.
     *
     * @param taskList List of tasks to be printed.
     */
    public static void printList(List<Task> taskList){
        printLine();
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
        }
        printLine();
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param taskList List of tasks to be printed.
     * @param description description of task to be added.
     */
    public static void addToList(List<Task> taskList, String description){
        Task newTask = new Task(description);
        taskList.add(newTask);
        printMessage("added: " + description);
    }

    /**
     * Marks task as done or undone.
     *
     * @param taskList List of tasks.
     * @param instruction User instruction on which task to mark.
     */
    public static void markTask(List<Task> taskList, String instruction) {
        boolean done = true;
        String message;

        if (instruction.substring(0, 4).equalsIgnoreCase("mark")) {
            done = true;
        } else if (instruction.substring(0, 6).equalsIgnoreCase("unmark")) {
            done = false;
        }

        int index = (new Scanner(instruction).useDelimiter("\\D+").nextInt()) - 1;
        message = done ? "Nice! I've marked this task as done:\n" + "   [X] "
                + taskList.get(index).getDescription() :
                " OK, I've marked this task as not done yet:\n" + "   [ ] "
                + taskList.get(index).getDescription();

        try {
            taskList.get(index).setDone(done);
            printMessage(message);
        } catch (Exception e){
            printMessage("Invalid mark instruction ZENBU FAKE!!");
        }
    }

    /**
     * Creates a list that users can add tasks to, read, and mark tasks as done or undone.
     */
    public static void startList(){
        List<Task> taskList = new ArrayList<>();
        while(true){
            String input = readInput();

            if (input.equalsIgnoreCase("bye")) {
                return;
            } else if (input.equalsIgnoreCase("list")) {
                printList(taskList);
            } else if (input.substring(0, 4).equalsIgnoreCase("mark") ||
                    input.substring(0, 6).equalsIgnoreCase("unmark")) {
                markTask(taskList, input);
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
