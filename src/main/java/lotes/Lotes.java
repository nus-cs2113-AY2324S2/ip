package lotes;

import lotes.parser.Parser;
import lotes.task.TaskList;
import lotes.storage.Storage;
import java.util.Scanner;

public class Lotes {

    public static final Scanner inputCommand = new Scanner(System.in); // Setting the Scanner

    public static void main(String[] args) {
        System.out.println(TaskList.greetingsMessage); // Print greetings message

        TaskList taskList = new TaskList(); // Creating the lotes.task.TaskList object

        Storage.readFile();

        Parser.interpretUserInput(inputCommand, taskList); // Interprets the user input
    }

}
