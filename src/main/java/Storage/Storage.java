package Storage;

import TaskList.TaskList;
import java.io.File;

public class Storage {
    public void newFile(String[] args) {
        BattchFile.main(args);
        File newData = BattchFile.getFileData();
        BattchFile.readFromFile(newData, TaskList.tasks);
    }
}
