package duke;

public class Command {
    private static TaskList taskList = new TaskList();
    private static UnparsedTaskList inputList = new UnparsedTaskList();

    /**
     * Prints out all items in the task list
     */
    public static void list() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i+1)+"."+taskList.get(i));
        }
    }

    /**
     * Tries to add a task to the task list.
     * If a proper command is not given asks user to provide a proper input
     * Also adds the raw command into the inputlist to be saved on their device
     *
     * @param input Input provided by the user
     */
    public static void tryAddTask(String input) {
        try {
            taskList.add(addTask(input));
            inputList.add(input);
            System.out.println("added: " + taskList.get(taskList.size() - 1));
        } catch (PythiaException pe) {
            System.out.println("Please provide a proper input");
        }
    }

    /**
     * Adds a task without the 'added ...' message appearing
     * Used only on startup when the locally saved list is retrieved
     *
     * @param input Input from the savedlist.txt file
     */
    public static void directAddTask(String input) {
        try {
            taskList.add(addTask(input));
            inputList.add(input);
        } catch (PythiaException pe) {
            System.out.println("Please provide a proper input");
        }
    }

    /**
     * Parses the input string and returns the appropriate task type
     *
     * @param task The user input the contains the task command
     * @return A Task type variable
     * @throws PythiaException Thrown if the required number of fields are not present
     */
    public static Task addTask(String task) throws PythiaException{
        Parser taskParser = new Parser();
        String[] parsedTask;
        if (task.contains("todo ")) {
            task = task.replaceFirst("todo", "");
            if (task.isBlank()) {
                throw new PythiaException();
            }
            return new Todo(task);
        } else if (task.contains("deadline ")) {
            parsedTask = taskParser.parseDeadline(task);
            if (parsedTask.length < 2) {
                throw new PythiaException();
            }
            return new Deadline(parsedTask[0], parsedTask[1]);
        } else if (task.contains("event ")){
            parsedTask = taskParser.parseEvent(task);
            if (parsedTask.length < 3) {
                throw new PythiaException();
            }
            return  new Event(parsedTask[0], parsedTask[1], parsedTask[2]);
        } else {
            throw new PythiaException();
        }
    }

    /**
     * Updates the Done status of a task to be false
     *
     * @param input The user input that contains the unmark command
     */
    public static void unmark(String input) {
        String[] splitInput = input.split(" ");
        taskList.get(Integer.parseInt(splitInput[1])-1).doneIsFalse();
        System.out.println("Unmarked "+ Integer.parseInt(splitInput[1]));
    }

    /**
     * Updates the Done status of a task to be true
     *
     * @param input The user input that contains the mark command
     */
    public static void mark(String input) {
        String[] splitInput = input.split(" ");
        taskList.get(Integer.parseInt(splitInput[1])-1).doneIsTrue();
        System.out.println("Marked "+ Integer.parseInt(splitInput[1]));
    }

    /**
     * Deletes a specific task from both the taskList and the inputList
     *
     * @param input The user input that contains the index of the item they want to delete
     */
    public static void delete(String input) {
        String[] splitInput = input.split(" ");
        int indexToRemove = Integer.parseInt(splitInput[1])-1;
        System.out.println("Deleted " + taskList.get(Integer.parseInt(splitInput[1])-1));
        taskList.remove(taskList.get(indexToRemove));
        inputList.remove(inputList.get(indexToRemove));
    }

    /**
     * Finds and prints all tasks that contain the keyword specified by the user
     *
     * @param input User input that contains the keyword they would like to find
     */
    public static void find(String input) {
        boolean isFound = false;
        String[] splitInput = input.split(" ");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(splitInput[1])) {
                isFound = true;
                System.out.println(taskList.get(i));
            }
        }
        if (!isFound) {
            System.out.println("No such keyword exists");
        }
    }
}
