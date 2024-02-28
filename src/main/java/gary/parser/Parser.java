package gary.parser;

import gary.exception.MissingDeadlineByException;
import gary.exception.UnknownCommandException;
import gary.exception.MissingTodoDescriptionException;
import gary.exception.MissingDeadlineDescriptionException;
import gary.exception.MissingEventFromException;
import gary.exception.MissingEventToException;
import gary.exception.MissingEventDescriptionException;
import gary.storage.Storage;
import gary.task.Task;
import gary.task.TaskList;
import gary.command.Command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void runCommandUntilExit(String line, File file, Scanner in, ArrayList<Task> todos) {
        String[] lineWords;
        String command;
        int todosCount = todos.size();
        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            command = lineWords[0];
            if (line.equalsIgnoreCase("LIST")) {
                Command.runList(todos, todosCount);
            } else if (command.equalsIgnoreCase("MARK")) {
                Command.runMark(file, todos, lineWords, todosCount);
            } else if (command.equalsIgnoreCase("UNMARK")) {
                Command.runUnmark(file, todos, lineWords, todosCount);
            } else if (command.equalsIgnoreCase("DELETE")) {
                Command.runDelete(file, todos, lineWords, todosCount);
            } else {
                Command.runAdd(line, file, todos, command, todosCount);
            }
            todosCount = todos.size();
            line = in.nextLine();
        }
    }

//    private static void runAdd(String line, File file, ArrayList<Task> todos, String command, int todosCount) {
//        try {
//            TaskList.processAddTask(command, todos, line);
//            todosCount += 1;
//            todos.get(todosCount - 1).printAdd(todosCount);
//            Storage.writeTaskToTxt(file, todosCount, todos);
//        } catch (UnknownCommandException e) {
//            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        } catch (MissingTodoDescriptionException e) {
//            System.out.println("OOPS!!! The description of a todo cannot be empty");
//        } catch (MissingDeadlineByException e) {
//            System.out.println("OOPS!!! Deadline must contain '/by' and its description");
//        } catch (MissingDeadlineDescriptionException e) {
//            System.out.println("OOPS!!! The description of a deadline cannot be empty");
//        } catch (MissingEventFromException e) {
//            System.out.println("OOPS!!! Event must contain '/from' and its description");
//        } catch (MissingEventToException e) {
//            System.out.println("OOPS!!! Event must contain '/to' and its description");
//        } catch (MissingEventDescriptionException e) {
//            System.out.println("OOPS!!! The description of an event cannot be empty");
//        }
//    }
//
//    private static void runDelete(File file, ArrayList<Task> todos, String[] lineWords, int todosCount) {
//        try {
//            TaskList.processDelete(todos, lineWords, todosCount);
//            todosCount -= 1;
//            Storage.writeTaskToTxt(file, todosCount, todos);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("OOPS!!! You don't have that much task");
//        } catch (NumberFormatException e) {
//            System.out.println("OOPS!!! input after 'delete' must be a number");
//        }
//    }
//
//    private static void runUnmark(File file, ArrayList<Task> todos, String[] lineWords, int todosCount) {
//        try {
//            TaskList.processUnmark(todos, lineWords);
//            Storage.writeTaskToTxt(file, todosCount, todos);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("OOPS!!! You don't have that much task");
//        } catch (NumberFormatException e) {
//            System.out.println("OOPS!!! input after 'unmark' must be a number");
//        }
//    }
//
//    private static void runMark(File file, ArrayList<Task> todos, String[] lineWords, int todosCount) {
//        try {
//            TaskList.processMark(todos, lineWords);
//            Storage.writeTaskToTxt(file, todosCount, todos);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("OOPS!!! You don't have that much task");
//        } catch (NumberFormatException e) {
//            System.out.println("OOPS!!! input after 'mark' must be a number");
//        }
//    }
//
//    private static void runList(ArrayList<Task> todos, int todosCount) {
//        TaskList.processList(todosCount, todos);
//    }
}
