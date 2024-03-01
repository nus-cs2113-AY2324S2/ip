import java.util.ArrayList;


/**
 * Defines a class that handles high level processing of the user's commands.
 */
public class Logic {
    private final TaskList list;
    private final Storage saveInstance;
    public Logic(TaskList currentList, Storage storageInstance) {
        this.list = currentList;
        this.saveInstance = storageInstance;
    }


    /**
     * Parses the user input for an index then marks that index on the list with an X.
     * Prints out list and then uploads it to the storage.
     *
     * @param userInput Raw input of the user to be parsed by this method.
     */
    public void markLogic(String userInput){
        int taskIndex = Parser.getFirstInt(userInput);
        list.markIndex(taskIndex);
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    /**
     * Parses the user input for an index then unmarks that index on the list.
     * Prints out list and then uploads it to the storage.
     *
     * @param userInput Raw input of the user to be parsed by this method.
     */
    public void unmarkLogic(String userInput){
        int taskIndex = Parser.getFirstInt(userInput);
        list.unmarkIndex(taskIndex);
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    /**
     * Parses the user input for a description and a time and adds a deadline element to the list.
     * Saves the item to the storage as well.
     *
     * @param userInput Raw input of the user to be parsed by this method.
     */
    public void deadlineLogic(String userInput){
        String description = Parser.extractDescription(userInput);
        String deadLine = Parser.extractStartTime(userInput);
        list.insertTask(new Deadline(description, deadLine, false));
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    /**
     * Parses the user input for a description and 2 times and adds an event element to the list.
     * Saves the item to the storage as well.
     *
     * @param userInput Raw input of the user to be parsed by this method.
     */
    public void eventLogic(String userInput){
        String description = Parser.extractDescription(userInput);
        String start = Parser.extractStartTime(userInput);
        String end = Parser.extractEndTime(userInput);
        list.insertTask(new Event(description, start, end, false));
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }

    /**
     * Parses the user input for a description and adds a to-do element to the list.
     * Saves the item to the storage as well.
     *
     * @param userInput Raw input of the user to be parsed by this method.
     */
    public void todoLogic(String userInput){
        list.insertTask(new Todo(Parser.extractDescription(userInput), false));
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }


    /**
     * Removes a specific element in the list based on a provided index.
     * Saves the updated list to the storage as well.
     *
     * @param userInput Raw input of the user containing an index to be parsed by the method.
     */
    public void removeLogic(String userInput){
        int taskIndex = Parser.getFirstInt(userInput);
        list.deleteIndex(taskIndex);
        saveInstance.setSavedList(list.getTasks());
        saveInstance.uploadTasks();
    }

    public void findLogic(String userInput) {
        String keyword = Parser.extractDescription(userInput);
        list.findKeyword(keyword);
    }
}
