package duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;

public class Storage {
    private final String FILEPATH;

    public Storage(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    public TaskList getTaskList() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
            String currLine;
            while ((currLine = br.readLine()) != null && !currLine.isEmpty()) {
                String[] words = currLine.split(" \\| ");
                String type = words[0];
                String marked = words[1];
                String description = words[2];
                String timeline = null;
                if (words.length == 4) {
                    timeline = words[3];
                }
                Task newTask;
                switch (type) {
                case "T":
                    newTask = new Todo(description);
                    break;
                case "D":
                    newTask = new Deadline(description, timeline);
                    break;
                case "E":
                    String[] times = timeline.split(" - ");
                    newTask = new Event(description, times[0], times[1]);
                    break;
                default:
                    throw new DukeException("ERROR.... \n\t OOPS!!! Invalid task in saved data!");
                }
                taskList.add(newTask);
                if (marked.equals("1")) {
                    newTask.markAsDone();
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            File f = new File(FILEPATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        return taskList;
    }

    public void editTaskList(TaskList taskList) throws DukeException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILEPATH, false));
            for (Task task : taskList) {
                bw.write(task.toDisk());
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("ERROR.... \n\t OOPS!!! Error occurred: " + e.getMessage());
        }
    }

    public void addTask(String newLine) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true); // create a FileWriter in append mode
        fw.write(newLine);
        fw.close();
    }
}
