package roleypoley.data;

import roleypoley.command.HandleCommand;
import roleypoley.exception.RoleyPoleyFileException;
import roleypoley.exception.RoleyPoleyParseException;
import roleypoley.task.Deadline;
import roleypoley.task.Event;
import roleypoley.task.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Read data from RoleyPoleyData.txt
 * If File does not exists, a new file will be created
 */
public class ReadFile {
    public static void readFileToArrayList() throws RoleyPoleyFileException {
        try {
            Path myPath = Paths.get("./RoleyPoleyData.txt");
            List<String> taskList = Files.readAllLines(myPath, StandardCharsets.UTF_8);
            for (String line : taskList) {
                if (!line.trim().isEmpty()) {
                    convertTask(line);
                }
            }
        } catch (IOException | RoleyPoleyParseException e) {
            File textFile = new File("RoleyPoleyData.txt");
        }
    }

    /**
     * Convert tasks in text file into an ArrayList
     *
     * @param line a line of string read in from the text file
     * @throws RoleyPoleyFileException if task has not been indicated as '1' or '0'
     */
    public static void convertTask(String line) throws RoleyPoleyFileException, RoleyPoleyParseException {
        String[] identifyTaskType = line.split(" ");
        String description = line.substring("X | Y | ".length());
        boolean isDone;
        switch (identifyTaskType[2]) {
        case "1":
            isDone = true;
            break;
        case "0":
            isDone = false;
            break;
        default:
            throw new RoleyPoleyFileException("FileContentError");
        }
        switch (identifyTaskType[0]) {
        case "T":
                HandleCommand.getTaskList().add(new Todo(description, isDone));
            break;
        case "D":
            if (!description.contains("(by:")) {
                throw new RoleyPoleyFileException("DeadLineFormatError");
            } else {
                HandleCommand.getTaskList().add(new Deadline(description, isDone));
            }
            break;
        case "E":
            if (!description.contains("(from:") || !description.contains("to:")) {
                throw new RoleyPoleyFileException("EventFormatError");
            } else {
                HandleCommand.getTaskList().add(new Event(description, isDone));
            }
            break;
        default:
            throw new RoleyPoleyFileException("FileContentError");
        }
    }
}