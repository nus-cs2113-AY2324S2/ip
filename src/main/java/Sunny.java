import java.util.Scanner;

public class Sunny {
    public static void main(String[] args) {
        String chatBotName = "Sunny";
        Task[] tasks = new Task[100]; //Fixed-size array to store tasks
        int counter = 0; //Counter to keep track of the number of tasks

        //Greets the user
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(" ");

        //Initialises scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Echoes commands after user says bye
        while (true) {
            //Gets user input
            String command = scanner.nextLine();;

            //If the user input is "bye"
            if (command.equalsIgnoreCase("bye")){
                //Exit message
                System.out.println(" ");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(" ");
                break;
            } else if (command.equalsIgnoreCase("list")){
                //Displays the list of tasks
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < counter; i ++) {
                    //
                    System.out.println((i + 1) + ". [" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
                }
                System.out.println(" ");
            } else if (command.startsWith("mark")){
                //Mark a task as done
                int taskIndex = extractTaskIndex(command);
                if (taskIndex > 0 && taskIndex <= counter) {
                    tasks[taskIndex - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].getDescription());
                } else {
                    System.out.println("Invalid task index. Please provide a valid task index.");

                }
                System.out.println(" ");
            } else if (command.startsWith("unmark")){
                // Mark a task as not done
                int taskIndex = extractTaskIndex(command);
                if (taskIndex > 0 && taskIndex <= counter) {
                    tasks[taskIndex - 1].unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].getDescription());
                } else {
                    System.out.println(" Invalid task index. Please provide a valid task index.");
                }
                System.out.println(" ");
            } else {
                //Display the added task message
                System.out.println("added: " + command);
                System.out.println(" ");

                //Add the command to the tasks array
                tasks[counter] = new Task(command);
                counter++;
            }
        }
        //Close the scanner
        scanner.close();
    }

    // Helper method to extract the task index from commands like "mark 2"
    private static int extractTaskIndex(String command) {
        try {
            return Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }
}

// Task class to represent tasks
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}
