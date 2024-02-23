package templates.task;

import templates.BaseDate;

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
