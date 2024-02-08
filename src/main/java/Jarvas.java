import java.util.Scanner;
public class Jarvas {
    public static void initialiseBot(){
        System.out.println(" _____                                  ");
        System.out.println("(___  )                                 ");
        System.out.println("    | |   _ _  _ __  _   _    _ _   ___ ");
        System.out.println(" _  | | /'_` )( '__)( ) ( ) /'_` )/',__)");
        System.out.println("( )_| |( (_| || |   | \\_/ |( (_| |\\__, \\");
        System.out.println("`\\___/'`\\__,_)(_)   `\\___/'`\\__,_)(____/");

        Reply.printReply("Hello! I'm Jarvas", "What can I do for you?");
    }
    public static void terminateBot(){
        Reply.printReply("Bye, see you soon!");
    }
    public static void runTime(){
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Task[] tasks = new Task[100];

        while (true) {
            String instruction = userInput.split(" ")[0];
            try {
                switch (instruction) {
                case "bye":
                    return;
                case "mark": {
                    String index = userInput.substring(5); // obtain value after mark
                    int i = Integer.parseInt(index) - 1; // convert to int
                    Task markTask = tasks[i];

                    tasks[i].setCompletedTrue();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markTask);
                    break;
                }
                case "unmark": {
                    String index = userInput.substring(7); // obtain value after unmark
                    int i = Integer.parseInt(index) - 1; // convert to int
                    Task markTask = tasks[i];

                    tasks[i].setCompletedFalse();

                    System.out.println("Okay, I've marked this task as not done yet:");
                    System.out.println(markTask);
                    break;
                }
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < Task.enumerateTask(); i++) {
                        System.out.println((i + 1) + ". "  + tasks[i]);
                    }
                    break;
                case "todo": {
                    int i = Task.enumerateTask();
                    String label = userInput.substring(5).trim();
                    ToDo toDo = new ToDo(label);


                    tasks[i] = toDo;
                    Reply.printReply(toDo, i+1);
                    break;
                }
                case "event": {
                    int i = Task.enumerateTask();
                    String eventDetails = userInput.substring(6).trim();
                    String[] eventParameters = Event.getInterval(eventDetails);
                    String eventLabel = eventParameters[0];
                    Event event = new Event(eventLabel, eventParameters[1], eventParameters[2]);

                    tasks[i] = event;
                    Reply.printReply(event, i+1);
                    break;
                }
                case "deadline": {
                    int i = Task.enumerateTask();
                    String deadlineDetails = userInput.substring(9).trim();
                    String[] deadlineParameters = Deadline.getDeadline(deadlineDetails);
                    String deadlineLabel = deadlineParameters[0];
                    Deadline deadline = new Deadline(deadlineLabel, deadlineParameters[1]);

                    tasks[i] = deadline;
                    Reply.printReply(deadline, i+1);
                    break;
                }
                default:
                    Reply.printReply("Invalid command provided");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid command format: " + e.getMessage());
            }

            Reply.printLine();
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        initialiseBot();
        runTime();
        terminateBot();
    }
}