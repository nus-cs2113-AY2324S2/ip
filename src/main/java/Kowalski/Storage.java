package Kowalski;

import Kowalski.UI.Ui;
import Kowalski.tasks.Deadline;
import Kowalski.tasks.Event;
import Kowalski.tasks.Task;
import Kowalski.tasks.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String TEXT_FILE_FOLDER = "data";
    private static final String FULL_FILE_PATH = "data/Kowalski.txt";
    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";
    private static final String COMPLETED_TASK_CROSS = "X";
    private static final String FROM_AND_TO_SEPARATOR = " - ";

    /**
     * This function takes in the lines of code from the Kowalski.txt and processes them to
     * list out all the previous tasks which we had saved.
     * @param fileInput: String containing each line of input from Kowalski.txt
     */
    public static void restoreTaskList(String fileInput, List<Task> currentTask){
        String [] inputArray = fileInput.split("\\s*\\|\\s*");
        switch (inputArray[0].trim()){
        case TODO:
            Task newToDoTask = new Todo(inputArray[2].trim());
            if (inputArray[1].trim().equals(COMPLETED_TASK_CROSS)) {
                newToDoTask.markAsDone();
            } else {
                newToDoTask.markAsNotDone();
            }
            currentTask.add(newToDoTask);
            break;

        case DEADLINE:
            Task newDeadlineTask = new Deadline(inputArray[2].trim(), inputArray[3].trim());
            if (inputArray[1].trim().equals(COMPLETED_TASK_CROSS)) {
                newDeadlineTask.markAsDone();
            } else {
                newDeadlineTask.markAsNotDone();
            }
            currentTask.add(newDeadlineTask);
            break;
        case EVENT:
            String [] fromAndTo = inputArray[3].trim().split(FROM_AND_TO_SEPARATOR);
            Task newEventTask = new Event(inputArray[2].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
            if (inputArray[1].trim().equals(COMPLETED_TASK_CROSS)) {
                newEventTask.markAsDone();
            } else {
                newEventTask.markAsNotDone();
            }
            currentTask.add(newEventTask);
            break;
        default:
            System.out.println("Kowalski Analysis Error: Text File Corrupted!");
            break;
        }
    }

    /**
     * Read text file accesses Kowalski.txt and calls the restoreTaskList function
     * @throws IOException when unable to get the Kowalski file or has any input errors
     */
    public static void readTextFile(List<Task> currentTask) throws IOException{
        try {
            createTextFileFolder(Paths.get(TEXT_FILE_FOLDER));
            Path filePath = Paths.get(FULL_FILE_PATH);
            if (!Files.exists(filePath)){
                System.out.println("Creating new Kowalski.txt file");
                Files.createFile(filePath);
            }
            FileReader fileReader = new FileReader(FULL_FILE_PATH);
            BufferedReader line = new BufferedReader(fileReader);
            System.out.println("Kowalski retrieving previous data...");
            while (line.ready()) {
                restoreTaskList(line.readLine(), currentTask);
            }
            System.out.println("Kowalski Data Retrieval Complete!");
            Ui.printDivider();
        } catch (IOException e){
            System.out.println("Kowalski Data Retrieval Failed!");
            throw e;
        }
    }

    /**
     * Function which is called to generate an arrayList "lines" which updates according to the users' inputs.
     * Calls the writeTextFile function to update Kowalski.txt
     */
    public static void writeText(List<Task> currentTask){
        List<String> lines = new ArrayList<>();
        for (Task task:currentTask){
            lines.add(task.textFileInputString());
        }
        Ui.printDivider();
        System.out.println("Kowalski analysing inputs...");
        writeTextFile(lines);
    }

    /**
     * Accesses the Kowalski.txt and updates it in the correct format.
     * @param lines: Arraylist containing the processed current tasks in the CurrentTask
     */
    public static void writeTextFile(List <String> lines) {
        try {
            Path parentPath = Paths.get(TEXT_FILE_FOLDER);
            createTextFileFolder(parentPath);

            FileWriter writer = new FileWriter(FULL_FILE_PATH);
            for (String line : lines) {
                writer.write(line + "\n");

            }
            System.out.println("Task recorded in the Text file!");
            writer.close();
        } catch (IOException e){
            System.out.println("Kowalski Analysis failed - Issue with directory/text file!");
        }
    }

    /**
     * Used to create the data folder to store the Kowalski.txt file
     * @param parentPath: Path file containing the path we intend to make Kowalski.txt in
     * @throws IOException whenever the input for the path or creation of the directory is improper
     */
    public static void createTextFileFolder(Path parentPath ) throws IOException{
        try {
            Files.createDirectories(parentPath);
        } catch (FileAlreadyExistsException ignored){
            //Ignore this error if file exists
        } catch (IOException e){
            System.out.println("Skipper, I am unable to create the data directory for you!");
            throw e;
        }
    }
}
