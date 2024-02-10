public class TodoTask extends Task {
    TodoTask(String description) {
        this.taskType = TaskType.TODO;
        this.description = description;
        this.isDone = false;

        System.out.println(Humi.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [T][ ] " + description);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " tasks in the list.");
        System.out.println(Humi.LINE);
    }
}
