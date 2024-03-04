package kvothe;
import kvothe.exception.KvotheException;
import kvothe.task.Deadline;
import kvothe.task.Event;
import kvothe.task.Task;
import kvothe.task.Todo;

import java.util.ArrayList;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) throws KvotheException {
        this.filePath = filePath;

        // Create file if it does not exist
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created successfully.");
            } catch (IOException e) {
                throw new KvotheException("Error creating file: " + e.getMessage());
            }
        }
    }

    public void dumpToFile(ArrayList<Task> tasks){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, false));

            for (Task task : tasks) {
                writer.write(task.toFileString());
                // [Type][Done] descr (arg1: value1, arg2: value2 ...)
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(this.filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                char type = line.charAt(0);
                boolean isDone = line.charAt(2) == '1';
                String args[] = line.split("\\|");
                switch (type) {
                    case 'T':
                        tasks.add(new Todo(args[2]));
                        break;
                    case 'D':
                        tasks.add(new Deadline(args[2], args[3]));
                        break;
                    case 'E':
                        tasks.add(new Event(args[2], args[3], args[4]));
                        break;
                    default:
                        System.err.println("Invalid task type: " + type);
                        break;
                }

                tasks.get(tasks.size() - 1).setIsDone(isDone);

            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }

        return tasks;
    }

}
