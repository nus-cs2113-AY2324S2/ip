import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    static final int MAX_SIZE = 100;
    public static void main(String[] args) {
        printsGreeting();
        mimicMessage();
    }

    private static void handleError(ThawException e) {
        if (e.getMessage().startsWith("Empty command")) {
            System.out.println("OOPS!!! The description of a " + e.getMessage().substring(14) +" cannot be empty.");
        } else if (e.getMessage().equals("Invalid command")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void markOrUnmarkTask (String usersInput, ArrayList<Task> task) {
        int taskIndex;
        boolean isDone = false;
        if (usersInput.startsWith("mark")) {
            taskIndex = Integer.parseInt(usersInput.substring(5)) - 1 ;
            System.out.println("Nice! I've marked this task as done:");
            isDone = true;
        } else {
            taskIndex = Integer.parseInt(usersInput.substring(7)) - 1;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        task.get(taskIndex).isDone = isDone;
        System.out.println(task.get(taskIndex).getStatusIcon());
    }

    private static void mimicMessage() {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int currentIteration = 0;
        boolean canExit = false;

        while (!canExit) {
            String usersInput = input.nextLine();

            if (usersInput.equals("bye")) {
                canExit = true;
                printGoodByeMessage();
            } else if (usersInput.equals("list")) {
                printList(currentIteration, list);
            } else if ((usersInput.startsWith("unmark") && !usersInput.strip().endsWith("unmark")) ||
                    (usersInput.startsWith("mark") && !usersInput.strip().endsWith("unmark"))) {
                markOrUnmarkTask(usersInput, list);
            } else {
                try {
                    addTask(usersInput, list);
                    currentIteration++;
                } catch (ThawException e) {
                    handleError(e);
                }
            }
        }
    }

    private static void addTask(String usersInput, ArrayList<Task> list) throws ThawException {
        if (usersInput.strip().equals("todo") || usersInput.strip().equals("deadline") || usersInput.strip().equals("event")) {
            throw new ThawException("Empty command " + usersInput.strip());
        } else if (usersInput.startsWith("todo")) {
            list.add(new Todo(usersInput.substring(5), usersInput));
        } else if (usersInput.startsWith("deadline")) {
            int startingIndex = usersInput.indexOf("/by");
            list.add(new Deadline(usersInput.substring(9, startingIndex - 1),
                    usersInput.substring(startingIndex + 4)));
        } else if (usersInput.startsWith("event")) {
            int startIndex = usersInput.indexOf("from");
            int endIndex = usersInput.indexOf("to");
            list.add(new Event(usersInput.substring(6, startIndex - 2),
                    "from: " + usersInput.substring(startIndex+ 5, endIndex - 2) + " " + "to: " + usersInput.substring(endIndex + 3)));
        } else {
            throw new ThawException("Invalid command");
        }
        printAcknowledgementMessage(list);
    }

    private static void printsGreeting() {
        String greetingMessage = "Hello! I'm ThawBot!\nWhat can I do for you?\n";
        System.out.println(greetingMessage);
    }

    private static void printGoodByeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private static void printList(int index, ArrayList<Task> task) {
        for (int i = 0; i < task.size(); i ++) {
            System.out.println((i + 1) + ". " + task.get(i).getStatusIcon());
        }
    }

    private static void printAcknowledgementMessage(ArrayList<Task> task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.get(task.size() - 1).getStatusIcon());
        System.out.print("Now you have " + task.size() + " task in the list.");
    }
}
