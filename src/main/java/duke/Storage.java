package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that deals with storage and retrieval of task list
 */
public class Storage {
    private static UnparsedTaskList inputList = new UnparsedTaskList();

    /**
     * Tries to save the list locally if the file is not found it creates it instead
     */
    public void trySaveList() {
        try {
            saveList();
        } catch (IOException e) {
            System.out.println("savedList not found, creating now");
        }
    }

    /**
     * Saves list the list locally the users device so that the list data can be later retrieved
     *
     * @throws IOException Thrown savedList.txt is not found
     */
    public void saveList() throws IOException {
        FileWriter fileInput = new FileWriter("./savedList.txt");
        for (int i = 0; i < inputList.size(); i++) {
            fileInput.write(inputList.get(i) + "\n");
        }
        fileInput.close();
    }

    /**
     * Tries to retrieve the list stored locally, prints error message if the list does not exist
     */
    public void tryRetrieveList() {
        try {
            retrieveList();
        } catch (FileNotFoundException e) {
            System.out.println("Saved list not found, creating now");
        }
    }

    /**
     * Reads from locally saved text file and adds the tasks to the task list, creates file if it doesnt exist
     *
     * @throws FileNotFoundException Thrown when the file does not exist
     */
    public void retrieveList() throws FileNotFoundException {
        File f = new File("./savedList.txt");
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            Command.directAddTask(in.nextLine());
        }
    }
}
