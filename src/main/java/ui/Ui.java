package ui;

import java.io.FileNotFoundException;

import format.Formatter;
import parser.Parser;
import task.Task;
import storage.Storage;
import task.TaskList;

import java.util.Scanner;


public class Ui {

    protected Formatter formatter;

    public Ui() {
        formatter = new Formatter();
    }

    public void startConversation() {
        Parser parser = new Parser();
        Scanner in = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList taskList = new TaskList();

        try {
            storage.readFile(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("\tCan not find your file!!!\n" + e.getMessage());
        }

        String userInput = in.nextLine().trim();
        while (!userInput.equalsIgnoreCase("bye")) {
            parser.parseCommand(userInput, taskList);
            userInput = in.nextLine().trim();
        }
    }

    public void printNewTaskAddedMessage(Task t, TaskList taskList) {
        formatter.printDividingLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.getIdentity() + t.getStatusIcon() + " " + t);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
        formatter.printDividingLine();
    }

    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + formatter.generateLogo());
        formatter.printDividingLine();
        System.out.println("\tHi!, I'm 'Noob'");
        System.out.println("\tWhat can I do for you?");
        formatter.printDividingLine();
    }

    public void printGoodbyeMessage() {
        formatter.printDividingLine();
        System.out.println("\tBye. Hope to see you again soon!");
        formatter.printDividingLine();
    }

}
