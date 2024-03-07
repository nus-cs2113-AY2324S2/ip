package templates.task;

import templates.BaseDate;

/**
 * Abstract base class and its subclasses representing different types of tasks within the Mario application.
 * Task is the base class providing common properties such as task description and completion status.
 * Event, Deadline, and ToDo extend Task to implement specific behaviors and properties relevant to their respective types,
 * such as start and end dates for Event, a deadline date for Deadline, and basic task properties for ToDo.
 */

 
public class Deadline extends Task {
    private BaseDate deadlineDateTime = null;
    public static String keyword = "/by";
    public Deadline(String args, BaseDate deadlineDateTime) throws Exception{
        super(args, "D", "Deadline");
        this.deadlineDateTime = deadlineDateTime;
    }

    public void setDeadline(BaseDate newDate){
        assert newDate != null : "Error setting new date for deadline";
        this.deadlineDateTime = newDate;
    }
    
    public BaseDate getDeadlineDateTime(){return this.deadlineDateTime;}
    @Override
    public String toString(){
        return super.toString() + String.format(" (by: %s)", deadlineDateTime.toString());
    }
}
