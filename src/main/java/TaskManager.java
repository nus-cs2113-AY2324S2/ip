public class TaskManager {
    Task[] taskList;
    public static int taskCount;
    TaskManager() {
        taskList = new Task[100];
        taskCount = 0;
    }
    public void handleCommand(String command) {
        if (command.equals("list")) {
            System.out.println(Humi.LINE);
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < taskCount; i++) {
                System.out.print("     " + (i+1) + ".");
                taskList[i].print();
            }
            System.out.println(Humi.LINE);
        }
        else if (command.startsWith("todo")) {
            String description = command.substring(5);
            taskList[taskCount] = new TodoTask(description);
            taskCount += 1;
        }
        else if (command.startsWith("deadline")) {
            String[] splitArray = command.split("/");
            String description = splitArray[0].substring(9);
            String deadline = splitArray[1].substring(3);
            taskList[taskCount] = new DeadlineTask(description, deadline);
            taskCount += 1;
        }
        else if (command.startsWith("event")) {
            String[] splitArray = command.split("/");
            String description = splitArray[0].substring(6);
            String startDate = splitArray[1].substring(5);
            String endDate = splitArray[2].substring(3);
            taskList[taskCount] = new EventTask(description, startDate, endDate);
            taskCount += 1;
        }
        else if (command.startsWith("mark")) {
            int taskIndex = Integer.parseInt(command.substring(5));
            taskList[taskIndex - 1].mark();
        }
        else if (command.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(command.substring(7));
            taskList[taskIndex - 1].unmark();
        }
        else {
            System.out.println("Invalid input");
        }
    }
}
