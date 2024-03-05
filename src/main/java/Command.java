public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return false;
    }
}

class EventCommand extends Command {
    private String content;

    public EventCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = content.split("/from | /to ");
        if (parts.length < 3) {
            throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + newEvent);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number!");
        }
        Task deletedTask = tasks.getTasks().get(taskNumber - 1);
        tasks.deleteTask(taskNumber - 1);
        ui.showLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + deletedTask);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

class DeadlineCommand extends Command {
    private final String content;

    public DeadlineCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = content.indexOf("/by");
        if (index != -1) {
            String description = content.substring(0, index).trim();
            String by = content.substring(index + 4).trim();
            Deadline newDeadline = new Deadline(description, by);
            tasks.addTask(newDeadline);
            ui.showLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + newDeadline);
            System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        }
        else{
            throw new DukeException("Please use '/by' to specify the deadline time.");
        }
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showWelcome();
        int i = 0;
        while (i < tasks.getTasks().size()) {
            Task task = tasks.getTasks().get(i);
            ui.showLine();
            System.out.println((i + 1) + "." + task);
            i++;
        }
    }
}

class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number!");
        }
        tasks.getTasks().get(taskNumber - 1).markAsDone();
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" [" + tasks.getTasks().get(taskNumber - 1).getStatusIcon() + "] " + tasks.getTasks().get(taskNumber - 1).description);
    }
}

class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPSSSSSSS!!! The task's description is empty! Please do specify!");
        }
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + newTodo);
        System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}

class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.getTasks().size()) {
            throw new DukeException("Invalid task number!");
        }
        tasks.getTasks().get(taskNumber - 1).unmarkAsDone();
        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" [" + tasks.getTasks().get(taskNumber - 1).getStatusIcon() + "] " + tasks.getTasks().get(taskNumber - 1).description);
    }
}
