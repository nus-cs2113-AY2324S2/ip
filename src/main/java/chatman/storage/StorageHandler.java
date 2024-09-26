package chatman.storage;

import chatman.exceptions.*;
import chatman.tasks.Task;
import chatman.utility.Parser;
import chatman.utility.Tasklist;
import chatman.utility.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

/**
 * Implements file handling operations, so that task list can be saved to .txt file for storage even after program
 * termination.
 *
 * @author LWachtel1
 */

public class StorageHandler {

    //@@author LWachtel1-reused
    //Reused from https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
    private static String root = System.getProperty("user.dir");
    ;
    private static Path storageFile = Paths.get(root, "chatman.txt");
    //@@author

    //@@author LWachtel1-reused
    //Reused from https://stackoverflow.com/questions/17552299/how-to-get-the-path-string-from-a-java-nio-path
    private static String pathString = Paths.get(root, "chatman.txt").normalize().toString();
    //@@author

    private static String storageFileSeparator = "!";


    /**
     * Constructor for StorageHandler.
     */
    public StorageHandler() {

    }

    /**
     * Checks if 'chatman.txt' (task storage file) already exists in current directory. If not, creates file.
     *
     * @throws IOException If file creation is attempted but fails.
     */
    public static void openStorageFile() throws IOException {
        System.out.printf("%s%n%n", Ui.getChatbotSeparator());

        try {
            if (Files.exists(storageFile)) {
                System.out.printf("Task storage file '%s' already exists.%n", pathString);
            } else {
                Files.createFile(storageFile);
                System.out.printf("Task storage file '%s' created.%n", pathString);
            }

        } catch (IOException e) {
            throw new IOException("Task storage file creation failed. Program failure.");
        }

    }

    /**
     * Reads 'chatman.txt' (task storage file) content and adds corresponding tasks into task list by calling
     * Parser's static parse() method.
     *
     * @throws FalseCommandException If thrown by Parser's static parse() method.
     * @throws FullListException If thrown by Parser's static parse() method.
     * @throws IncorrectArgumentNumException If thrown by Parser's static parse() method.
     * @throws IncorrectIndexException If thrown by Parser's static parse() method.
     * @throws EmptyListException If thrown by Parser's static parse() method.
     * @throws IncorrectFormatException If thrown by Parser's static parse() method.
     * @throws IOException If reading from file is attempted but fails.
     */
    public static void loadStorageFile() throws FalseCommandException, FullListException, IncorrectArgumentNumException,
            IncorrectIndexException, EmptyListException, IncorrectFormatException, IOException {

        try (BufferedReader reader = Files.newBufferedReader(storageFile)) {
            String line;
            int listCount = 0;
            String[] parsedLine;

            //@@author LWachtel1-reused
            //Reused from https://stackoverflow.com/questions/28977308/read-all-lines-with-bufferedreader
            //with modifications
            while ((line = reader.readLine()) != null && line.isEmpty() == false) {
            //@@author
                parsedLine = line.split(storageFileSeparator, 2);
                Parser.parse(parsedLine[1], true);

                if (parsedLine[0].equals("[X]")) {
                    Tasklist.getTask(listCount).setDone(true);
                } else {
                    Tasklist.getTask(listCount).setDone(false);
                }

                listCount++;
            }
        } catch (IOException e) {
            throw new IOException("Error reading stored tasks from hard disk.");
        }


    }

    /**
     * Writes all stored tasks to 'chatman.txt' (task storage file)
     *
     * @throws IOException If writing to task storage file 'chatman.txt' is attempted but fails.
     */
    public static void writeStorageFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(storageFile, StandardOpenOption.WRITE);
             BufferedReader reader = Files.newBufferedReader(storageFile)) {

            for (int i = 0; i < Tasklist.getSize(); i++) {

                Task currentTask= Tasklist.getTask(i);
                String command = currentTask.getCommand();
                String commandType = command.split(" ")[0].toUpperCase();

                switch (commandType) {
                case "TODO":
                    //Fallthrough
                case "DEADLINE":
                    //Fallthrough
                case "EVENT":

                    writer.write(currentTask.getStatusIcon());
                    writer.write(storageFileSeparator);
                    writer.write(command);
                    writer.write(System.getProperty("line.separator"));

                    break;

                default:
                    break;
                }

            }
        } catch (IOException e) {
            throw new IOException("Error saving currently stored tasks to hard disk.");
        }

    }

}


