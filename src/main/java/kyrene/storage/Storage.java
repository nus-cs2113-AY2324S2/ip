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

public class Storage {

    private String filePath;
    private String folderPath;

    public Storage() {
        this(null, null);
    }

    public Storage(String filePath, String folderPath) {
        setFilePath(filePath);
        setFolderPath(folderPath);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void write(TaskList tasks) throws IOException {
        FileWriter clearWriter = new FileWriter(getFilePath());
        clearWriter.write("");
        clearWriter.close();

        FileWriter fw = new FileWriter(getFilePath(), true);
        int taskCount = tasks.size();
        for (Task task : tasks) {
            fw.write(task.format());
        }
        fw.close();
    }

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
