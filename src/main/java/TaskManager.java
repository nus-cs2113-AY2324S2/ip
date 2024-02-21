import Exceptions.CorruptedFileException;
import Exceptions.DeadlineLackInputsException;
import Exceptions.EventLackInputsException;
import Exceptions.TodoLackInputsException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    public static final String DATA_TXT_FILE_PATH = "./data.txt";
    private static int numItems;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();

    public final String TODO_REQUIRED_INPUTS = "'todo <task>'";
    public final String DEADLINE_REQUIRED_INPUTS = "'Deadline <task> /by <due date>'";
    public final String EVENT_REQUIRED_INPUTS = "'Event <task> /from <start date> /to <end date>'";


    public TaskManager() {
        numItems = 0;
    }

    public void addListContents(String userInput) {
        try {
            String[] taskInformation = Parser.processTaskInformation(userInput);
            addByTaskToTaskArray(taskInformation, false);
            System.out.println("Got it. I've added this task:");
            System.out.println(taskArrayList.get(numItems));
            String taskPluralString = numItems > 1 ? " tasks" : " task";
            System.out.println("Now you have " + (numItems + 1) + taskPluralString + " in the list.");
            String dataToAppend = formatDataToAppend(taskInformation);
            if (numItems == 0) {
                FileProcessor.writeToFile(DATA_TXT_FILE_PATH, dataToAppend);
            }
            else {
                FileProcessor.appendToFile(DATA_TXT_FILE_PATH, dataToAppend);
            }
            numItems += 1;
        } catch (TodoLackInputsException e) {
            lackInputsErrorMessage(userInput, "todo", TODO_REQUIRED_INPUTS);
        } catch (DeadlineLackInputsException e) {
            lackInputsErrorMessage(userInput, "deadline", DEADLINE_REQUIRED_INPUTS);
        } catch (EventLackInputsException e) {
            lackInputsErrorMessage(userInput, "event", EVENT_REQUIRED_INPUTS);
        } catch (IndexOutOfBoundsException e) {
            // update this if /help is added
            if (userInput.contains("deadline")) {
                lackInputsErrorMessage(userInput, "deadline", DEADLINE_REQUIRED_INPUTS);
            } else if (userInput.contains("event")) {
                lackInputsErrorMessage(userInput, "event", EVENT_REQUIRED_INPUTS);
            }
        } catch (IOException e) {
            System.out.println("failed to append data to txt file");
        }

    }

    public static String formatDataToAppend(String[] taskInformation) {
        String output;
        switch (taskInformation[0]) {
        case ("todo"):
            output = "T,0," + taskInformation[1];
            break;

        case ("deadline"):
            output = "D,0," + taskInformation[1] + "," + taskInformation[2];
            break;

        case ("event"):
            output = "E,0," + taskInformation[1] + "," + taskInformation[2] + "," + taskInformation[3];
            break;

        default:
            output = "error";
        }
        return output;
    }

    private static void addByTaskToTaskArray(String[] taskInformation, boolean isDone) {
        switch (taskInformation[0]) {
        case ("todo"):
            taskArrayList.add(new Todo(taskInformation[1], isDone));
            break;

        case ("deadline"):
            taskArrayList.add(new Deadline(taskInformation[1], isDone, taskInformation[2]));
            break;

        case ("event"):
            taskArrayList.add(new Event(taskInformation[1], isDone, taskInformation[2],
                    taskInformation[3]));
            break;

        case ("error"):
            //should not hit this line
            System.out.println("Please give a proper input for todo/deadline/event");
            break;

        default:
            break;
        }
    }

    private static void lackInputsErrorMessage(String userInput, String errorType, String requiredInputs) {
        System.out.println("Your " + errorType + " task description seems to lack inputs. What you entered was " + userInput +
                ". Try typing it as " + requiredInputs);
    }

    public void showListContents() {
        if (numItems == 0) {
            System.out.println("List is empty. Please enter something first.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numItems; i += 1) {
                System.out.print(i + 1 + ". ");
                System.out.println(taskArrayList.get(i));
            }
        }
    }

    public static boolean isStringInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void changeTaskStatus(String userInput) {
        if (!isValidTaskId(userInput, "changeTaskStatus")) {
            return;
        }

        int id = Parser.processTaskIdforMarkingAndDeletingTask(userInput);

        if (userInput.contains("unmark")) {
            try {
                FileProcessor.changeTaskStatusData(DATA_TXT_FILE_PATH, id + 1, 0);
            } catch (FileNotFoundException e) {
                System.out.println("could not unmark task");
                return;
            }
            taskArrayList.get(id).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskArrayList.get(id));
        }
        // must contain mark at this point
        else {
            try {
                FileProcessor.changeTaskStatusData(DATA_TXT_FILE_PATH, id + 1, 1);
            } catch (FileNotFoundException e) {
                System.out.println("could not mark task");
                return;
            }
            taskArrayList.get(id).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskArrayList.get(id));
        }
    }

    public boolean isValidTaskId(String userInput, String purpose) {
        userInput = userInput.toLowerCase();
        String[] wordArray = userInput.split(" ");
        String commandStructure;
        if (purpose.equals("changeTaskStatus")) {
            commandStructure = "\"mark x or unmark x\"";
        } else if (purpose.equals("deleteTask")) {
            commandStructure = "\"delete x\"";
        } else {
            System.out.println("not a valid purpose =(");
            return false;
        }

        if (wordArray.length != 2 || !isStringInteger(wordArray[1])) {
            System.out.println("Please give a command in the structure of " + commandStructure +
                    "where x is the task number");
            return false;
        }

        int id = Integer.parseInt(wordArray[1]) - 1;
        if (id >= numItems || id < 0) {
            System.out.println("Please select a task number that exists. =)");
            return false;
        }
        return true;
    }

    public void deleteTask(String userInput) {
        if (!isValidTaskId(userInput, "deleteTask")) {
            return;
        }
        int id = Parser.processTaskIdforMarkingAndDeletingTask(userInput);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskArrayList.get(id));
        numItems -= 1;
        String taskPluralString = numItems > 1 ? " tasks" : " task";
        System.out.println("Now you have " + numItems + taskPluralString + " in the list.");
        try {
            FileProcessor.removeLineData(DATA_TXT_FILE_PATH, id + 1);
        } catch (FileNotFoundException e) {
            System.out.println("unable to delete task. Please ensure that you have input the correct command");
        }
        taskArrayList.remove(id);
    }

    public static void loadTasks(ArrayList<String> dataArrayList) {
        for (String line : dataArrayList) {
            try {
                String[] output = Parser.processTaskLoadingData(line);
                boolean isDone = output[1].equals("1");
                String[] filteredOutput = new String[4];
                filteredOutput[0] = output[0];
                System.arraycopy(output, 2, filteredOutput, 1, 3);
                addByTaskToTaskArray(filteredOutput, isDone);
                numItems += 1;
            } catch (CorruptedFileException e) {
                System.out.println("File is corrupted");
                FileProcessor.clearFile(DATA_TXT_FILE_PATH);
                System.out.println("Successfully restarted file");
                numItems = 0;
                taskArrayList.clear();
                return;
            }
        }
    }

}
