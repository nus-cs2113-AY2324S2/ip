import java.util.Scanner;
public class Jarvas {
    public static final String partitionLine = "____________________________________________________________";
    public static void initialiseBot(){
        System.out.println(" _____                                  ");
        System.out.println("(___  )                                 ");
        System.out.println("    | |   _ _  _ __  _   _    _ _   ___ ");
        System.out.println(" _  | | /'_` )( '__)( ) ( ) /'_` )/',__)");
        System.out.println("( )_| |( (_| || |   | \\_/ |( (_| |\\__, \\");
        System.out.println("`\\___/'`\\__,_)(_)   `\\___/'`\\__,_)(____/");

        System.out.println(partitionLine);
        System.out.println("Hello! I'm Jarvas");
        System.out.println("What can I do for you?");
        System.out.println(partitionLine);
    }
    public static void terminateBot(){
        System.out.println(partitionLine);
        System.out.println("Bye, see you soon!");
        System.out.println(partitionLine);
    }
    public static void runTime(){
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        while (true) {
            if (userInput.equals("bye")) {
                return;
            } else if (userInput.startsWith("mark")) {
                System.out.println(partitionLine);
                String index = userInput.substring(5); // obtain value after mark
                int i = Integer.parseInt(index) - 1; // convert to int
                tasks[i].setCompletedTrue();

                System.out.println("Nice! I've marked this task as done:");
                tasks[i].printTask();
                System.out.println(partitionLine);
            } else if (userInput.startsWith("unmark")) {
                System.out.println(partitionLine);
                String index = userInput.substring(7); // obtain value after unmark
                int i = Integer.parseInt(index) - 1; // convert to int
                tasks[i].setCompletedFalse();

                System.out.println("Okay, I've marked this task as not done yet:");
                tasks[i].printTask();
                System.out.println(partitionLine);
            } else if (userInput.equals("list")) {
                System.out.println(partitionLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.print((i + 1) + ".");
                    tasks[i].printTask();
                }
                System.out.println(partitionLine);
            } else {
                System.out.println(partitionLine);
                System.out.print("added: ");
                System.out.println(userInput);
                System.out.println(partitionLine);

                Task task = new Task(userInput);
                tasks[taskCounter] = task;
                taskCounter++;
            }
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        initialiseBot();
        runTime();
        terminateBot();
    }
}