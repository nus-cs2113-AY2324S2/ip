package gary;

import gary.exception.*;
import gary.task.Deadline;
import gary.task.Event;
import gary.task.Task;
import gary.task.Todo;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Gary {
    public static final int MAX_TASK = 100;

    public static final int TODO_DESCRIPTION_START_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
    public static final int EVENT_FROM_SPACE_LENGTH = 6;
    public static final int EVENT_TO_SPACE_LENGTH = 4;

    public static final String FILE_PATH = "./gary.txt";

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        greetings();

        Task[] todos = new Task[MAX_TASK];
        int todosCount = 0;

        String[] todo_temp = new String[MAX_TASK];

        File file = new File(FILE_PATH);
        try {
            Boolean isFileCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println("FILE NOT CREATED");
        }

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String lineText = fileReader.readLine();
            String[] lineWords;
            String command;

            while (lineText != null) {
                // convert each line into TASK_todo/deadline/event, then store in the array todos
                lineWords = lineText.split(" \\| ");
                System.out.println(Arrays.toString(lineWords));
                command = lineWords[0];

                if (command.equalsIgnoreCase("TODO")) {
                    todos[todosCount] = new Todo(lineWords[2]);
                } else if (command.equalsIgnoreCase("DEADLINE")) {
                    todos[todosCount] = new Deadline(lineWords[2], lineWords[3]);
                } else {
                    todos[todosCount] = new Event(lineWords[2], lineWords[3], lineWords[4]);
                }
                todosCount += 1;

                lineText = fileReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
//            createFile(file);
        }

        String line;
        line = in.nextLine();
        String[] lineWords;
        String command;

        while (!(line.toUpperCase().contains("BYE"))) {
            lineWords = line.split(" ");
            command = lineWords[0];
            if (line.equalsIgnoreCase("LIST")) {
                processList(todosCount, todos);
            } else if (command.equalsIgnoreCase("MARK")) {
                processMark(todos, lineWords);
            } else if (command.equalsIgnoreCase("UNMARK")) {
                processUnmark(todos, lineWords);
            } else {
                try {
                    processAddTask(command, todos, todosCount, line);
                    todosCount += 1;
                    todos[todosCount - 1].printAdd(todosCount);
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

        System.out.println("Bye. Hope to see you again!");
    }

//    private static void createFile(File file) throws IOException {
//        try {
//            Boolean isFileCreated = file.createNewFile();
//        } catch (IOException e) {
//            System.out.println("FILE NOT CREATED");
//        }
//    }

    private static void processUnmark(Task[] todos, String[] lineWords) {
        todos[Integer.parseInt(lineWords[1]) - 1].unmarkAsDone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println("  "
                + "[ ] "
                + todos[Integer.parseInt(lineWords[1]) - 1].getTaskDescription());
    }

    private static void processMark(Task[] todos, String[] lineWords) {
        todos[Integer.parseInt(lineWords[1]) - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + "[X] "
                + todos[Integer.parseInt(lineWords[1]) - 1].getTaskDescription());
    }

    private static void processList(int todosCount, Task[] todos) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < todosCount; i += 1) {
            todos[i].printTask(i);
        }
    }

    private static void processAddTask(String command, Task[] todos, int todosCount, String line)
            throws UnknownCommandException, MissingTodoDescriptionException,
            MissingDeadlineByException, MissingDeadlineDescriptionException,
            MissingEventFromException, MissingEventToException, MissingEventDescriptionException {
        if (command.equalsIgnoreCase("TODO")) {
            createNewTodo(todos, todosCount, line);
        } else if (command.equalsIgnoreCase("DEADLINE")) {
            createNewDeadline(todos, todosCount, line);
        } else if (command.equalsIgnoreCase("EVENT")) {
            createNewEvent(todos, todosCount, line);
        } else {
            throw new UnknownCommandException();
        }
    }

    private static void createNewTodo(Task[] todos, int todosCount, String line)
            throws MissingTodoDescriptionException {
        try {
            todos[todosCount] = new Todo(line.substring(TODO_DESCRIPTION_START_INDEX));
        } catch(StringIndexOutOfBoundsException e) {
            throw new MissingTodoDescriptionException();
        }
    }

    private static void createNewDeadline(Task[] todos, int todosCount, String line)
            throws MissingDeadlineByException, MissingDeadlineDescriptionException {
        if (!(line.contains("/by"))) {
            throw new MissingDeadlineByException();
        }

        int bySlashIndex = line.indexOf("/");
        String deadlineDescription = line.substring(DEADLINE_DESCRIPTION_START_INDEX, bySlashIndex);
        if (deadlineDescription.isBlank()) {
            throw new MissingDeadlineDescriptionException();
        }

        try {
            String deadlineBy = line.substring(bySlashIndex + DEADLINE_BY_SPACE_LENGTH);
            if (deadlineBy.isBlank()) {
                throw new MissingDeadlineByException();
            }
            todos[todosCount] = new Deadline(deadlineDescription, deadlineBy);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDeadlineByException();
        }
    }

    private static void createNewEvent(Task[] todos, int todosCount, String line)
            throws MissingEventFromException, MissingEventToException,
            MissingEventDescriptionException {
        if (!(line.contains("/from"))) {
            throw new MissingEventFromException();
        }
        if (!(line.contains("/to"))) {
            throw new MissingEventToException();
        }

        int fromSlashIndex = line.indexOf("/");
        String substringBeforeFrom = line.substring(0, fromSlashIndex + 1);
        int toSlashIndex = substringBeforeFrom.length()
                + line.substring(fromSlashIndex + 1).indexOf("/");
        String eventDescription = line.substring(EVENT_DESCRIPTION_START_INDEX, fromSlashIndex);
        if (eventDescription.isBlank()) {
            throw new MissingEventDescriptionException();
        }

        String eventFrom = line.substring(fromSlashIndex + EVENT_FROM_SPACE_LENGTH, toSlashIndex);
        if (eventFrom.isBlank()) {
            throw new MissingEventFromException();
        }

        try {
            String eventTo = line.substring(toSlashIndex + EVENT_TO_SPACE_LENGTH);
            if (eventTo.isBlank()) {
                throw new MissingEventToException();
            }
            todos[todosCount] = new Event(eventDescription, eventFrom, eventTo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingEventToException();
        }
    }

    private static void greetings() {
        System.out.println("Hello! I'm Gary");
        System.out.println("                    0   0   ______");
        System.out.println("                    | v | /oooooooo\\");
        System.out.println("                    |   |/OOOOOOOOOO\\");
        System.out.println("                    ===================");
        System.out.println("|   |   |   |   |   |   |   |   |   |   |   |");
        System.out.println("What can I do for you?");
    }
}
