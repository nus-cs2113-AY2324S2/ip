package main;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import static storage.Storage.saveData;

public class Command {
    public enum Commands {
        Todo, Deadline, Event
    }


    /**
     * Removes task from both arrays.
     * @param listTask list of tasks in Task type.
     * @param listString list of tasks in String type.
     * @param splitLine words of user input split by whitespace.
     * @return true if removing of task from both arrays is successful.
     */
    public static boolean removeElementFromBothArrays(ArrayList<TaskList> listTask, ArrayList<String> listString, String[] splitLine) {
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
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please try again!");
            return false;
        }
        return true;
    }

    /**
     * Adds task into the array of type Task.
     * @param listTask list of tasks in Task type.
     * @param originalUserInput user input.
     * @param splitLine words of user input split by whitespace.
     * @param typeOfTask special data type for the 3 tasks.
     * @return true if adding of task into array of type Task is successful.
     */
    public static boolean addTask(ArrayList<TaskList> listTask, String originalUserInput, String[] splitLine, Commands typeOfTask) {
        TaskList t;
        boolean success = true;


        switch (typeOfTask) {
            case Todo:
                if (checkMinimumArguments(splitLine, 2)) {
                    success = false;
                    break;
                }
                try {
                    t = new Todo(originalUserInput, true);
                    System.out.println(t);
                    listTask.add(t);
                } catch (RuntimeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

            case Deadline:
                if (checkMinimumArguments(splitLine, 4)) {
                    success = false;
                    break;
                }
                try {
                    t = new Deadline(originalUserInput, true);
                    System.out.println(t);
                    listTask.add(t);
                } catch (DukeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

            case Event:
                if (checkMinimumArguments(splitLine, 8)) {
                    success = false;
                    break;
                }
                try {
                    t = new Event(originalUserInput, true);
                    System.out.println(t);
                    listTask.add(t);
                } catch (RuntimeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

        }
        return success;

    }


    // Function to mark or unmark tasks

    /**
     * Mark or unmark the task to be completed based on user's command.
     * @param command either mark or unmark.
     * @param userInput user's input.
     * @param listTask  list of tasks in Task type.
     * @param listString list of tasks in String type.
     */
    public static void userMarkOrUnmark(String command, String userInput, ArrayList<TaskList> listTask, ArrayList<String> listString) {
        int index;
        String originalString, modifiedString;
        TaskList t;

        if (command.equals("mark")) {
            try {
                index = Integer.parseInt(userInput.substring(5));
                originalString = listString.get(index - 1);
                modifiedString = originalString.replace("notMarked:", "Marked:");
                listString.set(index - 1, modifiedString);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Task number is not a valid number or index out of bounds!");
                return;
            }
            t = listTask.get(index - 1);
            t.isDone =  true;
            System.out.println("Nice! I've marked this task as done:");
        }

        else {
            try {
                index = Integer.parseInt(userInput.substring(7));
                originalString = listString.get(index - 1);
                modifiedString = originalString.replace("Marked:", "notMarked:");
                listString.set(index - 1, modifiedString);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Task number is not a valid number or index out of bounds!");
                return;
            }
            t = listTask.get(index - 1);
            t.isDone =  false;
            System.out.println("OK, I've marked this task as not done yet:");
        }

        saveData(listString); //update the saved list as well
        System.out.println(t);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the list of tasks for the user.
     * @param listTask list of tasks in Task type.
     */
    public static void userList(ArrayList<TaskList> listTask) {
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
                throw new DukeException("Minimally " + number + " arguments, please try again!");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    /**
     * Saves task into array of string data type, and into the file.
     * @param listTask list of tasks in Task type.
     * @param listString list of tasks in String type.
     * @param userInput the exact format of the user input
     */
    public static void saveDataIntoListString (ArrayList<TaskList> listTask, ArrayList<String> listString, String userInput) {
        String savedLine;
        savedLine = "notMarked:" + userInput;
        listString.add(savedLine);
        saveData(listString);
        System.out.println("Now you have " + listTask.size() + " tasks in the list.");
    }
}
