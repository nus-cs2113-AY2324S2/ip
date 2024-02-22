import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RoleyPoley {

    protected static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws IOException, RoleyPoleyException {
        //Task[] taskList = new Task[100];
        ReadFile.readFileToArrayList();
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



    private static void printAddReply(ArrayList<Task> taskList) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  [" + taskList.getLast().getTaskTypeIcon() + "][ ]" + taskList.getLast().getDescription());
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void printDelReply(ArrayList<Task> taskList, int taskNum) {
        System.out.println("\t Got it. I've deleted this task:");
        System.out.println("\t  [" + taskList.get(taskNum).getTaskTypeIcon() + "][ ]" + taskList.get(taskNum).getDescription());
        System.out.println("\t Now you have " + (taskList.size() - 1) + " tasks in the list.");
    }

    public static void displayList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\tLooks like you need to find more work to do! Task list is empty!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i) == null) {
                    break;
                }
                System.out.println("\t" + (i+1) + ".[" + taskList.get(i).getTaskTypeIcon() + "]["
                        + taskList.get(i).getStatusIcon() + "]" + taskList.get(i).getDescription());
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
        String line;
        String[] words;
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
                    if (taskList.size() < taskNum) {
                        throw new RoleyPoleyException("markError");
                    }
                    taskList.get(taskNum - 1).markAsDone();
                    createLine();
                }
                break;
            case "unmark":
                words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList.size() < taskNum) {
                        throw new RoleyPoleyException("unmarkError");
                    }
                    taskList.get(taskNum - 1).markAsUndone();
                    createLine();
                }
                break;
            case "todo":
                if (line.length() < 5) {
                    throw new RoleyPoleyException("toDoError");
                } else {
                    taskList.add(new Todo(line.substring("todo".length())));
                    printAddReply(taskList);
                    createLine();
                    //counter++;
                }
                break;
            case "deadline":
                if (!line.contains("/by")) {
                    throw new RoleyPoleyException("deadlineError");
                } else {
                    taskList.add(new Deadline(line.substring("deadline".length())));
                    printAddReply(taskList);
                    createLine();
                    //counter++;
                }
                break;
            case "event":
                //words = line.split("/from");
                if (!line.contains("/from") || !line.contains("/to")) {
                    throw new RoleyPoleyException("eventError");
                } else {
                    taskList.add(new Event(line.substring("event".length())));
                    printAddReply(taskList);
                    createLine();
                }
                    /*description = words[0].substring("event".length());
                    int indexOfEndTime = words[1].indexOf("/to");
                    if (indexOfEndTime == -1) {
                        throw new RoleyPoleyException("eventError");
                    } else {
                        String startTime = words[1].substring(0, indexOfEndTime - 1);
                        String endTime = words[1].substring(indexOfEndTime + "/to".length());
                        taskList.add(new Event(description, startTime, endTime));
                        printAddReply(taskList);
                        createLine();
                        //counter++;
                    }
                }*/
                break;
            case "delete":
                words = line.split(" ");
                if (words.length == 2) {
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskList.size() < taskNum) {
                        throw new RoleyPoleyException("deleteError");
                    }
                    printDelReply(taskList, taskNum - 1);
                    taskList.remove(taskNum - 1);
                    createLine();
                }
                break;
            default:
                throw new RoleyPoleyException("defaultError");
            }
        }
    }
}




