import java.util.Scanner;

public class Miku {

    static Task[] storedList = new Task[100];
    static int numberOfListItems = 0;

    public static void newTodo(String newItem) throws MikuException {
        String[] itemString = newItem.split("todo");
        if (itemString.length < 1) {
            throw new MikuException();
        } else {
            storedList[numberOfListItems] = new Todo(itemString[1]);
            storedList[numberOfListItems].description = (itemString[1]);
            System.out.println("Got it! I've added this task:\n" + storedList[numberOfListItems].toString());
            numberOfListItems++;
            System.out.println("Now you have " + numberOfListItems + " tasks in the list!");
        }
    }

    public static void newDeadline(String newItem) throws MikuException {
        String[] itemString = newItem.split("deadline|/by");
        if (itemString.length < 1 || !newItem.contains("/by ")) {
            throw new MikuException();
        } else {
            storedList[numberOfListItems] = new Deadline(itemString[1], itemString[2]);
            storedList[numberOfListItems].description = (itemString[1]);
            System.out.println("Got it! I've added this task:\n" + storedList[numberOfListItems].toString());
            numberOfListItems++;
            System.out.println("Now you have " + numberOfListItems + " tasks in the list!");
        }
    }

    public static void newEvent(String newItem) throws MikuException {
        String[] itemString = newItem.split("event|/from|/to");
        if (itemString.length < 1 || !newItem.contains("/from ") || !newItem.contains("/to ")) {
            throw new MikuException();
        } else {
            storedList[numberOfListItems] = new Event(itemString[1], itemString[2], itemString[3]);
            storedList[numberOfListItems].description = (itemString[1]);
            System.out.println("Got it! I've added this task:\n" + storedList[numberOfListItems].toString());
            numberOfListItems++;
            System.out.println("Now you have " + numberOfListItems + " tasks in the list!");
        }
    }


    public static void main(String[] args) {
        System.out.println("______________________");
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println("______________________");

        Scanner in = new Scanner(System.in);
        String newItem = in.nextLine();


        while (!newItem.equals("bye")) {
            System.out.println("______________________");

            if (newItem.contains("list")) {
                System.out.println("Here are your list items!");
                for (int i = 0; i < numberOfListItems; i++) {
                    System.out.println((i + 1) + ". " + storedList[i].toString());
                }

                System.out.println("______________________");
                newItem = in.nextLine();
                continue;
            }

            if (newItem.contains("mark")) {
                boolean isUnmarking = newItem.contains("unmark");
                String[] markList = newItem.split(" ");
                int listNumberInt = Integer.parseInt(markList[1]);
                storedList[listNumberInt - 1].isDone = (!isUnmarking);

                System.out.println(isUnmarking ? "Aww... I've marked it as undone."
                        : "Good job~! I've marked it as done");
                System.out.println("[" + storedList[listNumberInt - 1].getStatusIcon()
                        + "] " + storedList[listNumberInt - 1].description + "\n");
                System.out.println("______________________");

                newItem = in.nextLine();
                continue;
            }

            String[] splitCommand = newItem.split(" ");

            switch (splitCommand[0]) {
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

            System.out.println("______________________");
            newItem = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________");
    }
}

