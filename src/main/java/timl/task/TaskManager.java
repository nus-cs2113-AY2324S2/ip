package timl.task;

import timl.exceptions.EmptyException;
import timl.utility.Printer;
import timl.utility.TextParser;
import timl.exceptions.TimException;

public class TaskManager {
    public void addTask(Task t, Task[] list) {
        int emptyIndex = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                emptyIndex = i;
                break;
            }
        }
        list[emptyIndex] = t;
    }
    public static String getTaskType(Task t) {
        String words = t.getStatus();
        return words.substring(0, 2);
    }
    public static void mark(int taskIndex, Task[] list, int emptyIndex) throws TimException {
        if ((taskIndex < 0) |(emptyIndex <= taskIndex)){
            throw new TimException();
        }
        list[taskIndex].isMarked = true;
        Printer.printMarkedOpening();
        System.out.println("    [X] " + list[taskIndex].getDescription());
        Printer.printLine();

    }
    public static void unMark(int taskIndex, Task[] list, int emptyIndex) throws TimException {
        if ((taskIndex < 0) |(emptyIndex <= taskIndex)){
            throw new TimException();
        }
        list[taskIndex].isMarked = false;
        Printer.printUnMarkedOpening();
        System.out.println("    [ ] " + list[taskIndex].getDescription());
        Printer.printLine();
    }
     public static void addToDo(String message, Task[] list, int index) throws EmptyException {
        if(message.isBlank()){
            throw new EmptyException();
        }
        Todo task = new Todo(message);
        list[index] = task;
        Printer.printAddedTaskOpening();
        System.out.println("    " + task.getStatus());
        System.out.println("    Now you have " + (index + 1) + " task in the list.");
        Printer.printLine();
     }
     public static void addDeadline(String message, Task[] list, int index) throws TimException{
         if(message.isEmpty()){
             throw new TimException();
         }
         String taskName = TextParser.extractTaskName(message);
         String finishBy = TextParser.extractDeadlineTime(message);
         Deadline newDeadline = new Deadline(taskName, finishBy);
         list[index] = newDeadline;
         Printer.printLine();
         System.out.println("    " + newDeadline.getStatus());
         System.out.println("    Now you have " + (index + 1) + " task in the list.");
         Printer.printLine();
     }
     public static void addEvent(String message, Task[] list, int index) throws TimException{
         if(message.isEmpty()){
             throw new TimException();
         }
         String[] eventDurations = TextParser.extractEventTime(message);
         String taskName = TextParser.extractTaskName(message);
         Events newEvent = new Events(taskName, eventDurations[0], eventDurations[1]);
         list[index] = newEvent;
         Printer.printLine();
         System.out.println("   " + newEvent.getStatus());
         System.out.println("   Now you have " + (index + 1) + " task in the list.");
         Printer.printLine();
     }
}