import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static Ui ui = new Ui();

    private static ArrayList<Task> tasksList = new ArrayList<>();

    public int size() {
        return tasksList.size();
    }

    public Task get(int index) {
        return tasksList.get(index);
    }

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void handleTodoCommand(String command) {
        try {
            if (command.length() <= 5) {
                throw new StringIndexOutOfBoundsException();
            }
            ui.showTaskAdded();
            tasksList.add(new Todo(command.substring(5)));
            System.out.println(tasksList.get(tasksList.size() - 1));

            ui.showTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            ui.handleErrors(e);
        } catch (NumberFormatException e) {
            ui.handleErrors(e);
        }
    }
    public void handleDeadlineCommand(String command) {
        try {
            int dividerPosition = command.indexOf("/by ");
            if (dividerPosition == -1) {
                throw new StringIndexOutOfBoundsException();
            }
            ui.showTaskAdded();
            tasksList.add(new Deadline(command.substring(9, dividerPosition - 1), command.substring(dividerPosition + 4)));
            System.out.println(tasksList.get(tasksList.size() - 1));

            ui.showTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            ui.handleErrors(e);
        } catch (NumberFormatException e) {
            ui.handleErrors(e);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            ui.handleErrors(e);
        }
    }

    public void handleEventCommand(String command) {
        try {
            int from = command.indexOf("/from ");
            int to = command.indexOf("/to ");
            if (from == -1 || to == -1) {
                throw new StringIndexOutOfBoundsException();
            }
            ui.showTaskAdded();
            tasksList.add(new Event(command.substring(6, from - 1), command.substring(from + 6, to - 1), command.substring(to + 4)));
            System.out.println(tasksList.get(tasksList.size() - 1));

            ui.showTaskCountMessage();
        } catch (StringIndexOutOfBoundsException e) {
            ui.handleErrors(e);
        } catch (NumberFormatException e) {
            ui.handleErrors(e);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            ui.handleErrors(e);
        }
    }

    public void printTaskList() {
        ui.showTaskList();

        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(i + 1 + ". " + tasksList.get(i));
        }
        System.out.println(" ");
    }

    public static int extractTaskIndex(String command) {
        String[] parts = command.split(" ");
        return (parts.length >= 2) ? Integer.parseInt(parts[1]) : -1;
    }

    public void markTaskAsDone(String command) {
        try {
            int taskIndex = extractTaskIndex(command);

            if (isValidTaskIndex(taskIndex)) {
                tasksList.get(taskIndex - 1).markAsDone();
                ui.showTaskAsMarked();
                System.out.println(tasksList.get(taskIndex - 1));
            } else {
                ui.showInvalidTaskIndexMessage();
            }
            System.out.println(" ");
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException (e.g., if extractTaskIndex() fails to parse an integer)
            ui.handleErrors(e);
        } catch (IndexOutOfBoundsException e) {
            // Handle the IndexOutOfBoundsException (e.g., if tasksList.get() accesses an invalid index)
            ui.handleErrors(e);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            ui.handleErrors(e);
        }
    }

    public void unmarkTaskAsDone(String command) {
        try {
            int taskIndex = extractTaskIndex(command);

            if (isValidTaskIndex(taskIndex)) {
                tasksList.get(taskIndex - 1).unmarkAsDone();
                ui.showTaskAsUnmarked();
                System.out.println(tasksList.get(taskIndex - 1));
            } else {
                ui.showInvalidTaskIndexMessage();
            }
            System.out.println(" ");
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException (e.g., if extractTaskIndex() fails to parse an integer)
            ui.handleErrors(e);
        } catch (IndexOutOfBoundsException e) {
            // Handle the IndexOutOfBoundsException (e.g., if tasksList.get() accesses an invalid index)
            ui.handleErrors(e);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            ui.handleErrors(e);
        }
    }

    public void addTaskFromSaved(String command, char taskType) {
        try {
            Task task;
            switch (taskType) {
            case 'T':
                task = addTodoFromSaved(command);
                break;
            case 'D':
                task = addDeadlineFromSaved(command);
                break;
            case 'E':
                task = addEventFromSaved(command);
                break;
            default:
                // Handle unknown task type or return if needed
                System.out.println("Unknown task type: " + taskType);
                return;
            }
            if (task != null) {
                tasksList.add(task);
            }

        } catch (Exception e) {
            ui.handleErrors(e);
        }
    }

    public Todo addTodoFromSaved(String description) {
        char status = description.charAt(4);
        String taskDescription = description.substring(7);
        Todo todoTask = new Todo(taskDescription);

        if (status == 'X') {
            todoTask.markAsDone();
        } else {
            todoTask.unmarkAsDone();
        }

        return todoTask;
    }

    public Deadline addDeadlineFromSaved(String description) {
        char status = description.charAt(4);
        int byIndex = description.indexOf("(by:");

        String taskDescription = description.substring(7, byIndex).trim();
        String by = description.substring(byIndex + 4, description.indexOf(")")).trim();
        Deadline deadlineTask = new Deadline(taskDescription, by);

        if (status == 'X') {
            deadlineTask.markAsDone();
        } else {
            deadlineTask.unmarkAsDone();
        }

        return deadlineTask;

    }

    public Event addEventFromSaved(String description) {
        char status = description.charAt(4);
        int fromIndex = description.indexOf("(from:");
        int toIndex = description.indexOf("to:");

        String taskDescription = description.substring(7, fromIndex).trim();
        String from = description.substring(fromIndex + 6, toIndex).trim();
        String to = description.substring(toIndex + 3, description.indexOf(")")).trim();
        Event eventTask = new Event(taskDescription, from, to);

        if (status == 'X') {
            eventTask.markAsDone();
        } else {
            eventTask.unmarkAsDone();
        }

        return eventTask;
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasksList.size();
    }
    public void deleteTask(String command) {
        try {
            int taskIndex = extractTaskIndex(command);

            if (isValidTaskIndex(taskIndex)) {
                ui.showTaskDeleted();
                System.out.println(tasksList.get(taskIndex - 1));
                tasksList.remove(taskIndex - 1);
                ui.showTaskCountMessage();
            } else {
                ui.showInvalidTaskIndexMessage();
            }
            System.out.println(" ");
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException (e.g., if extractTaskIndex() fails to parse an integer)
            ui.handleErrors(e);
        } catch (IndexOutOfBoundsException e) {
            // Handle the IndexOutOfBoundsException (e.g., if tasksList.get() accesses an invalid index)
            ui.handleErrors(e);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            ui.handleErrors(e);
        }
    }


    public void findTask(String command) {
        String keyword;
        try {
            keyword = command.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            ui.handleErrors(e);
            return;
        }
        int foundIndex = 0;
        ui.showFoundTasks();
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).getDescription().contains(keyword)) {
                foundIndex++;
                System.out.println(foundIndex + "." + tasksList.get(i));
            }
        }
        if (foundIndex == 0) {
            System.out.println("OH, no matching tasks found :(");
        }
        System.out.println(" ");
    }
}