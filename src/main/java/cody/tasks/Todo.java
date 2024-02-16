package cody.tasks;

import cody.tasks.Task;

public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

}
