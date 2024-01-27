import java.util.Scanner;

public class Duke {
    final static String appName = "mimichat";

    public static void startupSequence() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello! I'm " + appName);
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------------");
    }

    public static void customPrint(String input) {
        System.out.println("-------------------------------------------");
        System.out.println(input);
        System.out.println("-------------------------------------------");

    }

    public static String formatTask(Task task, int index){
        String statusIcon = task.getStatusIcon();
        String indexNumber = Integer.toString(index+1);
        String taskName = task.getName();
        return indexNumber + ". [" + statusIcon + "] " + taskName;

    }

    public static void listTasks(Task[] list, int numberOfTasks){
        System.out.println("-------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < numberOfTasks; i++){
            System.out.println(formatTask(list[i], i));
        }
        System.out.println("-------------------------------------------");
    }

    public static void markTask(Task[] list, int index){
        list[index].markAsDone();
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as done");
        System.out.println(formatTask(list[index], index));
        System.out.println("-------------------------------------------");
    }

    public static void unmarkTask(Task[] list, int index){
        list[index].markAsUndone();
        System.out.println("-------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(formatTask(list[index], index));
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {

        // Initial welcome message
        startupSequence();

        // Initialise a list of 100 tasks
        Task[] taskList = new Task[100];
        int numberOfTask = 0;
        boolean isRunning = true;

        while (isRunning) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");
            switch (inputs[0]) {
            case "bye":
                customPrint("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                listTasks(taskList, numberOfTask);
                break;
            case "mark":
                markTask(taskList, Integer.parseInt(inputs[1])-1);
                break;
            case "unmark":
                unmarkTask(taskList, Integer.parseInt(inputs[1])-1);
                break;
            default:
                // Adds task by default and prints that a task has been added
                Task newTask = new Task(input);
                taskList[numberOfTask] = newTask;
                numberOfTask++;
                customPrint("added: " + input);
                break;
            }
        }

    }
}
