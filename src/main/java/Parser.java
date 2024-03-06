/**
 * Responsible for interpreting user input and executing corresponding actions
 * on the TaskList in the Jane task management application.
 */
public class Parser {
    /** The TaskList instance to which the parsed tasks will be added or manipulated. */
    public TaskList taskList;

    /**
     * Constructs a Parser object with the specified TaskList.
     *
     * @param taskList The TaskList to be manipulated by the parser.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Processes a "todo" command and adds a new Todo task to the TaskList.
     *
     * @param input The user's input for the "todo" command.
     * @throws JaneException If the description of the todo task is empty.
     */
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

    /**
     * Processes a "deadline" command and adds a new Deadline task to the TaskList.
     *
     * @param input The user's input for the "deadline" command.
     * @throws JaneException If the description or deadline information is empty.
     */
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

    /**
     * Processes an "event" command and adds a new Event task to the TaskList.
     *
     * @param input The user's input for the "event" command.
     * @throws JaneException If the description or event information is empty.
     */
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

    /**
     * Marks a task as done in the TaskList based on the provided sequence number.
     *
     * @param sequence The sequence number of the task to be marked as done.
     */
    public void markAsDone(int sequence) {
        Task task = this.taskList.list.get(sequence);
        task.setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + task);

    }

    /**
     * Marks a task as undone in the TaskList based on the provided sequence number.
     *
     * @param sequence The sequence number of the task to be marked as undone.
     */
    public void markAsUndone(int sequence) {
        Task task = this.taskList.list.get(sequence);
        task.setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Searches for tasks in the TaskList containing a specific word in their descriptions.
     *
     * @param word The word to be searched for in task descriptions.
     */
    public void findWord(String word) {
        String description;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : this.taskList.list) {
            description = task.getDescription();
            if (description.contains(word)) {
                System.out.println(task);
            }
        }
    }
}