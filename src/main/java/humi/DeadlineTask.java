package humi;

public class DeadlineTask extends Task {
    public String deadline;
    DeadlineTask(String description, String deadline) {
        this.taskType = TaskType.DEADLINE;
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;

        System.out.println(Humi.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [D][ ] " + description + "by: " + deadline);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " tasks in the list.");
        System.out.println(Humi.LINE);
    }

    @Override
    public void print() {
        printTaskType();
        printMark();
        System.out.println(description + "by: " + this.deadline);
    }
}
