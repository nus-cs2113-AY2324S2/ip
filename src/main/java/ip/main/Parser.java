package ip.main;

public class Parser {
    private boolean hasEnded = false;

    public boolean parseInput(String line, Ui ui, TaskList tasks) {
        if (line.equals("bye")) {
            hasEnded = true;
            return false;
        }
        if (line.equals("list")) {
            tasks.printTaskList();
            return false;
        }
        if (line.startsWith("find")) {
            tasks.find(line);
            return false;
        }
        if (line.startsWith("mark")) {
            return tasks.markTask(line);
        }
        if (line.startsWith("unmark")) {
            return tasks.unmarkTask(line);
        }
        if (line.startsWith("delete")) {
            return tasks.deleteTask(line);
        }
        if (line.startsWith("todo")) {
            return tasks.addTodo(line);
        }
        if (line.startsWith("deadline")) {
            return tasks.addDeadline(line);
        }
        if (line.startsWith("event")) {
            return tasks.addEvent(line);
        }
        ui.print("Possible commands: bye, list, mark, unmark, todo, deadline, event");
        return false;
    }

    public boolean getEnded() {
        return hasEnded;
    }
}
