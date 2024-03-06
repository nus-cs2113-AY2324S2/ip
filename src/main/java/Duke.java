import Storage.DukeFile;
import TaskList.AddTask;
import TaskList.Task;
import Ui.PrintText;

import java.io.File;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String botName = "Battch";

        PrintText.printWithLinebreak("Hello! I'm " + botName + "\n" +
                "What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();

        DukeFile.main(args);

        File newData = DukeFile.getFileData();

        DukeFile.readFromFile(newData, tasks);

        AddTask.taskListManager(tasks);

        PrintText.printWithLinebreak("Bye. Hope to see you again soon!");

    }
}
