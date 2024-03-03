package humi;

public class Task {
    public String description;
    public Boolean isDone;
    public TaskType taskType;

    Task() {
        this.description = "";
        this.isDone = false;
    }

    public void printTaskType() {
        switch (taskType) {
        case TODO:
            System.out.print("[T]");
            break;
        case DEADLINE:
            System.out.print("[D]");
            break;
        case EVENT:
            System.out.print("[E]");
            break;
        }
    }

    public void printMark() {
        String mark = (isDone) ? "[X] " : "[ ] ";
        System.out.print(mark);
    }

    public void print() {
        printTaskType();
        printMark();
        System.out.println(description);
    }

    public void mark() {
        isDone = true;
        System.out.println(Ui.LINE);
        System.out.print("     Nice! I've marked this task as done:\n     ");
        printTaskType();
        printMark();
        System.out.println(description);
        System.out.println(Ui.LINE);
    }

    public void unmark() {
        isDone = false;
        System.out.println(Ui.LINE);
        System.out.print("     OK, I've marked this task as not done yet:\n     ");
        printTaskType();
        printMark();
        System.out.println(description);
        System.out.println(Ui.LINE);
    }
}
