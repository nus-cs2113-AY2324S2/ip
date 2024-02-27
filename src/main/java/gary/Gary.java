package gary;

import gary.exception.*;
import gary.task.*;
import gary.ui.Ui;
import gary.storage.Storage;
import gary.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gary {
//    public static final int TODO_DESCRIPTION_START_INDEX = 5;
//    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
//    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
//    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
//    public static final int EVENT_FROM_SPACE_LENGTH = 6;
//    public static final int EVENT_TO_SPACE_LENGTH = 4;

    public static ArrayList<Task> todos = new ArrayList<>();

//    public static final String FILE_PATH = "./gary.txt";

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        Ui.greetings();

        File file = Storage.createFile();
        todos = Storage.readFileStorage(file);

        String line;
        line = in.nextLine();

        runCommandUntilExit(line, file, in);
        Ui.exitProgramme();
    }

    private static void runCommandUntilExit(String line, File file, Scanner in) throws IOException {
        String[] lineWords;
        String command;
        int todosCount = todos.size();
        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            command = lineWords[0];
            if (line.equalsIgnoreCase("LIST")) {
                TaskList.processList(todosCount, todos);
            } else if (command.equalsIgnoreCase("MARK")) {
                try {
                    TaskList.processMark(todos, lineWords);
                    writeTaskToTxt(file, todosCount, todos);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! You don't have that much task");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! input after 'mark' must be a number");
                }
            } else if (command.equalsIgnoreCase("UNMARK")) {
                try {
                    TaskList.processUnmark(todos, lineWords);
                    writeTaskToTxt(file, todosCount, todos);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! You don't have that much task");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! input after 'unmark' must be a number");
                }
            } else if (command.equalsIgnoreCase("DELETE")) {
                try {
                    TaskList.processDelete(todos, lineWords, todosCount);
                    todosCount -= 1;
                    writeTaskToTxt(file, todosCount, todos);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! You don't have that much task");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! input after 'delete' must be a number");
                }
            } else {
                try {
                    // add task to array
                    TaskList.processAddTask(command, todos, line);

                    // update count
                    todosCount += 1;

                    todos.get(todosCount - 1).printAdd(todosCount);

                    // write all task to txt file
                    writeTaskToTxt(file, todosCount, todos);
                } catch (UnknownCommandException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (MissingTodoDescriptionException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty");
                } catch (MissingDeadlineByException e) {
                    System.out.println("OOPS!!! Deadline must contain '/by' and its description");
                } catch (MissingDeadlineDescriptionException e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty");
                } catch (MissingEventFromException e) {
                    System.out.println("OOPS!!! Event must contain '/from' and its description");
                } catch (MissingEventToException e) {
                    System.out.println("OOPS!!! Event must contain '/to' and its description");
                } catch (MissingEventDescriptionException e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty");
                }

            }
            line = in.nextLine();
        }
    }

    private static void writeTaskToTxt(File file, int todosCount, ArrayList<Task> todos) throws IOException {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < todosCount; i += 1) {
                writeFormattedString(todos, i, fileWriter);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
        }
    }

    private static void writeFormattedString(ArrayList<Task> todos, int i, BufferedWriter fileWriter) throws IOException {
        Task currentTask = todos.get(i);
        String description = currentTask.getTaskDescription();
        String taskStatus = currentTask.getTaskStatus() ? "1" : "0";
        TaskType taskType = currentTask.getTaskType();

        switch(taskType) {
        case DEADLINE:
            Deadline deadline = (Deadline) currentTask;
            String by = deadline.getBy();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + by + "\n");
            break;
        case EVENT:
            Event event = (Event) currentTask;
            String from = event.getFrom();
            String to = event.getTo();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + from + " | " + to + "\n");
            break;
        case TODO:
            fileWriter.write(taskType + " | " + taskStatus + " | " + description + "\n");
            break;
        }
    }
}
