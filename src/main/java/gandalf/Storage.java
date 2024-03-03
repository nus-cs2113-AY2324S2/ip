package gandalf;

import action.Task;
import exception.FileEmptyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static ArrayList<String> previousTasks = new ArrayList<>();

    public static void loadData(ArrayList<Task> listTasks) {
        try {
            Ui.printLoadingMessage();
            loadTasks("data/savefile.txt", listTasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundMessage();;
        } catch (FileEmptyException e) {
            Ui.printEmptyFileMessage();
        }
    }

    private static void loadTasks(String filePath, ArrayList<Task> listTasks)
            throws FileEmptyException, FileNotFoundException {
        File saveFile = new File(filePath);
        Scanner load = new Scanner(saveFile);
        if (!load.hasNext()) {
            throw new FileEmptyException();
        }
        else {
            int taskIndex = 1;
            while (load.hasNext()) {
                String text = load.nextLine();
                String parsedPreviousTask = Parser.parsePreviousTask(text);
                previousTasks.add(parsedPreviousTask);
                if (text.contains("[X]")) {
                    previousTasks.add("mark " + taskIndex);
                }
                taskIndex += 1;
            }
        }
        for (String previousTask : previousTasks) {
            TaskList.handleUserTasks(previousTask, listTasks);
        }
        load.close();
    }

    public static void saveTasks(ArrayList<Task> listTasks) {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                if (dataDir.mkdirs()) {
                    System.out.println("Data directory created successfully.");
                } else {
                    System.err.println("Failed to create data directory.");
                    return;
                }
            }

            String filePath = "./data/savefile.txt";
            FileWriter writer = new FileWriter(filePath);
            String concatenatedData = compileData(listTasks);
            writer.write(concatenatedData);
            writer.close();

            System.out.println("Content has been saved to the file successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static String compileData(ArrayList<Task> listTasks) {
        StringBuilder dataToSave = new StringBuilder();
        for (Task listTask : listTasks) {
            if (listTask != null) {
                dataToSave.append(listTask);
                dataToSave.append("\n");
            }
        }
        return dataToSave.toString();
    }
}
