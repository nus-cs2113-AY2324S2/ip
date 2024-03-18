package ip.main;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Deals with loading tasks from the data file and saving tasks in the file
 */
public class Storage {
    private File file;
    private Ui ui;

    /**
     * Constructor to initialise the file and ui attributes.
     * If the data file or the directory does not exist in the
     * hard disk yet, they will be created
     *
     * @param filePath relative file path of the data file
     * @param ui the user interface interacting with the user
     * @throws IOException if unable to create data file
     */
    public Storage(String filePath, Ui ui) throws IOException {
        this.ui = ui;
        file = new File(filePath);

        if (! file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (! file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Loads in all the stored tasks in the data file
     *
     * @param tasks the list of tasks
     * @throws FileNotFoundException if the data file does not exist
     */
    public void readStoredData(TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String str = s.nextLine();
            processStoredTaskData(tasks, str);
        }
        ui.printWithoutLeadingSpace("Stored data has been read in!");
    }

    private void processStoredTaskData(TaskList tasks, String str) {
        String[] dataLine = str.split(" :: ");
        try {
            boolean isDone = (Integer.parseInt(dataLine[1]) == 1);

            switch (dataLine[0]) {
            case "T":
                tasks.add(new Todo(isDone, dataLine[2]));
                break;
            case "D":
                tasks.add(new Deadline(isDone, dataLine[2], dataLine[3]));
                break;
            case "E":
                tasks.add(new Event(isDone, dataLine[2], dataLine[3], dataLine[4]));
                break;
            default:
                ui.printWithoutLeadingSpace("I have no idea what this is: " + str);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.printWithoutLeadingSpace("I have no idea what this is: " + str);
        }
    }

    /**
     * Writes the tasks into the data file
     *
     * @param tasks the list of tasks
     */
    public void updateStoredData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("./data/task_list.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task taskToWrite = tasks.get(i);
                writeStoredTaskData(fw, taskToWrite);
            }
            fw.close();
        } catch (IOException e) {
            ui.printWithoutLeadingSpace("Unable to update data file!");
        }
    }

    private void writeStoredTaskData(FileWriter fw, Task taskToWrite) throws IOException {
        String isDoneString = taskToWrite.getDone() ? "1" : "0";
        String description = taskToWrite.getDescription();

        if (Todo.class.isInstance(taskToWrite)) {
            fw.write("T :: " + isDoneString + " :: " + description + "\n");
        } else if (Deadline.class.isInstance(taskToWrite)) {
            Deadline deadlineToWrite = (Deadline) taskToWrite;
            fw.write("D :: " + isDoneString + " :: " + description
                    + " :: " + deadlineToWrite.getBy() + "\n");
        } else {
            Event eventToWrite = (Event) taskToWrite;
            fw.write("E :: " + isDoneString + " :: " + description + " :: "
                    + eventToWrite.getFrom() + " :: " + eventToWrite.getTo() + "\n");
        }
    }
}
