import drosstasks.List;
import myexceptions.InvalidTodoException;

import java.util.Scanner;

public class Dross {
    private static List drossTaskList = new List();
    private static Scanner in = new Scanner(System.in);

    //Method to print a line of _ characters
    public static void printLine() {
        System.out.println("_".repeat(55));
    }

    //Method to read in a line of text from standard input
    public static String readLine() {
        return in.nextLine().trim();
    }

    //Method to exit Dross bot
    public static void goodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    //Method to list all tasks
    public static void listAllTasks() {
        printLine();
        drossTaskList.printAllTasks();
        printLine();
    }

    //Method to toggle tasks as marked or unmarked
    public static void toggleMark(String instruction) {
        String[] tokens = instruction.split(" ");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (tokens[0].equals("mark")) {
                drossTaskList.markDoneByIndex(index);
            } else {
                drossTaskList.markUndoneByIndex(index);
            }
            listAllTasks();
        } catch (ArrayIndexOutOfBoundsException e){
            printLine();
            System.out.println("Yeah sure go ahead and mark that invisible task sir!");
            printLine();
        }
    }

    //Method to handle task creation and parse input to appropriately construct the correct object
    public static void handleTaskCreation(String line) {
        if (line.startsWith("todo")) {
            try {
                drossTaskList.addTask(line.substring("todo".length()).trim());
            } catch (InvalidTodoException e){
                System.out.println("You want to do nothing? Be my guest... Type it this way todo [task] if you are kind enough to stop wasting my time");
            }
        } else if (line.startsWith("deadline")) {
            String[] parts = line.substring("deadline".length()).trim().split(" /by ", 2);
            try {
                drossTaskList.addTask(parts[0], parts[1]);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Go ahead, live life with no deadlines. Procrastinate forever. deadline /by [time] is what you need to type, genius.");
            }
        } else if (line.startsWith("event")) {
            String[] parts = line.substring("event".length()).trim().split(" /from ", 2);
            try {
                String[] timeParts = parts[1].split(" /to ", 2);
                drossTaskList.addTask(parts[0], timeParts[0], timeParts[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("An event without a name, a start or end... What do you think you are, a philosopher?? event [name] /from [time1] /to [time2] is the way for goodness sakes...");
            }
        }
        drossTaskList.printLastTask();
        printLine();
    }

    //Method to determine what to do based on the input entered
    public static void receiveUserInput() {
        String line = readLine();
        while (!line.equals("bye")) {
            if (line.startsWith("list")) {
                listAllTasks();
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                toggleMark(line);
            } else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event")) {
                handleTaskCreation(line);
            }
            else if (line.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(line.split(" ")[1]);
                    drossTaskList.deleteTask(index);
                    System.out.println("Task " + index + " successfully deleted boss");
                    printLine();
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Delete what lah");
                    printLine();
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Delete simi delete");
                    printLine();
                }
            }
            else {
                printLine();
                System.out.println("Please enter a valid command");
                printLine();
            }
            line = readLine();
        }
        goodbye();
    }


    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Dross");
        System.out.println("What can I do for you?");
        printLine();
        receiveUserInput();
    }
}
