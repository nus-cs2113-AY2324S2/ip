package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Storage {
    /**
     * Directory constant.
     */
    private final String PATH = "./data/nocturne.txt";
    private File f;

    /**
     * Constructor for the Storage, creating the directory and file that
     * Nocturne will read and write to.
     */
    public Storage() {
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.f = new File(PATH);
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes to the file specified above.
     *
     * @param taskList The list of tasks that will be updated on call.
     * @throws IOException if the file cannot be access or written to.
     */
    public void saveToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.f);
            fileWriter.write("");
            for (Task task : taskList.tasks) {
                fileWriter.append(task.toString()).append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads from the file in the directory specified above.
     *
     * @param tasks The new instance of the list of tasks that will be shown when
     *              list is called.
     */
    public void readFromFile(TaskList tasks) {
        try {
            Scanner sc = new Scanner(this.f);

            while(sc.hasNextLine()) {
                String data = sc.nextLine();

                //Deserialization of the strings in the file occur here.

                String task = data.substring(6);
                task = task.replace('(', ' ');
                task = task.replace(')', ' ');
                boolean isDone = data.charAt(4) == 'X';
                switch (data.charAt(1)) {
                    case 'D':
                        String[] deadlineSplit = task.split("by:");
                        tasks.addTaskStealth(new Deadline(deadlineSplit[0].trim(),
                                deadlineSplit[1].trim(),
                                isDone));
                        break;
                    case 'E':
                        String[] eventSplit = task.split(":");
                        tasks.addTaskStealth(new Event(eventSplit[0].substring(0,
                                eventSplit[0].length() - 4).trim(),
                                eventSplit[1].substring(0, eventSplit[1].length() - 2).trim(),
                                eventSplit[2].trim(),
                                isDone));
                        break;
                    case 'T':
                        tasks.addTaskStealth(new Todo(task.trim(), isDone));
                }
            }
            sc.close();
        } catch (IndexOutOfBoundsException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
