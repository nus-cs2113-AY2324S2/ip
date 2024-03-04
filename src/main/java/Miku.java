import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Miku {
    static final String LINE_BREAK = "______________________";
    static final String FILE_PATH = "./data/miku.txt";
    static ArrayList<Task> storedList = new ArrayList<>();
    static int numberOfListItems = 0;

    public static void writeToFile(String filePath, String textToAdd) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter f = new FileWriter(filePath, false);
            f.write(textToAdd);
            f.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public static void appendToFile(String filePath, String textToAppend) {
        try {
            FileWriter f = new FileWriter(filePath, true);
            f.write(textToAppend);
            f.close();
        } catch (IOException e) {
            System.out.println("file is not found!");
        }
    }

    public static void loadData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
            String[] loadedTask = s.nextLine().split("\\|");
            transferLoadToArray(loadedTask);
        }
    }

    public static void transferLoadToArray(String[] loadedTask) {
        boolean isMarked = (loadedTask[1].equals("1"));
        String loadedTaskDescription;
        for (int i = 0; i < loadedTask.length; i ++) {
            System.out.println(loadedTask[i]);
        }

        switch (loadedTask[0]) {
        case "T":
            loadedTaskDescription = (loadedTask[2]);
            try {
                newTodo("todo " + loadedTaskDescription);
                storedList.get(numberOfListItems - 1).isDone = isMarked;
            } catch (MikuException e) {
                System.out.println("error in loading todo!");
            }
            break;
        case "D":
            loadedTaskDescription = ("deadline " + loadedTask[2] + "/by " + loadedTask[3]);
            try {
                newDeadline(loadedTaskDescription);
                storedList.get(numberOfListItems - 1).isDone = isMarked;
            } catch (MikuException e) {
                System.out.println("error in loading deadline!");
            } catch (wrongDeadlineArguments e) {
                System.out.println("there's something wrong with the loaded deadline's arguments");
            }
            break;
        case "E":
            loadedTaskDescription = ("event " + loadedTask[2] + "/from " + loadedTask[3] + "/to " + loadedTask[4]);
            try {
                newEvent(loadedTaskDescription);
                storedList.get(numberOfListItems - 1).isDone = isMarked;
            } catch (MikuException e) {
                System.out.println("error in loading event!");
            } catch (wrongEventArguments e) {
                System.out.println("there's something wrong with the loaded event's arguments");
            }
            break;
        default:
            System.out.println("there was an error loading the data.");
            break;
        }
    }

    private static String[] getEventArgument(String newItem) {
        return newItem.split("event|/from|/to");
    }

    private static String[] getDeadlineArgument(String newItem) {
        return newItem.split("deadline|/by");
    }

    private static String[] getTodoArgument(String newItem) {
        return newItem.split("todo");
    }

    public static void newTodo(String newItem) throws MikuException {
        String[] itemString = getTodoArgument(newItem);
        if (itemString.length < 1) {
            throw new MikuException();
        }

        storedList.add(new Todo(itemString[1]));
        storedList.get(numberOfListItems).printTask(numberOfListItems);
        numberOfListItems++;
    }

    public static void newDeadline(String newItem) throws MikuException, wrongDeadlineArguments {
        String[] itemString = getDeadlineArgument(newItem);
        if (itemString.length < 1) {
            throw new MikuException();
        }

        if (!newItem.contains("/by ")) {
            throw new wrongDeadlineArguments();
        }

        storedList.add(new Deadline(itemString[1], itemString[2]));
        storedList.get(numberOfListItems).printTask(numberOfListItems);
        numberOfListItems++;
    }

    public static void newEvent(String newItem) throws MikuException, wrongEventArguments {
        String[] itemString = getEventArgument(newItem);
        if (itemString.length < 3) {
            throw new MikuException();
        }

        if (!newItem.contains("/from ") || !newItem.contains("/to ")) {
            throw new wrongEventArguments();
        }

        storedList.add(new Event(itemString[1], itemString[2], itemString[3]));
        storedList.get(numberOfListItems).printTask(numberOfListItems);
        numberOfListItems++;
    }


    public static void markTask(String[] taskToMark)
            throws MikuException, voidNumberOfItems, indexOutOfListBounds {
        if (taskToMark.length > 2 || taskToMark.length == 1) {
            throw new MikuException();
        }

        if (storedList.isEmpty()) {
            throw new voidNumberOfItems();
        }

        int inputtedIndex = Integer.parseInt(taskToMark[1]);
        if (inputtedIndex <= 0 || inputtedIndex > storedList.size()) {
            throw new indexOutOfListBounds();
        }

        int listNumberIndex = (inputtedIndex - 1);
        storedList.get(listNumberIndex).isDone = true;
        System.out.println("Good job~! I've marked it as done");
        System.out.println("[" + storedList.get(listNumberIndex).getStatusIcon()
                + "] " + storedList.get(listNumberIndex).description);
    }


    public static void unmarkTask(String[] taskToUnmark)
            throws MikuException, voidNumberOfItems, indexOutOfListBounds {
        if (taskToUnmark.length > 2 || taskToUnmark.length == 1) {
            throw new MikuException();
        }

        if (storedList.isEmpty()) {
            throw new voidNumberOfItems();
        }

        int inputtedIndex = Integer.parseInt(taskToUnmark[1]);
        if (inputtedIndex <= 0 || inputtedIndex > storedList.size()) {
            throw new indexOutOfListBounds();
        }

        int listNumberIndex = (inputtedIndex - 1);
        storedList.get(listNumberIndex).isDone = false;
        System.out.println("Aww... I've marked it as undone.");
        System.out.println("[" + storedList.get(listNumberIndex).getStatusIcon()
                + "] " + storedList.get(listNumberIndex).description);
    }

    public static void deleteTask(String[] taskToDelete)
            throws MikuException, voidNumberOfItems, indexOutOfListBounds {
        if (taskToDelete.length > 2 || taskToDelete.length == 1) {
            throw new MikuException();
        }

        if (storedList.isEmpty()) {
            throw new voidNumberOfItems();
        }

        int inputtedIndex = Integer.parseInt(taskToDelete[1]);
        if (inputtedIndex <= 0 || inputtedIndex > storedList.size()) {
            throw new indexOutOfListBounds();
        }

        int listNumberIndex = (inputtedIndex - 1);
        System.out.println("Okay, I'm deleting this task:");
        System.out.println("[" + storedList.get(listNumberIndex).getStatusIcon()
                + "] " + storedList.get(listNumberIndex).description);
        storedList.remove(listNumberIndex);
        numberOfListItems--;
        System.out.println("Now you have " + numberOfListItems + " items in your list!");

    }

    public static void addTask(String[] splitCommand, String newItem) {
        switch (splitCommand[0]) {
        case "todo":
            try {
                newTodo(newItem);
            } catch (MikuException e) {
                System.out.println("Todo can't be blank!");
            }
            break;
        case "deadline":
            try {
                newDeadline(newItem);
            } catch (MikuException e) {
                System.out.println("Did you forget to type something?");
            } catch (wrongDeadlineArguments e) {
                System.out.println("You didn't add when it's due by D:");
            }
            break;
        case "event":
            try {
                newEvent(newItem);
            } catch (MikuException e) {
                System.out.println("Did you forget to type something?");
            } catch (wrongEventArguments e) {
                System.out.println("You didn't correctly input the arguments D: ");
            }
            break;
        default:
            System.out.println("You inputted something wrongly, I don't know what that means (ㅠ﹏ㅠ)");
            break;
        }
    }

    public static void editListStatus(String[] splitCommand) {
        switch (splitCommand[0]) {
        case "mark":
            try {
                markTask(splitCommand);
            } catch (MikuException e) {
                System.out.println("You didn't properly input it");
            } catch (NumberFormatException e) {
                System.out.println("Enter the index of what you want to mark!");
            } catch (voidNumberOfItems e) {
                System.out.println("There are no items in your list to mark :(");
            } catch (indexOutOfListBounds e) {
                System.out.println("Error! Try to input an index that exists in your current list instead.");
            }
            break;
        case "unmark":
            try {
                unmarkTask(splitCommand);
            } catch (MikuException e) {
                System.out.println("You didn't properly input it");
            } catch (NumberFormatException e) {
                System.out.println("Enter the index of what you want to unmark!");
            } catch (voidNumberOfItems e) {
                System.out.println("There are no items in your list to unmark :(");
            } catch (indexOutOfListBounds e) {
                System.out.println("Error! Try to input an index that exists in your current list instead.");
            }
            break;
        case "delete":
            try {
                deleteTask(splitCommand);
            } catch (MikuException e) {
                System.out.println("Check your delete arguments");
            } catch (NumberFormatException e) {
                System.out.println("Enter the index of what you want to delete.");
            } catch (voidNumberOfItems e) {
                System.out.println("There are no items in your list to delete :(");
            } catch (indexOutOfListBounds e) {
                System.out.println("Error! Try to input an index that exists in your current list instead.");
            }
            break;
        default:
            System.out.println("You inputted something wrongly, I don't know what that means (ㅠ﹏ㅠ)");
            break;
        }

    }

    public static void printList(int numberOfListItems) {
        System.out.println("Here are your list items!");
        for (int i = 0; i < numberOfListItems; i++) {
            System.out.println((i + 1) + ". " + storedList.get(i).toString());
        }
    }


    public static void main(String[] args) {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println(LINE_BREAK);

        try {
            loadData(FILE_PATH);
        } catch (FileNotFoundException ignored) {
        }

        Scanner in = new Scanner(System.in);
        String newItem = in.nextLine();


        while (!newItem.equals("bye")) {
            System.out.println(LINE_BREAK);
            String[] splitCommand = newItem.split(" ");

            if (newItem.equals("list")) {
                printList(numberOfListItems);
            } else if (splitCommand[0].matches("todo|event|deadline")) {
                addTask(splitCommand, newItem);
            } else if (splitCommand[0].matches("mark|unmark|delete")) {
                editListStatus(splitCommand);
            } else {
                System.out.println("You typed something wrongly, I don't know what that means (ㅠ﹏ㅠ)");
            }

            if (numberOfListItems > 0) {
                writeToFile(FILE_PATH, storedList.get(0).saveTaskDescription() + System.lineSeparator());
                for (int i = 1; i < numberOfListItems; i++) {
                    appendToFile(FILE_PATH, storedList.get(i).saveTaskDescription() + System.lineSeparator());
                }
            }

            System.out.println(LINE_BREAK);
            newItem = in.nextLine();
        }

        System.out.println("Bye, hope to see you again soon! :D");
        System.out.println(LINE_BREAK);
    }
}

