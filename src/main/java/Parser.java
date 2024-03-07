import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    protected static String from;
    protected static String to;
    public static String description;
    protected static String by;
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasklist;

    public String findInput;

    private void dealWithMark(String[] inputs, ArrayList<Task> tasks, int index, boolean isValidCommand) throws IOException {
        isValidCommand = true;
        try {
            int idx = Integer.parseInt(inputs[1]);
            tasks.get(idx - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.get(idx - 1));
            storage.saveToFile(tasks, index);
        } catch (IndexOutOfBoundsException e){
            ui.errorMessage("did not indicate which task to mark");
        }

    }

    private void dealWithUnmark(String[] inputs, ArrayList<Task> tasks, int index, boolean isValidCommand) throws IOException {
        isValidCommand = true;
        try {
            int idx = Integer.parseInt(inputs[1]);
            tasks.get(idx - 1).unmarkDone();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(tasks.get(idx - 1));
            storage.saveToFile(tasks, index);
        } catch (IndexOutOfBoundsException e){
            ui.errorMessage("did not indicate which task to unmark");
        }
    }

    private void listTasks(ArrayList<Task> tasks, int index, boolean isValidCommand){
        isValidCommand = true;
        System.out.println("Here are the tasks in your list: ");

        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private int deleteTask(ArrayList<Task> tasks, String[] inputs, int index, boolean isValidCommand) throws UnexpectedCommandException, IOException {
        isValidCommand = true;
        Storage.fillFileContents(tasks, "TaskList.txt", index);
        TaskList.dealWithDelete(inputs, inputs[1], tasks);
        index--;
        System.out.println("Now you have " + index + " tasks in the list.");
        storage.saveToFile(tasks, index);
        return index;
    }

    private void dealWithFind(String findInput, ArrayList<Task> tasks, int index){
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        int matchingTasksIndex = 1;
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < index; i ++){
            String xline = tasks.get(i).toString();
            if (xline.contains(findInput)){
                System.out.print(matchingTasksIndex + ". ");
                matchingTasks.add(tasks.get(i));
                System.out.println(matchingTasks.get(matchingTasksIndex - 1));
                matchingTasksIndex ++;
            }
        }
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

    public Parser(ArrayList<Task> tasks, int index, String line) throws IOException, IndexOutOfBoundsException, UnexpectedCommandException {
        boolean isInTxt = true;
        index = Storage.fillFileContents(tasks, "TaskList.txt", index);
        isInTxt = false;
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
                Storage.saveToFile(tasks, index);
            } else if (inputs[0].equals("unmark")) {//unmark done
                dealWithUnmark(inputs, tasks, index, isValidCommand);
                Storage.saveToFile(tasks, index);
            } else if (line.equals("list")) {//lists tasks
                listTasks(tasks, index, isValidCommand);
            } else if (line.equals("bye")) {//exit chat
                isValidCommand = true;
                Storage.saveToFile(tasks, index);
                break;
            } else if (inputs[0].equals("event") || inputs[0].equals("todo") || inputs[0].equals("deadline")) {//add items
                try {
                    if (inputs[0].equals("event")) {
                        isValidCommand = true;
                        TaskList.dealWithEvent(tasks, index, line, isInTxt);
                        Storage.writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    } else if (inputs[0].equals("deadline")) {
                        isValidCommand = true;
                        TaskList.dealWithDeadline(tasks, index, line, isInTxt);
                        Storage.writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    } else {
                        isValidCommand = true;
                        TaskList.dealWithTodo(tasks, index, line, isInTxt);
                        Storage.writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    }
                } catch (IOException | UnexpectedCommandException | IndexOutOfBoundsException e) {
                }
                System.out.println("Now you have " + index + " tasks in the list.");

            } else if (inputs[0].equals("delete")) {
                try {
                    index = deleteTask(tasks, inputs, index, isValidCommand);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("please secify which task you want to delete");
                }
            } else if (inputs[0].equals("find")){
                try {
                    dealWithFind(inputs[1], tasks, index);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("please state what you would like to find");
                }
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