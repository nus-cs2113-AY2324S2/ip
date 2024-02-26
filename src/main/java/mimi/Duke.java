package mimi;

import mimi.classes.Deadline;
import mimi.classes.Event;
import mimi.classes.Task;
import mimi.classes.ToDo;

import mimi.exceptions.MimiException;
import mimi.exceptions.MimiException.TaskNotFound;
import mimi.exceptions.MimiException.InsufficientParameters;
import mimi.exceptions.MimiException.IncorrectFormat;
import mimi.exceptions.MimiException.FileCorrupted;

import mimi.helper.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    final static String FILE_PATH = "data/mimi.logs";

    // DELIMITERS
    public final static String FILE_DELIMINITER = "|";
    public final static String DEADLINE_DELIMITER = "/by";

    public final static String EVENT_FROM_DELIMITER = "/from";
    public final static String EVENT_TO_DELIMITER = "/to";


    static ArrayList<Task> taskList = new ArrayList<>();

    static boolean isRunning;

    static Scanner scanner;

    private static Ui my_ui;




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
            my_ui.printSuccessMessage(toDo, taskList);
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

            String[] splitInputs = inputs[1].split(DEADLINE_DELIMITER, 2);
            Deadline deadline = Deadline.processDeadline(splitInputs);
            appendIntoTaskList(deadline);
            my_ui.printSuccessMessage(deadline, taskList);
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
            my_ui.printSuccessMessage(event, taskList);
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
        my_ui.printTasks(taskList);
        System.out.println("-------------------------------------------");
    }

    public static void deleteTask(ArrayList<Task> list, String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            Task removedTask = list.get(index);
            list.remove(index);
            my_ui.printDeleteMessage(removedTask, list);

        } catch (TaskNotFound | InsufficientParameters | IncorrectFormat e) {
            System.out.println(e.getMessage());
        }
    }

    public static void markTask(ArrayList<Task> list, String[] inputs) {
        try {
            int index = checkValidityOfIndex(inputs);
            list.get(index).markAsDone();
            my_ui.printMarkTask(list, index);
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
            my_ui.printUnmarkTask(list, index);
        } catch (TaskNotFound |
                 InsufficientParameters |
                 IncorrectFormat e) {
            System.out.println(e.getMessage());
        }

    }









    private static void saveFile(ArrayList<Task> taskList, String filePath) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task t : taskList) {
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
            while (fileScanner.hasNext()) {
                String[] task = fileScanner.next().split("\\" + FILE_DELIMINITER);
                if (task.length < 3) {
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

                if (taskStatus.equals("true")) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
            saveFile(taskList, FILE_PATH);

        } catch (IOException e) {
            System.out.println("\u001B[31mError:Unable to load file as " + filePath + " does not exists\u001B[0m");
        } catch (IncorrectFormat | InsufficientParameters | ArrayIndexOutOfBoundsException e) {
            throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
        }
    }


    private static ToDo loadToDo(String taskName, String taskStatus) throws FileCorrupted {
        ToDo toDo = new ToDo(taskName);

        if (taskStatus.equals("true")) {
            // mark as done
            toDo.markAsDone();
        } else if (taskStatus.equals("false")) {
            toDo.markAsUndone();
        } else {
            throw new FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
        }

        return toDo;
    }




    public static void main(String[] args) {

        // Initial welcome message
        my_ui = new Ui();

        try {
            loadFile(FILE_PATH);
        } catch (FileCorrupted e) {
            System.out.println(e.getMessage());
        }

        while (my_ui.isRunning()) {
            String[] inputs = my_ui.getInput();
            switch (inputs[0]) {
            case "bye":
                my_ui.shutdownSequence();
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
