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

    public static boolean removeElementFromBothArrays(ArrayList<TaskList> list, ArrayList<String> listString, String[] splitLine) {
        try {
            int index = Integer.parseInt(splitLine[1]) - 1;
            TaskList t = list.get(index);
            list.remove(index);
            listString.remove(index);
            saveData(listString);
            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task:\n" +
                    t + "\n" +
                    "Now you have " + list.size() + " tasks in the list.");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Invalid index, please try again!");
            return false;
        }
        return true;
    }

    public static boolean addTask(ArrayList<TaskList> list, String originalUserInput, String[] splitLine, Commands typeOfTask) {
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
                    list.add(t);
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
                    list.add(t);
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
                    list.add(t);
                } catch (RuntimeException e) {
                    System.out.println("Invalid Syntax, please try again!");
                    success = false;
                }
                break;

        }
        return success;

    }


    // Function to mark or unmark tasks
    public static void userMarkOrUnmark(String command, String line, ArrayList<TaskList> listTask, ArrayList<String> listString) {
        int index;
        String originalString, modifiedString;
        TaskList t;

        if (command.equals("mark")) {
            try {
                index = Integer.parseInt(line.substring(5));
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
                index = Integer.parseInt(line.substring(7));
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

    // Function to print the list of tasks
    public static void userList(ArrayList<TaskList> list) {
        for (TaskList task : list) {
            if (task == null) {
                break;
            }
            System.out.println(list.indexOf(task) + 1 + ". " + task);
        }
        System.out.println("____________________________________________________________");
    }


    // Function to check the minimum arguments supplied for each task
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

    public static void saveDataIntoBothArrays (ArrayList<TaskList> list, ArrayList<String> listString, String line) {
        String savedLine;
        savedLine = "notMarked:" + line;
        listString.add(savedLine);
        saveData(listString);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
}
