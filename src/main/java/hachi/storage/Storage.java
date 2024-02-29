package hachi.storage;

import hachi.data.HachiException;
import hachi.data.TaskList;
import hachi.data.task.Deadline;
import hachi.data.task.Event;
import hachi.data.task.Task;
import hachi.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private String filePath;
    private TaskList tasksList;
    protected File folder = new File("hachidata");

    public Storage (String filePath) {
        this.filePath = filePath;
        this.tasksList = new TaskList();
    }

    public void initializeData() throws SecurityException{
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void saveHandler() throws IOException {
        initializeData();

        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close(); // to clear text file
        } catch (IOException e) {
            System.out.println("\n\tCreating new task list save...");
        }

        FileWriter fw = new FileWriter(filePath);

        if (tasksList != null) {
            for (int i = 0; i < tasksList.getSize(); i++) {
                fw.write((tasksList.getSpecifiedTask(i).getSaveFormat()) + "\n");
            }
        }
        fw.close();
    }

    public void readFile() throws FileNotFoundException, HachiException {
        File taskFile = new File(filePath);
        Scanner s = new Scanner(taskFile);

        while (s.hasNext()) {
            String[] line = s.nextLine().split(" \\| ");
            Task toAdd;

            switch (line[0]){
            case "T":
                toAdd = new Todo(line[2]);
                break;

            case "D":
                toAdd = new Deadline(line[2], line[3]);
                break;

            case "E":
                toAdd = new Event(line[2], line[3], line[4]);
                break;

            default:
                throw new HachiException(HachiException.CORRUPTED_SAVE_MESSAGE);
            }

            if (line[1].equals("X")) {
                toAdd.setCompleteness(true);
            }

            tasksList.add(toAdd);
        }
    }

    public ArrayList<Task> load() throws HachiException, FileNotFoundException {
        readFile();
        return tasksList.getTasksArrayList();
    }
}
