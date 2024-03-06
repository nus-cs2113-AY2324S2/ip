package Storage;

import TaskList.TaskList;
import java.io.File;

public class Storage {
    public void newFile(String[] args) {
        DukeFile.main(args);
        File newData = DukeFile.getFileData();
        DukeFile.readFromFile(newData, TaskList.tasks);
    }
}
