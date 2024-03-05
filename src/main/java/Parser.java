import java.util.Scanner;

public class Parser {
    static String[] splitCommand;
    static String newItem;
    static int numberOfListItems;


    public Parser(String newItem, int numberOfListItems) {
        Parser.newItem = newItem;
        Parser.splitCommand = newItem.split(" ");
        Parser.numberOfListItems = numberOfListItems;
    }

    public static void setParser(String newItem) {
        Parser.newItem = newItem;
        Parser.splitCommand = newItem.split(" ");
        Parser.numberOfListItems = TaskList.numberOfListItems;
    }

    /**
     * Takes in user input and determines what to command to run from the input
     *
     */
    public static void decideAction() {
        Scanner in = new Scanner(System.in);
        newItem = in.nextLine();

        while (!newItem.equalsIgnoreCase("bye")) {
            setParser(newItem);

            if (newItem.equalsIgnoreCase("list")) {
                Ui.printList(numberOfListItems);
            } else if (splitCommand[0].matches("todo|event|deadline")) {
                addTask();
            } else if (splitCommand[0].matches("mark|unmark|delete")) {
                editListStatus();
            } else {
                Ui.invalidInput();
            }

            Storage.saveToFile();
            Ui.lineBreak();
            newItem = in.nextLine();
        }

    }

    /**
     * Handles the processes of adding a new task (todo/event/deadline) to the list
     *
     */
    public static void addTask() {
        switch (splitCommand[0]) {
        case "todo":
            try {
                TaskList.newTodo(newItem, TaskList.storedList);
                Ui.printNewTask(numberOfListItems);
            } catch (MikuException e) {
                Ui.blankTodo();
            }
            break;
        case "deadline":
            try {
                TaskList.newDeadline(newItem, TaskList.storedList);
                Ui.printNewTask(numberOfListItems);
            } catch (MikuException e) {
                Ui.blankDeadline();
            } catch (wrongDeadlineArguments e) {
                Ui.invalidDeadlineArguments();
            }
            break;
        case "event":
            try {
                TaskList.newEvent(newItem, TaskList.storedList);
                Ui.printNewTask(numberOfListItems);
            } catch (MikuException e) {
                Ui.blankEvent();
            } catch (wrongEventArguments e) {
                Ui.invalidEventArguments();
            }
            break;
        default:
            Ui.generalError();
            break;
        }
    }


    /**
     * Handles the processes of "mark", "unmark", and "delete"
     *
     */
    public static void editListStatus() {
        switch (splitCommand[0]) {
        case "mark":
            try {
                TaskList.markTask(splitCommand);
            } catch (MikuException e) {
                Ui.blankMark();
            } catch (NumberFormatException e) {
                Ui.notProperIndex();
            } catch (voidNumberOfItems e) {
                Ui.emptyList();
            } catch (indexOutOfListBounds e) {
                Ui.outOfBoundsIndex();
            }
            break;
        case "unmark":
            try {
                TaskList.unmarkTask(splitCommand);
            } catch (MikuException e) {
                Ui.blankUnark();
            } catch (NumberFormatException e) {
                Ui.notProperIndex();
            } catch (voidNumberOfItems e) {
                Ui.emptyList();
            } catch (indexOutOfListBounds e) {
                Ui.outOfBoundsIndex();
            }
            break;
        case "delete":
            try {
                TaskList.deleteTask(splitCommand);
            } catch (MikuException e) {
                Ui.blankDelete();
            } catch (NumberFormatException e) {
                Ui.notProperIndex();
            } catch (voidNumberOfItems e) {
                Ui.emptyList();
            } catch (indexOutOfListBounds e) {
                Ui.outOfBoundsIndex();
            }
            break;
        default:
            Ui.generalError();
            break;
        }
    }

}
