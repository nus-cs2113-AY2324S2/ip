package humi;

/**
 * Represents the todo task containing task type, mark, and description
 */
public class TodoTask extends Task {
    TodoTask(String description) {
        this.taskType = TaskType.TODO;
        this.description = description;
        this.isDone = false;
        Ui.printAddTodo(description);
    }

    TodoTask(String description, boolean isDone) {
        this.taskType = TaskType.TODO;
        this.description = description;
        this.isDone = isDone;
    }
}
