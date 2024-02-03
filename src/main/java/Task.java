public class Task {
    public String description;
    public Boolean isDone;
    public String taskType;

    Task() {
        this.description = "";
        this.isDone = false;
    }
    Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println(Humi.LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("[ ][ ]" + description);
        System.out.println(Humi.LINE);
    }

    public void print() {
        String mark = (isDone) ? "[X] " : "[ ] ";
        System.out.println("[" + this.taskType + "]" + mark + description);
    }

    public void mark() {
        isDone = true;
        System.out.println(Humi.LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     [" + this.taskType + "]" + "[X] " + description);
        System.out.println(Humi.LINE);
    }

    public void unmark() {
        isDone = false;
        System.out.println(Humi.LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("     [" + this.taskType + "]" + "[ ] " + description);
        System.out.println(Humi.LINE);
    }
}
