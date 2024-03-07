package helperFunctions;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Functions relating to FILE_NAME storing all the tasks
 */
public class Storage {
    /**
     * Appends a task to Tasks in FILE_NAME
     *
     * @param FILE_NAME to store tasks in
     * @param textToAppend the new Task to add
     * @throws InvalidParamsException if error appending to file
     */
    public static void appendToFile(String FILE_NAME, String textToAppend) throws InvalidParamsException {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true); // in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new InvalidParamsException("Cannot append to file");
        }
    }

    /**
     * Overwrites all tasks in FILE_NAME
     *
     * @param FILE_NAME to store tasks in
     * @param textToWrite the new tasks to write
     * @throws InvalidParamsException if error writting to file
     */
    public static void writeToFile(String FILE_NAME, String textToWrite) throws InvalidParamsException {
        try {
            FileWriter fw = new FileWriter(FILE_NAME); // overwrites file
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            throw new InvalidParamsException("Cannot write to file");
        }
    }

    /**
     * Gets absolute file path to FILE_NAME
     *
     * @param FILE_NAME to store tasks in
     * @return absolute file path
     */
    public static String getAbsoluteFilePath(String FILE_NAME) {
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path directoryPath = java.nio.file.Paths.get(currentDirectory);
        return directoryPath + "/" + FILE_NAME;
    }

    /**
     * Checks if FILE_NAME exists. If not, create it.
     *
     * @param f File object pointing to absoluteFilePath
     * @param absoluteFilePath of saveFile.txt
     * @throws InvalidParamsException if error creating file
     */
    public static void isFileExist(File f, String absoluteFilePath) throws InvalidParamsException {
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName() + "at" + absoluteFilePath);
            } else {
                System.out.println("File " + f.getName() + " already exists at " + absoluteFilePath);
            }
        } catch (IOException e) {
            throw new InvalidParamsException("Error Occurred");
        }
    }

    /**
     * Checks if FILE_NAME exists and process it
     *
     * @param FILE_NAME to store tasks in
     * @param tasks TaskList object to store all tasks
     * @throws InvalidParamsException if error creating or processing file
     **/
    public static void readFile(String FILE_NAME, TaskList tasks) throws InvalidParamsException {
        String absoluteFilePath = getAbsoluteFilePath(FILE_NAME);
        File f = new File(absoluteFilePath);

        try {
            isFileExist(f, absoluteFilePath);
            processFile(f, FILE_NAME, tasks);
        } catch (InvalidParamsException e) {
            throw new InvalidParamsException(e.getMessage());
        }

    }

    /**
     * Process FILE_NAME ie adds tasks to Tasks
     *
     * @param f File Object pointing to absolute file path of FILE_NAME
     * @param FILE_NAME to store tasks in
     * @param tasks TaskList object to hold all tasks
     * @throws InvalidParamsException if file not found or invalid mark command
     */
    private static void processFile(File f, String FILE_NAME, TaskList tasks) throws InvalidParamsException {
        Scanner s;
        try {
            s = new Scanner(f);  // create a Scanner using file as source
        } catch (FileNotFoundException e) {
            throw new InvalidParamsException("File not found");
        }
        int lineNum = 1; // keeps track of current lineNum
        while (s.hasNext()) {
            String currentLineInFile = s.nextLine();

            // process lines in saveFile.txt
            int START_INDEX_OF_TYPE = currentLineInFile.indexOf('[') + 1; // format of text: "1. [T][1] startOfTaskDescription"
            int START_INDEX_OF_MARK = START_INDEX_OF_TYPE + 3; // mark index is 3 positions to the right
            int START_INDEX_OF_DESCRIPTION = START_INDEX_OF_MARK + 3; // description index is 3 more positions to the right

            // checks validity of index
            if (!isIndexesValid(currentLineInFile, START_INDEX_OF_TYPE, START_INDEX_OF_MARK, START_INDEX_OF_DESCRIPTION)) {
                throw new InvalidParamsException(currentLineInFile + " is not valid");
            }

            String command = "";
            char mark;
            try {
                command += generateCommandFromFileLine(START_INDEX_OF_TYPE, START_INDEX_OF_DESCRIPTION, currentLineInFile);
                mark = getMarkStatus(START_INDEX_OF_MARK, currentLineInFile);
            } catch (InvalidParamsException e) {
                throw new InvalidParamsException(e.getMessage());
            }

            // process command in file
            boolean isReadMode = true;
            Parser.processUserInput(tasks, command, FILE_NAME, isReadMode);

            // create mark command (for mark tasks only)
            if (mark == '1') {
                // create mark command
                String markCommand;
                markCommand = "mark " + lineNum;
                Parser.processUserInput(tasks, markCommand, FILE_NAME, isReadMode);
            }
            lineNum += 1;
        }
    }

    /**
     * Checks if indexes are valid to add tasks when processing FILE_NAME
     *
     * @param currentLineInFile which is being processed
     * @param START_INDEX_OF_TYPE in currentLineInFile for task.getType()
     * @param START_INDEX_OF_MARK in currentLineInFile for task.getStatus()
     * @param START_INDEX_OF_DESCRIPTION in currentLineInFile for task.getDescription()
     * @return true if valid, else false
     */
    private static boolean isIndexesValid(String currentLineInFile, int START_INDEX_OF_TYPE,
            int START_INDEX_OF_MARK, int START_INDEX_OF_DESCRIPTION) {
        return !(currentLineInFile.indexOf('[') == -1) ||
                (START_INDEX_OF_TYPE + 1 > currentLineInFile.length()) ||
                (START_INDEX_OF_MARK + 1 > currentLineInFile.length()) ||
                (START_INDEX_OF_DESCRIPTION + 1 > currentLineInFile.length());
    }

    /**
     * Generate command to add tasks when processing FILE_NAME
     *
     * @param START_INDEX_OF_TYPE in currentLineInFile for task.getType()
     * @param START_INDEX_OF_DESCRIPTION in currentLineInFile for task.getDescription()
     * @param currentLineInFile which is being processed
     * @return command to add tasks
     * @throws InvalidParamsException if invalid or missing task.type
     */
    private static String generateCommandFromFileLine(int START_INDEX_OF_TYPE,
            int START_INDEX_OF_DESCRIPTION, String currentLineInFile) throws InvalidParamsException {

        try {
            String command = "";
            char type = currentLineInFile.charAt(START_INDEX_OF_TYPE);

            if (type == 'T') {
                command += "todo ";
            } else if (type == 'D') {
                command += "deadline ";
            } else if (type == 'E') {
                command += "event ";
            } else {
                throw new InvalidParamsException(currentLineInFile + " type is not valid");
            }
            command += getDescriptionCommand(type, START_INDEX_OF_DESCRIPTION, currentLineInFile);

            return command;

        } catch (InvalidParamsException e) {
            throw new InvalidParamsException(e.getMessage());
        }
    }

    /**
     * Get description from currentLineInFile when processing FILE_NAME
     *
     * @param type of task
     * @param START_INDEX_OF_DESCRIPTION in currentLineInFile for task.getDescription()
     * @param currentLineInFile which is being processed
     * @return taskDescription
     */
    private static String getDescriptionCommand(char type, int START_INDEX_OF_DESCRIPTION, String currentLineInFile) {
        String taskDescription = "";

        if (type == 'D') {
            int startIndexToChange = currentLineInFile.lastIndexOf("(by: "); // change "(by: " to "/"
            int endIndexToChange = currentLineInFile.lastIndexOf(")"); // change ")" to ""
            taskDescription += currentLineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
            taskDescription += "/";
            taskDescription += currentLineInFile.substring(startIndexToChange + 5, endIndexToChange);
        } else if (type == 'E') { // process input for event params
            int midIndexToChange = currentLineInFile.lastIndexOf("to "); // change "to " to "/"
            int startIndexToChange = currentLineInFile.lastIndexOf("(from: ", midIndexToChange); // change "(from: " to "/"
            int endIndexToChange = currentLineInFile.lastIndexOf(')'); // change ")" to "" ie last elem
            taskDescription += currentLineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
            taskDescription += "/";
            taskDescription += currentLineInFile.substring(startIndexToChange + 7, midIndexToChange);
            taskDescription += "/";
            taskDescription += currentLineInFile.substring(midIndexToChange + 3, endIndexToChange);
        } else { // type == 'T'
            taskDescription = currentLineInFile.substring(START_INDEX_OF_DESCRIPTION);
        }
        return taskDescription;
    }

    /**
     * Gets mark status of file when processing FILE_NAME
     *
     * @param START_INDEX_OF_MARK in currentLineInFile for task.getStatus()
     * @param currentLineInFile which is being processed
     * @return mark: '0' = unmarked, '1' = marked
     * @throws InvalidParamsException if invalid mark
     */
    private static char getMarkStatus(int START_INDEX_OF_MARK, String currentLineInFile)
            throws InvalidParamsException {
        char mark = currentLineInFile.charAt(START_INDEX_OF_MARK);

        if (mark != '0' && mark != '1') {
            throw new InvalidParamsException("invalid mark");
        }
        return mark;
    }
}
