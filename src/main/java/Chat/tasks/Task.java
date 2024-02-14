package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;

public class Task {
    protected TaskType type;
    protected String shortType;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = TaskType.TODO;
        shortType = this.type.name().substring(0, 1);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return  " " + getStatusIcon() + " " + getDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() throws RepeatMark {
        if(isDone){
            throw new RepeatMark();
        }
        isDone = true;
    }

    public void markAsNotDone() throws RepeatUnmark {
        if(!isDone){
            throw new RepeatUnmark();
        }
        isDone = false;
    }
}
