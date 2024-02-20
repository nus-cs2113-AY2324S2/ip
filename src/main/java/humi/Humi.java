package humi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Humi {
    public static final String LINE = "    ____________________________________________________________";
    //    String logo = " __        _        \n"
    //                + "|  _ \\ _   | | __ \n"
    //                + "| | | | | | | |/ / _ \\\n"
    //                + "| || | || |   <  __/\n"
    //                + "|_/ \\,||\\\\_|\n";

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("     Hello! I'm Humi");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);

        TaskManager taskManager = new TaskManager();
        FileProcessor.textContent = "";

        // read text file
        try {
            ArrayList<String> inputFile = FileProcessor.readFile("data/list.txt");
            if (!inputFile.isEmpty()) {
                for (int i = 0; i < inputFile.size(); i += 1) {
                    taskManager.addTask(inputFile.get(i));
                    FileProcessor.appendTextContent(inputFile.get(i));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, attempting to create file.");
            try {
                File textFile = new File("data/list.txt");
                if (textFile.createNewFile()) {
                    System.out.println("File has been created.");
                }
            } catch (IOException err) {
                System.out.println("Fail to create file.");
            }
        }

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            taskManager.handleCommand(input);
            input = in.nextLine();
        }

        // write text file
        try {
            FileProcessor.writeFile("data/list.txt", FileProcessor.textContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
