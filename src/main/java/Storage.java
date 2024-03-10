import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Deals with loading tasks from, and saving tasks into the specified filepath.
 */
public class Storage {
    private String filepath;

    /**
     * Constructs Storage.
     * Reads data from specified filepath, otherwise creates an empty file there.
     */
    public Storage(String filepath, Tasklist tasks) {
        this.filepath = filepath;

        try {
            readFile(tasks);
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            createFile();
        }
    }

    /**
     * Creates file for storing data at specified filepath.
     */
    public void createFile() {
        File data = new File(filepath);

        try {
            if (data.createNewFile()) {
                System.out.println("File created for storing my data :))");
                System.out.println(Ui.TEXTDIVIDER);
            }

        } catch (IOException e) {
            System.out.println("Something went wrong...my data cannot be stored >:(");
        }
    }

    /**
     * Reads formatted data from specified filepath.
     *
     * @throws FileNotFoundException If no file is found.
     */
    public void readFile(Tasklist tasks) throws FileNotFoundException {
        File data = new File(filepath);
        Scanner reader = new Scanner(data);

        while (reader.hasNext()) {
            String entry = reader.nextLine();
            final String DIVIDER = "/";
            String[] details = entry.split(DIVIDER);

            // Format and create task
            Task task = null;
            switch (details[0]) {
            case ("T"):
                task = new Todo(details[2]);
                break;
            case ("D"):
                task = new Deadline(details[2], details[3]);
                break;
            case ("E"):
                task = new Event(details[2], details[3], details[4]);
                break;
            default:
                // Do nothing
            }

            if (task != null) {
                task.setDone(details[1].equals("1"));
                tasks.addExisting(task);
            }
        }
    }

    /**
     * Writes formatted data into specified file.
     *
     * @throws IOException If there is an error during writing.
     */
    public void writeFile(String data) throws IOException {
        FileWriter writer = new FileWriter(filepath);
        writer.write(data);
        writer.close();
    }
}
