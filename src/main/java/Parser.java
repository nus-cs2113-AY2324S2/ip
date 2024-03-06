import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageInputs {
    protected static String from;
    protected static String to;
    public static String description;
    protected static String by;
    private static Ui ui;
    private static Storage storage;

    private void dealWithMark(String[] inputs, ArrayList<Task> tasks, int index, boolean isValidCommand) throws IOException {
        isValidCommand = true;
        int idx = Integer.parseInt(inputs[1]);
        tasks.get(idx - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(idx - 1));
        storage.saveToFile(tasks, index);
    }

    private void dealWithUnmark(String[] inputs, ArrayList<Task> tasks, int index, boolean isValidCommand) throws IOException {
        isValidCommand = true;
        int idx = Integer.parseInt(inputs[1]);
        tasks.get(idx - 1).unmarkDone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(tasks.get(idx - 1));
        storage.saveToFile(tasks, index);
    }

    private void listTasks(ArrayList<Task> tasks, int index, boolean isValidCommand){
        isValidCommand = true;
        System.out.println("Here are the tasks in your list: ");

        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private void deleteTask(ArrayList<Task> tasks, String[] inputs, int index, boolean isValidCommand) throws UnexpectedCommandException, IOException {
        isValidCommand = true;
        Storage.fillFileContents(tasks, "TaskList.txt", index);
        TaskList.dealWithDelete(inputs[1], tasks);
        index--;
        System.out.println("Now you have " + index + " tasks in the list.");
        storage.saveToFile(tasks, index);
    }

    private void handleUnexpectedCommand(boolean isValidCommand) throws UnexpectedCommandException {
        if (!isValidCommand) {
            throw new UnexpectedCommandException();
        }
    }

    private void handleEmptyInput(String line) throws EmptyLineException {
        if (line.isEmpty()) {
            throw new EmptyLineException();
        }
    }

    public ManageInputs(ArrayList<Task> tasks, int index, String line) throws IOException, UnexpectedCommandException {
        boolean isInTxt = false;
        index = Storage.fillFileContents(tasks, "TaskList.txt", index);
        ui = new Ui();

        while (!line.equals("bye")) {
            ui.readCommand();
            Boolean isValidCommand = false;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            Task t = new Task(line);
            String[] inputs = line.split(" ");
            
            if (inputs[0].equals("mark")) {//mark as done
                dealWithMark(inputs, tasks, index, isValidCommand);
            } else if (inputs[0].equals("unmark")) {//unmark done
                dealWithUnmark(inputs, tasks, index, isValidCommand);
            } else if (line.equals("list")) {//lists tasks
                listTasks(tasks, index, isValidCommand);
            } else if (line.equals("bye")) {//exit chat
                isValidCommand = true;
                storage.saveToFile(tasks, index);
                break;
            } else if (inputs[0].equals("event") || inputs[0].equals("todo") || inputs[0].equals("deadline")) {//add items
                TaskList.addTasks(tasks, inputs, index, line, isInTxt, isValidCommand);
            } else if (inputs[0].equals("delete")) {
                deleteTask(tasks, inputs, index, isValidCommand);

            } else {
                try {
                    handleEmptyInput(line);
                    handleUnexpectedCommand(isValidCommand);
                } catch (UnexpectedCommandException e) {
                    ui.errorMessage("Please enter a valid command");
                } catch (EmptyLineException e) {
                    ui.errorMessage("No input detected. Please enter a task");
                }
            }
        }
        ui.sayBye();
    }
}