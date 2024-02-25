package main;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import static storage.Storage.saveData;

public class Command {

    /**
     * Returns a list of tasks that contains the keyword.
     * @param keyword word to search in tasks.
     * @param listTask list of tasks in Task data type.
     * @param listString list of tasks in String data type.
     */
    public static void findMatchingTasks(String keyword, ArrayList<TaskList> listTask, ArrayList<String> listString) {
        ArrayList<String> searchList = new ArrayList<>();
        int index;
        int counter = 1;

        System.out.println("____________________________________________________________\n " +
                "Here are the matching tasks in your list:"
        );

        for (String s : listString) {
            if (s.startsWith("Marked:")) {
                searchList.add(s.substring(7));
                continue;
            }
            searchList.add(s.substring(10));
        }

        for (String task : searchList) {
            if (task.contains(keyword)) {
                index = searchList.indexOf(task);
                System.out.println(String.valueOf(counter) + '.' + listTask.get(index));
                counter += 1;
            }
        }
        System.out.println("____________________________________________________________");
    }


    /**
     * Removes task from both arrays.
     * @param listTask list of tasks in Task type.
     * @param listString list of tasks in String type.
     * @param splitLine words of user input split by whitespace.
     */
    public static void removeElementFromBothArrays(ArrayList<TaskList> listTask, ArrayList<String> listString, String[] splitLine) {
        try {
            int index = Integer.parseInt(splitLine[1]) - 1;
            TaskList t = listTask.get(index);
            listTask.remove(index);
            listString.remove(index);
            saveData(listString);
            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task:\n" +
                    t + "\n" +
                    "Now you have " + listTask.size() + " tasks in the list.");
        } catch(IndexOutOfBoundsException| NumberFormatException e) {
            System.out.println("Invalid index, please try again!");
        }
    }

    /**
     * @param listTask list of tasks in Task data type.
     * @param originalUserInput user input.
     * @param splitLine input split by whitespaces stored in an array.
     * @return the status of adding a ToDo task.
     */
    public static boolean addToDo(ArrayList<TaskList> listTask, String originalUserInput, String[] splitLine) {
        TaskList task;
        boolean success = true;

        if (checkMinimumArguments(splitLine, 2)) {
            success = false;
            return success;
        }
        try {
            task = new Todo(originalUserInput, true);
            System.out.println(task);
            listTask.add(task);
        } catch (RuntimeException e) {
            System.out.println("Invalid Syntax, please try again!");
            success = false;
        }
        return success;
    }

    /**
     * @param listTask list of tasks in Task data type.
     * @param originalUserInput user input.
     * @param splitLine input split by whitespaces stored in an array.
     * @return the status of adding a Deadline task.
     */
    public static boolean addDeadline(ArrayList<TaskList> listTask, String originalUserInput, String[] splitLine) {
        TaskList task;
        boolean success = true;

        if (checkMinimumArguments(splitLine, 4)) {
            success = false;
            return success;
        }
        try {
            task = new Deadline(originalUserInput, true);
            System.out.println(task);
            listTask.add(task);
        } catch (RuntimeException | DukeException e) {
            System.out.println("Invalid Syntax, please try again!");
            success = false;
        }
        return success;
    }

    /**
     * @param listTask list of tasks in Task data type.
     * @param originalUserInput user input.
     * @param splitLine input split by whitespaces stored in an array.
     * @return the status of adding an Event task.
     */
    public static boolean addEvent(ArrayList<TaskList> listTask, String originalUserInput, String[] splitLine) {
        TaskList task;
        boolean success = true;

        if (checkMinimumArguments(splitLine, 6)) {
            success = false;
            return success;
        }
        try {
            task = new Event(originalUserInput, true);
            System.out.println(task);
            listTask.add(task);
        } catch (RuntimeException e) {
            System.out.println("Invalid Syntax, please try again!");
            success = false;
        }
        return success;
    }

    /**
     * Mark the task to be completed.
     * @param userInput user's input.
     * @param listTask  list of tasks in Task type.
     * @param listString list of tasks in String type.
     */
    public static void markTasks(String userInput, ArrayList<TaskList> listTask, ArrayList<String> listString) {
        int index;
        String originalString, modifiedString;
        TaskList task;

        try {
            index = Integer.parseInt(userInput.substring(5));
            originalString = listString.get(index - 1);
            modifiedString = originalString.replace("notMarked:", "Marked:");
            listString.set(index - 1, modifiedString);

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Task number is not a valid number or index out of bounds!");
            return;
        }
        task = listTask.get(index - 1);
        task.isDone =  true;
        System.out.println("Nice! I've marked this task as done:");

        saveData(listString);
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Unmark the task to be uncompleted.
     * @param userInput user's input.
     * @param listTask  list of tasks in Task type.
     * @param listString list of tasks in String type.
     */
    public static void unMarkTasks(String userInput, ArrayList<TaskList> listTask, ArrayList<String> listString) {
        int index;
        String originalString, modifiedString;

        try {
            index = Integer.parseInt(userInput.substring(7));
            originalString = listString.get(index - 1);
            modifiedString = originalString.replace("Marked:", "notMarked:");
            listString.set(index - 1, modifiedString);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Task number is not a valid number or index out of bounds!");
            return;
        }
        TaskList task = listTask.get(index - 1);
        task.isDone =  false;
        System.out.println("OK, I've marked this task as not done yet:");

        saveData(listString);
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the list of tasks for the user.
     * @param listTask list of tasks in Task type.
     */
    public static void printList(ArrayList<TaskList> listTask) {
        for (TaskList task : listTask) {
            if (task == null) {
                break;
            }
            System.out.println(listTask.indexOf(task) + 1 + ". " + task);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Checks if the number of arguments supplied for each specific task is sufficient.
     * @param splitLine an array of words that are split by whitespace from user input.
     * @param number number of arguments required for the command to work.
     * @return true if number of arguments required is sufficient.
     */
    public static boolean checkMinimumArguments(String[] splitLine, int number) {
        try {
            if (splitLine.length < number) {
                throw new DukeException("Invalid syntax, please try again!");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    /**
     * Adds task into array of string data type, and save into the file.
     * @param listTask list of tasks in Task type.
     * @param listString list of tasks in String type.
     * @param userInput the exact format of the user input.
     */
    public static void saveDataIntoListString (ArrayList<TaskList> listTask, ArrayList<String> listString, String userInput) {
        String savedLine;
        savedLine = "notMarked:" + userInput;
        listString.add(savedLine);
        saveData(listString);
        System.out.println("Now you have " + listTask.size() + " tasks in the list.");
    }
}
