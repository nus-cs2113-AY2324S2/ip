package helperFunctions;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static void appendToFile(String filePath, String textToAppend) throws InvalidParamsException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new InvalidParamsException("Cannot append to file");
        }
    }

    public static void writeToFile(String filePath, String textToWrite) throws InvalidParamsException {
        try {
            FileWriter fw = new FileWriter(filePath); // overwrites file
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            throw new InvalidParamsException("Cannot write to file");
        }
    }

    public static String getAbsoluteFilePath(String FILE_PATH) {
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path directoryPath = java.nio.file.Paths.get(currentDirectory);
        return directoryPath + "/" + FILE_PATH;
    }

    /**
     * Checks if saveFile.txt exists
     *
     * @param absoluteFilePath of saveFile.txt
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
     * convert each line in textFile to a Task
     **/
    public static void readFile(String FILE_PATH, TaskList tasks) throws InvalidParamsException {
        String absoluteFilePath = getAbsoluteFilePath(FILE_PATH);
        File f = new File(absoluteFilePath);

        try {
            isFileExist(f, absoluteFilePath);
            processFile(f, FILE_PATH, tasks);
        } catch (InvalidParamsException e) {
            throw new InvalidParamsException(e.getMessage());
        }

    }

    private static void processFile(File f, String FILE_PATH, TaskList tasks) throws InvalidParamsException {
        Scanner s;
        try {
            s = new Scanner(f);  // create a Scanner using file as source
        } catch (FileNotFoundException e) {
            throw new InvalidParamsException("File not found");
        }
        int lineNum = 1; // keeps track of current lineNum
        while (s.hasNext()) {
            String lineInFile = s.nextLine(); // read line in file

            // process lines in saveFile.txt
            int START_INDEX_OF_TYPE = lineInFile.indexOf('[') + 1; // format of text: "1. [T][1] startOfTaskDescription"
            int START_INDEX_OF_MARK = START_INDEX_OF_TYPE + 3; // mark index is 3 positions to the right
            int START_INDEX_OF_DESCRIPTION = START_INDEX_OF_MARK + 3; // description index is 3 more positions to the right

            // checks validity of index
            if (!isIndexesValid(lineInFile, START_INDEX_OF_TYPE, START_INDEX_OF_MARK, START_INDEX_OF_DESCRIPTION)) {
                throw new InvalidParamsException(lineInFile + " is not valid");
            }

            String command = "";
            char mark;
            try {
                command += generateCommandFromFileLine(START_INDEX_OF_TYPE, START_INDEX_OF_DESCRIPTION, lineInFile);
                mark = getMarkStatus(START_INDEX_OF_MARK, lineInFile);
            } catch (InvalidParamsException e) {
                throw new InvalidParamsException(e.getMessage());
            }

            // process command in file
            boolean isReadMode = true;
            Parser.processUserInput(tasks, command, FILE_PATH, isReadMode);

            // create mark command (for mark tasks only)
            if (mark == '1') {
                // create mark command
                String markCommand;
                markCommand = "mark " + lineNum;
                Parser.processUserInput(tasks, markCommand, FILE_PATH, isReadMode);
            }
            lineNum += 1;
        }
    }
    private static boolean isIndexesValid(String lineInFile, int START_INDEX_OF_TYPE,
            int START_INDEX_OF_MARK, int START_INDEX_OF_DESCRIPTION) {
        return !(lineInFile.indexOf('[') == -1) ||
                (START_INDEX_OF_TYPE + 1 > lineInFile.length()) ||
                (START_INDEX_OF_MARK + 1 > lineInFile.length()) ||
                (START_INDEX_OF_DESCRIPTION + 1 > lineInFile.length());
    }

    private static String generateCommandFromFileLine(int START_INDEX_OF_TYPE,
            int START_INDEX_OF_DESCRIPTION, String lineInFile) throws InvalidParamsException {

        try {
            String command = "";
            char type = lineInFile.charAt(START_INDEX_OF_TYPE);

            if (type == 'T') {
                command += "todo ";
            } else if (type == 'D') {
                command += "deadline ";
            } else if (type == 'E') {
                command += "event ";
            } else {
                throw new InvalidParamsException(lineInFile + " type is not valid");
            }
            command += getDescriptionCommand(type, START_INDEX_OF_DESCRIPTION, lineInFile);

            return command;

        } catch (InvalidParamsException e) {
            throw new InvalidParamsException(e.getMessage());
        }
    }

    private static String getDescriptionCommand(char type, int START_INDEX_OF_DESCRIPTION, String lineInFile) {
        String taskDescription = "";

        if (type == 'D') {
            int startIndexToChange = lineInFile.lastIndexOf("(by: "); // change "(by: " to "/"
            int endIndexToChange = lineInFile.lastIndexOf(")"); // change ")" to ""
            taskDescription += lineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
            taskDescription += "/";
            taskDescription += lineInFile.substring(startIndexToChange + 5, endIndexToChange);
        } else if (type == 'E') { // process input for event params
            int midIndexToChange = lineInFile.lastIndexOf("to "); // change "to " to "/"
            int startIndexToChange = lineInFile.lastIndexOf("(from: ", midIndexToChange); // change "(from: " to "/"
            int endIndexToChange = lineInFile.lastIndexOf(')'); // change ")" to "" ie last elem
            taskDescription += lineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
            taskDescription += "/";
            taskDescription += lineInFile.substring(startIndexToChange + 7, midIndexToChange);
            taskDescription += "/";
            taskDescription += lineInFile.substring(midIndexToChange + 3, endIndexToChange);
        } else { // type == 'T'
            taskDescription = lineInFile.substring(START_INDEX_OF_DESCRIPTION);
        }
        return taskDescription;
    }

    private static char getMarkStatus(int START_INDEX_OF_MARK, String lineInFile)
            throws InvalidParamsException {
        char mark = lineInFile.charAt(START_INDEX_OF_MARK);

        if (mark != '0' && mark != '1') {
            throw new InvalidParamsException("invalid mark");
        }
        return mark;
    }
}
