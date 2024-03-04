package gandalf;

import action.Task;
import exception.FailedDirectoryCreationException;
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
            loadTasks("data/save-file.txt", listTasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundMessage();
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
            boolean hideInput = true;
            Ui.handleUserCommand(previousTask, listTasks, hideInput);
        }
        Ui.handleUserCommand("list", listTasks, false);
        load.close();
    }

    public static void saveTasks(ArrayList<Task> listTasks) {
        try {

            createDirectory();
            String filePath = "./data/save-file.txt";
            FileWriter writer = new FileWriter(filePath);
            String concatenatedData = compileData(listTasks);
            writer.write(concatenatedData);
            writer.close();

            Ui.printSuccessfulSaveMessage();

        } catch (FailedDirectoryCreationException e) {
            Ui.printFailedDirectoryCreationMessage();
        } catch(IOException e) {
            Ui.printCorruptedWriteMessage(e);
        }
    }

    private static void createDirectory() throws FailedDirectoryCreationException {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdirs()) {
                Ui.printSuccessfulDirectoryCreationMessage();
            } else {
                throw new FailedDirectoryCreationException();
            }
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
