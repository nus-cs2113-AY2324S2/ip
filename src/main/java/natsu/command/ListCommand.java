package natsu.command;

import natsu.task.Task;

import java.util.ArrayList;

import static natsu.util.Printer.printList;

public class ListCommand {
    public ListCommand(ArrayList<Task> list) {
        printList(list);
    }
}
