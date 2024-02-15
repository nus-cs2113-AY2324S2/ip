

public class TaskManager {
    public static void mark(int taskIndex, Task[] list){
        list[taskIndex].isMarked = true;
        Printer.printMarkedOpening();
        System.out.println("    [X] " + list[taskIndex].getDescription());
        Printer.printLine();

    }
    public static void unMark(int taskIndex, Task[] list){
        list[taskIndex].isMarked = false;
        Printer.printUnMarkedOpening();
        System.out.println("    [ ] " + list[taskIndex].getDescription());
        Printer.printLine();
    }
     public static void addToDo(String message, Task[] list, int index){
        Todo task = new Todo(message);
        list[index] = task;
        Printer.printAddedTaskOpening();
        System.out.println("    " + task.getStatus());
        System.out.println("    Now you have " + (index + 1) + " task in the list.");
        Printer.printLine();
     }
     public static void addDeadline(String message, Task[] list, int index){
         String taskName = TextParser.getTaskName(message);
         String finishBy = TextParser.getDeadlineTime(message);
         Deadline newDeadline = new Deadline(taskName, finishBy);
         list[index] = newDeadline;
         System.out.println("   " + newDeadline.getStatus());
         System.out.println("    Now you have " + (index + 1) + " task in the list.");
         Printer.printLine();
     }
     public static void addEvent(String message, Task[] list, int index){
         String[] eventDurations = TextParser.getEventTime(message);
         String taskName = TextParser.getTaskName(message);
         Events newEvent = new Events(taskName, eventDurations[0], eventDurations[1]);
         list[index] = newEvent;
         System.out.println("   " + newEvent.getStatus());
         System.out.println("   Now you have " + (index + 1) + " task in the list.");
         Printer.printLine();
     }
}