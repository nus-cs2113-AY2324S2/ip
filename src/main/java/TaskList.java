import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> list;
    protected int count;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int getCount() {
        return list.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < getCount(); i++) {
            System.out.println(i+1 + "." + this.list.get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    public void markAsDone(int sequence) {
        Task task = this.list.get(sequence);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + task);

    }

    public void markAsUndone(int sequence) {
        Task task = this.list.get(sequence);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void processTodo(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException("OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(input);
        this.addTask(todo);
        System.out.println("Got it. I've added this task:\n"
                + todo
                + "\n"
                + "Now you have "
                + this.getCount()
                + " tasks in the list.");
    }

    public void processDeadline(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineInput = input.split(" /", 2);
        Deadline deadline = new Deadline(deadlineInput[0],
                deadlineInput[1].replace("/", "").replace("by ", ""));
        this.addTask(deadline);
        System.out.println("Got it. I've added this task:\n"
                + deadline
                + "\n"
                + "Now you have "
                + this.getCount()
                + " tasks in the list.");
    }

    public void processEvent(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException("OOPS!!! The description of a event cannot be empty.");
        }
        String[] eventInput = input.split(" /", 3);
        Event event = new Event(eventInput[0],
                eventInput[1].replace("/", "").replace("from ", ""),
                eventInput[2].replace("/", "").replace("to ", ""));
        this.addTask(event);
        System.out.println("Got it. I've added this task:\n"
                + event
                + "\n"
                + "Now you have "
                + this.getCount()
                + " tasks in the list.");
    }

    public void removeTask(int sequence) {
        Task task = this.list.get(sequence);
        this.list.remove(sequence);
        System.out.println("Noted. I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + getCount()
                + " tasks in the list.");
    }
}
