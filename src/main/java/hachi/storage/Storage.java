package hachi.storage;

import hachi.data.HachiException;
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
    private static String filePath = "hachidata/hachidata.txt";
    private static ArrayList<Task> tasksArrayList;
    protected static File folder = new File("hachidata");

    public Storage () {
        tasksArrayList = new ArrayList<>();
    }

    public static void initializeData() throws SecurityException{
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public static void save() throws IOException {
        saveHandler();
    }

    public ArrayList<Task> load() throws Exception {
        readFile();
        return tasksArrayList;
    }

    private static void saveHandler() throws IOException {
        initializeData();

        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close(); // to clear text file
        } catch (IOException e) {
            System.out.println("\n\tCreating new task list save...");
        }

        FileWriter fw = new FileWriter(filePath);
        if (tasksArrayList != null) {
            for (Task task : tasksArrayList) {
                if (task != null) {
                    fw.write(task.getSaveFormat() + "\n");
                } else {
                    break;
                }
            }
        }
        fw.close();
    }

    private static void readFile() throws FileNotFoundException, HachiException {
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

            tasksArrayList.add(toAdd);
        }
    }

    public static void retrieveFileOnStartup() {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Error finding save file. Creating empty task list.");
        } catch (HachiException e) {
            System.out.println("Save file is corrupted. Creating empty task list.");
        }
    }
}
