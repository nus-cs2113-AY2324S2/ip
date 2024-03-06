package Storage;

import TaskList.TaskList;
import java.io.File;

/**
 * A class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public void newFile(String[] args) {
        DukeFile.main(args);
        File newData = DukeFile.getFileData();
        DukeFile.readFromFile(newData, TaskList.tasks);
    }
}
