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

// TODO
// now in branch-Level-6
// copy paste code to notepad, revert to old branch6

// checkout master, paste in master
// merge
public class RecrBad {

    /**
     * Prints each Task in Task array
     *
     * @param tasks Existing Task array
     */
    private static String displayList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "List is empty!" + System.lineSeparator() + PrintHelper.printCommandsList();
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
        int taskNum = Integer.parseInt(req[1]);
        if (tasks.size() < taskNum || taskNum < 1) {
            throw new InvalidParamsException("No such taskNum");
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
    public static void main(String[] args) { // Ctrl B to see def, shift F10 to run, Ctrl Alt L reformat
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
        while (true) {
            PrintHelper.printLine();
            String line = in.nextLine(); // reads input

            try {
                processUserInput(tasks, line, FILE_PATH, isReadMode);
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
        // check directory got file?
        // if no file, create the file
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(currentDirectory);
        String absoluteFilePath = path + "/" + FILE_PATH;

        // checks if saveFile.txt exists
        File f = new File(absoluteFilePath);
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName()
                        + System.lineSeparator() + path);
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
        int lineNum = 1;

        while (s.hasNext()) {
            String lineInFile = s.nextLine(); // read line in file

            // create the command input line
            String command = "";
            // process lines in saveFile.txt

            int START_INDEX_OF_TYPE = lineInFile.indexOf('[') + 1;
            int START_INDEX_OF_MARK = START_INDEX_OF_TYPE + 3;
            int START_INDEX_OF_DESCRIPTION = START_INDEX_OF_MARK + 3;

            // getType
            char type = lineInFile.charAt(START_INDEX_OF_TYPE);
            try {
                if (type == 'T') { // is a TODO
                    command += "todo ";
                } else if (type == 'D') {
                    command += "deadline ";
                } else if (type == 'E') {
                    command += "event ";
                } else {
                    throw new InvalidParamsException(lineInFile + " is not a valid command");
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidParamsException("invalid command");
            }
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
            }
            // process input for event params
            if (type == 'E') {
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
            }
            // process input for todo
            if (type == 'T') {
                taskDescription = lineInFile.substring(START_INDEX_OF_DESCRIPTION);
            }
            command += taskDescription;
            processUserInput(tasks, command, FILE_PATH, isReadMode);

            // get mark / unmark
            String markCommand;
            boolean isMark;
            try {
                if (lineInFile.charAt(START_INDEX_OF_MARK) == '0') {
                    // do nothing as task is unmark by default
                } else if (lineInFile.charAt(START_INDEX_OF_MARK) == '1') {
                    markCommand = "mark " + lineNum;
                    processUserInput(tasks, markCommand, FILE_PATH, isReadMode);
                } else {
                    throw new InvalidParamsException(lineInFile + "has invalid mark/unmark parameter");
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidParamsException(lineInFile + "has no mark/unmark parameter");
            }
            lineNum += 1;
        }

    }
}

