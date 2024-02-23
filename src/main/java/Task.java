public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getTask() {
        String taskType;
        if (this.getClass().toString().equals("class Todo")) {
            taskType = "[T]";
        } else if (this.getClass().toString().equals("class Deadline")) {
            taskType = "[D]";
        } else {
            taskType = "[E]";
        }
        this.taskType = taskType;
        return (taskType + "[" + (isDone ? "X" : " ") + "]" + this.description);
    }

    public String getTime() {
        String output = "";
        try {
        if (this.taskType.equals("[D]")) {
            String initial = this.getTask().split("by:")[1];
            output = initial.split("\\)")[0];
        } else if (this.taskType.equals("[E]")) {
            String initialFrom = (this.getTask().split("from:")[1]).split("to:")[0];
            String initialTo = (this.getTask().split("to:")[1]).split("\\)")[0];
            output = initialFrom.trim() + " to: " + initialTo.trim();
        }
        } catch (Exception e) {
            System.out.println("Error loading data:" + e.getMessage());
        }
        return output;
    }

}
