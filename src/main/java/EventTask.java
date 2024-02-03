public class EventTask extends Task{
    public String startDate;
    public String endDate;
    EventTask(String description, String startDate, String endDate) {
        this.taskType = "E";
        this.description = description;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
        System.out.println(Humi.LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [E][ ] " + description + "from: " + startDate + "to: " + endDate);
        System.out.println("     Now you have " + (TaskManager.taskCount + 1) + " in the list.");
        System.out.println(Humi.LINE);
    }

    @Override
    public void print() {
        String mark = (isDone) ? "[X] " : "[ ] ";
        System.out.println("[" + this.taskType + "]" + mark + description + "from: " + startDate + "to: " + endDate);
    }
}
