import java.io.IOException;
import java.util.ArrayList;

public class TaskHandler {

    static protected ArrayList<Task> taskList = new ArrayList<>();
    static protected int taskCounter = 0;

    /**
     * Adds a new task to the list, its type based on the input from the user, handles the various variables that
     * different task types have, such as by for deadlines and to and from for events, it handles these differently
     * based on the task type.
     * Also handles the writing or appending to the data file for addition of new tasks.
     * @param taskToAdd description and relevant info for the variables of the task
     * @throws InvalidCommandException if the user uses the command incorrectly, throw this exception
     */
    protected static void addToList(String[] taskToAdd) throws InvalidCommandException {
        if (taskToAdd[0].equals("todo")) {
            ToDo todoToAdd = new ToDo(taskToAdd[1]);
            taskList.add(todoToAdd);
        } else if (taskToAdd[0].equals("deadline")) {
            String[] deadline = taskToAdd[1].split("/", 2);
            String by = deadline[1].substring(3);
            String description = deadline[0].substring(0, deadline[0].length() - 1);

            Deadline deadlineToAdd = new Deadline(description, by);
            taskList.add(deadlineToAdd);
        } else if (taskToAdd[0].equals("event")) {
            String[] event = taskToAdd[1].split("/", 3);
            String description = event[0].substring(0, event[0].length() - 1);
            String from = event[1].substring(5, event[1].length() - 1);
            String to = event[2].substring(3);
            Event eventToAdd = new Event(description, from, to);
            taskList.add(eventToAdd);
        } else {
            throw new InvalidCommandException();
        }

        System.out.println("The following task has been added: ");
        taskList.get(taskCounter).printTask();
        taskCounter++;
        try {
            String taskCounterString = taskCounter + "\n";
            JigaChat.previousData.appendAtFirstLine(taskCounterString);
        }
        catch (IOException e) {
            System.out.println("File writing/reading failed!");
        }
        System.out.print("You have " + taskCounter + " task");
        if (taskCounter != 1) {
            System.out.print("s");
        }
        System.out.println(" in your list.");
    }

    /**
     * Adds a new task to the list, its type based on the input from the user, handles the various variables that
     * different task types have, such as by for deadlines and to and from for events, it handles these differently
     * based on the task type.
     * Same as addToList, except that does not print any text, used for the initialisation of previous data, also does
     * not make changes to the user file.
     * @param taskToAdd description and relevant info for the variables of the task
     * @throws InvalidCommandException if the user uses the command incorrectly, throw this exception
     */
    protected static void addToListWithoutPrints(String[] taskToAdd) throws InvalidCommandException {
        if (taskToAdd[0].equals("todo")) {
            ToDo todoToAdd = new ToDo(taskToAdd[1]);
            taskList.add(todoToAdd);
        } else if (taskToAdd[0].equals("deadline")) {
            String[] deadline = taskToAdd[1].split("/", 2);
            String by = deadline[1].substring(3);
            String description = deadline[0].substring(0, deadline[0].length() - 1);

            Deadline deadlineToAdd = new Deadline(description, by);
            taskList.add(deadlineToAdd);
        } else if (taskToAdd[0].equals("event")) {
            String[] event = taskToAdd[1].split("/", 3);
            String description = event[0].substring(0, event[0].length() - 1);
            String from = event[1].substring(5, event[1].length() - 1);
            String to = event[2].substring(3);
            Event eventToAdd = new Event(description, from, to);
            taskList.add(eventToAdd);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Removes a task from the task list, based on its index in the task list, as well as prints text specific to the
     * removeFromList method. Also handles the writing of the deletion to the data file.
     * Performs minor optimisation by emptying the data file if the tasks in the task list ever reach 0. If the index
     * input is not in the list, prints out text to inform the user.
     * @param taskIndex index in the task list of the task to be removed
     */
    protected static void removeFromList(int taskIndex) {
        try {
            taskList.get(taskIndex).printTask();
            System.out.println("Has been removed from your list");
            taskList.remove(taskIndex);
            taskCounter--;
            String taskCounterString = taskCounter + "\n";
            if (taskCounter == 0) {
                JigaChat.previousData.writeToFile("0");
            } else {
                JigaChat.previousData.appendAtFirstLine(taskCounterString);
            }
            System.out.println("You have " + taskCounter + " tasks in your list");
        }
        catch (IOException e) {
            System.out.println("File writing/reading failed!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Task " + taskIndex + " is not in your list!");
        }
    }

    /**
     * Removes a task from the task list, based on its index in the task list, as well as prints text specific to the
     * removeFromList method. Except it does not print any text, or make any changes to the data file.
     * @param taskIndex index in the task list of the task to be removed
     */
    protected static void removeFromListWithoutPrints(int taskIndex) {
        taskList.remove(taskIndex);
    }

    /**
     * Finds if a task in the task list has a description that matches the text input by the user and prints it out,
     * the index printed out is just formatting for the print and not the actual index of the task, can print more than
     * 1 task if multiple tasks contain matches for the text input. If a match is not found, prints out text to inform
     * the user.
     * @param text text to find matches for in task descriptions
     */
    protected static void findInTaskList(String text) {
        boolean exists = false;
        int counter = 0;
        System.out.println("JigaChat has found the following matching task(s)");
        for (int i = 0; i < taskCounter; i++) {
            if (taskList.get(i).description.contains(text)) {
                counter ++;
                System.out.print(counter + ". ");
                taskList.get(i).printTask();
                exists = true;
            }
        }
        if (!exists) {
            System.out.println(text + " is not in your list!");
        }
    }
}
