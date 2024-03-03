package humi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Humi {
    private static Ui ui;
    private static TaskManager taskManager;
    private static Storage storage;

    public Humi(String filePath) {
        ui = new Ui();
        storage = new Storage();
        taskManager = new TaskManager(storage);
    }

    public void run() {
        ui.printWelcome();

        // read stored text file
        try {
            ArrayList<String> inputFile = storage.readFile("data/list.txt");
            if (!inputFile.isEmpty()) {
                for (String s : inputFile) {
                    taskManager.loadTask(s);
                    storage.appendTextContent(s);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, attempting to create file.");
            try {
                File dataFolder = new File("./data");
                if (!dataFolder.exists()) {
                    System.out.println("Folder not found, attempting to create folder.");
                    if (dataFolder.mkdirs()) {
                        System.out.println("Data folder has been created successfully.");
                    } else {
                        System.err.println("Failed to create data folder.");
                    }
                }
                File textFile = new File("data/list.txt");
                if (textFile.createNewFile()) {
                    System.out.println("File has been created.");
                }
            } catch (IOException err) {
                System.out.println("Failed to create file.");
            }
        }

        // read user input
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager.handleCommand(input);
            input = in.nextLine();
        }

        // write text file
        try {
            storage.writeFile("data/list.txt", storage.textContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        ui.printExit();
    }

    public static void main(String[] args) {
        new Humi("data/list.txt").run();
    }
}
