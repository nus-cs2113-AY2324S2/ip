package chatman.storage;

import chatman.ChatMan;
import chatman.exceptions.*;
import chatman.tasks.Task;
import chatman.utility.CommandParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

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
        System.out.printf("%s%n%n", "____________________________________________________________");

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


    public static void readStorageFile() throws FalseCommandException, FullListException, IncorrectArgumentNumException, IncorrectMarkUnmarkException, EmptyListException, IncorrectFormatException {


        try (BufferedReader reader = Files.newBufferedReader(storageFile)) {
            String line;
            int listCount = 0;
            String[] parsedLine;

            //@@author LWachtel1-reused
            //Reused from https://stackoverflow.com/questions/28977308/read-all-lines-with-bufferedreader
            //with modifications
            while ((line = reader.readLine()) != null && line.isEmpty() == false) {
                //@@author
                parsedLine = line.split("!", 2);
                CommandParser.parse(parsedLine[1], true);

                if (parsedLine[0].equals("[X]")) {
                    ChatMan.accessTasks().get(listCount).setDone(true);
                } else {
                    ChatMan.accessTasks().get(listCount).setDone(false);
                }

                listCount++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void writeStorageFile() {


        int index;
        String line;
        ArrayList<String> fileContents;
        String[] parsedCommand;

        try (BufferedWriter writer = Files.newBufferedWriter(storageFile, StandardOpenOption.WRITE);
             BufferedReader reader = Files.newBufferedReader(storageFile)) {

            for (int i = 0; i < ChatMan.accessTasks().size(); i++) {

                Task currentTask=ChatMan.accessTasks().get(i);
                String command = currentTask.getCommand();
                switch (command.split(" ")[0].toUpperCase()) {
                    case "TODO":
                        //Fallthrough
                    case "DEADLINE":
                        //Fallthrough
                    case "EVENT":

                        writer.write(currentTask.getStatusIcon());
                        writer.write("!");
                        writer.write(command);
                        writer.write(System.getProperty("line.separator"));

                        break;

                    default:
                        break;
                }


            }
        } catch (IOException e) {

        }

    }

}


