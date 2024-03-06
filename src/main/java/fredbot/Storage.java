package fredbot;

import fredbot.exception.EmptyDescriptionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    public Storage() {

    }

    public static void loadTaskList(File f, Ui ui, TaskList tasks) throws FileNotFoundException {
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

    private static String[] splitArgs(String task) {
        return task.split(ARG_SEPARATOR, MAX_ARG);
    }

    public static void initSaveFile(File f, Ui ui) {
        try {
            new File(FOLDER_NAME).mkdir();
            f.createNewFile();
        } catch (IOException e) {
            ui.showErrorMessage();
        }
    }

    public static void saveFredBot(Ui ui, TaskList tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            ui.showErrorMessage();
        }
    }

    private static void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        int count = tasks.getCount();
        for (int i = 0; i < count; i++) {
            fw.write(tasks.getTask(i).saveString() + System.lineSeparator());
        }
        fw.close();
    }
}
