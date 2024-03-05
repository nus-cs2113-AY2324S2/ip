package task;

/**
 * Represents a Todo in Kapwa
 * 
 * @see Task
 * 
 * @author yyangda
 * @version 0.1
 * @since 2024-03-03
 * 
 */

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
    }
        

    @Override
    public String toString() {
        String isDoneIcon = super.isDone ? "X" : " ";
        return "[" + type + "][" + isDoneIcon + "] " + super.description;
    }
}
