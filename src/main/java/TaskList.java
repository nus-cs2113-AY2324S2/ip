import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> storedList;
    public static int numberOfListItems;


    public TaskList() {
        storedList = new ArrayList<>();
        numberOfListItems = 0;
    }

    public static void setTaskList(ArrayList<Task> loadList) {
        storedList = loadList;
        numberOfListItems = loadList.size();
    }

    public static void setTaskList() {
        storedList = new ArrayList<>();
        numberOfListItems = 0;
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

    public static void newTodo(String newItem, ArrayList<Task> storedTask) throws MikuException {
        String[] itemString = getTodoArgument(newItem);
        if (itemString.length < 1) {
            throw new MikuException();
        }

        storedTask.add(new Todo(itemString[1]));
        numberOfListItems++;
    }

    public static void newDeadline(String newItem, ArrayList<Task> storedTask) throws MikuException, wrongDeadlineArguments {
        String[] itemString = getDeadlineArgument(newItem);
        if (itemString.length < 1) {
            throw new MikuException();
        }

        if (!newItem.contains("/by ")) {
            throw new wrongDeadlineArguments();
        }

        storedTask.add(new Deadline(itemString[1], itemString[2]));
        numberOfListItems++;
    }

    public static void newEvent(String newItem, ArrayList<Task> storedTask) throws MikuException, wrongEventArguments {
        String[] itemString = getEventArgument(newItem);
        if (itemString.length < 3) {
            throw new MikuException();
        }

        if (!newItem.contains("/from ") || !newItem.contains("/to ")) {
            throw new wrongEventArguments();
        }

        storedTask.add(new Event(itemString[1], itemString[2], itemString[3]));
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
        Ui.printMark(listNumberIndex);
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
        Ui.printUnmark(listNumberIndex);
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
        Ui.printDelete(listNumberIndex);
        storedList.remove(listNumberIndex);
        numberOfListItems--;
        Ui.printNumberOfListItems(numberOfListItems);
    }

}