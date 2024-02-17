import java.util.Scanner;

public class Chris {
    public static void main(String[] args) {
        System.out.println("------------------------------------------------");
        System.out.println("Hello, I am Chris");
        System.out.println("------------------------------------------------");
        boolean done = false;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskCount = 0;
        while (!done) {
            System.out.println("How can I help you today?");
            System.out.println("1) Task List");
            System.out.println("2) Add tasks");
            System.out.println("3) Remove tasks");
            System.out.println("4) Mark Tasks");
            System.out.println("5) Quit");
            String line = in.nextLine();
            switch (line) {
                case "1":
                    if (taskCount == 0) {
                        System.out.println("List is empty.");
                    } else {
                        for (int i = 0; i < taskCount; i++) {
                            System.out.print(i + 1);
                            System.out.print(". ");
                            System.out.println(taskList[i]);
                        }
                    }
                    break;
                case "2":
                    System.out.println("Type of task: ");
                    String taskType = in.nextLine();
                    System.out.println("Description of task: ");
                    String taskDescription = in.nextLine();
                    switch (taskType) {
                        case "T":
                            taskList[taskCount] = new ToDo(taskDescription);
                            taskCount++;
                            break;
                        case "D":
                            System.out.println("By: ");
                            String taskby = in.nextLine();
                            taskList[taskCount] = new Deadline(taskDescription, taskby);
                            taskCount++;
                            break;
                        case "E":
                            System.out.println("From: ");
                            String taskFrom = in.nextLine();
                            System.out.println("To: ");
                            String taskTo = in.nextLine();
                            taskList[taskCount] = new Event(taskDescription, taskFrom, taskTo);
                            taskCount++;
                            break;
                        default:
                            taskList[taskCount] = new Task(taskDescription);
                            taskCount++;
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Currently unavailable.");
                    break;
                case "4":
                    System.out.println("Which task do you want to mark?");
                    int taskNumber = Integer.parseInt(in.nextLine());
                    taskList[taskNumber - 1].markTask();
                    break;
                case "5":
                    done = true;
                    break;
                default:
                    System.out.println("Please enter again: ");
                    break;
            }
        }
        System.out.println("Thank you! Have a nice day!");
    }
}
