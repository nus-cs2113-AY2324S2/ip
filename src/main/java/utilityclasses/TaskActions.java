package utilityclasses;
import drosstasks.Task;
import myexceptions.InvalidTodoException;
import drosstasks.DrossList;
import utilityclasses.Ui;
import utilityclasses.FileIO;
import java.util.ArrayList;

/**
 * Provides utility methods for task management actions including listing, searching,
 * marking tasks as complete or incomplete, task creation, and deletion.
 */
public class TaskActions {

    private static Ui ui;

    /**
     * Displays all tasks in the given list.
     * @param list The DrossList containing tasks to be displayed.
     */
    public static void listAllTasks(DrossList list) {
        Ui.printLine();
        list.printAllTasks();
        Ui.printLine();
    }

    /**
     * Searches for and displays tasks matching a given name.
     * @param name The name to search for within task descriptions.
     * @param list The DrossList containing tasks to be searched.
     */
    public static void handleSearchForTask(String name, DrossList list){
        ArrayList<Task> matches = new ArrayList<>();
        ui = new Ui();
        for (int i = 0; i < list.getSize(); i++) {
            Task currentTask = list.getTask(i);
            if (currentTask.getDescription().contains(name)) {
                matches.add(currentTask);
            }
        }
        if (matches.isEmpty()){
            ui.printEmptySearchResult();
        } else {
            ui.printSearchResults(matches);
        }
    }


    /**
     * Toggles the mark of a task as complete or incomplete based on the given instruction.
     * @param instruction The command to mark or unmark a task, including the task index.
     * @param list The DrossList containing the task to be toggled.
     */
    public static void toggleMark(String instruction, DrossList list) {
        ui = new Ui();
        String[] tokens = instruction.split(" ");
        try {
            int index = Integer.parseInt(tokens[1]);
            if (tokens[0].equals("mark")) {
                list.markDoneByIndex(index);
                FileIO.saveTasksToFile(list);
            } else {
                list.markUndoneByIndex(index);
                FileIO.saveTasksToFile(list);
            }
            listAllTasks(list);
        } catch (ArrayIndexOutOfBoundsException e){
            ui.printLine();
            System.out.println("Yeah sure go ahead and mark that invisible task sir!");
            ui.printLine();
        } catch (IndexOutOfBoundsException e){
            ui.printLine();
            System.out.println("Your mother task");
            ui.printLine();
        }

    }

    /**
     * Deletes a task from the list at the specified index.
     * @param index The index of the task to be deleted.
     * @param list The DrossList from which the task will be removed.
     */
    public static void handleDeleteTask(int index, DrossList list){
        list.deleteTask(index);
    }

    /**
     * Handles the creation of tasks based on user input, parsing the input to
     * construct and add the appropriate task object to the list.
     * @param line The input line containing the task to be added.
     * @param list The DrossList to which the new task will be added.
     */
    public static void handleTaskCreation(String line, DrossList list) {
        if (line.startsWith("todo")) {
            try {
                list.addTask(line.substring("todo".length()).trim());
                FileIO.saveTasksToFile(list);
            } catch (InvalidTodoException e){
                System.out.println("You want to do nothing? Be my guest... Type it this way todo [task] if you are kind enough to stop wasting my time");
            }
        } else if (line.startsWith("deadline")) {
            String[] parts = line.substring("deadline".length()).trim().split(" /by ", 2);
            try {
                list.addTask(parts[0], parts[1]);
                FileIO.saveTasksToFile(list);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Go ahead, live life with no deadlines. Procrastinate forever. deadline /by [time] is what you need to type, genius.");
            }
        } else if (line.startsWith("event")) {
            String[] parts = line.substring("event".length()).trim().split(" /from ", 2);
            try {
                String[] timeParts = parts[1].split(" /to ", 2);
                list.addTask(parts[0], timeParts[0], timeParts[1]);
                FileIO.saveTasksToFile(list);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("An event without a name, a start or end... What do you think you are, a philosopher?? event [name] /from [time1] /to [time2] is the way for goodness sakes...");
            }
        }
        list.printLastTask();
        Ui.printLine();
    }
}
