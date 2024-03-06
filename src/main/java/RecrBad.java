import helperFunctions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecrBad { //TODO more OOP

    /**
     * Input
     * [any text]           to addToList,
     * list                 to displayList,
     * mark/unmark [index]  to mark item
     */
    public static void main(String[] args) {
        String FILE_PATH = "saveFile.txt";

        Scanner in = new Scanner(System.in);

        Ui.sayHi();

        ArrayList<Task> tasks = new ArrayList<>();
        boolean isReadMode = true; // in reading mode


        try {
            Storage.readFile(FILE_PATH, tasks, isReadMode);
        } catch (InvalidParamsException e) {
            System.out.println(e.getMessage());
            return;
        }

        isReadMode = false; // switch to writing mode
        boolean isRun = true;
        while (isRun) {
            Ui.printLine();
            String line = in.nextLine(); // reads input

            try {
                isRun = Parser.processUserInput(tasks, line, FILE_PATH, isReadMode);
            } catch (InvalidParamsException e) {
                System.out.println(e.getMessage()); // prints out error message
            }
        }
    }

}

