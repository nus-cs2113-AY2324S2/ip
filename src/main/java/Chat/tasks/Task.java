package Chat.tasks;

import Chat.exceptions.RepeatMark;
import Chat.exceptions.RepeatUnmark;

import java.util.Objects;

public class Task {
    protected TaskType type;
    protected String shortType;
    protected String description;
    protected boolean isDone;
    public String time;

    /**
     * Construct a Task object which takes in description of the task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = TaskType.TODO;
        this.time = null;
        shortType = this.type.name().substring(0, 1);
    }

    /**
     * Return the description of the task which is protected.
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Override the default toString method to restructure the format.
     * @return A reformatted task representation, in String.
     */
    @Override
    public String toString() {
        return  " " + getStatusIcon() + " " + getDescription();
    }

    /**
     * Change the status icon according to its isDone status.
     * @return The icon representing the completion of a task.
     * [X] is done, [ ] is not done.
     */

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Return the completion status of a task.
     * @return "1" if isDone, "0" is not done.
     */
    public String numisDone(){
        return (isDone ? "1" : "0");
    }

    /**
     * Mark a task as done.
     * @throws RepeatMark If the task is already marked as done.
     */
    public void markAsDone() throws RepeatMark {
        if(isDone){
            throw new RepeatMark();
        }
        isDone = true;

    }

    /**
     * Mark a task as not done.
     * @throws RepeatUnmark If the task is already marked as not done.
     */

    public void markAsNotDone() throws RepeatUnmark {
        if(!isDone){
            throw new RepeatUnmark();
        }
        isDone = false;
    }

    /**
     * Read the string command from either user or txt file and add to the taskList.
     * @param taskString The string command from either user or txt file.
     * @return The task object from the string.
     * @throws RepeatMark If the task is already marked as done or not done, repeat command gives a warning message.
     */
    public static Task fromString(String taskString) throws RepeatMark {
        String[] parts = taskString.split("\\|");
        String type = parts[0].trim();
        String description = parts[2].trim();
        String time = null;
        String from = null;
        String to = null;
        if(parts[3] != null) {
            time = parts[3].trim();
            if(time.contains("-")) {
                String[] splitTime = time.split("-");
                from = splitTime[0].trim();
                to = splitTime[1].trim();
            }
        }
        Task task = null;
        switch (type) {
        case "T":
            task = new Todos(description);
            if(Objects.equals(parts[1].trim(), "1")){
                task.isDone = true;
            }
            break;
        case "D":
            // Assuming Deadlines constructor takes description and by as parameters
            task = new Deadlines(description, time);
            if(Objects.equals(parts[1].trim(), "1")){
                task.isDone = true;
            }
            break;
        case "E":
            // Assuming Events constructor takes description, from, and to as parameters
            task = new Events(description, from, to);
            if(Objects.equals(parts[1].trim(), "1")){
                task.isDone = true;
            }
            break;
        // Add more cases for other task types if needed
        }
        return task;
    }
}
