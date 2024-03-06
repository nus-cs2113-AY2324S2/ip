package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static UnparsedTaskList inputList = new UnparsedTaskList();
    public void trySaveList() {
        try {
            saveList();
        } catch (IOException e) {
            System.out.println("savedList not found, creating now");
        }
    }

    public void saveList() throws IOException {
        FileWriter fileInput = new FileWriter("./savedList.txt");
        for (int i = 0; i < inputList.size(); i++) {
            fileInput.write(inputList.get(i) + "\n");
        }
        fileInput.close();
    }

    public void tryRetrieveList() {
        try {
            retrieveList();
        } catch (FileNotFoundException e) {
            System.out.println("Saved list not found, creating now");
        }
    }

    public void retrieveList() throws FileNotFoundException {
        File f = new File("./savedList.txt");
        Scanner in = new Scanner(f);
        while (in.hasNext()) {
            Command.directAddTask(in.nextLine());
        }
    }
}
