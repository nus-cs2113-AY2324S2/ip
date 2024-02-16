package kurobot;

public class Task {
    protected String taskType;
    protected boolean isMarked;

    protected boolean isDeleted;
    protected String taskName;
    protected int lineLen = 60;
    protected String lineBreak = "-".repeat(lineLen);

    public Task(String taskType, String taskName) {
        this.taskType = taskType;
        this.isMarked = false;
        this.isDeleted = false;
        this.taskName = taskName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted() {
        this.isDeleted = true;
        System.out.println(lineBreak);
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    public void mark() {
        this.isMarked = true;
        System.out.println(lineBreak);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    public void unmark() {
        this.isMarked = false;
        System.out.println(lineBreak);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    public String markStatus() {
        if (isMarked) {
            return "X";
        } else {
            return " ";
        }
    }

    public String printTask() {
        return "[" + this.taskType+ "][" +markStatus()+ "] " + this.taskName;
    }
}
