import java.util.Scanner;

public class Miku {
    static final int TOTAL_LIST_SIZE = 100;
    static final String LINE_BREAK = "______________________";
    static Task[] storedList = new Task[TOTAL_LIST_SIZE];
    static int numberOfListItems = 0;

    public static void newTodo(String newItem) throws MikuException {
        String[] itemString = newItem.split("todo");
        if (itemString.length < 1) {
            throw new MikuException();
        } else {
            storedList[numberOfListItems] = new Todo(itemString[1]);
            storedList[numberOfListItems].description = (itemString[1]);
            storedList[numberOfListItems].printTask(numberOfListItems);
            numberOfListItems++;
        }
    }

    public static void newDeadline(String newItem) throws MikuException {
        String[] itemString = newItem.split("deadline|/by");
        if (itemString.length < 1 || !newItem.contains("/by ")) {
            throw new MikuException();
        } else {
            storedList[numberOfListItems] = new Deadline(itemString[1], itemString[2]);
            storedList[numberOfListItems].description = (itemString[1]);
            storedList[numberOfListItems].printTask(numberOfListItems);
            numberOfListItems++;
        }
    }

    public static void newEvent(String newItem) throws MikuException {
        String[] itemString = getEventArgument(newItem);
        if (itemString.length < 1 || !newItem.contains("/from ") || !newItem.contains("/to ")) {
            throw new MikuException();
        } else {
            storedList[numberOfListItems] = new Event(itemString[1], itemString[2], itemString[3]);
            storedList[numberOfListItems].description = (itemString[1]);
            storedList[numberOfListItems].printTask(numberOfListItems);
            numberOfListItems++;
        }
    }

    private static String[] getEventArgument(String newItem) {
        String[] itemString = newItem.split("event|/from|/to");
        return itemString;
    }

    public static void markTask(Task[] storedList, String[] markedTask) throws MikuException {
        if (markedTask.length > 2 || markedTask.length == 1) {
            throw new MikuException();
        } else {
            int listNumberInt = Integer.parseInt(markedTask[1]);
            storedList[listNumberInt - 1].isDone = (true);
            System.out.println("Good job~! I've marked it as done");
            System.out.print("[" + storedList[listNumberInt - 1].getStatusIcon()
                    + "] " + storedList[listNumberInt - 1].description + "\n");
        }
    }

    public static void unmarkTask(Task[] storedList, String[] markedTask) throws MikuException {
        if (markedTask.length > 2 || markedTask.length == 1) {
            throw new MikuException();
        } else {
            int listNumberInt = Integer.parseInt(markedTask[1]);
            storedList[listNumberInt - 1].isDone = (false);
            System.out.println("Aww... I've marked it as undone.");
            System.out.print("[" + storedList[listNumberInt - 1].getStatusIcon()
                    + "] " + storedList[listNumberInt - 1].description + "\n");
        }
    }

    public static void runProcess(Task[] storedList, String[] splitCommand, String newItem) {

        switch (splitCommand[0]) {
            case "mark": {
                try {
                    markTask(storedList, splitCommand);
                } catch (MikuException e) {
                    System.out.println("You didn't properly input it");
                } catch (NumberFormatException e) {
                    System.out.println("That's not a number!");
                }
                break;
            }
            case "unmark": {
                try {
                    unmarkTask(storedList, splitCommand);
                } catch (MikuException e) {
                    System.out.println("You didn't properly input it");
                } catch (NumberFormatException e) {
                    System.out.println("That's not a number!");
                }
                break;
            }
            case "todo":
                try {
                    newTodo(newItem);
                } catch (MikuException e) {
                    System.out.println("Todo can't be blank!");
                }
                break;
            case "deadline": {
                try {
                    newDeadline(newItem);
                } catch (MikuException e) {
                    System.out.println("Check your deadline arguments!");
                }
                break;
            }
            case "event": {
                try {
                    newEvent(newItem);
                } catch (MikuException e) {
                    System.out.println("Check your event arguments!");
                }
                break;
            }
            default:
                System.out.println("You inputted something wrongly, idk what that means.");
                break;
        }
    }

    public static void printList(Task[] storedList, int numberOfListItems) {
        System.out.println("Here are your list items!");
        for (int i = 0; i < numberOfListItems; i++) {
            System.out.println((i + 1) + ". " + storedList[i].toString());
        }
    }


    public static void main(String[] args) {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println(LINE_BREAK);

        Scanner in = new Scanner(System.in);
        String newItem = in.nextLine();


        while (!newItem.equals("bye")) {
            System.out.println(LINE_BREAK);

            if (newItem.equals("list")) {
                 printList(storedList, numberOfListItems);
            } else {
                String[] splitCommand = newItem.split(" ");
                runProcess(storedList, splitCommand, newItem);
            }

            System.out.println(LINE_BREAK);
            newItem = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }
}

