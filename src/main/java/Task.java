public class Task {
    protected String description;
    protected boolean isDone;

    protected String type; // T or D or E

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static void addTask(String text, Task[] list, int num) {
        // Todo
        if (text.startsWith("todo")) {
            list[num] = new ToDo(text);
            System.out.println("Bala-lala. I've added this task:" + System.lineSeparator() + "[T] " + "[ ] " + list[num].description);
        }
        // Deadline
        else if (text.startsWith("deadline")) {
            list[num] = new Deadline(text);
            System.out.println("Bala-lala. I've added this task:" + System.lineSeparator() + "[D] " + "[ ] " + list[num].description);
        }
        // Event
        else if (text.startsWith("event")) {
            list[num] = new Event(text);
            System.out.println("Bala-lala. I've added this task:" + System.lineSeparator() + "[E] " + "[ ] " + list[num].description);
        }
        // ERROR
        else {
            System.out.println("Oh no, I do not understand.");
        }

    }

}
