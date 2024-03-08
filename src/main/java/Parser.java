/**
 * Responsible for interpreting user input and executing corresponding actions
 * on the TaskList in the Jane task management application.
 */
public class Parser {
    /** The TaskList instance to which the parsed tasks will be added or manipulated. */
    public TaskList taskList;

    /** Expected length of Deadline Task. One description string and one task deadline. */
    public static final int EXPECTED_DEADLINE_LENGTH = 2;

    /** Expected length of Event Task. One description string, one task start date/time, and one task end date/time */
    public static final int EXPECTED_EVENT_LENGTH = 3;

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
            throw new JaneException(Message.TODO_EMPTY_DESCRIPTION_ERROR);
        }
        Todo todo = new Todo(input);
        this.taskList.addTask(todo);
        System.out.println(Message.TASK_ADDED + todo);
        System.out.println(Message.numberOfTasks(this.taskList.getCount()));
    }

    /**
     * Processes a "deadline" command and adds a new Deadline task to the TaskList.
     *
     * @param input The user's input for the "deadline" command.
     * @throws JaneException If the description or deadline information is empty.
     */
    public void processDeadline(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException(Message.DEADLINE_EMPTY_DESCRIPTION_ERROR);
        }
        String[] deadlineInput = input.split(" /", 2);
        if (deadlineInput.length < EXPECTED_DEADLINE_LENGTH) {
            throw new JaneException(Message.DEADLINE_MISSING_DUE);
        }
        Deadline deadline = new Deadline(deadlineInput[0],
                deadlineInput[1].replace("/", "").replace("by ", ""));
        this.taskList.addTask(deadline);
        System.out.println(Message.TASK_ADDED + deadline);
        System.out.println(Message.numberOfTasks(this.taskList.getCount()));
    }

    /**
     * Processes an "event" command and adds a new Event task to the TaskList.
     *
     * @param input The user's input for the "event" command.
     * @throws JaneException If the description or event information is empty.
     */
    public void processEvent(String input) throws JaneException {
        if (input.isEmpty()) {
            throw new JaneException(Message.EVENT_EMPTY_DESCRIPTION_ERROR);
        }
        String[] eventInput = input.split(" /", 3);
        if (eventInput.length < EXPECTED_EVENT_LENGTH) {
            throw new JaneException(Message.EVENT_MISSING_START_END);
        }
        Event event = new Event(eventInput[0],
                eventInput[1].replace("/", "").replace("from ", ""),
                eventInput[2].replace("/", "").replace("to ", ""));
        this.taskList.addTask(event);
        System.out.println(Message.TASK_ADDED + event);
        System.out.println(Message.numberOfTasks(this.taskList.getCount()));
    }

    /**
     * Marks a task as done in the TaskList based on the provided sequence number.
     *
     * @param input The sequence number of the task to be marked as done in String.
     * @throws JaneException If the input cannot be converted to an Integer or sequence number out of bounds.
     */
    public void markAsDone(String input) throws JaneException {
        try {
            int sequence = Integer.parseInt(input) - 1;
            Task task = this.taskList.list.get(sequence);
            task.setDone(true);
            System.out.println(Message.MARK_AS_DONE + task);
        } catch (NumberFormatException e) {
            throw new JaneException(Message.INTEGER_NUMBER_REQUIRED);
        } catch (IndexOutOfBoundsException e) {
        throw new JaneException(Message.INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Marks a task as undone in the TaskList based on the provided sequence number.
     *
     * @param input The sequence number of the task to be marked as undone in String.
     * @throws JaneException If the input cannot be converted to an Integer or sequence number out of bounds.
     */
    public void markAsUndone(String input) throws JaneException {
        try {
            int sequence = Integer.parseInt(input) - 1;
            Task task = this.taskList.list.get(sequence);
            task.setDone(false);
            System.out.println(Message.MARK_AS_UNDONE + task);
        } catch (NumberFormatException e) {
            throw new JaneException(Message.INTEGER_NUMBER_REQUIRED);
        } catch (IndexOutOfBoundsException e) {
            throw new JaneException(Message.INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Searches for tasks in the TaskList containing a specific word in their descriptions.
     *
     * @param word The word to be searched for in task descriptions.
     */
    public void findWord(String word) {
        String description;
        System.out.println(Message.MATCHING_LIST_HEADER);
        for (Task task : this.taskList.list) {
            description = task.getDescription();
            if (description.contains(word)) {
                System.out.println(task);
            }
        }
    }
}