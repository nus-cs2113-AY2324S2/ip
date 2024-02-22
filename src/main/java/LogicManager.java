import exceptions.IllegalNumberOfArguments;
import exceptions.Confusion;
import exceptions.EmptyTaskDescription;
import exceptions.InvalidTaskArguments;
import exceptions.InvalidTaskIndex;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

public class LogicManager {
    private final ListKeeper listKeeper;

    public LogicManager(ListKeeper listKeeper) {
        this.listKeeper = listKeeper;
    }

    private void executeList(String[] words) throws IllegalNumberOfArguments {
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

    private void executeDelete (String[] words)
        throws  IllegalNumberOfArguments, InvalidTaskIndex {
        int taskIndex = getTaskIndex(words);
        this.listKeeper.deleteTask(taskIndex);
    }

    private void executeToDo(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        ToDo todo = ToDo.getTask(currentInput);
        this.listKeeper.addToList(todo);
    }

    private void executeDeadline(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        Deadline deadline = Deadline.getTask(currentInput);
        this.listKeeper.addToList(deadline);
    }

    private void executeEvent(String currentInput)
        throws EmptyTaskDescription, InvalidTaskArguments {
        Event event = Event.getTask(currentInput);
        this.listKeeper.addToList(event);
    }

    private void executeCommand (String currentInput)
        throws IllegalNumberOfArguments, InvalidTaskIndex,
            EmptyTaskDescription, InvalidTaskArguments, Confusion {

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

        case "delete":
            executeDelete(words);
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
        currentInput = currentInput.trim();
        if (currentInput.isEmpty()) {
            return;
        }
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
