package Xavier;

import Exceptions.XavierException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles writing and reading a file
 */
public class Storage {

    /** Specifies the path to the file containing the todo list  */
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if file exists, otherwise creates new file
     */
    public void createOrCheckFile() {
        try {
            File fileDir = new File("./data");
            if (!fileDir.exists()) {
                boolean created = fileDir.mkdirs();
                if (created) {
                    System.out.println("Created data directory");
                }
                else {
                    System.out.println("Unable to create data directory");
                }
            }

            File file = new File(filePath);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File created: toDoList");
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating or checking file: " + e.getMessage());
        }
    }

    /**
     * Appends task to file
     *
     * @throws IOException if unable to write to file
     */
    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Clears file before writing to it
     *
     * @throws IOException if unable to write to file
     */
    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Encodes the {@code TaskList} object into a data file for storage.
     *
     * @param taskList the list of task and list related operations
     */
    public void saveFile(TaskList taskList) {
        createOrCheckFile();
        try {
            clearFile();
            for (Task task : taskList.getItemList()) {
                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    appendToFile("T|" + todo.getStatus() + "|" + todo.getDescription());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    appendToFile("D|" + deadline.getStatus() + "|" + deadline.getDescription() + "|" + deadline.getBy());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    appendToFile("E|" + event.getStatus() + "|" + event.getDescription() + "|" + event.getStart() + "|" + event.getEnd());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Decodes the storage data file into an ArrayList of tasks
     *
     * @return ArrayList of tasks decoded from file
     * @throws XavierException if unable to read file
     */
    public ArrayList<Task> readFile() throws XavierException {
        createOrCheckFile();
        ArrayList<Task> itemList = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            if (!f.canRead()) {
                throw new XavierException();
            }
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] stringArray = line.split("\\|");
                String type = stringArray[0];
                String status = stringArray[1];
                switch (type) {
                    case "T":
                        Todo todo = new Todo(stringArray[2]);
                        if (status.equals("1")) {
                            todo.markAsDone();
                        }
                        itemList.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(stringArray[2], stringArray[3]);
                        if (status.equals("1")) {
                            deadline.markAsDone();
                        }
                        itemList.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(stringArray[2], stringArray[3], stringArray[4]);
                        if (status.equals("1")) {
                            event.markAsDone();
                        }
                        itemList.add(event);
                        break;
                }

            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return itemList;
    }
}
