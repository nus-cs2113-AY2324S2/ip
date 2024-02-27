package artemis.ui;

import artemis.processing.*;
import artemis.storage.Storage;
import artemis.tasks.*;
import artemis.errors.Errors;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class UserInterface {
    private String username;
    private final Scanner in;
    private final PrintStream out;
    private TaskHandler taskHandler;
    private Storage storage;

    public UserInterface() {
        this(System.in, System.out);
    }

    public UserInterface(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showFirstRunMessage() {
        Messages.printBanner();
        requestUsername();
        Messages.printHelp(username);
    }

    public void initialize(String[] args) {
        try {
            if (args.length > 0) {
                storage = new Storage(args[0]);
            } else {
                storage = new Storage();
            }

            storage.loadSave();
            username = storage.getUsername();
            if (username == null) {
                throw new Errors.CorruptedSaveException();
            }

            ArrayList<Task> taskList = storage.getTaskList();
            this.taskHandler = new TaskHandler(taskList);
        } catch (Errors.CorruptedSaveException e) {
            System.out.println("[artemis]: there seems to be an error when loading saved data.");
            showFirstRunMessage();
            this.taskHandler = new TaskHandler(new ArrayList<>());
        } catch (FileNotFoundException e) {
            showFirstRunMessage();
            this.taskHandler = new TaskHandler(new ArrayList<>());
        }
    }


    public void requestUsername() {
        out.println("hello! i'm artemis. what is your name?");
        out.print("[unknown user]: ");
        username = in.nextLine();
    }



    public void commandLine() {
        String userInput;
        Task currentTask;

        while (true) {
            out.printf("[%s]: ", username);
            userInput = in.nextLine().trim();
            Parser.Command commandGiven = Parser.parseCommand(userInput);

            try {
                switch (commandGiven) {
                case TODO:
                    currentTask = taskHandler.createToDo(userInput);
                    break;
                case DEADLINE:
                    currentTask = taskHandler.createDeadline(userInput);
                    break;
                case EVENT:
                    currentTask = taskHandler.createEvent(userInput);
                    break;
                case LIST:
                    printList();
                    continue;
                case MARK:
                case UNMARK:
                    Object[] parsedContent = Parser.parseMarkUnmark(userInput, this.taskHandler.taskList.size());
                    int markItem = (int) parsedContent[0];
                    boolean isMarked = (boolean) parsedContent[1];
                    taskHandler.markUnmarkItem(markItem, isMarked);

                    continue;
                case BYE:

                    return;
                case DELETE:
                    int deleteItem = Parser.parseDelete(userInput, this.taskHandler.taskList.size());
                    taskHandler.deleteTask(deleteItem);
                    continue;
                case SAVE:
                    storage.saveData(username, taskHandler.taskList);
                    continue;
                case UNKNOWN:
                default:
                    out.println("[artemis]: unknown command!");
                    continue;
                }

                taskHandler.taskList.add(currentTask);

                out.printf("[artemis]: you have added this task:%s %s%s",
                        System.lineSeparator(), currentTask, System.lineSeparator());

            } catch (Errors.InvalidTodoException e) {
                out.println("[artemis]: invalid todo given. please enter \"todo <task>\"");
            } catch (Errors.InvalidDeadlineException e) {
                out.println("[artemis]: invalid deadline given. please enter \"deadline <task> /by <deadline of task>\"");
            } catch (Errors.InvalidEventException e) {
                out.println("[artemis]: invalid event given. please enter \"event <event title> /from <start datetime> /to <end datetime>\"");
            } catch (Errors.InvalidMarkUnmarkException e) {
                out.println("[artemis]: please enter \"[mark/unmark] <list item number>\".");
            } catch (Errors.InvalidMarkUnmarkIndexException e) {
                out.println("[artemis]: the given list item number given is invalid!");
            } catch (Errors.InvalidDeleteException e) {
                out.println("[artemis]: invalid delete. please enter \"delete <list item number>\".");
            } catch (Errors.TaskNotFoundException e) {
                out.println("[artemis]: the task you are requesting to delete does not exist!");
            }
        }
    }

    public void printList() {
        if (taskHandler.taskList.isEmpty()) {
            out.println("[artemis]: Your list is empty!");
            return;
        }

        out.println("[artemis]: Your list is as such:");
        for (ListIterator<Task> it = taskHandler.taskList.listIterator(); it.hasNext(); ) {
            Task currentTask = it.next();
            out.printf("%d. %s\n", it.nextIndex(), currentTask);
        }
    }

}
