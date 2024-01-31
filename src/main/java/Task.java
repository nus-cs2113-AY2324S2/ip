public class Task {
    public static int taskCount;
    public String description;
    public Boolean isDone;

    Task() {
        taskCount = 0;
    }
    Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
        System.out.println(Humi.LINE);
        System.out.println("added: " + description);
        System.out.println(Humi.LINE);
    }

    public void print() {
        String mark = (isDone) ? "[X] " : "[ ] ";
        System.out.println(mark + description);
    }

    public void mark() {
        isDone = true;
        System.out.println(Humi.LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + description);
        System.out.println(Humi.LINE);
    }

    public void unmark() {
        isDone = false;
        System.out.println(Humi.LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + description);
        System.out.println(Humi.LINE);
    }
}
