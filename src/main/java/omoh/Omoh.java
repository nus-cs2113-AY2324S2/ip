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
        readUserInput();
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



    //Method reads in what user types
    //If bye is not typed, we check 2 conditions
    //if list is typed, list out all tasks
    //if mark or unmark is typed, mark or unmark the task in tasklist
    //if none of the above, add the typed input to task list
    //If bye is typed, function exits
    public static void readUserInput() {
        String line;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {
            if (line.trim().isEmpty()) {
                System.out.println("Please enter a non empty Input!");
            } else if (line.equalsIgnoreCase("list")) {
                printAllTasks();
            } else if (line.startsWith("deadline")) {
                Deadline.addDeadline(line);
            }
            else if (line.startsWith("todo")) {
                try {
                    Todo.addTodo(line);
                } catch (EmptyTodoException e) {
                    e.printStackTrace();
                }
            }
            else if (line.startsWith("event")) {
                Event.addEvent(line);
            }
            else if (line.startsWith("mark") || line.startsWith("unmark") || line.startsWith("delete")) {
                try {
                    int taskNumber = Task.extractTaskNumber(line);
                    Task.modifyDoneStateOrDelete(taskNumber, line);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid number without alphabets after mark or unmark or delete " +
                            "Example: mark 1");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter valid number within boundaries of list. " +
                            "Example: if list only has 2 items, dont enter beyond 2");
                } catch (EmptyTaskNumberException e) {
                    e.printStackTrace();
                }
            } else {
                Task.addTask(line);
                Task.printAddedTask();
            }
            line = in.nextLine();
        }
    }

    //Method that prints all the tasks stored in myTaskList array
    public static void printAllTasks() {
        printHorizontalLine();
        Task.getAllTasks();
        printHorizontalLine();
    }
}
