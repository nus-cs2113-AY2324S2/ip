package gary.storage;

import gary.task.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static final String FILE_PATH = "./gary.txt";
    public static ArrayList<Task> todos = new ArrayList<>();
    public static File createFile() {
        File file = new File(FILE_PATH);
        try {
            Boolean isFileCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println("FILE NOT CREATED");
        }
        return file;
    }

    public static ArrayList<Task> readFileStorage(File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String lineText = fileReader.readLine();
            String[] lineWords;
            String command;
            int todosCount = 0;

            while (lineText != null) {
                // convert each line into TASK_todo/deadline/event, then store in the array list todos
                lineWords = lineText.split(" \\| ");
                command = lineWords[0];
                String description = lineWords[2];

                if (command.equalsIgnoreCase("TODO")) {
                    todos.add(new Todo(description));
                } else if (command.equalsIgnoreCase("DEADLINE")) {
                    String by = lineWords[3];
                    todos.add(new Deadline(description, by));
                } else if (command.equalsIgnoreCase("EVENT")){
                    String from = lineWords[3];
                    String to = lineWords[4];
                    todos.add(new Event(description, from, to));
                }
                todosCount += 1;

                // Update task status in array list todos
                String taskStatus = lineWords[1];
                if (taskStatus.equalsIgnoreCase("1")) {
                    Task currentTask = todos.get(todosCount - 1);
                    currentTask.markAsDone();
                }

                lineText = fileReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
        return todos;
    }

    public static void writeTaskToTxt(File file, int todosCount, ArrayList<Task> todos) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < todosCount; i += 1) {
                writeFormattedString(todos, i, fileWriter);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    private static void writeFormattedString(ArrayList<Task> todos, int i, BufferedWriter fileWriter) throws IOException {
        Task currentTask = todos.get(i);
        String description = currentTask.getTaskDescription();
        String taskStatus = currentTask.getTaskStatus() ? "1" : "0";
        TaskType taskType = currentTask.getTaskType();

        switch(taskType) {
        case DEADLINE:
            Deadline deadline = (Deadline) currentTask;
            String by = deadline.getBy();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + by + "\n");
            break;
        case EVENT:
            Event event = (Event) currentTask;
            String from = event.getFrom();
            String to = event.getTo();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + from + " | " + to + "\n");
            break;
        case TODO:
            fileWriter.write(taskType + " | " + taskStatus + " | " + description + "\n");
            break;
        }
    }
}
