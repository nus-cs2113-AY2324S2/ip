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

    public static void addTask(String text, Task[] tasks, int num) throws InvalidTodoSyntaxException {
        // Todo
        if (text.startsWith("todo")) {
            if (text.length() < "todo".length() + 2) {
                throw new InvalidTodoSyntaxException();
            } else {
                tasks[num] = new ToDo(text);
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[T] " + "[ ] " + tasks[num].description);
            }
        }

        // Deadline
        else if (text.startsWith("deadline")) {
            if (text.length() < "deadline".length() + 2) {
                System.out.println("OH NOOO! Please enter the deadline task name.");
            } else {
                tasks[num] = new Deadline(text);
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[D] " + "[ ] " + tasks[num].description);
            }
        }
        // Event
        else if (text.startsWith("event")) {
            if (text.length() < "event".length() + 2) {
                System.out.println("OH NOOO! Please enter the event task name.");
            } else {
                tasks[num] = new Event(text);
                System.out.println("Bala-lala. I've added this task:" + System.lineSeparator()
                        + "[E] " + "[ ] " + tasks[num].description);
            }
        }
        // ERROR
        else {
            System.out.println("Oh no, I do not understand.");
        }

    }

}
