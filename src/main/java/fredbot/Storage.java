package fredbot;

import fredbot.exception.EmptyDescriptionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the save file and saving tasks in that file
 */
public class Storage {
    private static final int INDEX_SECOND = 1;
    private static final int INDEX_FIRST = 0;
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String ARG_SEPARATOR = "\\|";
    private static final int MAX_ARG = 5;
    private static final String FILE_PATH = "data/fredbot.txt";
    private static final String FOLDER_NAME = "data";

    /**
     * Constructs the Storage object.
     */
    public Storage() {

    }

    /**
     * Reads each line from the save file.
     * If the task in that line has an empty description, an error message is shown.
     *
     * @param f Save file.
     * @param ui User interface.
     * @param tasks List of tasks.
     * @throws FileNotFoundException If the save file does not exist.
     */
    public void loadTaskList(File f, Ui ui, TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            try {
                processLine(task, tasks);
            } catch (EmptyDescriptionException e) {
                ui.showEmptyDescription();
            }
        }
    }

    /**
     * Processes the task in the line to determine its type.
     *
     * @param task Task details in the current line.
     * @param tasks List of tasks.
     * @throws EmptyDescriptionException If the description of the Task is empty.
     */
    private static void processLine(String task, TaskList tasks) throws EmptyDescriptionException {
        String taskType = task.substring(INDEX_FIRST, INDEX_SECOND);
        String[] taskArgs = splitArgs(task);
        switch (taskType) {
        case TASK_TODO:
            tasks.readTodo(taskArgs);
            break;
        case TASK_DEADLINE:
            tasks.readDeadline(taskArgs);
            break;
        case TASK_EVENT:
            tasks.readEvent(taskArgs);
            break;
        }
    }

    /**
     * Splits the Task arguments into specific details of the Task.
     *
     * @param task Task details.
     * @return Separated array of Task details.
     */
    private static String[] splitArgs(String task) {
        return task.split(ARG_SEPARATOR, MAX_ARG);
    }

    /**
     * Creates a new save file.
     * If there is an error in this process, an error message is shown.
     *
     * @param f Save file.
     * @param ui User interface.
     */
    public void initSaveFile(File f, Ui ui) {
        try {
            new File(FOLDER_NAME).mkdir();
            f.createNewFile();
        } catch (IOException e) {
            ui.showErrorMessage();
        }
    }

    /**
     * Saves the list of tasks into the save file.
     * If there is an error in this process, an error message is shown.
     *
     * @param ui User interface.
     * @param tasks List of tasks.
     */
    public void saveFredBot(Ui ui, TaskList tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            ui.showErrorMessage();
        }
    }

    /**
     * Writes each task into individual lines in the save file.
     *
     * @param tasks List of tasks.
     * @throws IOException If there is an error in writing to the save file.
     */
    private static void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int count = tasks.getCount();
        for (int i = 0; i < count; i++) {
            fw.write(tasks.getTask(i).saveString() + System.lineSeparator());
        }
        fw.close();
    }
}
