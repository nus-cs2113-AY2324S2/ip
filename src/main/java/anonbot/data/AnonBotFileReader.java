package anonbot.data;

import anonbot.exception.ImportDataException;
import anonbot.misc.ImportParser;
import anonbot.task.Task;
import anonbot.task.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AnonBotFileReader {
    private static int getMostRecentTaskNumber(Scanner fileReader) throws ImportDataException {
        if (!fileReader.hasNextLine()){
            throw new ImportDataException("File is empty");
        }
        try {
            String data = fileReader.nextLine();
            return Integer.parseInt(data);
        } catch (NumberFormatException e){
            throw new ImportDataException("First line Parameter is corrupted");
        }
    }

    private static void populateTasks(Scanner fileReader){
        while(fileReader.hasNextLine()){
            String rawData= fileReader.nextLine();
            try {
                String[] parsedData = ImportParser.convertToParsableTask(rawData);
                String taskType = parsedData[0];
                boolean isTaskDone = parsedData[1].equals("Y");
                int taskNumber = Integer.parseInt(parsedData[2]);
                String taskDescription = parsedData[3];
                switch (taskType){
                case "todo":
                    TaskManager.createTask(taskDescription, Task.TaskType.TODO,taskNumber,isTaskDone);
                    break;
                case "deadline":
                    TaskManager.createTask(taskDescription, Task.TaskType.DEADLINE,taskNumber,isTaskDone);
                    break;
                case "event":
                    TaskManager.createTask(taskDescription, Task.TaskType.EVENT,taskNumber,isTaskDone);
                    break;
                default:
                    System.out.println("Import Error: Unknown Task Type");
                }
            } catch (ImportDataException e){
                e.printErrorMessage();
            } catch (NumberFormatException e){
                System.out.println("Error Processing Task Import data: "
                        + "Not a task number where a task number was expected");
            }
        }
    }
    public static void loadAnonBotData(){
        File f = new File(AnonBotFile.FILE_NAME);
        try {
            Scanner fileReader = new Scanner(f);
            int mostRecentTaskNumber = getMostRecentTaskNumber(fileReader);
            if (fileReader.hasNextLine()){
                TaskManager.setNumberOfActiveTasks(mostRecentTaskNumber);
                populateTasks(fileReader);
            }


        } catch (FileNotFoundException e){
            System.out.println("Warning: Unable to find the task list to load.");
            return;
        } catch (ImportDataException e){
            e.printErrorMessage();
            return;
        }
    }
}
