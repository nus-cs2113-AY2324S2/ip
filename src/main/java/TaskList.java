import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList() {
        tasks = new ArrayList<>();
        Storage.loadTasksFromFile(tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTodoTask(String description) throws DukeException {
        tasks.add(new Todo(description));
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    public void addDeadlineTask(String description) throws DukeException {
        String[] split = description.split(" /by ");
        if (split.length != 2) {
            throw new DukeException("The description of a deadline task should be followed by '/by <deadline>'.");
        }
        tasks.add(new Deadline(split[0], split[1]));
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    public void addEventTask(String description) throws DukeException {
        String[] split = description.split(" /from | /to ");
        if (split.length != 3) {
            throw new DukeException("The description of an event task should be followed by '/from <start> /to <end>'.");
        }
        tasks.add(new Event(split[0], split[1], split[2]));
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    public void displayTasks() throws DukeException {
        if (tasks.isEmpty()) {
            ui.showLine();
            System.out.println("Ehhh, you got no tasks to do leh. Try adding some! :(");
            ui.showLine();
            return;
        }
        ui.showLine();
        System.out.println("LaiLaiLai, Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        ui.showLine();
    }

    public void markTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsDone();
                ui.showLine();
                System.out.println("Nice one lah! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
                ui.showLine();
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    public void unmarkTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsNotDone();
                ui.showLine();
                System.out.println("OK can, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
                ui.showLine();
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    public void deleteTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                ui.showLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + tasks.get(taskIndex));
                tasks.remove(taskIndex);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                Storage.saveTasksToFile(tasks);
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showLine();
            System.out.println("No tasks found matching the keyword.");
            ui.showLine();
        } else {
            ui.showLine();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
            ui.showLine();
        }
    }
}
