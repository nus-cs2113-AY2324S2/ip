package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses an array of tokens from the database,
     * and creates the corresponding class object,
     * before adding it to an array of Tasks.
     *
     * @param taskList List of tasks to be updated.
     * @param tokens Array of information of class object to be added.
     */
    public void addTask(List<Task> taskList, String[] tokens){
        String description, by, start, end;
        boolean done;
        Task newTask;

        try {
            switch (tokens[0]) {
            case "todo":
                description = tokens[1];
                done = tokens[2].contains("X");

                newTask = new ToDo(description);
                newTask.setDone(done);
                taskList.add(newTask);
                break;
            case "deadline":
                description = tokens[1];
                by = tokens[2];
                done = tokens[3].contains("X");

                newTask = new Deadline(description, by);
                newTask.setDone(done);
                taskList.add(newTask);
                break;
            case "event":
                description = tokens[1];
                start = tokens[2];
                end = tokens[3];
                done = tokens[4].contains("X");

                newTask = new Event(description, start, end);
                newTask.setDone(done);
                taskList.add(newTask);
                break;
            }
        } catch (MissingParamsException ignored) {

        }
    }

    /**
     * Parses the database and extracts saved Tasks from it,
     * before returning a List representation of the database.
     *
     * @return An array of Task objects from the database.
     */
    public List<Task> loadTasks ()
            throws DukeException.DatabaseLoadException {
        List<Task> taskList = new ArrayList<>();
        File databaseFile = new File(filePath);
        try {
            databaseFile.getParentFile().mkdirs();
            databaseFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException.DatabaseLoadException();
        }

        Scanner s;
        try {
            s = new Scanner(databaseFile);
        } catch (FileNotFoundException e) {
            throw new DukeException.DatabaseLoadException();
        }

        while (s.hasNext()) {
            String entry = s.nextLine();
            String[] tokens = entry.split("\\|");
            addTask(taskList, tokens);
        }

        return taskList;
    }

    /**
     * Creates a string representation of the data contained in a list of tasks,
     * before saving the string to the database folder.
     *
     * @param taskList List of tasks to be saved.
     */
    public void saveTasks (List<Task> taskList)
            throws IOException {
        StringBuilder database = new StringBuilder();
        String entry = null;

        for (Task task : taskList) {
            if (task instanceof ToDo){
                entry = String.format("%s|%s|%s" + System.lineSeparator(),
                        "todo", task.getDescription(),
                        task.getStatusIcon());
            }
            else if (task instanceof Deadline) {
                entry = String.format("%s|%s|%s|%s" + System.lineSeparator(),
                        "deadline",
                        task.getDescription(), ((Deadline) task).getBy(),
                        task.getStatusIcon());
            }
            else if (task instanceof Event) {
                entry = String.format("%s|%s|%s|%s|%s" + System.lineSeparator(),
                        "event",
                        task.getDescription(), ((Event) task).getStart(), ((Event) task).getEnd(),
                        task.getStatusIcon());
            }
            database.append(entry);
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(database.toString());
        fw.close();
    }
}
