package geepee.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import geepee.task.*;

public class FileHandler {

    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;

    private static final int DEADLINE_BY_INDEX = 3;

    private static final int EVENT_FROM_INDEX = 3;
    private static final int EVENT_TO_INDEX = 4;

    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void addFileContentsToList(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String line = "";
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split(";");
            String taskType = words[TASK_TYPE_INDEX];
            boolean taskStatus = words[TASK_STATUS_INDEX].equals("X") ? true : false;
            if (taskType.equals("T")) {
                tasks.add(new Todo(words[TASK_DESCRIPTION_INDEX], taskStatus));
            } else if (taskType.equals("D")) {
                tasks.add(new Deadline(words[TASK_DESCRIPTION_INDEX], words[DEADLINE_BY_INDEX], taskStatus));
            } else if (taskType.equals("E")) {
                tasks.add(new Event(words[TASK_DESCRIPTION_INDEX], words[EVENT_FROM_INDEX], words[EVENT_TO_INDEX],
                        taskStatus));
            }
        }
    }

    public void overwriteFileWithNewList(ArrayList<Task> tasks) {
        try {
            String newData = "";
            for (Task task : tasks) {
                newData += task.toFileFriendlyString() + System.lineSeparator();
            }
            writeToFile(newData);
        } catch (IOException e) {
            SystemMessage.printIOExceptionMessage();
        }
    }
}
