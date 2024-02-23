package bob;

import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    List<Task> list;
    Ui ui;
    public TaskList(List<Task> tasks) {
        list = tasks;
        ui = new Ui();
    }

    public void deleteTask(String line) {
        String content;
        content = line.split(" ", 2)[1];

        displayDeleteTaskMessage(content);

        list.remove(Integer.parseInt(content) - 1);


    }

    private void displayDeleteTaskMessage(String content) {
        ui.displayHorizontalLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        ui.displayHorizontalLine();
    }

    public void unmarkTask(String line) {
        String content;
        content = line.split(" ", 2)[1];
        list.get(Integer.parseInt(content) - 1).setDone(false);

        displayMarkTaskMessage("OK, I've marked this task as not done yet:", content);
    }

    private void displayMarkTaskMessage(String x, String content) {
        ui.displayHorizontalLine();
        System.out.println(x);
        System.out.println(list.get(Integer.parseInt(content) - 1).getListItem());
        ui.displayHorizontalLine();
    }

    public void markTask(String line) {
        String content;
        content = line.split(" ", 2)[1];
        list.get(Integer.parseInt(content) - 1).setDone(true);

        displayMarkTaskMessage("Nice! I've marked this task as done: ", content);
    }

    public void addEvent(String line) throws BobException {
        String content, description, start, by;

        try {
            content = line.split(" ", 2)[1];

            description = content.split(" /from ")[0];
            start = content.split(" /from ")[1].split(" /to ")[0];
            by = content.split(" /to ")[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("An event must have a description, a start time, and an end time.");
        }

        Event e = new Event(description, start, by, false);
        list.add(e);

        displayAddTaskMessage("Got it. I've added this event: ", e);
    }

    public void addDeadline(String line) throws BobException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("A deadline must have a description and a deadline.");
        }

        Deadline d = new Deadline(content.split(" /by ")[0], content.split(" /by ")[1], false);
        list.add(d);

        displayAddTaskMessage("Got it. I've added this deadline: ", d);
    }

    public void addTodo(String line) throws BobException {
        String content;
        try {
            content = line.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BobException("The description of a todo cannot be empty.");
        }

        Task t = new Task(content, false);
        list.add(t);

        displayAddTaskMessage("Got it. I've added this todo: ", t);
    }

    private void displayAddTaskMessage(String x, Task t) {
        ui.displayHorizontalLine();
        System.out.println(x);
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        ui.displayHorizontalLine();
    }

    public void findTask(String line) {
        String content;
        content = line.split(" ", 2)[1];

        List<Task> foundList = list.stream()
                .filter(task -> task.getDescription().contains(content))
                .collect(Collectors.toList());

        ui.displayList(foundList);
    }
}