package Chat.tasks;

import Chat.exceptions.InvalidIndex;
public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the input line and execute respective commands.
     * The command could include exit the program, list the tasks, mark and unmark a task,
     * add todos, deadlines, events classes,
     * delete a task, and search for keywords.
     * @param input The input to be parsed by the Parse class.
     * @throws InvalidIndex If the index is invalid or out of bound.
     */
    public static void parse(String input) throws InvalidIndex {
        String[] inputs = input.trim().split("\\s+", 2);
        String commandWord = inputs[0];
        String arguments = inputs.length > 1 ? inputs[1] : "";

        switch (commandWord) {
        case COMMAND_EXIT:
            //System.out.println("Bye. Hope to see you again soon!");
            break;
        case COMMAND_LIST:
            printList();
            printLine();
            break;
        case COMMAND_MARK:
            markTask(arguments);
            printLine();
            break;
        case COMMAND_UNMARK:
            unmarkTask(arguments);
            printLine();
            break;
        case COMMAND_TODO:
            addTodoTask(arguments);
            printLine();
            break;
        case COMMAND_DEADLINE:
            addDeadlineTask(arguments);
            printLine();
            break;
        case COMMAND_EVENT:
            addEventTask(arguments);
            printLine();
            break;
        case COMMAND_DELETE:
            deleteTask(arguments);
            printLine();
            break;
        case COMMAND_FIND:
            findTasks(arguments);
            printLine();
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            printLine();
        }
    }

    private static void printList() {
        taskList.printList();
    }

    private static void markTask(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            taskList.markTask(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList.getTask(index).getStatusIcon() + " "
                    + taskList.getTask(index).description);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index, please try again!");
        } catch (NumberFormatException e){
            System.out.println("Please key in a number after 'mark'");
        }
    }

    private static void unmarkTask(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            taskList.unmarkTask(index);
            System.out.println("Nice! I've marked this task as not done:");
            System.out.println("  " + taskList.getTask(index).getStatusIcon() + " "
                    + taskList.getTask(index).description);
        } catch ( IndexOutOfBoundsException e) {
            System.out.println("Invalid task index, please try again!");
        } catch ( NumberFormatException e){
            System.out.println("Please key in a number after 'unmark'");
        }
    }

    private static void addTodoTask(String description) {
        if (!description.isEmpty()) {
            taskList.addTask(new Todos(description));
            System.out.println("Got it. I've added this task: " + description);
            System.out.println("Now you have " + (taskList.getSize()) + " tasks in the list.");
        } else {
            System.out.println("Please enter a description for the TODO task.");
        }
    }

    private static void addDeadlineTask(String arguments) {
        String[] parts = arguments.split("/by");
        if (parts.length != 2) {
            System.out.println("Invalid format for deadline command. Please use 'deadline <description> /by <date>'");
            return;
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        taskList.addTask(new Deadlines(description, by));
        System.out.println("Got it. I've added this task: " + description);
        System.out.println("Now you have " + (taskList.getSize()) + " tasks in the list.");
    }

    private static void addEventTask(String arguments) {
        String[] parts = arguments.split("/from");
        if (parts.length != 2) {
            System.out.println("Invalid format for event command. Please use 'event <description> /from <start> /to <end>'");
            return;
        }
        String description = parts[0].trim();
        String[] dates = parts[1].split("/to");
        if (dates.length != 2) {
            System.out.println("Invalid format for event command. Please use 'event <description> /from <start> /to <end>'");
            return;
        }
        String start = dates[0].trim();
        String end = dates[1].trim();
        taskList.addTask(new Events(description, start, end));
        System.out.println("Got it. I've added this task: " + description);
        System.out.println("Now you have " + (taskList.getSize()) + " tasks in the list.");
    }

    private static void deleteTask(String arguments) throws InvalidIndex {
        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            taskList.deleteTask(index);
            System.out.println("Ok! Task " + (index + 1) + " is removed from the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index, please try again!");
        } catch (NumberFormatException e){
            System.out.println("Please key in a number after 'delete'");
        }
    }

    private static void findTasks(String keyword){
        boolean isFound = false;
        for (int i = 0; i < taskList.getSize(); i++){
            Task task = taskList.getTask(i);
            if(task.getDescription().contains(keyword)){
                if(!isFound) {
                    System.out.println("Here are the matching tasks in your list:");
                    isFound = true;
                }
                System.out.println((i+1) + "."+ task.toString());
                }
            }
        if(!isFound){
            System.out.println("No matching tasks in the list. Please try again!");
        }
    }
    private static void printLine() {
        System.out.print("____________________________________________________________\n");
    }
}
