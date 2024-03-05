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
    private static final String TEXT_FILE_CORRUPTED_ERROR = "Kowalski Analysis Error: Text File Corrupted!";
    private static final String CREATE_NEW_TEXT_FILE = "Creating new Kowalski.txt file";
    private static final String RETRIEVING_PREVIOUS_DATA_MESSAGE = "Kowalski retrieving previous data...";
    private static final String DATA_RETRIEVAL_SUCCESS_MESSAGE = "Kowalski Data Retrieval Success!";
    private static final String DATA_RETRIEVAL_FAIL_MESSAGE = "Kowalski Data Retrieval Failed!";
    private static final String ANALYSING_INPUT_MESSAGE = "Kowalski analysing inputs..." ;
    private static final String CHANGE_RECORDED_IN_TEXT_FILE = "Change recorded in the Text file!";
    private static final String ISSUE_WITH_DIRECTORY_OR_TEXT_FILE = "Kowalski Analysis failed - Issue with directory/text file!";
    private static final String FAILURE_TO_CREATE_DIRECTORY = "Skipper, I am unable to create the data directory for you!" ;
    private static final String NEWLINE = "\n" ;

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
            System.out.println(TEXT_FILE_CORRUPTED_ERROR);
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
                System.out.println(CREATE_NEW_TEXT_FILE);
                Files.createFile(filePath);
            }
            FileReader fileReader = new FileReader(FULL_FILE_PATH);
            BufferedReader line = new BufferedReader(fileReader);
            System.out.println(RETRIEVING_PREVIOUS_DATA_MESSAGE);
            while (line.ready()) {
                restoreTaskList(line.readLine(), currentTask);
            }
            System.out.println(DATA_RETRIEVAL_SUCCESS_MESSAGE);
            Ui.printDivider();
        } catch (IOException e){
            System.out.println(DATA_RETRIEVAL_FAIL_MESSAGE);
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
        System.out.println(ANALYSING_INPUT_MESSAGE);
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
                writer.write(line + NEWLINE);

            }
            System.out.println(CHANGE_RECORDED_IN_TEXT_FILE);
            writer.close();
        } catch (IOException e){
            System.out.println(ISSUE_WITH_DIRECTORY_OR_TEXT_FILE);
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
            System.out.println(FAILURE_TO_CREATE_DIRECTORY);
            throw e;
        }
    }
}
