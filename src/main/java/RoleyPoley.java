import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RoleyPoley {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        greet();
        while (true) {
            try {
                echo(taskList);
            } catch (RoleyPoleyException error) {
                System.out.println("\tError Detected!\n" + error);
                createLine();
            }
        }
    }

    private static void printReply(Task[] taskList, int counter) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  [" + taskList[counter].getTaskTypeIcon() + "][ ]" + taskList[counter].getDescription());
        System.out.println("\t Now you have " + counter + " tasks in the list.");
    }

    public static void displayList(Task[] taskList) {
        if (taskList[0] == null) {
            System.out.println("\tLooks like you need to find more work to do! Task list is empty!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 1; i < taskList.length; i++) {
                if (taskList[i] == null) {
                    break;
                }
                System.out.println("\t" + i + ".[" + taskList[i].getTaskTypeIcon() + "][" + taskList[i].getStatusIcon() + "]" + taskList[i].getDescription());
            }
        }
    }

    public static void createLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void greet() {
        System.out.println("Hello! I'm RoleyPoley \nWhat can I do for you today?");
        createLine();
    }

    public static void echo(Task[] taskList) throws RoleyPoleyException{
        String line;
        int counter = 1;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] splitString = line.split(" ");
            if (splitString[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                createLine();
                break;
            } else if (line.equals("list")) {
                displayList(taskList);
                createLine();
            } else if (splitString[0].equals("mark")) {
                String[] words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList[taskNum] == null) {
                        throw new RoleyPoleyException("Task " + taskNum + " cannot be found. Enter command 'list' to view task list.");
                    }
                    taskList[taskNum].markAsDone();
                    createLine();
                }
            } else if (splitString[0].equals("unmark")) {
                String[] words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList[taskNum] == null) {
                        throw new RoleyPoleyException("Task " + taskNum + " cannot be found. Enter command 'list' to view task list.");
                    }
                    taskList[taskNum].markAsUndone();
                    createLine();
                }
            } else if (splitString[0].equals("todo")) {
                String description = line.substring("todo".length());
                taskList[counter] = new Todo(description);
                printReply(taskList, counter);
                createLine();
                counter++;
            } else if (splitString[0].equals("deadline")) {
                String[] words = line.split("/by");
                if (words.length == 1) {
                    throw new RoleyPoleyException("Invalid entry. Please enter input in the following format:" +
                            "\ndeadline <Task Description> /by <Due Date>" );
                } else {
                    String description = words[0].substring("deadline".length());
                    String dueDate = words[1];
                    taskList[counter] = new Deadline(description, dueDate);
                    printReply(taskList, counter);
                    createLine();
                    counter++;
                }
            } else if (splitString[0].equals("event")) {
                String[] words = line.split("/from");
                if (words.length == 1) {
                    throw new RoleyPoleyException("Invalid entry. Please enter input in the following format:" +
                                "\nevent <Task Description> /from <Start Time> /to <End Time");
                } else {
                    String description = words[0].substring("event".length());
                    int indexOfEndTime = words[1].indexOf("/to");
                    if (indexOfEndTime == -1) {
                        throw new RoleyPoleyException("Invalid entry. Please enter input in the following format:" +
                                "\nevent <Task Description> /from <Start Time> /to <End Time");
                    } else {
                        String startTime = words[1].substring(0, indexOfEndTime - 1);
                        String endTime = words[1].substring(indexOfEndTime + "/to".length());
                        taskList[counter] = new Event(description, startTime, endTime);
                        printReply(taskList, counter);
                        createLine();
                        counter++;
                    }
                }
            } else {
                taskList[counter] = new Todo(" " + line);
                System.out.println("Task type not defined. Task is automatically assigned to todo task type.");
                printReply(taskList, counter);
                createLine();
                counter++;
            }
        }
    }
}



