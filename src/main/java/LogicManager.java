public class LogicManager {
    private final ListKeeper listKeeper;

    public LogicManager(ListKeeper listKeeper) {
        this.listKeeper = listKeeper;
    }

    private void executeList(String[] words) throws IllegalNumberOfArguments{
        if (words.length != 1) {
            throw new IllegalNumberOfArguments();
        }
        this.listKeeper.printList();
    }

    private int getTaskIndex (String[] words)
        throws IllegalNumberOfArguments, InvalidTaskIndex {
        if (words.length != 2) {
            throw new IllegalNumberOfArguments();
        }
        String taskIndexString = words[1];

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndex();
        }

        if (!this.listKeeper.isValidTaskIndex(taskIndex)) {
            throw new InvalidTaskIndex();
        }

        return taskIndex;
    }

    private void executeMark (String[] words)
        throws IllegalNumberOfArguments, InvalidTaskIndex {
        int taskIndex = getTaskIndex(words);
        boolean isCompleted = words[0].equals("mark");
        this.listKeeper.processMark(taskIndex, isCompleted);
    }

    private void executeToDo(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            // Extract after _todo_, which is 4 characters long
            String taskName = currentInput.substring(4);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }
            this.listKeeper.addToList(new ToDo(taskName));

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }

    private void executeDeadline(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            int idxOfDeadline = currentInput.indexOf(" /by ");
            if (idxOfDeadline == -1) {
                throw new InvalidTaskArguments();
            }

            // Extract after _deadline_, which is 8 characters long
            String taskName = currentInput.substring(8, idxOfDeadline);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            // Extract after _ /by _, which is 5 characters long
            String by = currentInput.substring(idxOfDeadline + 5);
            by = by.trim();

            this.listKeeper.addToList(new Deadline(taskName, by));

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }

    private void executeEvent(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        try {
            int idxOfStart = currentInput.indexOf(" /from ");
            int idxOfEnd = currentInput.indexOf(" /to ");

            if (idxOfStart == -1 || idxOfEnd == -1) {
                throw new InvalidTaskArguments();
            }

            // Extract after _event_, which is 5 characters long
            String taskName = currentInput.substring(5, idxOfStart);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                throw new EmptyTaskDescription();
            }

            // Extract start after _ /from _, which is 7 characters long, and before idxOfEnd
            String start = currentInput.substring(idxOfStart + 7, idxOfEnd);
            start = start.trim();

            // Extract end after _ /to _, which is 5 characters long
            String end = currentInput.substring(idxOfEnd + 5);
            end = end.trim();

            this.listKeeper.addToList(new Event(taskName, start, end));

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskArguments();
        }

    }

    private void executeCommand (String currentInput)
        throws IllegalNumberOfArguments, InvalidTaskIndex,
            EmptyTaskDescription, InvalidTaskArguments, Confusion{

        String[] words = currentInput.split(" ");
        String commandType = words[0];

        switch (commandType) {
        case "list":
            executeList(words);
            break;

        case "mark":
        case "unmark":
            executeMark(words);
            break;

        case "todo":
            executeToDo(currentInput);
            break;

        case "deadline":
            executeDeadline(currentInput);
            break;

        case "event":
            executeEvent(currentInput);
            break;

        default:
            throw new Confusion();
        }
    }

    public void processCommand(String currentInput) {
        try {
            executeCommand(currentInput);
        } catch (IllegalNumberOfArguments e) {
            System.out.println("You provided too few or too many arguments");
        } catch (InvalidTaskIndex e) {
            System.out.println("Task specified does not exist");
        } catch (EmptyTaskDescription e) {
            System.out.println("Task description cannot be empty");
        } catch (InvalidTaskArguments e) {
            System.out.println("Not a valid task");
        } catch (Confusion e) {
            System.out.println("I'm very very confused~~~");
        }
    }
}
