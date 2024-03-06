import roleypoley.data.ReadFile;
import roleypoley.data.WriteFile;
import roleypoley.exception.RoleyPoleyFileException;
import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.task.Deadline;
import roleypoley.task.Event;
import roleypoley.task.Task;
import roleypoley.task.Todo;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class RoleyPoley {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    private static String myPath = "./src/main/java/RoleyPoleyData.txt";

    public static void main(String[] args) throws RoleyPoleyParseException, RoleyPoleyFileException {
        ReadFile.readFileToArrayList();
        boolean isExit = false;
        greet();
        while (!isExit) {
            isExit = echo();
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

    public static boolean echo() throws RoleyPoleyParseException {
        String line;
        String[] words;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] splitString = line.split(" ");

            switch (splitString[0]) {
            case "bye":
                ExitCommand();
                createLine();
                return true;
            case "list":
                displayList(taskList);
                createLine();
                break;
            case "mark":
                MarkCommand(line);
                break;
            case "unmark":
                UnmarkCommand(line);
                break;
            case "todo":
                AddCommand("T", line);
                break;
            case "deadline":
                AddCommand("D", line);
                break;
            case "event":
                AddCommand("E", line);
                break;
            case "delete":
                DeleteCommand(line);
                break;
            default:
                throw new RoleyPoleyParseException("defaultError");
            }
        }
    }

    private static void MarkCommand(String line) throws RoleyPoleyParseException {
        String[] words;
        words = line.split(" ");
        if (words.length == 2) {
            int taskNum = Integer.parseInt(words[1]);
            if (taskList.size() < taskNum) {
                throw new RoleyPoleyParseException("markError");
            }
            taskList.get(taskNum - 1).markAsDone();
            createLine();
        }
    }

    private static void UnmarkCommand(String line) throws RoleyPoleyParseException {
        String[] words;
        words = line.split(" ");
        if (words.length == 2) {
            int taskNum = Integer.parseInt(words[1]);
            if (taskList.size() < taskNum) {
                throw new RoleyPoleyParseException("unmarkError");
            }
            taskList.get(taskNum - 1).markAsUndone();
            createLine();
        }
    }


    private static void AddCommand(String taskType,String line) throws RoleyPoleyParseException {
        switch (taskType) {
        case "T":
            AddToDoCommand(line);
            break;
        case "D":
            AddDeadlineCommand(line);
            break;
        case "E":
            AddEventCommand(line);
            break;
        default:
            throw new RoleyPoleyParseException("defaultError");
        }
    }

    private static void AddToDoCommand(String line) throws RoleyPoleyParseException {
        if (line.length() < 5) {
            throw new RoleyPoleyParseException("toDoError");
        } else {
            taskList.add(new Todo(line.substring("todo".length()), false));
            printAddReply(taskList);
            createLine();
        }
    }

    private static void AddDeadlineCommand(String line) throws RoleyPoleyParseException {
        if (!line.contains("/by")) {
            throw new RoleyPoleyParseException("deadlineError");
        } else {
            taskList.add(new Deadline(line.substring("deadline".length()), false));
            printAddReply(taskList);
            createLine();
        }
    }

    private static void AddEventCommand(String line) throws RoleyPoleyParseException {
        if (!line.contains("/from") || !line.contains("/to")) {
            throw new RoleyPoleyParseException("eventError");
        } else {
            taskList.add(new Event(line.substring("event".length()), false));
            printAddReply(taskList);
            createLine();
        }
    }

    private static void DeleteCommand(String line) throws RoleyPoleyParseException {
        String[] words;
        words = line.split(" ");
        if (words.length == 2) {
            int taskNum = Integer.parseInt(words[1]);
            if (taskList.size() < taskNum) {
                throw new RoleyPoleyParseException("deleteError");
            }
            printDelReply(taskList, taskNum - 1);
            taskList.remove(taskNum - 1);
            createLine();
        }
    }

    private static void ExitCommand() {
        try {
            WriteFile.writeToFile(myPath, taskList);
        } catch (IOException ex) {
            System.out.println("File Error!");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}




