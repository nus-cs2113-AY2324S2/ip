package winter;

import java.io.IOException;

import java.util.Scanner;
import winter.checkedexceptions.EmptyCommandException;
import winter.checkedexceptions.InvalidCommandException;
import winter.checkedexceptions.InvalidDeadlineException;
import winter.checkedexceptions.InvalidToDoException;
import winter.checkedexceptions.InvalidEventException;
import winter.checkedexceptions.InvalidDeleteException;
import winter.task.Deadline;
import winter.task.Event;
import winter.task.Task;
import winter.task.ToDo;

import static winter.Commands.*;
import java.util.ArrayList;
import java.io.FileWriter;





public class Manager {
    private static final String line = "-----------------------------------\n";
    private static final String indent = "   ";
    private static int taskIndex = 0;
    private static final ArrayList<Task> taskList = new ArrayList<>();


    public static void readFile (Scanner s) throws IOException {
        while (s.hasNext()) {
            String inputLine = s.nextLine();
            String[] inputArray = inputLine.split(" \\| ");
            switch(inputArray[0]) {
            case "E":
                taskList.add(new Event(taskIndex,Boolean.parseBoolean(inputArray[1]),inputArray[2],inputArray[3],inputArray[4]));
                taskIndex++;
                break;
            case "D":
                taskList.add(new Deadline(taskIndex,Boolean.parseBoolean(inputArray[1]),inputArray[2],inputArray[3]));
                taskIndex++;
                break;
            case "T":
                taskList.add(new ToDo(taskIndex,Boolean.parseBoolean(inputArray[1]),inputArray[2]));
                taskIndex++;
                break;
            }

        }
    }

    public static void acceptInput () throws IOException {
        Scanner input = new Scanner(System.in);
        String inputString;
        Commands action;
        boolean flag = true;

        do {
            inputString = input.nextLine();
            action = classifyCommand(inputString);

            int spaceIndex = inputString.indexOf(" ");
            int slashIndex = inputString.indexOf("/");

            if (slashIndex == -1) {
                slashIndex = inputString.length();
            }

            switch (action) {
            case TODO:
            case DEADLINE:
            case EVENT:
                addTask(action,inputString.substring(spaceIndex + 1,slashIndex),inputString,slashIndex);
                continue;
            case DELETE:
                deleteTask(Integer.parseInt(inputString.substring(spaceIndex+1)));
                continue;
            case BYE:
                sayBye();
                flag = false;
                break;
            case LIST:
                displayList();
                continue;
            case MARK:
                taskList.get(Integer.parseInt(inputString.substring(5)) - 1).mark();
                continue;
            case UNMARK:
                taskList.get(Integer.parseInt(inputString.substring(7)) - 1).unmark();
                continue;
            //case CLEARDATA:
              //  clearData();
            case INVALIDTODO:
            case INVALIDDEADLINE:
            case INVALIDEVENT:
            case INVALIDCOMMAND:
            case EMPTYCOMMAND:
            case INVALIDDELETE:
                handleCheckedExceptions(action);

            }

        } while (flag);

    }

    /*private static void clearData () {
        if (inputFile == null || !(inputFile.delete()) ) {
            System.out.println("No data to delete");

        } else {
            System.out.println("New chapter! Data cleared!");
        }
    }*/

    private static void addTask(Commands taskType, String taskName, String inputString, int slashIndex) {
        ToDo newToDo = null;
        Deadline newDeadline = null;
        Event newEvent = null;

        switch (taskType) {
        case TODO:
            newToDo = new ToDo(taskIndex, false, taskName);
            taskList.add(newToDo);
            break;
        case DEADLINE:
            String deadline = inputString.substring(slashIndex+3);
            newDeadline = new Deadline(taskIndex,false,taskName,deadline);
            taskList.add(newDeadline);

            break;
        case EVENT:
            String startAndEnd = inputString.substring(slashIndex+5);
            String startTime = startAndEnd.substring(0,startAndEnd.indexOf("/"));
            String endTime = startAndEnd.substring(startAndEnd.indexOf("/")+3);
            newEvent = new Event(taskIndex,false,taskName,startTime,endTime);
            taskList.add(newEvent);

            break;
        }


        System.out.print(line);
        System.out.print(indent);
        System.out.println("OK, I've added: " + taskName);

        if ((taskList.get(taskIndex)).getType().equals("D")) {
            System.out.println(newDeadline);

        }else if ((taskList.get(taskIndex)).getType().equals("E")){

            System.out.println(newEvent);

        }else {
            System.out.println(newToDo);
        }

        System.out.println(indent + "Now, you have " + (taskIndex + 1) + " tasks in your list.");
        System.out.print(line);

        try {
            Task addedTask  = taskList.get(taskIndex);
            switch (addedTask.getType()) {
            case "E":
                writeToFile("E" + " | " + addedTask.isMarked() + " | " +
                        addedTask.getTaskName() + " | " + addedTask.getStartTime() + " | " +
                        addedTask.getEndTime() + System.lineSeparator(),true);
                break;
            case "D":
                writeToFile("D" + " | " + addedTask.isMarked() + " | " +
                        addedTask.getTaskName() + " | " + addedTask.getEndTime() + System.lineSeparator(),true);
                break;
            default:
                writeToFile("T" + " | " + addedTask.isMarked() + " | " +
                        addedTask.getTaskName() + System.lineSeparator(),true);
                break;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        taskIndex++;
    }


    private static void  deleteTask(int taskNumber) {
        taskIndex--;
        System.out.print(line);
        System.out.print(indent);
        System.out.println("No problemo, I've removed this task: ");

        System.out.println(taskList.get(taskNumber - 1));

        System.out.println(indent + "Now, you have " + taskIndex + " tasks in your list.");
        System.out.print(line);
        taskList.remove(taskNumber - 1);

        for (int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setOrder(i);
        }

        String writeFileString = "";
        for (Task task : taskList) {
            switch (task.getType()) {
            case "E":
                writeFileString += "E" + " | " + task.isMarked() + " | " +
                        task.getTaskName() + " | " + task.getStartTime() + " | " +
                        task.getEndTime() + System.lineSeparator();
                break;
            case "D":
                writeFileString += "D" + " | " + task.isMarked() + " | " +
                        task.getTaskName() + " | " + task.getEndTime() + System.lineSeparator();
                break;
            default:
                writeFileString += "T" + " | " + task.isMarked() + " | " +
                        task.getTaskName() + System.lineSeparator();
                break;
            }
        }
        try {
            writeToFile(writeFileString,false);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

    }
    private static void writeToFile (String textToAdd, boolean flag) throws IOException {
        FileWriter fw = new FileWriter("data/winter.txt", flag);
        fw.write(textToAdd);
        fw.close();

    }

    private static Commands classifyCommand(String inputString) {
        switch (inputString) {
        // Cases include farewell and list commands
        case "bye":
        case "Bye":
        case "BYE":
            return BYE;
        case "list":
        case "List":
        case "LIST":
            return LIST;

        }
        try {
            String[] commandWords = inputString.split(" ");
            switch (commandWords[0]) {
            case "todo":
                verifyToDo(commandWords);
                return TODO;

            case "deadline":
                verifyDeadline(commandWords);
                return DEADLINE;
            case "event":
                verifyEvent(commandWords);
                return EVENT;
            case "delete":
                verifyDelete(commandWords);
                return DELETE;
            // Cases for marking tasks
            case "mark":
                return MARK;

            case "unmark":
                return UNMARK;

            case "":
                handleEmptyString();

            default:
                handleInvalidCommand();
            }
        } catch (InvalidToDoException e) {
            return INVALIDTODO;
        } catch (InvalidDeadlineException e) {
            return INVALIDDEADLINE;
        } catch (InvalidEventException e) {
            return INVALIDEVENT;
        } catch (EmptyCommandException e) {
            return EMPTYCOMMAND;
        } catch (InvalidCommandException | ArrayIndexOutOfBoundsException e) {
            return INVALIDCOMMAND;
        } catch (InvalidDeleteException e) {
            return INVALIDDELETE;
        }
        return INVALIDCOMMAND;

    }

    // Method for displaying list
    private static void displayList() {
        for (Task task : Manager.taskList) {
            System.out.print(indent);
            switch (task.getType()) {
            case "D":
                System.out.println(task.getOrder() + 1 + ". [D] " + task.doneCheckbox + " "
                        + task.getTaskName() + " (by: " + task.getEndTime() + ")");
            case "E":
                System.out.println(task.getOrder() + 1 + ". [E] " + task.doneCheckbox + " "
                        + task.getTaskName() + " (from: " + task.getStartTime()
                        + " to: " + task.getEndTime() + ")");
            default:
                System.out.println(task.getOrder() + 1 + ". [T]" + task.doneCheckbox + " "
                        + task.getTaskName());
            }
        }
        System.out.print(line);
    }


    // Method for farewell message
    private static void sayBye() {
        String farewell = "Farewell. Hope to see you again soon!";
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);
    }

    public static void verifyToDo(String[] commandWords) throws InvalidToDoException {
        if (commandWords.length < 2) {
            throw new InvalidToDoException();
        }
    }

    public static void verifyDeadline(String[] commandWords) throws InvalidDeadlineException {
        boolean isValidDeadline = true;

        for (String commandWord : commandWords) {
            if (commandWord.equals("/by")) {
                isValidDeadline = false;
                break;
            }
        }
        if (commandWords.length < 2 || isValidDeadline) {
            throw new InvalidDeadlineException();
        }
    }

    public static void verifyEvent(String[] commandWords) throws InvalidEventException {
        boolean isValidEvent = false;
        boolean hasValidStart = true;
        boolean hasValidEnd = true;
        for (String commandWord : commandWords) {
            if (commandWord.equals("/from")) {
                hasValidStart = false;
            }
            if (commandWord.equals("/to")) {
                hasValidEnd = false;
            }
        }
        if (hasValidStart && hasValidEnd) {
            isValidEvent = true;
        }
        if (commandWords.length < 2 || isValidEvent) {
            throw new InvalidEventException();
        }
    }

    public static void verifyDelete(String[] commandWords) throws InvalidDeleteException {
        if (commandWords.length < 2 || !isInteger(commandWords[1])) {
            throw new InvalidDeleteException();
        }
    }

    public static boolean isInteger (String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i  = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void handleEmptyString () throws EmptyCommandException {
        throw new EmptyCommandException();
    }

    public static void handleInvalidCommand () throws InvalidCommandException {
        throw new InvalidCommandException();
    }

    public static void handleCheckedExceptions (Commands action) {
        switch (action) {
        case INVALIDTODO:
            System.out.println("Oh no! You did not enter a ToDo task after the todo command!");
            System.out.println("The correct format should be 'todo (task)'");
            System.out.print(line);
            break;
        case INVALIDDEADLINE:
            System.out.println("Oh no! You did not enter a Deadline task / specify the deadline correctly " +
                    "after the deadline command!");
            System.out.println("The correct format should be 'deadline (task) by/ (deadline)'");
            System.out.print(line);
            break;
        case INVALIDEVENT:
            System.out.println("Oh no! You did not enter a Event task / specify the event correctly " +
                    "after the event command!");
            System.out.println("The correct format should be 'event (task) /from (start time) /to (end time)'");
            System.out.print(line);
            break;
        case EMPTYCOMMAND:
            System.out.println("Oh no! You did not enter anything! What can one do with nothing? I wonder...");
            System.out.println("Valid commands are todo, deadline, event\nThanks!");
            System.out.print(line);
            break;
        case INVALIDCOMMAND:
            System.out.println("Thank you for your input but you did not enter any command! :(");
            System.out.println("Valid commands are todo, deadline, event\nThanks!");
            System.out.print(line);
            break;
        case INVALIDDELETE:
            System.out.println("Haishhh, I don't know what to remove! Please specify accordingly! :(");
            System.out.println("The correct format should be 'delete (task number)'\nThanks!");
            System.out.print(line);
            break;
        }
    }
}
