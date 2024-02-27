package gary.storage;

import gary.task.Deadline;
import gary.task.Event;
import gary.task.Task;
import gary.task.Todo;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

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

    public static ArrayList<Task> readFileStorage(File file) throws IOException {
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
        }
        return todos;
    }
}
