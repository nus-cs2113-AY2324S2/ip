package schmidt.storage;

import schmidt.exception.SchmidtException;
import schmidt.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() throws SchmidtException {
        try {
            ArrayList<Task> tasks = null;
            tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            boolean isFileEmpty = true;

            while (scanner.hasNextLine()) {
                isFileEmpty = false;
                String line = scanner.nextLine();
                String[] tokens = line.split(" \\| ");

                switch (tokens[0]) {
                case "T":
                    Todo todo = new Todo(tokens[2]);
                    if (tokens[1].equals("1")) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(tokens[2], tokens[3]);
                    if (tokens[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(tokens[2], tokens[4], tokens[5]);
                    if (tokens[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new SchmidtException("Saved tasks are corrupted\n" + "Starting with a new task list");
                }
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new SchmidtException("Storage file not found\n" + "Starting with an empty task list");
        }
    }

    public void save(TaskList tasks) throws SchmidtException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                // type of task
                String taskType = "";
                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                } else if (task instanceof Event) {
                    taskType = "E";
                }

                // status of task
                String isDone = "0";
                if (task.getStatus()) {
                    isDone = "1";
                }

                // description of task
                String description = task.getDescription();

                // bye
                String by = "";
                if (task instanceof Deadline) {
                    by = ((Deadline) task).getBy();
                }

                // from, to
                String from = "";
                String to = "";
                if (task instanceof Event) {
                    from = ((Event) task).getFrom();
                    to = ((Event) task).getTo();
                }

                fileWriter.write(taskType + " | " + isDone + " | " + description + " | " + by + " | " + from + " | " + to + "\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            throw new SchmidtException("An error occurred while saving tasks");
        }
    }
}
