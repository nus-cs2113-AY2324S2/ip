import java.util.Scanner;
public class Jarvas {
    public static void initialiseBot() {
        Reply.printArt();
        Reply.printReply("Hello! I'm Jarvas", "What can I do for you?");
    }
    public static void terminateBot() {
        Reply.printReply("Bye, see you soon!");
    }
    public static void processInput() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        Task[] tasks = new Task[100];

        while (true) {
            String instruction = userInput.split(" ")[0];
            try {
                switch (instruction) {
                case "bye": {
                    return;
                }
                case "mark": {
                    String index = userInput.substring(4).trim(); // obtain value after mark
                    if (index.isEmpty()) {
                        throw new CustomException("Index to mark done is unspecified.");
                    }
                    int taskIndex = Integer.parseInt(index) - 1; // convert to int
                    if (taskIndex < 0 || taskIndex >= Task.enumerateTask()) {
                        throw new CustomException("Index to mark done is invalid and out of bounds");
                    }
                    Task markTask = tasks[taskIndex];

                    tasks[taskIndex].setCompletedTrue();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markTask);
                    break;
                }
                case "unmark": {
                    String index = userInput.substring(6).trim(); // obtain value after unmark
                    if (index.isEmpty()) {
                        throw new CustomException("Index to mark done is unspecified.");
                    }
                    int taskIndex = Integer.parseInt(index) - 1; // convert to int
                    if (taskIndex < 0 || taskIndex >= Task.enumerateTask()) {
                        throw new CustomException("Index to unmark is invalid and out of bounds");
                    }
                    Task markTask = tasks[taskIndex];

                    tasks[taskIndex].setCompletedFalse();

                    System.out.println("Okay, I've marked this task as not done yet:");
                    System.out.println(markTask);
                    break;
                }
                case "list":
                    if (Task.enumerateTask() == 0) {
                        throw new CustomException("List is empty.");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < Task.enumerateTask(); i++) {
                        System.out.println((i + 1) + ". "  + tasks[i]);
                    }
                    break;
                case "todo": {
                    int i = Task.enumerateTask();
                    String label = userInput.substring(4).trim();
                    if (label.isEmpty()) {
                        throw new CustomException("Todo cannot be left blank.");
                    }
                    ToDo toDo = new ToDo(label);


                    tasks[i] = toDo;
                    Reply.printReply(toDo, i+1);
                    break;
                }
                case "event": {
                    int taskCount = Task.enumerateTask();
                    String eventDetails = userInput.substring(5).trim();
                    if (eventDetails.isEmpty()) {
                        throw new CustomException("Event cannot be left blank.");
                    }
                    String[] eventParameters = Event.getInterval(eventDetails);
                    String eventLabel = eventParameters[0];
                    Event event = new Event(eventLabel, eventParameters[1], eventParameters[2]);

                    tasks[taskCount] = event;
                    Reply.printReply(event, taskCount+1);
                    break;
                }
                case "deadline": {
                    int taskCount = Task.enumerateTask();
                    String deadlineDetails = userInput.substring(8).trim();
                    if (deadlineDetails.isEmpty()) {
                        throw new CustomException("Deadline cannot be left blank.");
                    }
                    String[] deadlineParameters = Deadline.getDeadline(deadlineDetails);
                    String deadlineLabel = deadlineParameters[0];
                    Deadline deadline = new Deadline(deadlineLabel, deadlineParameters[1]);

                    tasks[taskCount] = deadline;
                    Reply.printReply(deadline, taskCount+1);
                    break;
                }
                case "help": {
                    Reply.printHelp();
                    break;
                }
                case "man": {
                    String command = userInput.substring(3).trim(); // obtain command after mark
                    Reply.printManual(command);
                    break;
                }
                default:
                    throw new CustomException("Invalid command. Enter 'help' to view available commands.");
                }
            } catch (CustomException e) {
                System.err.println("Custom Exception Caught!" + "\n" + e.getMessage());
            }

            Reply.printLine();
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        initialiseBot();
        processInput();
        terminateBot();
    }
}