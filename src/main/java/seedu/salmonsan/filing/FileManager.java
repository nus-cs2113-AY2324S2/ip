package seedu.salmonsan.filing;

import seedu.salmonsan.data.TasksList;

import java.io.File;
import java.io.IOException;

public class FileManager implements FileInterface {
    public FileManager() {}

    /**
     * find existing file to import TasksList
     * @return
     * @throws IOException
     */
    public static TasksList getTasksList() throws IOException {
        File f = new File(TASK_LIST_FILE);
        System.out.println("full path: " + f.getAbsolutePath());
        if (f.exists()) {
            System.out.println("Existing file found, importing content...");
            return new TasksListReader().parse(f);
        } else {
            // TasksList file does not exist
            System.out.println("Previous Task List  not found, creating a new one!");
            f.createNewFile();
            return new TasksListReader().parse(f);
        }
    }
}
