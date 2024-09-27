package Storage;

import ChelleCommands.Deadline;
import ChelleCommands.Event;
import ChelleCommands.Task;
import ChelleCommands.ToDo;
import ChelleExceptions.InvalidCommandFormatException;
import ChelleExceptions.SaveFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDecoder {

    private String FILE_PATH;

    FileDecoder(String path){
        FILE_PATH = path;
    }

    /**
     * Decodes the save file to reproduce the task list from the last save
     *
     * @param tasks task list
     * @throws FileNotFoundException Save file is not found or path is incorrect.
     */
    public void loadTasksFromFile(ArrayList<Task> tasks) throws SaveFileNotFoundException{
        try (Scanner fileScanner = new Scanner(new File(this.FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // three parts are split as such 1 | 2 | 3
                // 1 is the task index, 2 is the task type, 3 is the task description
                String[] savedTextParts = line.split(" \\| ");

                if (savedTextParts.length < 3) {
                    // Handle case where there are not enough parts
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
            throw new SaveFileNotFoundException();
        }
    }
}
