package omoh;

import omoh.customexceptions.CorruptedFileException;
import omoh.customexceptions.EmptyTaskNumberException;
import omoh.customexceptions.EmptyTodoException;
import omoh.tasktypes.Deadline;
import omoh.tasktypes.Event;
import omoh.tasktypes.Task;
import omoh.tasktypes.Todo;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class Omoh {
    public static void main(String[] args) {
        Task.createFileDirectory();
        Task.createOutputFile();
        printWelcomeMessage();
        //initialise the size 100 array if it was not initialised previously
        //because if tasks were already initialised from reading output.txt, we don't need to initialise the array again
        if(Task.totalTasks == 0) {
            Task.initArray();
        }
        Ui.readUserInput();
        bye();
    }

    //Method that prints horizontal line using "_" char
    public static void printHorizontalLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println("Hello! I'm Omoh");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    //Method that prints the bye message
    public static void bye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
        try {
            Task.writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        printHorizontalLine();
        Task.getAllTasks();
        printHorizontalLine();
    }
}
