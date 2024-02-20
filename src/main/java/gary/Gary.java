package gary;

import gary.exception.*;
import gary.task.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gary {
    public static final int TODO_DESCRIPTION_START_INDEX = 5;
    public static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    public static final int DEADLINE_BY_SPACE_LENGTH = 4;
    public static final int EVENT_DESCRIPTION_START_INDEX = 6;
    public static final int EVENT_FROM_SPACE_LENGTH = 6;
    public static final int EVENT_TO_SPACE_LENGTH = 4;

    public static ArrayList<Task> todos = new ArrayList<>();

    public static final String FILE_PATH = "./gary.txt";

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        greetings();

        int todosCount = 0;

//        String[] todo_temp = new String[MAX_TASK];

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
                command = lineWords[0];
                String description = lineWords[2];

                if (command.equalsIgnoreCase("TODO")) {
//                    todos[todosCount] = new Todo(description);
                    todos.add(new Todo(description));
                } else if (command.equalsIgnoreCase("DEADLINE")) {
                    String by = lineWords[3];
//                    todos[todosCount] = new Deadline(description, by);
                    todos.add(new Deadline(description, by));
                } else if (command.equalsIgnoreCase("EVENT")){
                    String from = lineWords[3];
                    String to = lineWords[4];
//                    todos[todosCount] = new Event(description, from, to);
                    todos.add(new Event(description, from, to));
                }
                todosCount += 1;

                // Update task status in array todos
                String taskStatus = lineWords[1];
                if (taskStatus.equalsIgnoreCase("1")) {
//                    todos[todosCount - 1].markAsDone();
                    Task currentTask = todos.get(todosCount - 1);
                    currentTask.markAsDone();
                }

                lineText = fileReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
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
                try {
                    processMark(todos, lineWords);
                    writeTaskToTxt(file, todosCount, todos);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! You don't have that much task");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! input after 'mark' must be a number");
                }
            } else if (command.equalsIgnoreCase("UNMARK")) {
                try {
                    processUnmark(todos, lineWords);
                    writeTaskToTxt(file, todosCount, todos);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! You don't have that much task");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! input after 'unmark' must be a number");
                }
            } else if (command.equalsIgnoreCase("DELETE")) {
                try {
                    processDelete(todos, lineWords, todosCount);
                    todosCount -= 1;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! You don't have that much task");
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! input after 'delete' must be a number");
                }
            } else {
                try {
                    // add task to array
                    processAddTask(command, todos, todosCount, line, file);

                    // update count
                    todosCount += 1;

                    todos.get(todosCount - 1).printAdd(todosCount);
//                    todos[todosCount - 1].printAdd(todosCount);

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

        System.out.println("Bye. Hope to see you again!");
    }

    private static String getTaskSymbol(Task currentTask) {
        String taskSymbol = null;
        switch(currentTask.getTaskType()) {
        case TODO:
            taskSymbol = "T";
            break;
        case DEADLINE:
            taskSymbol = "D";
            break;
        case EVENT:
            taskSymbol = "E";
            break;
        }
        return taskSymbol;
    }
    private static void processDelete(ArrayList<Task> todos, String[] lineWords, int todosCount) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        int taskIndex = Integer.parseInt(lineWords[1]) - 1;
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        todos.remove(taskIndex);
        System.out.println("Noted, I've removed this task:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
        System.out.println("Now you have " + (todosCount - 1) + " tasks in the list.");
    }

    private static void processUnmark(ArrayList<Task> todos, String[] lineWords) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        currentTask.unmarkAsDone();
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
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
//        String description = todos[i].getTaskDescription();
//        String taskStatus = todos[i].getTaskStatus() ? "1" : "0";
//        TaskType taskType = todos[i].getTaskType();

        String description = currentTask.getTaskDescription();
        String taskStatus = currentTask.getTaskStatus() ? "1" : "0";
        TaskType taskType = currentTask.getTaskType();

        switch(taskType) {
        case DEADLINE:
//            Deadline deadline = (Deadline) todos[i];
            Deadline deadline = (Deadline) currentTask;
            String by = deadline.getBy();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + by + "\n");
            break;
        case EVENT:
//            Event event = (Event) todos[i];
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

    private static void processMark(ArrayList<Task> todos, String[] lineWords) {
        Task currentTask = todos.get(Integer.parseInt(lineWords[1]) - 1);
        currentTask.markAsDone();
        String taskStatus = currentTask.getTaskStatus() ? "X" : " ";
        String taskType = getTaskSymbol(currentTask);
        String taskDescription = currentTask.getTaskDescription();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" [" + taskType + "][" + taskStatus + "] " + taskDescription);
    }

    private static void processList(int todosCount, ArrayList<Task> todos) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < todosCount; i += 1) {
            todos.get(i).printTask(i);
        }
    }

    private static void processAddTask(String command, ArrayList<Task> todos, int todosCount, String line, File file)
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

    private static void createNewTodo(ArrayList<Task> todos, int todosCount, String line)
            throws MissingTodoDescriptionException {
        try {
            todos.add(new Todo(line.substring(TODO_DESCRIPTION_START_INDEX)));
        } catch(StringIndexOutOfBoundsException e) {
            throw new MissingTodoDescriptionException();
        }
    }

    private static void createNewDeadline(ArrayList<Task> todos, int todosCount, String line)
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
            todos.add(new Deadline(deadlineDescription, deadlineBy));
        } catch (StringIndexOutOfBoundsException e) {
            throw new MissingDeadlineByException();
        }
    }

    private static void createNewEvent(ArrayList<Task> todos, int todosCount, String line)
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
            todos.add(new Event(eventDescription, eventFrom, eventTo));
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
