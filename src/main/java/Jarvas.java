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
            switch(userInput){
            case "bye":
                return;
            case "list":
                System.out.println(partitionLine);
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i+1) + ". " + tasks[i].label);
                }
                System.out.println(partitionLine);
                break;
            default:
                System.out.println(partitionLine);
                System.out.print("added: ");
                System.out.println(userInput);
                System.out.println(partitionLine);

                Task task = new Task(userInput);
                tasks[taskCounter] = task;
                taskCounter++;
                break;
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