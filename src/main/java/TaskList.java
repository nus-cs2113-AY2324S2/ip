import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> storedList;
    public static int numberOfListItems;

    /*
        public TaskList() {
            storedList = new ArrayList<>();
            numberOfListItems = 0;
        }


     */
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

    /**
     * Creates a new todo in the current list
     *
     * @param newItem A string of the entire user input
     * @param storedTask The current list
     * @throws MikuException If the String array < 1
     */
    public static void newTodo(String newItem, ArrayList<Task> storedTask) throws MikuException {
        String[] itemString = getTodoArgument(newItem);
        if (itemString.length < 1) {
            throw new MikuException();
        }

        storedTask.add(new Todo(itemString[1]));
        numberOfListItems++;
    }

    /**
     * Creates a new deadline in the current list
     *
     * @param newItem A string of the entire user input
     * @param storedTask The current list
     * @throws MikuException If the String array < 1
     * @throws wrongDeadlineArguments If the given input is not in the proper format (does not contain "/by")
     */
    public static void newDeadline(String newItem, ArrayList<Task> storedTask)
            throws MikuException, wrongDeadlineArguments {
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

    /**
     * Creates a new event in the current list
     *
     * @param newItem A string of the entire user input
     * @param storedTask The current list
     * @throws MikuException If the String array < 3
     * @throws wrongEventArguments If the given input is not in the proper format (does not contain "/from" or "/to")
     */
    public static void newEvent(String newItem, ArrayList<Task> storedTask)
            throws MikuException, wrongEventArguments {
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

    /**
     * Marks a task from the current list.
     *
     * @param taskToMark An array of Strings that contains the user input of the task to mark
     * @throws MikuException If the String array length > 2 or = 1
     * @throws voidNumberOfItems If the current list is empty
     * @throws indexOutOfListBounds If the inputted index of the task to delete does not exist in the list
     */
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

    /**
     * Unmarks a given task from the current list.
     *
     * @param taskToUnmark An array of Strings that contains the user input of the task to unmark
     * @throws MikuException If the String array length > 2 or = 1
     * @throws voidNumberOfItems If the current list is empty
     * @throws indexOutOfListBounds If the inputted index of the task to delete does not exist in the list
     */
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

    /**
     * Deletes a task from the current list.
     *
     * @param taskToDelete An array of Strings that contains the user input of the task to delete
     * @throws MikuException If the String array length > 2 or = 1
     * @throws voidNumberOfItems If the current list is empty
     * @throws indexOutOfListBounds If the inputted index of the task to delete does not exist in the list
     */
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

    public static void findKeyword(String inputKeyword) throws MikuException, voidNumberOfItems {
        String[] keyword = inputKeyword.split("find");

        if (storedList.isEmpty()) {
            throw new voidNumberOfItems();
        }

        if (keyword.length < 1) {
            throw new MikuException();
        }

        boolean hasMatchingTask = false;
        for (int i = 0; i < storedList.size(); i++) {
            if (storedList.get(i).description.contains(keyword[1])) {
                Ui.printWholeTask(i);
                hasMatchingTask = true;
            }
        }

        if (!hasMatchingTask) {
            Ui.blankFind();
        }
    }

}