package kyrene.storage;

import kyrene.exception.KyreneInvalidCommandException;
import kyrene.exception.KyreneMissingTaskException;
import kyrene.exception.KyreneMissingTimeException;
import kyrene.task.Deadline;
import kyrene.task.Event;
import kyrene.task.Task;
import kyrene.task.Todo;
import kyrene.taskList.TaskList;
import kyrene.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents a storage object that is used to read from and write to the file provided specific file path.
 */
public class Storage {

    private String filePath;
    private String folderPath;

    public Storage() {
        this(null, null);
    }

    /**
     * Construct a storage object with file path and folder path of the target file.
     *
     * @param filePath String represents the path of the target file.
     * @param folderPath String represents the path of the folder that stores the target file.
     */
    public Storage(String filePath, String folderPath) {
        setFilePath(filePath);
        setFolderPath(folderPath);
    }

    /**
     * Set the file path of the target file with the input string.
     *
     * @param filePath String represents the path of the target file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Set the folder path of folder that store the target file with the input string.
     *
     * @param folderPath String represents the path of the target file.
     */
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    /**
     * Returns the string representing the file path of the target file.
     *
     * @return The path of the target file.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns the string representing the path of the folder that stores the target file.
     *
     * @return The path of the folder that stores the target file.
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     * Write the entire task list to the target file.
     *
     * @param tasks A task list containing all existing tasks.
     * @throws IOException If an error relating to file access occurs.
     */
    public void write(TaskList tasks) throws IOException {
        FileWriter clearWriter = new FileWriter(getFilePath());
        clearWriter.write("");
        clearWriter.close();

        FileWriter fw = new FileWriter(getFilePath(), true);
        for (Task task : tasks) {
            fw.write(task.format());
        }
        fw.close();
    }

    /**
     * Read the existing task list from the target file.
     *
     * @return A task list containing all existing tasks loaded from the target file.
     * @throws FileNotFoundException If the target file is not found by the given file path.
     */
    public TaskList load() throws FileNotFoundException {
        Ui.showLoadingFile(getFilePath());
        TaskList tasks = new TaskList();
        File f = new File(getFilePath());
        Scanner s = new Scanner(f);
        int lineNumber = 1;
        while (s.hasNext()) {
            String line = s.nextLine();
            boolean isDone = line.startsWith("true");
            String task;
            if (isDone) {
                task = line.substring("true ".length());
            } else {
                task = line.substring("false ".length());
            }
            try {
                loadTask(task, isDone, tasks);
            } catch (KyreneMissingTaskException | KyreneInvalidCommandException e) {
                Ui.showErrorFileCorrupted(lineNumber);
            }
            lineNumber ++;
        }
        Ui.showSuccessLoadingFile(getFilePath());
        return tasks;
    }

    /**
     * Create file under the given file path,
     * and create folder if folder under the given folder path does not exist.
     */
    public void createFile() {
        Ui.showErrorFileNotFound(getFilePath());
        Path folderPath = Paths.get(getFolderPath());
        Path filePath = Paths.get(getFilePath());
        if (Files.exists(folderPath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException ex) {
                Ui.showErrorCreatingFileFailed();
                return;
            }
        } else {
            Ui.showErrorFolderNotFound(getFolderPath());
            try {
                Files.createDirectory(folderPath);
                Files.createFile(filePath);
            } catch (IOException ex) {
                Ui.showErrorCreatingFolderFailed();
                return;
            }
            Ui.showSuccessCreatingFolder(getFolderPath());
        }
        Ui.showSuccessCreatingFile(getFilePath());
    }

    /**
     * Load a single task into the task list.
     *
     * @param sentence String containing all information of the task being loaded.
     * @param isDone Indicates the done status of the task. True for done, otherwise not done.
     * @param tasks A task list containing all existing tasks.
     * @throws KyreneInvalidCommandException If the file has been corrupted.
     * @throws KyreneMissingTaskException If the file has been corrupted.
     */
    public void loadTask(String sentence, boolean isDone, TaskList tasks) throws KyreneInvalidCommandException, KyreneMissingTaskException {
        String[] words = sentence.split(" ");
        String classType = words[0];

        switch (classType) {
        case "todo":
            try {
                tasks.add(new Todo(sentence.substring(5)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            }
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(sentence.substring(9)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                Ui.showErrorMissingTime();
                return;
            }
            break;
        case "event":
            try {
                tasks.add(new Event(sentence.substring(6)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new KyreneMissingTaskException();
            } catch (KyreneMissingTimeException e) {
                Ui.showErrorMissingTime();
                return;
            }
            break;
        default:
            throw new KyreneInvalidCommandException();
        }

        tasks.get(tasks.size() - 1).setDone(isDone);
    }

}
