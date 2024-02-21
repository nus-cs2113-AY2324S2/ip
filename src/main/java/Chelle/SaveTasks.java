package Chelle;

import ChelleCommands.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveTasks {

    private static final String FILE_PATH = "./src/main/java/Chelle/ChelleTasks.txt";

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(FILE_PATH))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {
                    // Handle case where there are not enough parts
                    continue;
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

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
        return tasks;
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
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
