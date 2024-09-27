package Storage;

import ChelleCommands.Deadline;
import ChelleCommands.Event;
import ChelleCommands.Task;
import ChelleCommands.ToDo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileEncoder {
    private String FILE_PATH;

    FileEncoder(String path){
        FILE_PATH = path;
    }

    /**
     * Encodes the existing task list and saves it into the save file
     *
     * @param tasks task list
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.FILE_PATH))) {
            for (Task task : tasks) {
                String type;
                if (task instanceof ToDo) {
                    type = "T";
                } else if (task instanceof Deadline) {
                    type = "D";
                } else if (task instanceof Event) {
                    type = "E";
                } else {
                    // Handle unknown task type
                    continue;
                }

                String isDone = task.isDone() ? "1" : "0";
                String[] description = extractTaskInformation(task);

                // Format the information based on task type
                String formattedDescription = formatTaskDetails(type, description);

                // Write the task to the file
                writer.println(type + " | " + isDone + " | " + formattedDescription);
            }
        } catch (IOException e) {
            // Handle IO exception
            e.printStackTrace();
        }
    }

    /**
     * formats tasks from the task list into a readable form for the program
     *
     * @param type The type of task
     * @param description An array containing all the segments that make up the task description
     * @return formatted string
     */
    private static String formatTaskDetails(String type, String[] description) {
        switch (type) {
        case "T":
            return description.length >= 1 ? description[0] : "";
        case "D":
            return description.length >= 2 ? description[0] + " /by " + description[1] : "";
        case "E":
            return description.length >= 3 ? description[0] + " /from " + description[1] + " /to " + description[2] : "";
        default:
            // For unknown types, return an empty string
            System.out.println("Invalid file type detected.");
            return "";
        }
    }

    /**
     * Breaks the task description into parts
     * For deadline, it is split into description and 'by' part
     * For event, it is split into description, 'from' part and 'to' part
     *
     * @param task task list
     * @return An array containing all the segments that make up the task description
     */
    private static String[] extractTaskInformation(Task task) {
        if (task instanceof ToDo) {
            return new String[]{task.getTaskName()};
        } else if (task instanceof Deadline) {
            return new String[]{task.getTaskName(), ((Deadline) task).getBy()};
        } else if (task instanceof Event) {
            return new String[]{task.getTaskName(), ((Event) task).getFrom(), ((Event) task).getTo()};
        } else {
            return new String[]{};
        }
    }

}
