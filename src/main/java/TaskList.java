import java.io.IOException;
import java.util.ArrayList;

/**
 * contains the task list and operations to add or delete tasks
 */
public class TaskList {
    protected static Ui ui;
    protected static ArrayList<Task> tasks;
    public TaskList(){
        this.tasks = tasks;
    }

    /**
     * adds event to array of tasks
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param line the input string from the user
     * @param isInTxt true if ArrayList<Task> tasks is being loaded from TaskList.txt
     * @throws UnexpectedCommandException exception when the format or details of the tasks are not followed and provided respectively
     * @throws IOException exception when there is an I/O error
     */
    public static void dealWithEvent (ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexTo = line.lastIndexOf("to");
        int indexFrom = line.indexOf("from");
        String from;
        String to;
        String description;
        String errorDescription;
        
        final int LENGTHOFFROM = 5; //length of "from "
        final int LENGTHOFTO = 3;
        final int EVENTCOMMANDLENGTH = 6;//length of "event "

        final int FROMLENGTHCOLON = 6;//length of "from: "
        final int TOLENGTHCOLON = 4;
        final int STATUSLENGTH = 5;//length of "[E][ ]"

        if ((indexTo == -1) || (indexFrom == -1)) { //invalid format
            errorDescription = "Invalid format! Enter event in the format: event (description) from (start) to (end)";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }
        try {//timeline not specified/ both not specified
            from = line.substring(indexFrom + LENGTHOFFROM, indexTo - 1);
            to = line.substring(indexTo + LENGTHOFTO);
        } catch (IndexOutOfBoundsException e) {
            try {
                description = line.substring(EVENTCOMMANDLENGTH, indexFrom - 1);
            } catch (IndexOutOfBoundsException f) {
                errorDescription = "event description and timeline not specified";
                ui.errorMessage(errorDescription);
                throw new UnexpectedCommandException();
            }
            errorDescription = "event timeline not specified";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }
        try {//description not specified
            description = line.substring(EVENTCOMMANDLENGTH, indexFrom - 1);
        } catch (IndexOutOfBoundsException e) {
            errorDescription = "event description not specified";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }
        if (!isInTxt) {
            from = line.substring(indexFrom + LENGTHOFFROM, indexTo - 1);
            to = line.substring(indexTo + LENGTHOFTO);
            description = line.substring(STATUSLENGTH, indexFrom - 1);
        } else {
            from = line.substring(indexFrom + FROMLENGTHCOLON, indexTo - 1);
            to = line.substring(indexTo + TOLENGTHCOLON, line.length() - 1);
            description = line.substring(EVENTCOMMANDLENGTH, indexFrom - 1);
        }
        tasks.add(index, new Event(description, from, to));
        final int STATUSINDEX = 4;
        if (isInTxt){
            char[] charArray = line.toCharArray();
            if (charArray[STATUSINDEX] == '1'){
                tasks.get(index).markAsDone();
            } else{
                tasks.get(index).unmarkDone();
            }
        }
    }

    /**
     * adds deadline to array of tasks
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param line the input string from the user
     * @param isInTxt true if ArrayList<Task> tasks is being loaded from TaskList.txt
     * @throws UnexpectedCommandException exception when the format or details of the tasks are not followed and provided respectively
     * @throws IOException exception when there is an I/O error
     */
    public static void dealWithDeadline (ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexBy = line.indexOf("by");
        int space = line.indexOf(" ");
        String by;
        String description;
        String errorDescription;

        final int LENGTHFBY = 3;//length of "by "
        final int BYLENGTHCOLON = 4; //length of "by: "

        if (indexBy == -1) {//invalid format
            errorDescription = "Invalid format! Enter deadline in the format: deadline (description) by (deadline)";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }
        try {//deadline / both not specified
            by = line.substring(indexBy + LENGTHFBY);
        } catch (IndexOutOfBoundsException e) {
            try {
                description = line.substring(space + 1, indexBy - 1);
            } catch (IndexOutOfBoundsException f) {
                errorDescription = "deadline description and deadline not specified";
                ui.errorMessage(errorDescription);
                throw new UnexpectedCommandException();
            }
            errorDescription = "deadline not specified";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }
        try {//deadline not specified
            description = line.substring(space + 1, indexBy - 1);
        } catch (IndexOutOfBoundsException e) {
            errorDescription = "deadline description not specified";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }
        if (!isInTxt) {
            description = line.substring(space, indexBy - 1);
            by = line.substring(indexBy + LENGTHFBY);
        } else {
            description = line.substring(space, indexBy - 1);
            by = line.substring(indexBy + BYLENGTHCOLON, line.length() - 1);
        }

        tasks.add(index, new Deadline(description, by));
        if (isInTxt) {
            final int STATUSINDEX = 4;
            char[] charArray = line.toCharArray();
            if (charArray[STATUSINDEX] == '1'){
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).unmarkDone();
            }
        }
    }

    /**
     * adds todo to array of tasks
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param line the input string from the user
     * @param isInTxt true if ArrayList<Task> tasks is being loaded from TaskList.txt
     * @throws UnexpectedCommandException exception when the format or details of the tasks are not followed and provided respectively
     * @throws IOException exception when there is an I/O error
     */
    public static void dealWithTodo (ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexSpace = line.indexOf(" ");
        final int STATUSINDEX = 4;
        String errorDescription;

        if (indexSpace == -1) {
            errorDescription = "todo description not specified";
            ui.errorMessage(errorDescription);
            throw new UnexpectedCommandException();
        }

        String description = line.substring(indexSpace);
        tasks.add(index, new Todo(description));
        if (isInTxt) {
            char[] charArray = line.toCharArray();
            if (charArray[STATUSINDEX] == '1') {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).unmarkDone();
            }
        }
    }

    /**
     * deletes item from array of tasks
     * @param inputs the array of words input by the user
     * @param ListIndex the string containing the task number to delete
     * @param tasks the array of tasks
     */
    public static void dealWithDelete (String[] inputs, String ListIndex, ArrayList<Task> tasks) {
        if (inputs[0].equals("delete")) {
            int deleteIdx = Integer.parseInt(ListIndex) - 1; //index of task to delete
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(deleteIdx));
            tasks.remove(deleteIdx);
        }
    }
}
