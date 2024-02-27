package mimi.classes;
import static mimi.helper.Storage.FILE_DELIMITER;

/**
 * This class represents a ToDo task, which is a task without a specific date or time.
 * It inherits from the Task class and overrides the toString and toFileString method.
 */
public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    /**
     * This method overrides the toString method in the Task class.
     * It returns a string representation of the ToDo task.
     * e.g. [T][✘] return book
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString(){
        return "[T]" + super.toString() ;
    }
    /**
     * This method overrides the toFileString method in the Task class.
     * It returns a string representation of the ToDo task to be written to the file.
     * e.g. T|false|return book
     * @return a string representation of the ToDo task to be written to the file
     */
    @Override
    public String toFileString(){
        return "T" + FILE_DELIMITER + super.toFileString();
    }
}
