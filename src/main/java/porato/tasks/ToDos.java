package porato.tasks;

/**
 * Represents the todo tasks.
 * It inherits from the Task class
 */
public class ToDos extends Task{
    public ToDos(String line){
        super(line);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
