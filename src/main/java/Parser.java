public class Parser {

    public static boolean parseAndExecute(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.trim().equalsIgnoreCase("bye")) {
            ui.showGoodbye();
            return true; // Return true to indicate the program should exit
        } else if (userInput.trim().equalsIgnoreCase("list")) {
            ui.showTaskList(tasks.getTasks());
        } else if (userInput.startsWith("todo")) {
            handleToDo(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("deadline")) {
            handleDeadline(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("event")) {
            handleEvent(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("mark")) {
            handleMark(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("unmark")) {
            handleUnmark(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("delete")) {
            handleDelete(userInput, tasks, ui, storage);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return false; // Return false to keep the program running
    }

    private static void handleToDo(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String description = userInput.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task newTask = new Task(description);
        tasks.addTask(newTask);
        ui.showAddedTask(newTask, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }

    private static void handleDeadline(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = userInput.substring("deadline".length()).trim().split("/by");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("The timing for a deadline cannot be empty.");
        }
        Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(newDeadline);
        ui.showAddedTask(newDeadline, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }

    private static void handleEvent(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = userInput.substring("event".length()).trim().split("/at");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("The timing for an event cannot be empty.");
        }
        Event newEvent = new Event(parts[0].trim(), parts[1].trim().split(" ")[0], parts[1].trim().split(" ")[1]);
        tasks.addTask(newEvent);
        ui.showAddedTask(newEvent, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }

    private static void handleMark(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(userInput.substring("mark".length()).trim()) - 1;
        tasks.markTask(index, true);
        ui.showMarkedTask(tasks.getTasks().get(index), true);
        storage.save(tasks.getTasks());
    }

    private static void handleUnmark(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(userInput.substring("unmark".length()).trim()) - 1;
        tasks.markTask(index, false);
        ui.showMarkedTask(tasks.getTasks().get(index), false);
        storage.save(tasks.getTasks());
    }

    private static void handleDelete(String userInput, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(userInput.substring("delete".length()).trim()) - 1;
        Task removedTask = tasks.deleteTask(index);
        ui.showDeletedTask(removedTask, tasks.getTasks().size());
        storage.save(tasks.getTasks());
    }
}
