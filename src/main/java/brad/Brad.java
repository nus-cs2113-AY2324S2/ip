package brad;

import brad.exceptions.emptyArgumentException;
import brad.exceptions.invalidNumberException;
import brad.exceptions.dataCorruptedException;;
import brad.tasks.TaskList;
import brad.tasks.TaskType;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;


public class Brad {
    private static TaskList tasklist = new TaskList();
    private static boolean toSave = true;

    // define literals
    private static final String SEPARATOR = "__________________________________________________________";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    private static final String EVENT = "event";

    private static final String DELETE = "delete";


    public static void main(String[] args) {
        greetUser();
        boolean canInitialize = true;
        try {
            tasklist.initializeFile();
        } catch (FileNotFoundException e) {
            printFileNotFound();
            toSave = false;
        } catch (dataCorruptedException e) {
            printOutput("File is corrupted! Please check the file.\n" +
                    "Exiting program...\n");
            canInitialize = false;
        }
        Scanner userInput = new Scanner(System.in);
        if (canInitialize) {
            if (tasklist.listSize() != 0) {
                printOutput((tasklist.getList()));
            } else if (toSave) {
                try {
                    tasklist.addHeader();
                } catch (IOException e) {
                    printOutput("Something went wrong\n" +
                            "Error message: " + e.getMessage());
                }
            }
        }
        while (canInitialize) {
            String input = userInput.nextLine();
            String[] splitInput = input.split(" ",2);
            String command = splitInput[0];
            if (command.equals("bye")) {
                printOutput("Bye. Hope to see you again soon!");
                break;
            }
            switch (command) {
                case LIST:
                    printOutput("Here are the tasks in your list:\n" + tasklist.getList());
                    break;
                case MARK:
                    try {
                        doMarkAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Uh oh. Please enter a number to mark the corresponding " +
                                "task as done");
                    } catch (invalidNumberException e) {
                        printOutput("No! >:( Exceeded existing list size of: " + tasklist.listSize() +
                                "\nPlease enter a valid number.\n");
                    }
                    break;
                case UNMARK:
                    try {
                        doUnmarkAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                            printOutput("Uh oh. Please enter a number to mark the corresponding " +
                                    "task as undone");
                        } catch (invalidNumberException e) {
                            printOutput("No! >:( Exceeded existing list size of: " + tasklist.listSize() +
                                            "\nPlease enter a valid number.\n");
                    }
                    break;
                case TODO:
                    try {
                        doTodoAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Hey, you can't give me an empty todo!");
                    }
                    break;
                case DEADLINE:
                    try {
                        doDeadlineAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Hey, you can't give me an empty deadline with no " +
                                "specified due date!");
                    }
                    break;
                case EVENT:
                    try {
                        doEventAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Hey, you can't give me an event with no start & end time!");
                    }
                    break;
                case DELETE:
                    try {
                        doDeleteAction(splitInput[1]);
                    } catch (ArrayIndexOutOfBoundsException | emptyArgumentException e) {
                        printOutput("Uh oh. Please enter a number to delete this task");
                    } catch (invalidNumberException e) {
                        printOutput("No! >:( Exceeded existing list size of: " + tasklist.listSize()  +
                                "\nPlease enter a valid number.\n");
                    }
                    break;
                default:
                    printOutput("Huh?! Sorry I don't understand. T_T\n Please only enter valid commands: " +
                            "'list', 'mark', 'unmark', 'todo', 'deadline', 'event', 'delete', 'bye'");
                    break;
            }
        }
    }

    private static void greetUser() {
        final String name = "brad";
        System.out.println("Hello I am " + name + ".\n");
        System.out.println("How can I help you today?\n");
    }

    private static void printOutput(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    private static void doMarkAction(String input) throws
            emptyArgumentException, invalidNumberException {
        int taskNumber = Integer.parseInt(input);
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        if (taskNumber > tasklist.listSize()) {
            throw new invalidNumberException();
        }
        tasklist.markAsDone(taskNumber, true);
        String message = "Nice! I've marked this task as done: \n" + tasklist.getTask(taskNumber);
        printOutput(message);
        if (toSave) {
            try {
                tasklist.updateFile();
            } catch (IOException e) {
                printOutput(("Something went wrong!\n Error message: " +
                        e.getMessage()));
            }
        }
    }

    private static void doUnmarkAction(String input) throws
            emptyArgumentException, invalidNumberException {
        int taskNumber = Integer.parseInt(input);
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        if (taskNumber > tasklist.listSize()) {
            throw new invalidNumberException();
        }
        tasklist.markAsDone(taskNumber, false);
        String message = "Ok. I've marked this task as not done yet: \n" + tasklist.getTask(taskNumber);
        printOutput(message);
        if (toSave) {
            try {
                tasklist.updateFile();
            } catch (IOException e) {
                printOutput(("Something went wrong!\n Error message: " +
                        e.getMessage()));
            }
        }
    }

    private static void doTodoAction(String input) throws emptyArgumentException {
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        tasklist.addToList(input, TaskType.TODO, false, toSave);
        int size = tasklist.listSize();
        String message = "Got it. I've added this task:\n" + tasklist.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
        if (toSave) {
            try {
                tasklist.updateFile();
            } catch (IOException e) {
                printOutput("Something went wrong, error data: " + e.getMessage());
            }
        }
    }

    private static void doDeadlineAction(String input) throws emptyArgumentException {
        if (input.isBlank() || !input.contains("/by")) {
            throw new emptyArgumentException();
        }
        tasklist.addToList(input, TaskType.DEADLINE, false, toSave);
        int size = tasklist.listSize();
        String message = "Got it. I've added this task:\n" + tasklist.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
        if (toSave) {
            try {
                tasklist.updateFile();
            } catch (IOException e) {
                printOutput("Something went wrong, error data: " + e.getMessage());
            }
        }
    }

    private static void doEventAction(String input) throws emptyArgumentException {
        if (input.isBlank() || !input.contains("/from") || !input.contains("/to")) {
            throw new emptyArgumentException();
        }
        tasklist.addToList(input, TaskType.EVENT, false, toSave);
        int size = tasklist.listSize();
        String message = "Got it. I've added this task:\n" + tasklist.getTask(size)
                + "\n Now you have " + size + " tasks in the list.";
        printOutput(message);
        if (toSave) {
            try {
                tasklist.updateFile();
            } catch (IOException e) {
                printOutput("Something went wrong, error data: " + e.getMessage());
            }
        }
    }

    private static void doDeleteAction(String input)
            throws emptyArgumentException, invalidNumberException {
        if (input.isBlank()) {
            throw new emptyArgumentException();
        }
        int taskNumber = Integer.parseInt(input);
        if (taskNumber > tasklist.listSize()) {
            throw new invalidNumberException();
        }
        int size = tasklist.listSize() - 1;
        String message = "Got it. I've removed this task:\n" + tasklist.getTask(taskNumber)
                + "\n Now you have " + size + " tasks in the list.";
        tasklist.deleteTask(taskNumber);
        printOutput(message);
        if (toSave) {
            try {
                tasklist.updateFile();
            } catch (IOException e) {
                printOutput("Something went wrong, error data: " + e.getMessage());
            }
        }
    }

    private static void printFileNotFound() {
        printOutput("File is not found. Make sure that the " +
                "data file is located in: 'data/data.md'\n" +
                "I'm unable to save.");
    }
}

