package huan.task;

/**
 * Class for a regular todo Task
 */
public class TodoTask extends Task{
    /**
     * Contructor method for TodoTask
     * @param name name of task
     * @param isDone whether the task is marked as finished
     */
    public TodoTask(String name, Boolean isDone) {
        super(name, isDone);
        setTaskType(1);
    }

    /**
     * Method for printing the TodoTask
     */
    @Override
    public void printTask() {
        System.out.println("[T][" + (getIsDone() ? "X" : " ") + "] " + getName());
    }

    /**
     * Method for writing the Task to file
     * @return the line to be written on the file
     */
    @Override
    public String writeLine() {
        return "T" + (getIsDone() ? "T" : "F") + " " + getName();
    }
}
