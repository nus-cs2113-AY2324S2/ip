package chatman.storage;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageHandler {

    //@@author LWachtel1-reused
    //Reused from https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
    private static String root = System.getProperty("user.dir");;
    private static Path storageFile =Paths.get(root, "chatman.txt");
    //@@author

    //@@author LWachtel1-reused
    //Reused from https://stackoverflow.com/questions/17552299/how-to-get-the-path-string-from-a-java-nio-path
    private static String pathString = Paths.get(root, "chatman.txt").normalize().toString();
    //@@author
    public StorageHandler() {

    }

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




}
