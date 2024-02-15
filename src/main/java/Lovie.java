import java.util.ArrayList;
import java.util.Scanner;

public class Lovie {
    public static void main(String[] args) {
        Scanner inputGetter = new Scanner(System.in);
        String input;
        ArrayList<Task> tasksList = new ArrayList<Task>();

        String LOGO = "██╗░░░░░░█████╗░██╗░░░██╗██╗███████╗ \n" +
                "██║░░░░░██╔══██╗██║░░░██║██║██╔════╝ \n" +
                "██║░░░░░██║░░██║╚██╗░██╔╝██║█████╗░░ \n" +
                "██║░░░░░██║░░██║░╚████╔╝░██║██╔══╝░░ \n" +
                "███████╗╚█████╔╝░░╚██╔╝░░██║███████╗ \n" +
                "╚══════╝░╚════╝░░░░╚═╝░░░╚═╝╚══════╝ \n";
        print(LOGO + "Hey hey! My name is Lovie! How can I help you today?");

        while (true) {
            System.out.print("\t");
            input = inputGetter.nextLine().split(" ")[0];

            // Switch statement to keep track of user input commands
            switch (input) {
                case "bye":
                    print("Thanks for using me! See you next time ♡〜٩( ˃▿˂ )۶〜♡");
                    break;
                case "list":
                    listTaskPrinter(tasksList);
                    break;
                case "unmark":
                    unmarkTaskHelper(input, tasksList);
                    break;
                case "mark":
                    markTaskHelper(input, tasksList);
                    break;
                default:
                    String taskType = input.split(" ")[0];
                    Task newTask;

                    // Switch statement to keep track of taskType + default of incorrect input
                    switch (taskType) {
                        case "event":
                            newTask = new Event(input);
                            tasksList.add(newTask);
                            addTaskPrinter(newTask);
                            break;
                        case "deadline":
                            newTask = new Deadline(input);
                            tasksList.add(newTask);
                            addTaskPrinter(newTask);
                            break;
                        case "todo":
                            try {
                                todoFormatChecker(input);
                            } catch (LovieException e) {
                                print(e.getMessage());
                                break;
                            }
                            newTask = new ToDo(input);
                            tasksList.add(newTask);
                            addTaskPrinter(newTask);
                            break;
                        default:
                            invalidCommandPrinter();
                    }
            }
        }
    }


    public static void print(String line) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(line);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void invalidCommandPrinter() {
        print("Sorry, I can't understand what you are saying.\n" +
                "Please try again! Your command options are as follows: \n\n" +
                "(1) To add a todo: todo **description** \n(2) To add an event: event **description** /from **start**" +
                "/to **end**\n(3) To add a deadline: deadline **description** /by **end**\n(4) To list all tasks: list\n"
                + "(5) To mark a task as done: mark **task number**\n(6) To unmark a task as not done: unmark " +
                "**task number**\n(7) To leave our session: bye"
        );
    }

    public static void addTaskPrinter(Task newTask) {
        print("Added: [" + newTask.getTaskIcon() + "] [ ] " + newTask.getDescription() +
                newTask.getTimespan());
    }

    public static void listTaskPrinter(ArrayList<Task> tasksList) {
        if (tasksList.size() == 0) {
            print("Your list is empty right now. \nTry adding a task using one of the following " +
                    "command formats: \n\n" + "(1) todo **description** \n" + "(2) event **description** /from **start** " +
                    "/to **end**\n" + "(3) deadline **description** /by **end**");
        } else {
            int counter = 0;
            String output = new String();
            while (counter < tasksList.size()) {
                String listNumber = Integer.toString(counter + 1);
                Task selected = tasksList.get(counter);
                output += listNumber + ". [" + selected.getTaskIcon() + "] [" + selected.getStatusIcon() + "] " +
                        selected.getDescription() + selected.getTimespan() + "\n";
                counter += 1;
            }
            print(output);
        }
    }

    public static void unmarkTaskPrinter(Task selectedTask) {
        print("Okay, no worries. I've unmarked this task for you: \n" + "[" + selectedTask.getTaskIcon() + "] [" +
                selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "What else can I do for you today?");

    }

    public static void unmarkTaskHelper(String input, ArrayList<Task> tasksList) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        boolean output = true;
        if (taskNumber >= tasksList.size() || taskNumber < 0) {
            print("Sorry, there is no record of a task number " + input.split(" ")[1] +
                    "\n Can I help you with anything else?");
        } else {
            Task selectedTask = tasksList.get(taskNumber);
            selectedTask.markAsUndone();
            unmarkTaskPrinter(selectedTask);
        }
    }

    public static void markTaskPrinter(Task selectedTask) {
        print("Woo hoo! Way to go. I've marked this task as done: \n" + "[" + selectedTask.getTaskIcon() + "] " +
                "[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() +
                selectedTask.getTimespan() + "\n" + "What else can I do for you today?");
    }

    public static void markTaskHelper(String input, ArrayList<Task> tasksList) {
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= tasksList.size() || taskNumber < 0) {
            print("Sorry, there is no record of a task number " + input.split(" ")[1] +
                    "\n Can I help you with anything else?");
        } else {
            Task selectedTask = tasksList.get(taskNumber);
            selectedTask.markAsDone();
            markTaskPrinter(selectedTask);
        }
    }

    public static void todoFormatChecker(String input) throws LovieException {
        String[] splitUpInput = input.split(" ", 2);
        if (splitUpInput.length == 1) {
            throw new LovieException("Oops! Make sure you add a description for your todo! Here is the format:\n");
        }
    }
}

