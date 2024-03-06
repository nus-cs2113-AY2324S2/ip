package timl.task;

import timl.exceptions.EmptyException;
import timl.utility.Printer;
import timl.utility.TextParser;
import timl.exceptions.TimException;

import java.util.ArrayList;

/**
 * Manages and executes all commands related to the Array List
 */
public class TaskManager {
    public static ArrayList<Task> list = new ArrayList<>();
    public void addTask(Task t) {
        list.add(t);
    }
    public static String getTaskType(Task t) {
        String words = t.getStatus();
        return words.substring(0, 3);
    }

    /**
     * List all tasks that is currently in the Array List
     */
    public static void printList(){
        Printer.printList();
        for (Task i : list) {
            System.out.println("    " + (list.indexOf(i) + 1) + ": " + i.getStatus());
        }
        Printer.printLine();
    }

    /**
     * Sets the marked status of a task in the Array List to true
     *
     * @param taskIndex index of the task that is to be marked
     * @throws TimException if the list is empty or the taskIndex exceeds the list
     */
    public static void mark(int taskIndex) throws TimException {
        if ((taskIndex < 0) |(list.size() <= taskIndex)){
            throw new TimException();
        }
        list.get(taskIndex).isMarked = true;
        Printer.printMarkedOpening();
        System.out.println("    [X] " + list.get(taskIndex).getDescription());
        Printer.printLine();
    }

    /**
     * Sets the marked status of a task in the Array List to false
     *
     * @param taskIndex index of the task that is to be marked
     * @throws TimException if the list is empty or the taskIndex exceeds the list
     */
    public static void unMark(int taskIndex) throws TimException {
        if ((taskIndex < 0) |(list.size() <= taskIndex)){
            throw new TimException();
        }
        list.get(taskIndex).isMarked = false;
        Printer.printUnMarkedOpening();
        System.out.println("    [ ] " + list.get(taskIndex).getDescription());
        Printer.printLine();
    }

    /**
     * Adds a task of class todo in the array list
     *
     * @param message the description of the todo task that is being added
     * @throws EmptyException if the message is a blank message
     */
    public static void addToDo(String message) throws EmptyException {
        if(message.isBlank()){
            throw new EmptyException();
        }
        Todo task = new Todo(message);
        list.add(task);
        Printer.printAddedTaskOpening();
        System.out.println("    " + task.getStatus());
        System.out.println("    Now you have " + list.size() + " task in the list.");
        Printer.printLine();
    }

    /**
     * Adds a task of class Deadline in the array list
     *
     * @param message the description and deadline (marked by "/by") of the deadline task that is being added
     * @throws EmptyException if the message is a blank message
     */
    public static void addDeadline(String message) throws EmptyException{
        if(message.isEmpty()){
            throw new EmptyException();
        }
        String taskName = TextParser.extractTaskName(message);
        String finishBy = TextParser.extractDeadlineTime(message);
        Deadline newDeadline = new Deadline(taskName, finishBy);
        list.add(newDeadline);
        Printer.printLine();
        System.out.println("    " + newDeadline.getStatus());
        System.out.println("    Now you have " + list.size() + " task in the list.");
        Printer.printLine();
    }

    /**
     * Adds a task of class Event in the array list
     *
     * @param message the description and time period (marked by "/from" and "/to") of the Event task that is being added
     * @throws EmptyException if the message is a blank message
     */
    public static void addEvent(String message) throws EmptyException{
        if(message.isEmpty()){
            throw new EmptyException();
        }
        String[] eventDurations = TextParser.extractEventTime(message);
        String taskName = TextParser.extractTaskName(message);
        Events newEvent = new Events(taskName, eventDurations[0], eventDurations[1]);
        list.add(newEvent);
        Printer.printLine();
        System.out.println("   " + newEvent.getStatus());
        System.out.println("   Now you have " + list.size() + " task in the list.");
        Printer.printLine();
    }

    /**
     * Removes a task from the array list
     *
     * @param taskIndex the position of the task that is to be removed
     * @throws TimException if the list is empty or the task index exceeds the size of the list
     */
    public static void delete(int taskIndex) throws TimException {
        if ((taskIndex < 0) |(list.size() <= taskIndex)){
            throw new TimException();
        }
        Printer.printDeleteOpening();
        System.out.println("     " + list.get(taskIndex).getStatus());
        list.remove(taskIndex);
        System.out.println("     Now you have " + list.size() + " task in the list.");
        Printer.printLine();
    }
    public static void find(String keyword){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Task i : list){
            if (i.description.contains(keyword)) {
                indexes.add(list.indexOf(i));
            }
        }

        if (indexes.isEmpty()) {
            Printer.printFoundNothingMessage();
            return;
        }
        Printer.printFindOpening();
        int k = 1;
        for (int i : indexes) {
            System.out.println("    " + k + ": " + list.get(i).getStatus());
            k ++;
        }
        Printer.printLine();
    }
}