package daisy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = LocalDateTime.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (this.isDone)? "X":" ", this.taskName, this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")));
    }

    public String save() {
        return String.format("D,%s,%s,%s", (this.isDone)? "1":"0", this.taskName, this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

}
