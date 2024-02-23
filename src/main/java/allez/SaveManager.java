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

import static allez.Allez.LIST_SIZE;

public class SaveManager {
    private File dataFile;

    public File getDataFile() {
        return dataFile;
    }
    public SaveManager(String filePath) {
        dataFile = new File(filePath);
        verifyFile();
    }

    public void verifyFile(){
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

    public Task[] loadSave(){
        Task[] tasks = new Task[LIST_SIZE];
        try {
            ArrayList<String> dataStored = readFile();
            tasks = parse(dataStored);
        } catch (IOException e) {
            System.out.println("Encountered issue loading save file");
        }
        return tasks;
    }

    private Task[] parse(ArrayList<String> dataStored) {
        Task[] tasks = new Task[LIST_SIZE];
        if(dataStored == null) {
            return tasks;
        }
        int count = 0;
        for(String line : dataStored) {
            String[] lineSegment = line.split(" \\| ",5);

            switch(lineSegment[0]){
            case "T":
                tasks[count] = new ToDo(lineSegment[2]);
                break;
            case "D":
                tasks[count] = new Deadline(lineSegment[2], lineSegment[3]);
                break;
            case "E":
                tasks[count] = new Event(lineSegment[2], lineSegment[3], lineSegment[4]);
                break;
            default:
                continue;
            }

            if(lineSegment[1].equals("[X]")) {
                tasks[count].markDone();
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

    public void writeSave(Task[] tasks) {
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
