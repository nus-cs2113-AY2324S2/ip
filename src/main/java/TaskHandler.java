import java.io.IOException;
import java.util.ArrayList;

public class TaskHandler {

    static protected ArrayList<Task> taskList = new ArrayList<>();
    static protected int taskCounter = 0;

    protected static void addToList(String[] taskToAdd) throws InvalidCommandException {
            if (taskToAdd[0].equals("todo")) {
                    ToDo todoToAdd = new ToDo(taskToAdd[1]);
                    taskList.add(todoToAdd);
            }
            else if (taskToAdd[0].equals("deadline")) {
                String[] deadline = taskToAdd[1].split("/", 2);
                String by = deadline[1].substring(3);
                String description = deadline[0].substring(0, deadline[0].length() - 1);

                Deadline deadlineToAdd = new Deadline(description, by);
                taskList.add(deadlineToAdd);
            }
            else if (taskToAdd[0].equals("event")) {
                String[] event = taskToAdd[1].split("/", 3);
                String description = event[0].substring(0, event[0].length() - 1);
                String from = event[1].substring(5, event[1].length() - 1);
                String to = event[2].substring(3);
                Event eventToAdd = new Event(description, from, to);
                taskList.add(eventToAdd);
            }
            else {
                throw new InvalidCommandException();
            }

            System.out.println("The following task has been added: ");
            taskList.get(taskCounter).printTask();
            taskCounter++;
            try {
                String taskCounterString = taskCounter + "\n";
                JigaChat.previousData.appendAtFirstLine(taskCounterString);
            }
            catch(IOException e) {
            }
            System.out.print("You have " + taskCounter + " task");
            if (taskCounter != 1) {
                System.out.print("s");
            }
            System.out.println(" in your list.");
    }

    protected static void addToListWithoutPrints(String[] taskToAdd) throws InvalidCommandException {
        if (taskToAdd[0].equals("todo")) {
            ToDo todoToAdd = new ToDo(taskToAdd[1]);
            taskList.add(todoToAdd);
        }
        else if (taskToAdd[0].equals("deadline")) {
            String[] deadline = taskToAdd[1].split("/", 2);
            String by = deadline[1].substring(3);
            String description = deadline[0].substring(0, deadline[0].length() - 1);

            Deadline deadlineToAdd = new Deadline(description, by);
            taskList.add(deadlineToAdd);
        }
        else if (taskToAdd[0].equals("event")) {
            String[] event = taskToAdd[1].split("/", 3);
            String description = event[0].substring(0, event[0].length() - 1);
            String from = event[1].substring(5, event[1].length() - 1);
            String to = event[2].substring(3);
            Event eventToAdd = new Event(description, from, to);
            taskList.add(eventToAdd);
        }
        else {
            throw new InvalidCommandException();
        }
    }

    protected static void removeFromList(int taskIndex) {
        try {
            taskList.get(taskIndex).printTask();
            System.out.println("Has been removed from your list");
            taskList.remove(taskIndex);
            taskCounter--;
            String taskCounterString = taskCounter + "\n";
            if (taskCounter == 0) {
                JigaChat.previousData.writeToFile("0");
            }
            else {
                JigaChat.previousData.appendAtFirstLine(taskCounterString);
            }
            System.out.println("You have " + taskCounter + " tasks in your list");
        }
        catch (IOException e) {
            System.out.println("but why?");
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Task " + taskIndex + " is not in your list!");
        }
    }

    protected static void removeFromListWithoutPrints(int taskIndex) {
        taskList.remove(taskIndex);
    }
}
