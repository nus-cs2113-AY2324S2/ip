import java.util.Scanner;

public class Aragorn {

    private static final String LINE =  "    __________________________________________________________\n";
    private static final String GREET = "    Hello! I am Aragorn son of Arathorn, and am called Elessar, the Elfstone, Dúnadan,\n" +
            "    the heir of Isildur Elendil's son of Gondor.\n" +
            "    What can I do for you?\n";
    private static final String EXIT = "    Bye. Hope to see you again soon!\n";
    private static final String TAB = "    ";

    private static final String commandList = "    Here is a list of commands:\n" +
            "\n" +
            "    \"list\": Displays list of tasks.\n" +
            "\n" +
            "    \"todo [description]\": Adds a Todo task to the list.\n" +
            "\n" +
            "    \"deadline [description] /by [deadline]\": Adds a task and its deadline to the list.\n" +
            "\n" +
            "    \"event [description] /from [start] /to [end]\": Adds an event and its start and end conditions to the list.\n" +
            "\n" +
            "    \"mark [task number]\": Marks the corresponding task in the list as completed.\n" +
            "\n" +
            "    \"unmark [task number]\": Marks the corresponding task in the list as incomplete.\n" +
            "\n" +
            "    \"/help\": Displays this list of commands.\n" +
            "\n" +
            "    \"bye\": Closes the program.\n";

    public static void main(String[] args) {
        Task[] list = new Task[100];
        int listLength = 0;
        int remainingTasks = 0;
        System.out.println(LINE + GREET + LINE);
        int index;
        String icon;
        String[] splitInput;
        Scanner in = new Scanner(System.in);
        while(true) {
            String userInput = in.nextLine();
            String commandType = commandIdentifier(userInput);
            splitInput = inputParser(userInput, commandType);

            switch (commandType) {
                case "LIST":
                    System.out.println(LINE);
                    System.out.println("    Here are the tasks in your list: ");
                    for (int i = 0; i < listLength; i += 1) {
                        System.out.println(TAB + (i + 1) + ". " + list[i].taskString());
                    }
                    System.out.println("\n");
                    printRemainingTasks(remainingTasks);
                    break;

                case "UNMARK":
                    index = Integer.parseInt(splitInput[0]);
                    icon = list[index].getStatusIcon();
                    if (icon.equals(" ")) {
                        System.out.println(LINE + "    This task has already been unmarked.\n" + LINE);
                        break;
                    }
                    list[index].markAsUndone();
                    remainingTasks += 1;
                    System.out.println(LINE + TAB + "OK, I've marked this task as not done yet:\n" + TAB +
                            "   " + list[index].taskString() +"\n");
                    printRemainingTasks(remainingTasks);
                    break;

                case "MARK":
                    index = Integer.parseInt(splitInput[0]);
                    icon = list[index].getStatusIcon();
                    if (icon.equals("X")) {
                        System.out.println(LINE + "    This task has already been marked.\n" + LINE);
                        break;
                    }
                    list[index].markAsDone();
                    remainingTasks -= 1;
                    System.out.println(LINE + TAB + "Nice! I've marked this task as done:\n" + TAB +
                            "   " + list[index].taskString() + "\n");
                    printRemainingTasks(remainingTasks);
                    break;

                case "TODO":
                    list[listLength] = new ToDo(userInput);
                    printAddTask(list[listLength]);
                    listLength += 1;
                    remainingTasks += 1;
                    printRemainingTasks(remainingTasks);
                    break;

                case "DEADLINE":
                    list[listLength] = new Deadline(splitInput[0].trim(), splitInput[1].trim());
                    printAddTask(list[listLength]);
                    listLength += 1;
                    remainingTasks += 1;
                    printRemainingTasks(remainingTasks);
                    break;

                case "EVENT":
                    list[listLength] = new Event(splitInput[0].trim(), splitInput[1].trim(), splitInput[2].trim());
                    printAddTask(list[listLength]);
                    listLength += 1;
                    remainingTasks += 1;
                    printRemainingTasks(remainingTasks);
                    break;

                case "HELP":
                    System.out.println(LINE + commandList + LINE);
                    break;

                case "INVALID":
                    System.out.println("Your input is invalid. Use the \"/help\" command to view the list of commands.");
                    break;

                case "BYE":
                    System.out.println(LINE + TAB + EXIT + LINE);
                    return;
            }
        }
    }

    private static void printAddTask(Task list) {
        System.out.println(LINE + "    Got it. I've added this task:");
        System.out.println(TAB + list.taskString() + "\n");
    }

    private static void printRemainingTasks(int remainingTasks) {
        System.out.println("    You have " + remainingTasks + " remaining tasks in the list.\n" + LINE);
    }

    public static String commandIdentifier(String userInput) {
        String commandType;

        if (userInput.equals("bye")) {
            commandType = "BYE";
        } else if (userInput.equals("list")) {
            commandType = "LIST";
        } else if (userInput.startsWith("unmark ")) {
            commandType = "UNMARK";
        } else if (userInput.startsWith("mark ")) {
            commandType = "MARK";
        } else if (userInput.startsWith("todo ")) {
            commandType = "TODO";
        } else if (userInput.startsWith("deadline ")) {
            commandType = "DEADLINE";
        } else if (userInput.startsWith("event ")) {
            commandType = "EVENT";
        } else if (userInput.equals("/help")) {
            commandType = "HELP";
        } else {
            commandType = "INVALID";
        }
        return commandType;
    }

    public static String[] inputParser(String userInput, String commandType) {
        String[] output = new String[3];
        String[] splitInput;
        String[] splitEvent;
        switch (commandType) {
            case "MARK":
                output[0] = String.valueOf(Integer.parseInt(userInput.substring(5)) - 1);
                output[1] = null;
                output[2] = null;
                break;

            case "UNMARK":
                output[0] = String.valueOf(Integer.parseInt(userInput.substring(7)) - 1);
                output[1] = null;
                output[2] = null;
                break;

            case "DEADLINE":
                splitInput = userInput.split(" /by ", 2);
                output[0] = splitInput[0].substring(9);
                output[1] = splitInput[1];
                output[2] = null;
                break;

            case "EVENT":
                splitInput = userInput.split(" /from ", 2);
                splitEvent = splitInput[1].split(" /to ", 2);
                output[0] = splitInput[0].substring(6);
                output[1] = splitEvent[0];
                output[2] = splitEvent[1];
                break;
        }
        return output;
    }
}
