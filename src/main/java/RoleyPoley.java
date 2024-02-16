import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RoleyPoley {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        boolean isExit = false;
        greet();
        while(!isExit) {
            try {
                isExit = echo(taskList);
            } catch (RoleyPoleyException error) {
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
        if (taskList[1] == null) {
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

    public static boolean echo(Task[] taskList) throws RoleyPoleyException {

        String line;
        String[] words;
        String description;
        int counter = 1;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] splitString = line.split(" ");

            switch (splitString[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                createLine();
                return true;
            case "list":
                displayList(taskList);
                createLine();
                break;
            case "mark":
                words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList[taskNum] == null) {
                        throw new RoleyPoleyException("markError");
                    }
                    taskList[taskNum].markAsDone();
                    createLine();
                }
                break;
            case "unmark":
                words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList[taskNum] == null) {
                        throw new RoleyPoleyException("unmarkError");
                    }
                    taskList[taskNum].markAsUndone();
                    createLine();
                }
                break;
            case "todo":
                description = line.substring("todo".length());
                if (description.isEmpty() || description.equals(" ")) {
                    throw new RoleyPoleyException("toDoError");
                } else {
                    taskList[counter] = new Todo(description);
                    printReply(taskList, counter);
                    createLine();
                    counter++;
                }
                break;
            case "deadline":
                words = line.split("/by");
                if (words.length == 1) {
                    throw new RoleyPoleyException("deadlineError");
                } else {
                    description = words[0].substring("deadline".length());
                    String dueDate = words[1];
                    taskList[counter] = new Deadline(description, dueDate);
                    printReply(taskList, counter);
                    createLine();
                    counter++;
                }
                break;
            case "event":
                words = line.split("/from");
                if (words.length == 1) {
                    throw new RoleyPoleyException("eventError");
                } else {
                    description = words[0].substring("event".length());
                    int indexOfEndTime = words[1].indexOf("/to");
                    if (indexOfEndTime == -1) {
                        throw new RoleyPoleyException("eventError");
                    } else {
                        String startTime = words[1].substring(0, indexOfEndTime - 1);
                        String endTime = words[1].substring(indexOfEndTime + "/to".length());
                        taskList[counter] = new Event(description, startTime, endTime);
                        printReply(taskList, counter);
                        createLine();
                        counter++;
                    }
                }
                break;
            default:
                throw new RoleyPoleyException("defaultError");
            }
        }
    }
}




