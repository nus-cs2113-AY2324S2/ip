package humi;

public class TaskManager {
    public static final int MAX_TASK = 100;
    Task[] taskList;
    public static int taskCount;
    TaskManager() {
        taskList = new Task[MAX_TASK];
        taskCount = 0;
    }
    public void handleCommand(String command) {
        try {
            if (command.equals("list")) {
                handleList();
            } else if (command.startsWith("todo")) {
                handleTodo(command);
            } else if (command.startsWith("deadline")) {
                handleDeadline(command);
            } else if (command.startsWith("event")) {
                handleEvent(command);
            } else if (command.startsWith("mark")) {
                int taskIndex = Integer.parseInt(command.substring(5));
                taskList[taskIndex - 1].mark();
            } else if (command.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(command.substring(7));
                taskList[taskIndex - 1].unmark();
            } else if (command.startsWith("delete")) {
                handleDelete(command);
            } else {
                System.out.println("Invalid input. What is " + command + "?");
            }
        } catch (HumiException e) {
            System.out.println(e.message);
        }
    }

    private void handleDelete(String command) {
        System.out.println(Humi.LINE);
        System.out.print("    Noted. I've removed this task:\n      ");
        int taskIndex = Integer.parseInt(command.substring(7));
        taskList[taskIndex - 1].print();
        for (int i = taskIndex-1; i < taskCount; i+=1) {
            taskList[i] = taskList[i+1];
        }
        taskCount -= 1;
        System.out.println("    Now you have " + taskCount + " tasks in the list");
        System.out.println(Humi.LINE);
    }

    private void handleEvent(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 5) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 3) {
                String description = splitArray[0].substring(6);
                String startDate = splitArray[1].substring(5);
                String endDate = splitArray[2].substring(3);
                taskList[taskCount] = new EventTask(description, startDate, endDate);
                taskCount += 1;
            } else {
                throw new HumiException("Please specify both the start date and end date");
            }
        } else {
            throw new HumiException("Description of an event cannot be empty.");
        }
    }

    private void handleDeadline(String command) throws HumiException{
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 8) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 2) {
                String description = splitArray[0].substring(9);
                String deadline = splitArray[1].substring(3);
                taskList[taskCount] = new DeadlineTask(description, deadline);
                taskCount += 1;
            } else {
                throw new HumiException("Please specify the deadline date.");
            }
        } else {
            throw new HumiException("Description of a deadline cannot be empty.");
        }
    }

    private void handleTodo(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 4) {
            String description = trimmedCommand.substring(5);
            taskList[taskCount] = new TodoTask(description);
            taskCount += 1;
        } else {
            throw new HumiException("Description of a todo cannot be empty.");
        }
    }

    private void handleList() {
        System.out.println(Humi.LINE);
        System.out.println("    Here are the tasks in your list");
        for (int i = 0; i < taskCount; i++) {
            System.out.print("     " + (i+1) + ".");
            taskList[i].print();
        }
        System.out.println(Humi.LINE);
    }
}