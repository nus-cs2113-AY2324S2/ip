import java.util.Scanner;

public class Incy {

    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[Constants.MAX_TASKS];
        int taskCount = 0;

        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Oi bruv! I'm\n" + Constants.LOGO + Constants.ANSI_CYAN + "Wotcha need from me today?\n" + Constants.LINE_STRING_BOTTOM);

        while (true) {
            input = scanner.nextLine();
            if ("bye".equalsIgnoreCase(input)) {
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                handleListCommand(tasks, taskCount);
            } else if (input.startsWith("mark ")) {
                handleMarkCommand(input, tasks, taskCount, true);
            } else if (input.startsWith("unmark ")) {
                handleMarkCommand(input, tasks, taskCount, false);
            } else {
                taskCount = handleAddTask(input, tasks, taskCount);
            }
        }

        System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Cya later mate!\n" + Constants.LINE_STRING_BOTTOM);
        scanner.close();
    }

    private static void handleListCommand(Task[] tasks, int taskCount) {
        System.out.println(Constants.LINE_STRING_BOTTOM);
        if (taskCount == 0) {
            System.out.println(Constants.ANSI_RED + "Blimey, your list is empty, innit?");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(Constants.ANSI_CYAN + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(Constants.LINE_STRING_BOTTOM);
    }

    private static void handleMarkCommand(String input, Task[] tasks, int taskCount, boolean markAsDone) {
        int index = Integer.parseInt(input.substring(markAsDone ? 5 : 7)) - 1;
        if (index >= 0 && index < taskCount) {
            if (markAsDone) {
                tasks[index].markAsDone();
            } else {
                tasks[index].markAsNotDone();
            }
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Done with this one:\n  " + tasks[index] + "\n" + Constants.LINE_STRING_BOTTOM);
        }
    }

    private static int handleAddTask(String input, Task[] tasks, int taskCount) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts.length > 1 ? parts[1] : "";
    
        if (taskCount >= tasks.length) {
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "The list is full. Can't add more tasks!\n" + Constants.LINE_STRING_BOTTOM);
            return taskCount;
        }
    
        Task newTask = null;
    
        switch (command.toLowerCase()) {
            case "todo":
                newTask = new Todo(taskInfo);
                break;
            case "deadline":
                String[] deadlineParts = taskInfo.split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "Error: Invalid deadline format. Please use 'deadline [task] /by [date/time]'.\n" + Constants.LINE_STRING_BOTTOM);
                    return taskCount;
                }
                newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = taskInfo.split(" /from ", 2);
                String[] eventTime = eventParts.length > 1 ? eventParts[1].split(" /to ", 2) : new String[0];
                if (eventParts.length < 2 || eventTime.length < 2) {
                    System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_RED + "Error: Invalid event format. Please use 'event [task] /from [start time] /to [end time]'.\n" + Constants.LINE_STRING_BOTTOM);
                    return taskCount;
                }
                newTask = new Event(eventParts[0], eventTime[0], eventTime[1]);
                break;
            default:
                newTask = new Todo(input);
        }
    
        if (newTask != null) {
            tasks[taskCount] = newTask;
            System.out.println(Constants.LINE_STRING_TOP + Constants.ANSI_CYAN + "Sorted! Your task's in the bag, innit mate:\n" 
                + "  " + newTask + "\n" 
                + "You're now juggling " + (taskCount + 1) + " tasks on your list, innit.\n" 
                + Constants.LINE_STRING_BOTTOM);
            
            return taskCount + 1;
        }
        return taskCount;
    }
        
}