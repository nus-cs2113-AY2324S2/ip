import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Storage interface for the Jarvas bot.
 * Used for reading and writing from data files.
 */
public class Persistence {
    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws CustomException If an error occurs during file operations.
     */
    public static void saveTasks(ArrayList<Task> tasks) throws CustomException {
        try (FileWriter writer = new FileWriter(Constant.FILE_NAME)) {

            // Write each task in a specific format
            for (Task task : tasks) {
                writer.write(task.getType() + ":" + task.getLabel() + ":" + task.getRange() + ":" +
                        task.getStatusIcon() + '\n');
            }

            writer.close();

        } catch (IOException e) {
            throw new CustomException(Constant.SAVE_ERROR);
        }
    }

    /**
     * Verifies the integrity of the data file, checking if it exists and if it's readable.
     * If not, it creates a data file, a message is printed indicating the missing file
     * and the program continues with an empty list.
     * If the file exists but cannot be read, an exception is thrown.
     *
     * @param data The {@code File} object representing the data file to be verified.
     * @throws IOException If an I/O error occurs during file operations.
     */
    public static void verifyIntegrity(File data) throws IOException {
        if (data.createNewFile()) {
            Reply.printReply(Constant.MISSING_FILE);
        } else if (data.canRead()) {
            Reply.printReply(Constant.SUCCESSFUL_LOAD);
        } else {
            Reply.printReply(Constant.MISSING_FILE);
        }
    }

    /**
     * Loads tasks to the list of tasks from a given specific file name.
     * If the file doesn't exist or is empty, an empty list is returned.
     *
     * @param tasks The list of tasks to be loaded into that may be empty initially.
     * @throws CustomException If an error occurs during file operations or the file
     * representing the saved tasks cannot be found.
     */
    public static void loadTasks(ArrayList<Task> tasks) throws CustomException {
        int taskCount = 0;

        try (final Scanner scanner = new Scanner(Constant.SAVE_FILE)) {

            verifyIntegrity(Constant.SAVE_FILE);

            while (scanner.hasNextLine()) {
                String[] task = scanner.nextLine().split(":");
                String input = task[0].trim();

                try {
                    Command command = Command.valueOf(input);

                    switch (command) {
                    case TODO:
                        spawnToDo(tasks, task, taskCount);
                        break;
                    case DEADLINE:
                        spawnDeadline(tasks, task, taskCount);
                        break;

                    case EVENT:
                        spawnEvent(tasks, task, taskCount);
                        break;

                    default:

                        break; // valueOf results in immediate exception for non-match with enum Command

                    }
                } catch (IllegalArgumentException e) {
                    Reply.printException(e, Constant.LOAD_ERROR + (taskCount + 1) + Constant.CORRUPT_ERROR);
                    break;
                }

                taskCount++;
            }

        } catch (IOException e) {
            if (!new File(Constant.FILE_NAME).exists()) {
                Reply.printReply(Constant.MISSING_FILE);
            } else {
                throw new CustomException(Constant.LOAD_ERROR + (taskCount + 1));
            }
        } catch (Exception e) {
            throw new CustomException(Constant.LOAD_ERROR + (taskCount + 1));
        }
    }

    /**
     * Converts a string array representing a saved Deadline task back into a Deadline object
     * and adds it to the tasks list.
     *
     * @param tasks The list of tasks to add the Deadline to.
     * @param task The string array representing the saved Deadline task.
     * @param taskCount The current number of tasks in the list.
     * @throws CustomException If an error occurs during task creation.
     */
    public static void spawnDeadline(ArrayList<Task> tasks, String[] task, int taskCount) throws CustomException {

        String label = task[1].trim();
        String due = task[2].trim();
        boolean isCompleted = !task[3].trim().isEmpty();

        Deadline deadline = new Deadline(label, due);
        tasks.add(deadline);

        updateCompletion(tasks, isCompleted, taskCount);
    }

    /**
     * Converts a string array representing a saved Event task back into an Event object
     * and adds it to the tasks list.
     *
     * @param tasks The list of tasks to add the Event to.
     * @param task The string array representing the saved Event task.
     * @param taskCount The current number of tasks in the list.
     * @throws CustomException If an error occurs during task creation.
     */
    public static void spawnEvent(ArrayList<Task> tasks, String[] task, int taskCount) throws CustomException {

        String label = task[1].trim();
        String from = task[2].trim();
        String to = task[3].trim();
        boolean isCompleted = !task[4].trim().isEmpty();

        Event event = new Event(label, from, to);
        tasks.add(event);

        updateCompletion(tasks, isCompleted, taskCount);
    }


    /**
     * Converts a string array representing a saved ToDo task back into a ToDo object
     * and adds it to the tasks list.
     *
     * @param tasks The list of tasks to add the ToDo to.
     * @param task The string array representing the saved ToDo task.
     * @param taskCount The current number of tasks in the list.
     * @throws CustomException If an error occurs during task creation.
     */
    public static void spawnToDo(ArrayList<Task> tasks, String[] task, int taskCount) throws CustomException {
        String label = task[1].trim();
        boolean isCompleted = !task[3].trim().isEmpty();

        ToDo toDo = new ToDo(label);
        tasks.add(toDo);

        updateCompletion(tasks, isCompleted, taskCount);
    }

    /**
     * Updates the completion status of a task in the tasks list based on a provided boolean value.
     *
     * @param tasks The list of tasks to update.
     * @param isCompleted A boolean representing the new completion status (true for completed, false for incomplete).
     * @param taskCount The index of the task in the list to update.
     * @throws CustomException If an error occurs during task update.
     */
    public static void updateCompletion(ArrayList<Task> tasks, boolean isCompleted, int taskCount) throws CustomException {
        try {
            tasks.get(taskCount).setCompleted(isCompleted);
        } catch (CustomException e) {
            Reply.printException(e);
        }
    }

}
