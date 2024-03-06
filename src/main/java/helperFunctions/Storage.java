package helperFunctions;

import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    /**
     * convert each line in textFile to a Task
     **/
    public static void readFile(String FILE_PATH, TaskList tasks, boolean isReadMode) throws InvalidParamsException {
        // get absoluteFilePath
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currentDirectory);
        String absoluteFilePath = path + "/" + FILE_PATH;

        // checks if saveFile.txt exists
        File f = new File(absoluteFilePath);
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName() + "@" + path);
            } else {
                System.out.println("File " + f.getName() + " already exists");
            }
        } catch (IOException e) {
            throw new InvalidParamsException("Error Occurred");
        }

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

            // checks validity of index TODO
            if ((lineInFile.indexOf('[') == -1) ||
                    (START_INDEX_OF_TYPE + 1 > lineInFile.length()) ||
                    (START_INDEX_OF_MARK + 1 > lineInFile.length()) ||
                    (START_INDEX_OF_DESCRIPTION + 1 > lineInFile.length())) {
                throw new InvalidParamsException(lineInFile + " is not valid");
            }

            // create the command input line
            String command = "";

            // getType
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

            // getMark
            char mark = lineInFile.charAt(START_INDEX_OF_MARK);
            if (mark != '0' && mark != '1') {
                throw new InvalidParamsException("invalid mark");
            }

            // getDescription
            String taskDescription = "";
            // process input for deadline params
            if (type == 'D') {
                // change "(by: " to "/"
                // change ")" to "" ie last elem
                int startIndexToChange = lineInFile.lastIndexOf("(by: ");
                int endIndexToChange = lineInFile.lastIndexOf(")");
                taskDescription += lineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
                taskDescription += "/";
                taskDescription += lineInFile.substring(startIndexToChange + 5, endIndexToChange);
            } else if (type == 'E') { // process input for event params
                // change "(from: " to "/"
                // change "to " to "/"
                // change ")" to "" ie last elem
                // NOTE: startIndexToChange & midIndextoChange may be ambiguous depending on file text
                int midIndexToChange = lineInFile.lastIndexOf("to ");
                int startIndexToChange = lineInFile.lastIndexOf("(from: ", midIndexToChange);
                int endIndexToChange = lineInFile.lastIndexOf(')');
                taskDescription += lineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
                taskDescription += "/";
                taskDescription += lineInFile.substring(startIndexToChange + 7, midIndexToChange);
                taskDescription += "/";
                taskDescription += lineInFile.substring(midIndexToChange + 3, endIndexToChange);
            } else { // type == 'T'
                taskDescription = lineInFile.substring(START_INDEX_OF_DESCRIPTION);
            }
            command += taskDescription;

            // process command in file
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
}
