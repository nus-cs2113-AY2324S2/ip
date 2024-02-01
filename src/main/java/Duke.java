import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true){
            String userInput = scanner.nextLine();
            printLineSeparator();

            if (userInput.equals("bye")){
                printGoodbyeMessage();
                break;
            } else if(userInput.equals("list")){
                listTasks();
            }else{
                addTasks(userInput);
            }
        }

        scanner.close();
    }
    private static void printWelcomeMessage(){
        printLineSeparator();
        System.out.println("Hello! I'm Byte, your friendly chat assistant!");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }
    private static void printGoodbyeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }
    private static void addTasks(String task){
        if (taskCount < MAX_TASKS){
            tasks[taskCount] = task;
            taskCount ++;
            System.out.println("added: " + task);
        }else{
            System.out.println("Sorry, I can only handle up to " + MAX_TASKS + " tasks!");
        }
        printLineSeparator();
    }
    private static void listTasks(){
        for (int i=0; i<taskCount; i++){
            System.out.println((i+1) + ". " + tasks[i]);
        }
        printLineSeparator();
    }

    private static void printLineSeparator(){
        System.out.println("____________________________________________________________");
    }
}


