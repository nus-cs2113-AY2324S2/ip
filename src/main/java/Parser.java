public class Parser {
    public TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void processTodo(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException("OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(input);
        this.taskList.addTask(todo);
        System.out.println("Got it. I've added this task:\n"
                + todo
                + "\n"
                + "Now you have "
                + this.taskList.getCount()
                + " tasks in the list.");
    }

    public void processDeadline(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] deadlineInput = input.split(" /", 2);
        Deadline deadline = new Deadline(deadlineInput[0],
                deadlineInput[1].replace("/", "").replace("by ", ""));
        this.taskList.addTask(deadline);
        System.out.println("Got it. I've added this task:\n"
                + deadline
                + "\n"
                + "Now you have "
                + this.taskList.getCount()
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
        this.taskList.addTask(event);
        System.out.println("Got it. I've added this task:\n"
                + event
                + "\n"
                + "Now you have "
                + this.taskList.getCount()
                + " tasks in the list.");
    }

    public void markAsDone(int sequence) {
        Task task = this.taskList.list.get(sequence);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + task);

    }

    public void markAsUndone(int sequence) {
        Task task = this.taskList.list.get(sequence);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }
}
