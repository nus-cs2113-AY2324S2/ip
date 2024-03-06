package seedu.laika.storage;

import seedu.laika.task.Task;
import seedu.laika.tasklist.TaskList;
import seedu.laika.ui.TextUi;
import seedu.laika.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {

    protected File f;

    public Storage(String filename){
        this.f = new File(filename);
    }

    public TaskList load() {
        TaskList taskList = new TaskList();
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNext()){
                String[] command = reader.nextLine().split(" ",2);
                Task task = Parser.commandtoTask(command);
                taskList.addTaskToTasks(task);
                taskList.addLines(command[1]);
            }
        }
        catch (FileNotFoundException e) {
            TextUi.showErrorMessage(TextUi.MESSAGE_NO_SAVE_DATA);
            taskList = new TaskList();
        }
        return taskList;
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter myWriter = new FileWriter("laika.txt");
        for (int i = 0; i < taskList.getSize(); i++) {
            myWriter.write(taskList.getTasks(i).getIsDoneValue() + " " + taskList.getLine(i) + System.lineSeparator());
        }
        myWriter.close();
    }
}
