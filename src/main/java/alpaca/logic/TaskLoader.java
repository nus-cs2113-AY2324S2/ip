package alpaca.logic;

import alpaca.tasks.TaskList;
import alpaca.storage.Storage;

public class TaskLoader {
    public static TaskList loadTask() {
        if (!Storage.isFileExist()) {
            Storage.createEmptyFile();
            return new TaskList();
        }
        return Storage.restoreTask();
    }
}
