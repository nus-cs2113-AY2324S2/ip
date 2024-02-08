import java.util.Scanner;


public class Omoh {
    //static List declared here so that whole Omoh class
    //can use the same size 100 array for myTaskList
    private static List myTaskList = new List();

    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("     Hello! I'm Omoh");
        System.out.println("     What can I do for you?");
        printHorizontalLine();
        readUserInput();
        bye();
    }

    //Method that prints horizontal line using "_" char
    public static void printHorizontalLine() {
        System.out.print("    ");
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
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
            if (line.equalsIgnoreCase("list")) {
                printAllTasks();
                line = in.nextLine();
            } else if (line.startsWith("deadline")) {
                myTaskList.addTaskAndDeadline(line);
                line = in.nextLine();
            }
            else if (line.startsWith("todo")) {
                myTaskList.addTodo(line);
                line = in.nextLine();
            }
            else if (line.startsWith("event")) {
                myTaskList.addEvent(line);
                line = in.nextLine();
            }
            else if (line.startsWith("mark") || line.startsWith("unmark")) {
                int taskNumber = Task.extractTaskNumber(line);
                Task.modifyDoneState(taskNumber, line);
                List.printMarkTask(taskNumber, line);
                line = in.nextLine();
            } else {
                myTaskList.addTask(line);
                myTaskList.printAddedTask();
                line = in.nextLine();
            }
        }
    }

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        printHorizontalLine();
        myTaskList.getAllTasks();
        printHorizontalLine();
    }

    //Method that prints the bye message
    public static void bye() {
        printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
