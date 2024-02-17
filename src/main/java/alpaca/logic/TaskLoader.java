package alpaca.logic;

import alpaca.tasks.TaskList;
import alpaca.file.FileReader;

public class TaskLoader {
    public static TaskList loadTask() {
        if (!FileReader.isFileExist()) {
            FileReader.createEmptyFile();
            return new TaskList();
        }
        return FileReader.restoreTask();
    }
}
