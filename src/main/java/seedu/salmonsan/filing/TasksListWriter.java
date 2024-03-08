package seedu.salmonsan.filing;

import seedu.salmonsan.data.TasksList;

import java.io.FileWriter;
import java.io.IOException;

public class TasksListWriter implements FileInterface {
    /**
     * write .txt file based on TasksList object passed in
     * @param tasksList
     * @throws IOException
     */
    public static void writeTasksListToFile(TasksList tasksList) throws IOException {
        FileWriter fw = new FileWriter(TASK_LIST_FILE);
        fw.write(tasksList.toString()); //toString to be made?
        fw.close();
    }
}
