package Tasks;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import static java.util.stream.Collectors.toList;

/**
 * Helper class to manage task-related operations. Primarily manages the array of tasks stored.
 */
public class TasksList {
    private final ArrayList<Task> TASKS = new ArrayList<>();

    private void printFilteredList(ArrayList<Task> filteredList) {
        if (filteredList.isEmpty()) {
            System.out.println("\tThere are no matches!");
            return;
        }

        int i = 0;
        System.out.println("\tThe following matches what you are trying to find:");
        for (Task task: filteredList) {
            System.out.println("\t\t" + (i + 1) + "." + task);
            i += 1;
        }
    }

    private ArrayList<Task> filterTasksByKeyword(String keyword) {
        return (ArrayList<Task>) TASKS.stream()
                .filter(t -> t.getTaskName().toLowerCase().contains(keyword))
                .collect(toList());
    }

    /**
     * Searches the array of tasks for tasks that contains the keyword. Then prints out in a similar way to the list
     * command. Searching is not case-sensitive.
     *
     * @param arguments Arguments for the find command. Specifically only the keyword in arguments[1].
     */
    public void find(String[] arguments) {
        String keyword = arguments[1].toLowerCase();
        ArrayList<Task> filteredList = filterTasksByKeyword(keyword);
        printFilteredList(filteredList);
    }

    /**
     * Adds a new Task into array of tasks.
     *
     * @param newTask A Task to be added. Can be of 1 of the 3 inherited types of Task (Todo, Deadline or Event).
     */
    public void addTask(Task newTask) {
        TASKS.add(newTask);
    }

    private void printTasksList() {
        boolean isAllTasksDone = true;
        System.out.println("\tHere's your current list of tasks:");

        int i = 0;
        for (Task task: TASKS) {
            if (!task.isTaskDone()) {
                isAllTasksDone = false;
            }

            System.out.println("\t\t" + (i + 1) + "." + task);
            i += 1;
        }

        System.out.println("\tNow you have " + TASKS.size() + " tasks in your list.");

        if (isAllTasksDone) {
            System.out.println("\tExcellent! You have completed all your tasks!");
        }
    }

    private boolean isTasksListEmpty() {
        if (TASKS.isEmpty()) {
            System.out.println("\tThere are no tasks in your list! Please add some tasks.");
            return true;
        }
        return false;
    }

    /**
     * Lists out all the tasks that are currently stored.
     */
    public void listTasks() {
        if (isTasksListEmpty()) {
            return;
        }
        printTasksList();
    }

    /**
     * Marks or unmarks a specific task according to the isDone value.
     *
     * @param arguments Contains the arguments for the mark or unmark command. Mainly uses arguments[1], which
     *                  corresponds to the task number of the task in the current most updated list which the user
     *                  wishes to mark or unmark.
     * @param isDone Value to be updated for the task
     */
    public void mark(String[] arguments, boolean isDone) {
        if (isTasksListEmpty()) {
            return;
        }

        int taskNumber = Integer.parseInt(arguments[1]);
        Task taskToEdit = TASKS.get(taskNumber);

        if (isAlreadyMarked(isDone, taskToEdit) || isAlreadyUnmarked(isDone, taskToEdit)) {
            return;
        }

        changeTaskStatus(isDone, taskToEdit, taskNumber);
    }
    private void changeTaskStatus(boolean isDone, Task taskToEdit, int taskNumber) {
        taskToEdit.setTaskDone(isDone);
        if (isDone) {
            System.out.println("\tWell done, you are one step closer to finishing your tasks!");
            System.out.println("\tI've marked this task done for you:");
        } else {
            System.out.println("\tNo worries, let's do our best!");
            System.out.println("\tI've unmarked this task done for you:");
        }
        System.out.println("\t\t" + (taskNumber + 1) + ". " + taskToEdit);
    }

    private boolean isAlreadyUnmarked(boolean isDone, Task taskToEdit) {
        if (!taskToEdit.isTaskDone() && !isDone) {
            System.out.println("\tTask is already unmarked!");
            return true;
        }
        return false;
    }

    private boolean isAlreadyMarked(boolean isDone, Task taskToEdit) {
        if (taskToEdit.isTaskDone() && isDone) {
            System.out.println("\tTask is already marked done!");
            return true;
        }
        return false;
    }

    /**
     * Deletes a specific task, which is specified by the task number. Mainly uses arguments[1] which corresponds to the
     * task number of the task in the current most updated list which the user wishes to delete.
     *
     * @param arguments The array containing arguments supplied by user for the delete command.
     * @return The task that was deleted
     */
    public Task delete(String[] arguments) {
        int taskNumber = Integer.parseInt(arguments[1]);
        Task taskToDelete = TASKS.get(taskNumber);
        TASKS.remove(taskToDelete);
        return taskToDelete;
    }

    /**
     * Stores the final status of the array into the file specified by the outputFilePath.
     * Formats each Task into a specific format before saving into save file.
     *
     * @param outputFilePath The file location where the output file is located at.
     * @throws IOException Throws IO Exception if data cannot be stored into file.
     */
    public void outputDataIntoFile(String outputFilePath) throws IOException {
        FileWriter fw = new FileWriter(outputFilePath);
        for (Task task: TASKS) {
            String output = task.getTaskType() +  " | " + task.getTaskName();
            switch (task.getTaskType()) {
            case "D":
                Deadline deadline = (Deadline) task;
                output = output.concat(" | " + deadline.getDeadline());
                break;

            case "E":
                Event event = (Event) task;
                output = output.concat(" | " + event.getStart() + " | " + event.getEnd());
                break;

            default:
                break;
            }
            output = output + " | " + task.isTaskDone() + "\n";
            fw.write(output);
        }

        fw.close();
    }

    /**
     * Used to enquire how many tasks are currently stored in the array of tasks. Returns an integer corresponding to
     * size of the array of tasks.
     *
     * @return the number of tasks in the array of tasks.
     */
    public int getNumberOfTasks() {
        return TASKS.size();
    }
}
