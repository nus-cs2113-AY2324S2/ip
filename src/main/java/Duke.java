import classes.Deadline;
import classes.Event;
import classes.Task;
import classes.ToDo;
import exceptions.MimiException;
import exceptions.MimiException.TaskNotFound;
import exceptions.MimiException.InsufficientParameters;
import exceptions.MimiException.IncorrectFormat;
import java.util.ArrayList;
import exceptions.MimiException.FileCorrupted;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    final static String APP_NAME = "mimichat";
    final static int INPUT_LIMIT = 2;
    final static String FILE_PATH = "data/mimi.logs";

    static ArrayList<Task> taskList = new ArrayList<>();

    static boolean isRunning;

    static Scanner scanner;


    // METHODS TO GET INPUT
    private static String[] getInput() {
        String input = scanner.nextLine();
        return input.split(" ", INPUT_LIMIT);
    }

    private static void addToDo(String[] inputs) {

        try {
            if (inputs.length != 2) {
                throw new InsufficientParameters(MimiException.INSUFFICIENT_TODO_PARAMETERS_MSG);
            }
            if (inputs[1].isBlank()) {
                throw new IncorrectFormat(MimiException.INCORRECT_TODO_FORMAT_MSG);
            }

            ToDo toDo = new ToDo(inputs[1]);
            appendIntoTaskList(toDo);
            printSuccessMessage(toDo);
        } catch (InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addDeadline(String[] inputs) {

        try {
            // Throws exception if deadline parameters are incomplete
            if (inputs.length < 2) {
                throw new InsufficientParameters(MimiException.INSUFFICIENT_DEADLINE_PARAMETERS_MSG);
            }

            String[] splitInputs = inputs[1].split("/by", 2);
            Deadline deadline = Deadline.processDeadline(splitInputs);
            appendIntoTaskList(deadline);
            printSuccessMessage(deadline);
        } catch (InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEvent(String[] inputs) {
        try {
            if (inputs.length != 2) {
                throw new InsufficientParameters(MimiException.INSUFFICIENT_EVENT_PARAMETERS_MSG);
            }

            Event event = Event.processEvent(inputs[1]);
            appendIntoTaskList(event);
            printSuccessMessage(event);
        } catch (InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }

    private static void appendIntoTaskList(Task newTask) {
        taskList.add(newTask);
    }

    public static void listTasks(ArrayList<Task> list) {
        System.out.println("-------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (Task t: list) {
            System.out.println(formatTask(t, list.indexOf(t)));
        }
        System.out.println("-------------------------------------------");
    }

    public static void deleteTask(ArrayList<Task> list, String[] inputs){
        try {
            int index = checkValidityOfIndex(inputs);
            Task removedTask = list.get(index);
            list.remove(index);
            printDeleteMessage(removedTask);
            
        } catch (TaskNotFound | InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    public static void markTask(ArrayList<Task> list, String[] inputs) {

        try {
            int index = checkValidityOfIndex(inputs);
            list.get(index).markAsDone();
            printMarkTask(list, index);
        } catch (TaskNotFound | InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    private static int checkValidityOfIndex(String[] inputs) throws TaskNotFound,
            InsufficientParameters, IncorrectFormat {
        if (inputs.length != 2) {
            // Throws an error if parameters is incomplete
            throw new InsufficientParameters(MimiException.INSUFFICIENT_INDEX_PARAMETERS_MSG);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                // Throws an error if task is not found
                throw new TaskNotFound(MimiException.TASK_NOT_FOUND_MSG);
            }
            return index;
        } catch (NumberFormatException e) {
            // Throws an error if the format is incorrect
            throw new IncorrectFormat(MimiException.INCORRECT_INDEX_FORMAT_MSG);
        }
    }

    public static void unmarkTask(ArrayList<Task> list, String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            list.get(index).markAsUndone();
            printUnmarkTask(list, index);
        } catch (TaskNotFound |
                 InsufficientParameters |
                 IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }



    // METHOD REGARDING PRINT FORMATTING
    private static void printMarkTask(ArrayList<Task> list, int index) {
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as done");
        System.out.println(formatTask(list.get(index), index));
        System.out.println("-------------------------------------------");
    }

    private static void printUnmarkTask(ArrayList<Task> list, int index) {
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(formatTask(list.get(index), index));
        System.out.println("-------------------------------------------");
    }

    public static void printDescription(String input) {
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");
    }

    private static void printSuccessMessage(Task task) {
        System.out.println("-------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + formatTask(task));
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks in the list");
        System.out.println("-------------------------------------------");
    }

    private static void printDeleteMessage(Task task) {
        System.out.println("-------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + formatTask(task));
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks in the list");
        System.out.println("-------------------------------------------");
    }

    public static String formatTask(Task task) {
        return "\t" + task;
    }

    public static String formatTask(Task task, int index) {
        String indexNumber = Integer.toString(index + 1);
        return indexNumber + ". " + task;
    }

    // STARTUP AND SHUTDOWN SEQUENCES
    public static void startupSequence() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello! I'm " + APP_NAME);
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------");
        isRunning = true;
        scanner = new Scanner(System.in);
    }

    private static void shutdownSequence() {
        printDescription("Bye. Hope to see you again soon!");
        isRunning = false;
        scanner.close();
    }

    private static void saveFile(ArrayList<Task> taskList , String filePath){
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task t: taskList) {
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("\u001B[31mError:Unable to save file as " + filePath + " does not exists. Please create a " +
                    "/data folder in the root directory first.\u001B[0m");
        }
    }

    private static void loadFile(String filePath) throws FileCorrupted {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            fileScanner.useDelimiter("\n");
            while(fileScanner.hasNext()){
                String[] task = fileScanner.next().split(",");

                if(task.length < 3) {
                    throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
                }

                String taskType = task[0];
                String taskStatus = task[1];
                String taskName = task[2];

                if (!taskStatus.equals("true") && !taskStatus.equals("false")) {
                    throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
                }

                switch (taskType) {
                case "T":
                    ToDo toDo = loadToDo(taskName, taskStatus);
                    appendIntoTaskList(toDo);
                    break;
                case "D":
                    String until = task[3];
                    String[] deadlineParameter = {taskName, until};
                    Deadline deadline = Deadline.processDeadline(deadlineParameter);
                    appendIntoTaskList(deadline);
                    break;
                case "E":
                    String to = task[3];
                    String from = task[4];
                    Event event = Event.processEvent(taskName, to, from);
                    if (taskStatus.equals("true")) {
                        event.markAsDone();
                    }
                    appendIntoTaskList(event);
                    break;
                default:
                    throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
                }

                if(taskStatus.equals("true")) {
                    taskList.get(taskList.size()-1).markAsDone();
                }
            }
            saveFile(taskList, FILE_PATH);

        } catch (IOException e) {
            System.out.println("\u001B[31mError:Unable to load file as " + filePath + " does not exists\u001B[0m");
        } catch(IncorrectFormat | InsufficientParameters | ArrayIndexOutOfBoundsException e) {
            throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
        }
    }



    private static ToDo loadToDo(String taskName, String taskStatus) throws FileCorrupted {
        ToDo toDo = new ToDo(taskName);

        if (taskStatus.equals("true")) {
            // mark as done
            toDo.markAsDone();
        }
        else if (taskStatus.equals("false")){
            toDo.markAsUndone();
        }
        else{
            throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
        }

        return toDo;
    }


    public static void main(String[] args) {

        // Initial welcome message
        startupSequence();

        try{
            loadFile(FILE_PATH);
        } catch (FileCorrupted e){
            System.out.println(e.getMessage());
        }

        while (isRunning) {
            String[] inputs = getInput();
            switch (inputs[0]) {
            case "bye":
                shutdownSequence();
                break;
            case "list":
                listTasks(taskList);
                break;
            case "mark":
                markTask(taskList, inputs);
                break;
            case "unmark":
                unmarkTask(taskList, inputs);
                break;
            case "todo":
                addToDo(inputs);
                break;
            case "deadline":
                addDeadline(inputs);
                break;
            case "event":
                addEvent(inputs);
                break;
            case "delete":
                deleteTask(taskList, inputs);
                break;
            default:
                // raise invalid instruction
                try {
                    throw new IncorrectFormat(MimiException.INCORRECT_INSTRUCTION_MSG);
                } catch (IncorrectFormat e) {
                    System.out.println(e.getMessage());
                }
                break;
            }

            saveFile(taskList, FILE_PATH);
        }
    }


}
