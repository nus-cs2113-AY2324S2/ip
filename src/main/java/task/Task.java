package task;

/**
 * Abstract class for any type of task that can be implemented.
 * Base form has a name and a check for completion. 
 */
public abstract class Task {

    protected String name;
    protected boolean isCompleted = false;
    public final static String DATA_SEPERATOR = ";";

    /**
     * Creates and arbitrary task object with a placeholder name.
     */
    public Task() {
        name = "Unnamed";
    }

    /**
     * Constructor for a task. Takes in a string and sets it as the name.
     * By default the task's completion is set to false.
     * 
     * @param input Name of the task to be set to.
     */
    public Task(String input) {
        this.name = input;
    }

    /**
     * Returns the name of the task in a string.
     * 
     * @return String containing the name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of a task to a given input string.
     * 
     * @param name String of the name to be set to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks the completion status of a given task.
     * 
     * @return True if completed, false if not.
     */
    public boolean getCompletedStatus() {
        return isCompleted;
    }

    /**
     * Changes the completion status of a given task to true.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Changes the completion status of a given task to false.
     */
    public void markUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Override method for calling the task as a string to print.
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return String.format("%s %s", "[X]", this.name);
        } else {
            return String.format("%s %s", "[ ]", this.name);
        }

    }

    /**
     * Prints out the name of the task in the system console.
     */
    public void printName() {
        System.out.print(this.name);
    }

    /**
     * Default condensed string containing the task's information
     * @return String which concatenates all relevant information of the string.
     */
    public String getDataStorageString() {
        return "t" + DATA_SEPERATOR + this.name + DATA_SEPERATOR + this.isCompleted;
    }
}
