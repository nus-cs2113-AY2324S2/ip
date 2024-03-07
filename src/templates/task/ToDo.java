package templates.task;

/**
 * Abstract base class and its subclasses representing different types of tasks within the Mario application.
 * Task is the base class providing common properties such as task description and completion status.
 * Event, Deadline, and ToDo extend Task to implement specific behaviors and properties relevant to their respective types,
 * such as start and end dates for Event, a deadline date for Deadline, and basic task properties for ToDo.
 */

 
public class ToDo extends Task {
    public ToDo(String args) throws Exception{
        super(args, "T", "Todo");
    }
}
