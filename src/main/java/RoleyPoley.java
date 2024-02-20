import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RoleyPoley {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        //Task[] taskList = new Task[100];
        boolean isExit = false;
        greet();
        while(!isExit) {
            try {
                isExit = echo();
            } catch (RoleyPoleyException error) {
                createLine();
            }
        }
    }

    private static void printReply(ArrayList<Task> taskList, int counter) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  [" + taskList.get(counter).getTaskTypeIcon() + "][ ]" + taskList.get(counter).getDescription());
        System.out.println("\t Now you have " + (counter + 1) + " tasks in the list.");
    }

    public static void displayList(ArrayList<Task> taskList) {
        if (taskList.get(1) == null) {
            System.out.println("\tLooks like you need to find more work to do! Task list is empty!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) == null) {
                    break;
                }
                System.out.println("\t" + (i+1) + ".[" + taskList.get(i).getTaskTypeIcon() + "][" + taskList.get(i).getStatusIcon() + "]" + taskList.get(i).getDescription());
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

    public static boolean echo() throws RoleyPoleyException {
        int counter = 0;
        String line;
        String[] words;
        String description;
        //int counter = 1;
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
                    if (taskList.get(taskNum) == null) {
                        throw new RoleyPoleyException("markError");
                    }
                    taskList.get(taskNum).markAsDone();
                    createLine();
                }
                break;
            case "unmark":
                words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList.get(taskNum) == null) {
                        throw new RoleyPoleyException("unmarkError");
                    }
                    taskList.get(taskNum).markAsUndone();
                    createLine();
                }
                break;
            case "todo":
                description = line.substring("todo".length());
                if (description.isEmpty() || description.equals(" ")) {
                    throw new RoleyPoleyException("toDoError");
                } else {
                    taskList.add(new Todo(description));
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
                    taskList.add(new Deadline(description, dueDate));
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
                        taskList.add(new Event(description, startTime, endTime));
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




