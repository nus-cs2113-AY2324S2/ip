import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * deals with making sense of the user command
 */
public class Parser {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasklist;

    /**
     * marks task as done
     * @param inputs the array of words the user input
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param isValidCommand boolean is true if the command from the user is a valid action
     * @throws IOException exception when there is an I/O error
     */
    private void dealWithMark (String[] inputs, ArrayList<Task> tasks, int index, boolean isValidCommand) throws IOException {
        isValidCommand = true;
        String errorDescription;

        try {
            int markIdx = Integer.parseInt(inputs[1]) - 1; //index to mark as done
            tasks.get(markIdx).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(tasks.get(markIdx));
            storage.saveToFile(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            errorDescription = "did not indicate which task to mark";
            ui.errorMessage(errorDescription);
        }
    }

    /**
     * marks task as undone
     * @param inputs the array of words input by the user
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param isValidCommand boolean is true if the command from the user is a valid action
     * @throws IOException exception when there is an I/O error
     */
    private void dealWithUnmark (String[] inputs, ArrayList<Task> tasks, int index, boolean isValidCommand) throws IOException {
        isValidCommand = true;
        String errorDescription;

        try {
            int unmarkIdx = Integer.parseInt(inputs[1]) - 1; //index of task to unmark
            tasks.get(unmarkIdx).unmarkDone();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(tasks.get(unmarkIdx));
            storage.saveToFile(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            errorDescription = "did not indicate which task to unmark";
            ui.errorMessage(errorDescription);
        }
    }

    /**
     * lists tasks
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param isValidCommand boolean is true if the command from the user is a valid action
     */
    private void listTasks (ArrayList<Task> tasks, int index, boolean isValidCommand) {
        isValidCommand = true;
        System.out.println("Here are the tasks in your list: ");

        for (int taskNumber = 0; taskNumber < index; taskNumber++) {
            System.out.println((taskNumber + 1) + ". " + tasks.get(taskNumber));
        }
    }

    /**
     * deletes task
     * @param tasks the array of tasks
     * @param inputs the array of words input by the user
     * @param index the number of tasks in the array of tasks
     * @param isValidCommand boolean is true if the command from the user is a valid action
     * @return the number of tasks in the array of tasks after deletion
     * @throws UnexpectedCommandException exception when the format or details of the tasks are not followed and provided respectively
     * @throws IOException exception when there is an I/O error
     */
    private int deleteTask (ArrayList<Task> tasks, String[] inputs, int index, boolean isValidCommand) throws UnexpectedCommandException, IOException {
        isValidCommand = true;
        Storage.fillFileContents(tasks, "TaskList.txt", index);
        TaskList.dealWithDelete(inputs, inputs[1], tasks);
        index--;
        System.out.println("Now you have " + index + " tasks in the list.");
        storage.saveToFile(tasks, index);
        return index;
    }

    /**
     * filters tasks with the keyword input by the user
     * @param findInput the keyword the user is looking for
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     */
    private void dealWithFind (String findInput, ArrayList<Task> tasks, int index) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        int matchingTasksIndex = 1;
        System.out.println("Here are the matching tasks in your list: ");
        for (int taskNumber = 0; taskNumber < index; taskNumber ++){
            String taskString = tasks.get(taskNumber).toString();
            if (taskString.contains(findInput)) {
                System.out.print(matchingTasksIndex + ". ");
                matchingTasks.add(tasks.get(taskNumber));
                System.out.println(matchingTasks.get(matchingTasksIndex - 1));
                matchingTasksIndex ++;
            }
        }
    }


    private void handleUnexpectedCommand (boolean isValidCommand) throws UnexpectedCommandException {
        if (!isValidCommand) {
            throw new UnexpectedCommandException();
        }
    }

    private void handleEmptyInput (String line) throws EmptyLineException {
        if (line.isEmpty()) {
            throw new EmptyLineException();
        }
    }

    /**
     * handles the different commands input by the user
     * @param tasks the array of tasks
     * @param index the number of tasks in the array of tasks
     * @param line the user input
     * @throws IOException exception when there is an I/O error
     * @throws IndexOutOfBoundsException exception when there is missing required details in the input
     * @throws UnexpectedCommandException exception when the format or details of the tasks are not followed and provided respectively
     */
    public Parser (ArrayList<Task> tasks, int index, String line) throws IOException, IndexOutOfBoundsException, UnexpectedCommandException {
        boolean isInTxt = true;
        index = Storage.fillFileContents(tasks, "TaskList.txt", index);
        isInTxt = false;
        ui = new Ui();
        String errorDescription;

        while (!line.equals("bye")) {
            ui.readCommand();
            Boolean isValidCommand = false;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            Task t = new Task(line);
            String[] inputs = line.split(" ");

            final String commandType = inputs[0];
            
            if (commandType.equals("mark")) {//mark as done
                dealWithMark(inputs, tasks, index, isValidCommand);
                Storage.saveToFile(tasks, index);
            } else if (commandType.equals("unmark")) {//unmark done
                dealWithUnmark(inputs, tasks, index, isValidCommand);
                Storage.saveToFile(tasks, index);
            } else if (line.equals("list")) {//lists tasks
                listTasks(tasks, index, isValidCommand);
            } else if (line.equals("bye")) {//exit chat
                isValidCommand = true;
                Storage.saveToFile(tasks, index);
                break;
            } else if (commandType.equals("event") || commandType.equals("todo") || commandType.equals("deadline")) {//add items
                try {
                    if (commandType.equals("event")) {
                        isValidCommand = true;
                        TaskList.dealWithEvent(tasks, index, line, isInTxt);
                        Storage.writeToFile("TaskList.txt", tasks.get(index));
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tasks.get(index));
                        index++;
                    } else if (commandType.equals("deadline")) {
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

            } else if (commandType.equals("delete")) {
                try {
                    index = deleteTask(tasks, inputs, index, isValidCommand);
                } catch (ArrayIndexOutOfBoundsException e) {
                    errorDescription = "please specify which task you want to delete";
                    ui.errorMessage(errorDescription);
                }
            } else if (commandType.equals("find")) {
                try {
                    dealWithFind(inputs[1], tasks, index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    errorDescription = "please state what you would like to find";
                    ui.errorMessage(errorDescription);
                }
            } else {
                try {
                    handleEmptyInput(line);
                    handleUnexpectedCommand(isValidCommand);
                } catch (UnexpectedCommandException e) {
                    errorDescription = "Please enter a valid command";
                    ui.errorMessage(errorDescription);
                } catch (EmptyLineException e) {
                    errorDescription = "No input detected. Please enter a task";
                    ui.errorMessage(errorDescription);
                }
            }
        }
        ui.sayBye();
    }
}