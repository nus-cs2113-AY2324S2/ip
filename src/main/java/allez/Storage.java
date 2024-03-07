package allez;

import allez.task.Deadline;
import allez.task.Event;
import allez.task.Task;
import allez.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Handles storage of data from Chatbot usage to keep track of tasks inputted.
 * Able to load and save data.
 */
public class Storage {
    private final File dataFile;

    public File getDataFile() {
        return dataFile;
    }

    /**
     * When object is initialised, store location of save file into object.
     * Check if location is valid. If not, create a new file.
     *
     * @param filePath path of save file
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);
        verifyFile();
    }

    private void verifyFile(){
        try {
            if (dataFile.exists()) {
                return;
            }
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create file because: " + e.getMessage());
        }
    }

    /**
     * Decipher save file and returns an ArrayList of Task objects
     * from previous Chatbot usage.
     *
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> loadSave(){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            ArrayList<String> dataStored = readFile();
            tasks = parse(dataStored);
        } catch (IOException e) {
            System.out.println("Encountered issue loading save file");
        }
        return tasks;
    }

    private ArrayList<Task> parse(ArrayList<String> dataStored) {
        ArrayList<Task> tasks = new ArrayList<>();
        if(dataStored == null) {
            return tasks;
        }
        int count = 0;
        for(String line : dataStored) {
            String[] lineSegment = line.split(" \\| ",5);

            switch(lineSegment[0]){
            case "T":
                tasks.add(new ToDo(lineSegment[2]));
                break;
            case "D":
                tasks.add(new Deadline(lineSegment[2], lineSegment[3]));
                break;
            case "E":
                tasks.add(new Event(lineSegment[2], lineSegment[3], lineSegment[4]));
                break;
            default:
                continue;
            }

            if(lineSegment[1].equals("[X]")) {
                tasks.get(count).markDone();
            }
            count += 1;
        }

        return tasks;
    }

    private ArrayList<String> readFile() throws IOException {
        if (dataFile.length()==0) {
            return null;
        }
        return  (ArrayList<String>) Files
                .readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }

    /**
     * Store the current task list into the save file, so that
     * it can be loaded up again the next time the application is used.
     *
     * @param tasks ArrayList of Task to be stored
     */
    public void writeSave(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(dataFile);
            for(Task t: tasks) {
                if (t instanceof ToDo) {
                    fw.write("T | " + t.getStatusIcon() + " | "
                            + t.getDescription());
                } else if (t instanceof Deadline) {
                    fw.write("D | " + t.getStatusIcon() + " | "
                            + t.getDescription() + " | " + ((Deadline) t).getBy());
                } else if (t instanceof Event) {
                    fw.write("E | " + t.getStatusIcon() + " | "
                            + t.getDescription() + " | " + ((Event) t).getFrom()
                            + " | " + ((Event) t).getTo());

                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Encountered issue writing save file");
        }
    }



}
