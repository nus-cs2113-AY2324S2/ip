import helperFunctions.InvalidParamsException;
import helperFunctions.PrintHelper;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecrBad {

    /**
     * Prints each Task in Task array
     *
     * @param tasks Existing Task array
     */
    private static String displayList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "List is empty!" + System.lineSeparator()
                    + PrintHelper.printCommandsList() + System.lineSeparator();
        }
        int count = 0;
        StringBuilder fullList = new StringBuilder();
        while (count != tasks.size()) {
            fullList.append(displayListItem(tasks, count));
            count += 1;
        }
        return fullList.toString();
    }

    /**
     * Returns a task in String format
     *
     * @param tasks existing Task array
     * @param index index of specific task
     */
    private static String displayListItem(ArrayList<Task> tasks, int index) { // change from void to string
        return ((index + 1) + ". [" + tasks.get(index).getType() + "]["
                + tasks.get(index).getStatus() + "] "
                + tasks.get(index).getDescription()
                + System.lineSeparator());
    }

    private static void addEventTask(String[] req, String line, ArrayList<Task> tasks, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(event)

        int indexTo = line.lastIndexOf('/');
        int indexFrom = line.lastIndexOf('/', indexTo - 1); // counts from back
        // checks for invalid input
        if (indexFrom == -1 || indexTo == -1) {
            throw new InvalidParamsException("No event arguments: Add a '/', then the startTime, followed by a '/' and the endTime");
        }
        if (startIndexOfDescription > indexFrom - 1) {
            throw new InvalidParamsException(("no event description"));
        }
        // process input as Event object
        String description = line.substring(startIndexOfDescription, indexFrom - 1);
        String timeRange = " (from: " + line.substring(indexFrom + 1, indexTo) +
                "to " + line.substring(indexTo + 1) + ")";
        // add to tasks
        Task newTask = new Event(description, timeRange);
        tasks.add(newTask);
        if (isReadMode) {
            return; // no need execute code below (for writing only)
        }
        appendToFile(FILE_PATH, displayListItem(tasks, tasks.indexOf(newTask)));
        System.out.println("Event added!");
        System.out.println(displayListItem(tasks, tasks.size() - 1));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    private static void addDeadlineTask(String[] req, String line, ArrayList<Task> tasks, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(deadline);

        int indexDeadline = line.lastIndexOf('/');
        // checks for invalid input
        if (indexDeadline == -1) {
            throw new InvalidParamsException("No deadline argument: Add a parameter '/' followed by the deadline");
        }
        if (startIndexOfDescription > indexDeadline - 1) {
            throw new InvalidParamsException("No deadline description");
        }
        // process input as Deadline object
        String deadline = "(by: " + line.substring(indexDeadline + 1) + ")";
        String description = line.substring(startIndexOfDescription, indexDeadline);
        // add to tasks
        Task newTask = new Deadline(description, deadline);
        tasks.add(newTask);

        if (isReadMode) {
            return; // no need execute code below (for writing only)
        }
        appendToFile(FILE_PATH, displayListItem(tasks, tasks.indexOf(newTask)));
        System.out.println("Deadline added!");
        System.out.println(displayListItem(tasks, tasks.size() - 1));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    private static void addTodoTask(String[] req, String line, ArrayList<Task> tasks, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        final int startIndexOfDescription = req[0].length() + 1; // req[0].equals(TODO);

        // checks for invalid input
        if (startIndexOfDescription > line.length() - 1) {
            throw new InvalidParamsException("No task description");
        }
        // process input
        String description = line.substring(startIndexOfDescription);
        // add to tasks
        Task newTask = new Todo(description);
        tasks.add(newTask);
        if (isReadMode) {
            // no need execute code below (for writing only)
            return;
        }
        appendToFile(FILE_PATH, displayListItem(tasks, tasks.indexOf(newTask)));

        System.out.println("Todo added!");
        System.out.println(displayListItem(tasks, tasks.size() - 1));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }

    private static void deleteOperation(ArrayList<Task> tasks, String[] req, String FILE_PATH) throws InvalidParamsException {
        // check for input validity
        if (req.length < 2) {
            throw new InvalidParamsException("Invalid delete operation");
        }
        // process input
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(req[1]);
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid non-integer delete index");
        }
        if (taskIndex > tasks.size() || taskIndex < 1) {
            throw new InvalidParamsException("Out of bounds delete index");
        }
        // delete Task
        System.out.println("Good riddance, task deleted!");
        System.out.println(displayListItem(tasks, taskIndex - 1));
        tasks.remove(taskIndex - 1);
        writeToFile(FILE_PATH, displayList(tasks));
        System.out.println("Congrats, now have " + tasks.size() + " tasks");
    }


    /**
     * Marks Task as done or not done
     *
     * @param tasks  existing Tasks array
     * @param req    String[] input from user
     * @param isMark type of operation: mark or unmark
     */
    private static void markOperation(ArrayList<Task> tasks, String[] req, boolean isMark, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        // check for input validity
        if (req.length < 2) {
            throw new InvalidParamsException("invalid mark/ unmark operation");
        }
        int taskNum;
        try {
            taskNum = Integer.parseInt(req[1]);
            if (tasks.size() < taskNum || taskNum < 1) {
                throw new InvalidParamsException("No such taskNum");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParamsException("Invalid taskNum");
        }

        // process input
        if (isMark) { // (isMark = true) == mark as done
            tasks.get(taskNum - 1).markAsDone();
        } else {
            tasks.get(taskNum - 1).markAsNotDone();
        }
        if (isReadMode) {
            // no need to execute code below (for writing only)
            return;
        }
        // mark tasks
        writeToFile(FILE_PATH, displayList(tasks));
        String mark = isMark ? "marked" : "unmarked";
        System.out.println("Has " + mark + " task" + taskNum + ":");
        System.out.print(displayListItem(tasks, taskNum - 1));
    }

    private static boolean processUserInput(ArrayList<Task> tasks, String line, String FILE_PATH, boolean isReadMode) throws InvalidParamsException {
        String[] req = line.split(" ");

        if (req[0].equalsIgnoreCase("BYE")) {
            PrintHelper.sayBye();
            return false; // EXITS loop
        }
        if (req[0].equalsIgnoreCase("LIST")) {
            System.out.print(displayList(tasks));
        } else if (req[0].toUpperCase().contains("MARK")) { // both unmark & mark contains 'mark'
            boolean isMark = !line.toUpperCase().contains("UNMARK");
            markOperation(tasks, req, isMark, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("DELETE")) {
            deleteOperation(tasks, req, FILE_PATH);
        } else if (req[0].equalsIgnoreCase("TODO")) {
            addTodoTask(req, line, tasks, FILE_PATH, isReadMode); // change tasks to ArrayList<TAsk>, f void now
        } else if (req[0].equalsIgnoreCase("DEADLINE")) {
            addDeadlineTask(req, line, tasks, FILE_PATH, isReadMode);
        } else if (req[0].equalsIgnoreCase("EVENT")) {
            addEventTask(req, line, tasks, FILE_PATH, isReadMode);
        } else {
            throw new InvalidParamsException("No such command." + System.lineSeparator() + PrintHelper.printCommandsList());
        }
        return true;
    }

    /**
     * Input
     * [any text]           to addToList,
     * list                 to displayList,
     * mark/unmark [index]  to mark item
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintHelper.sayHi();
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isReadMode = true; // in reading mode

        String FILE_PATH = "saveFile.txt";

        try {
            readFile(FILE_PATH, tasks, isReadMode);
        } catch (InvalidParamsException e) {
            System.out.println(e.getMessage());
            return;
        }

        isReadMode = false; // switch to writing mode
        boolean isRun = true;
        while (isRun) {
            PrintHelper.printLine();
            String line = in.nextLine(); // reads input

            try {
                isRun = processUserInput(tasks, line, FILE_PATH, isReadMode);
            } catch (InvalidParamsException e) {
                System.out.println(e.getMessage()); // prints out error message
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws InvalidParamsException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new InvalidParamsException("Cannot append to file");
        }
    }

    private static void writeToFile(String filePath, String textToWrite) throws InvalidParamsException {
        try {
            FileWriter fw = new FileWriter(filePath); // overwrites file
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            throw new InvalidParamsException("Cannot write to file");
        }
    }

    /**
     * convert each line in textFile to a Task
     **/
    private static void readFile(String FILE_PATH, ArrayList<Task> tasks, boolean isReadMode) throws InvalidParamsException {
        // get absoluteFilePath
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currentDirectory);
        String absoluteFilePath = path + "/" + FILE_PATH;

        // checks if saveFile.txt exists
        File f = new File(absoluteFilePath);
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName() + "@" + path);
            } else {
                System.out.println("File " + f.getName() + " already exists");
            }
        } catch (IOException e) {
            throw new InvalidParamsException("Error Occurred");
        }

        Scanner s;
        try {
            s = new Scanner(f);  // create a Scanner using file as source
        } catch (FileNotFoundException e) {

            throw new InvalidParamsException("File not found");
        }
        int lineNum = 1; // keeps track of current lineNum
        while (s.hasNext()) {
            String lineInFile = s.nextLine(); // read line in file

            // process lines in saveFile.txt
            int START_INDEX_OF_TYPE = lineInFile.indexOf('[') + 1; // format of text: "1. [T][1] startOfTaskDescription"
            int START_INDEX_OF_MARK = START_INDEX_OF_TYPE + 3; // mark index is 3 positions to the right
            int START_INDEX_OF_DESCRIPTION = START_INDEX_OF_MARK + 3; // description index is 3 more positions to the right

            // checks validity of index TODO
            if ((lineInFile.indexOf('[') == -1) ||
                    (START_INDEX_OF_TYPE + 1 > lineInFile.length()) ||
                    (START_INDEX_OF_MARK + 1 > lineInFile.length()) ||
                    (START_INDEX_OF_DESCRIPTION + 1 > lineInFile.length())) {
                throw new InvalidParamsException(lineInFile + " is not valid");
            }

            // create the command input line
            String command = "";

            // getType
            char type = lineInFile.charAt(START_INDEX_OF_TYPE);
            if (type == 'T') {
                command += "todo ";
            } else if (type == 'D') {
                command += "deadline ";
            } else if (type == 'E') {
                command += "event ";
            } else {
                throw new InvalidParamsException(lineInFile + " type is not valid");
            }

            // getMark
            char mark = lineInFile.charAt(START_INDEX_OF_MARK);
            if (mark != '0' && mark != '1') {
                throw new InvalidParamsException("invalid mark");
            }

            // getDescription
            String taskDescription = "";
            // process input for deadline params
            if (type == 'D') {
                // change "(by: " to "/"
                // change ")" to "" ie last elem
                int startIndexToChange = lineInFile.lastIndexOf("(by: ");
                int endIndexToChange = lineInFile.lastIndexOf(")");
                taskDescription += lineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
                taskDescription += "/";
                taskDescription += lineInFile.substring(startIndexToChange + 5, endIndexToChange);
            } else if (type == 'E') { // process input for event params
                // change "(from: " to "/"
                // change "to " to "/"
                // change ")" to "" ie last elem
                // NOTE: startIndexToChange & midIndextoChange may be ambiguous depending on file text
                int midIndexToChange = lineInFile.lastIndexOf("to ");
                int startIndexToChange = lineInFile.lastIndexOf("(from: ", midIndexToChange);
                int endIndexToChange = lineInFile.lastIndexOf(')');
                taskDescription += lineInFile.substring(START_INDEX_OF_DESCRIPTION, startIndexToChange);
                taskDescription += "/";
                taskDescription += lineInFile.substring(startIndexToChange + 7, midIndexToChange);
                taskDescription += "/";
                taskDescription += lineInFile.substring(midIndexToChange + 3, endIndexToChange);
            } else { // type == 'T'
                taskDescription = lineInFile.substring(START_INDEX_OF_DESCRIPTION);
            }
            command += taskDescription;

            // process command in file
            processUserInput(tasks, command, FILE_PATH, isReadMode);

            // create mark command (for mark tasks only)
            if (mark == '1') {
                // create mark command
                String markCommand;
                markCommand = "mark " + lineNum;
                processUserInput(tasks, markCommand, FILE_PATH, isReadMode);
            }

            lineNum += 1;
        }
    }
}

