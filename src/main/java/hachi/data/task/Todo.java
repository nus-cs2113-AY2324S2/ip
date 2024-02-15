package hachi.data.task;

public class Todo extends Task {
    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Todos are also considered as tasks.
     *
     * @param name The name of the task to be created.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTaskType () {
        return "T";
    }
}
