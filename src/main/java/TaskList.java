import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected static Ui ui;
    protected static ArrayList<Task> tasks;
    public TaskList(){
        this.tasks = tasks;
    }

    public static void dealWithEvent(ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexTo = line.lastIndexOf("to");
        int indexFrom = line.indexOf("from");
        String from;
        String to;
        String description;

        if ((indexTo == -1) || (indexFrom == -1)) { //invalid format
            ui.errorMessage("Invalid format! Enter event in the format: event (description) from (start) to (end)");
            throw new UnexpectedCommandException();
        }
        try {//timeline not specified/ both not specified
            from = line.substring(indexFrom + 5, indexTo - 1);
            to = line.substring(indexTo + 3);
        } catch (IndexOutOfBoundsException e) {
            try {
                description = line.substring(6, indexFrom - 1);
            } catch (IndexOutOfBoundsException f) {
                ui.errorMessage("event description and timeline not specified");
                throw new UnexpectedCommandException();
            }
            ui.errorMessage("event timeline not specified");
            throw new UnexpectedCommandException();
        }
        try {//description not specified
            description = line.substring(6, indexFrom - 1);
        } catch (IndexOutOfBoundsException e) {
            ui.errorMessage("event description not specified");
            throw new UnexpectedCommandException();
        }
        if(!isInTxt) {
            from = line.substring(indexFrom + 5, indexTo - 1);
            to = line.substring(indexTo + 3);
            description = line.substring(5, indexFrom - 1);
        } else {

            from = line.substring(indexFrom + 6, indexTo - 1);
            to = line.substring(indexTo + 4, line.length() - 1);
            description = line.substring(6, indexFrom - 1);


        }
        tasks.add(index, new Event(description, from, to));
        if (isInTxt){
            char[] charArray = line.toCharArray();
            if (charArray[4] == '1'){
                tasks.get(index).markAsDone();
            } else{
                tasks.get(index).unmarkDone();
            }
        }
    }

    public static void dealWithDeadline(ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexBy = line.indexOf("by");
        int space = line.indexOf(" ");
        String by;
        String description;
        if (indexBy == -1) {//invalid format
            ui.errorMessage("Invalid format! Enter deadline in the format: deadline (description) by (deadline)");
            throw new UnexpectedCommandException();
        }
        try {//deadline / both not specified
            by = line.substring(indexBy + 3);
        } catch (IndexOutOfBoundsException e) {
            try {
                description = line.substring(space + 1, indexBy - 1);
            } catch (IndexOutOfBoundsException f) {
                ui.errorMessage("deadline description and deadline not specified");
                throw new UnexpectedCommandException();
            }
            ui.errorMessage("deadline not specified");
            throw new UnexpectedCommandException();
        }
        try {//deadline not specified
            description = line.substring(space + 1, indexBy - 1);
        } catch (IndexOutOfBoundsException e) {
            ui.errorMessage("deadline description not specified");
            throw new UnexpectedCommandException();
        }
        if (!isInTxt) {
            description = line.substring(space, indexBy - 1);
            by = line.substring(indexBy + 3);
        } else{
            description = line.substring(space, indexBy - 1);
            by = line.substring(indexBy + 4, line.length() - 1);
        }

        tasks.add(index, new Deadline(description, by));
        if (isInTxt){
            char[] charArray = line.toCharArray();
            if (charArray[4] == '1'){
                tasks.get(index).markAsDone();
            } else{
                tasks.get(index).unmarkDone();
            }
        }
    }

    public static void dealWithTodo(ArrayList<Task> tasks, int index, String line, boolean isInTxt) throws UnexpectedCommandException, IOException {
        int indexSpace = line.indexOf(" ");
        if (indexSpace == -1) {
            ui.errorMessage("todo description not specified");
            throw new UnexpectedCommandException();
        }

        String description = line.substring(indexSpace);
        tasks.add(index, new Todo(description));
        if (isInTxt){
            char[] charArray = line.toCharArray();
            if (charArray[4] == '1'){
                tasks.get(index).markAsDone();
            } else{
                tasks.get(index).unmarkDone();
            }
        }

    }

    public static void dealWithDelete (String[] inputs, String ListIndex, ArrayList<Task> tasks){
        if (inputs[0].equals("delete")) {
            int idx = Integer.parseInt(ListIndex);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(idx - 1));
            tasks.remove(idx - 1);
        }
    }
}
