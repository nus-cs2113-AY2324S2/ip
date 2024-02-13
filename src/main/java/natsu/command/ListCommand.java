package natsu.command;

import natsu.task.Task;
import static natsu.util.Printer.printList;

public class ListCommand {
    public ListCommand(Task[] list, int taskCount) {
        printList(list, taskCount);
    }
}
