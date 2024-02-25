package bean.storage;

import bean.task.TaskList;
import bean.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static bean.Bean.processAndExecute;

public class Storage {
    public Storage(String filePath){
        this.filePath = Path.of(filePath);
    }

    private final Path filePath;

    public TaskList loadTaskList() {
        TaskList listOfTasks = new TaskList();
        File taskArchive = filePath.toFile();
        Ui.printLoadingTasks();

        try {
            if (taskArchive.createNewFile()) {
                return listOfTasks;
            }
        } catch (IOException e) {
            Ui.printIOException(e);
        }

        try {
            Scanner taskArchiveScanner = new Scanner(taskArchive);
            while (taskArchiveScanner.hasNext()) {
                String line = taskArchiveScanner.nextLine();
                if (!line.trim().isEmpty()) {
                    processAndExecute(line, listOfTasks, true);
                }
            }
        } catch (FileNotFoundException e) {
            Ui.printIOException(e);
        }

        return listOfTasks;
    }

    public void saveTaskList(TaskList listOfTasks) {
        Ui.printSavingTasks();
        try {
            FileWriter taskListArchiver = new FileWriter(filePath.toFile());
            taskListArchiver.write(listOfTasks.toCommand());
            taskListArchiver.close();
        } catch (IOException e) {
            Ui.printIOException(e);
        }
    }
}
