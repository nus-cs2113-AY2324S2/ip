package Storage;

import ChelleCommands.Deadline;
import ChelleCommands.Event;
import ChelleCommands.Task;
import ChelleCommands.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDecoder {

    private String FILE_PATH;

    FileDecoder(String path){
        FILE_PATH = path;
    }

    public void loadTasksFromFile(ArrayList<Task> tasks) {
        try (Scanner fileScanner = new Scanner(new File(this.FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] savedTextParts = line.split(" \\| ");

                if (savedTextParts.length < 3) {
                    // Handle case where there are not enough parts
                    // three parts are split as such 1 | 2 | 3
                    // 1 is the task index, 2 is the task type, 3 is the task description
                    continue;
                }

                String type = savedTextParts[0];
                boolean isDone = savedTextParts[1].equals("1");
                String description = savedTextParts[2];

                Task task;
                switch (type) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    task = new Deadline(description);
                    break;
                case "E":
                    task = new Event(description);
                    break;
                default:
                    // Handle unknown task type
                    System.out.println("Invalid file type detected.");
                    continue;
                }

                if (isDone) {
                    task.markDone();
                } else {
                    task.markUndone();
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception (initial run or file not created yet)
            System.out.println("Save file not found.");
        }
    }
}
