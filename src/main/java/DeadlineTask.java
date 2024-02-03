public class DeadlineTask extends Task {
    public String deadline;
    DeadlineTask(String description, String deadline) {
        this.taskType = "D";
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
        System.out.println(Humi.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [D][ ] " + description + "by: " + deadline);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " in the list.");
        System.out.println(Humi.LINE);
    }

    @Override
    public void print() {
        String mark = (isDone) ? "[X] " : "[ ] ";
        System.out.println("[" + this.taskType + "]" + mark + description + "by: " + this.deadline);
    }
}
