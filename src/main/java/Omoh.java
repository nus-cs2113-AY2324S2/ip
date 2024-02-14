import java.util.Scanner;


public class Omoh {
    public static void main(String[] args) {
        printWelcomeMessage();
        //initialise the size 100 array
        Task.initArray();
        readUserInput();
        bye();
    }

    //Method that prints horizontal line using "_" char
    public static void printHorizontalLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("Hello! I'm Omoh");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    //Method that prints the bye message
    public static void bye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    //Method reads in what user types
    //If bye is not typed, we check 2 conditions
    //if list is typed, list out all tasks
    //if mark or unmark is typed, mark or unmark the task in tasklist
    //if none of the above, add the typed input to task list
    //If bye is typed, function exits
    public static void readUserInput() {
        String line;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.trim().isEmpty()) {
                System.out.println("Please enter a non empty Input!");
            } else if (line.equalsIgnoreCase("list")) {
                printAllTasks();
            } else if (line.startsWith("deadline")) {
                Deadline.addDeadline(line);
            }
            else if (line.startsWith("todo")) {
                Todo.addTodo(line);
            }
            else if (line.startsWith("event")) {
                Event.addEvent(line);
            }
            else if (line.startsWith("mark") || line.startsWith("unmark")) {
                int taskNumber = Task.extractTaskNumber(line);
                Task.modifyDoneState(taskNumber, line);
                Task.printMarkTask(taskNumber, line);
            } else {
                Task.addTask(line);
                Task.printAddedTask();
            }
            line = in.nextLine();
        }
    }

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        printHorizontalLine();
        Task.getAllTasks();
        printHorizontalLine();
    }
}
