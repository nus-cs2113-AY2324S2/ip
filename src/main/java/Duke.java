import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static Boolean isIndexValid(int index) {
        return index >= 1 && index <= tasks.size();
    }
    public static void addTask(String taskName) {
        Task newTask = new Task(taskName, false);
        tasks.add(newTask);
        System.out.println("Added task with name: " + taskName);
    }

    public static void listTasks() {
        int cnt = 0;
        for (Task task : tasks) {
            cnt += 1;
            System.out.printf(cnt + ". ");
            task.printTask();
        }
    }
    public static void readProcessCommand() {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------");
            String inputCommand = scanner.nextLine();
            String[] words = inputCommand.split(" ");
            String firstWord = words[0];
            switch (firstWord) {
            case ("bye"):
                System.out.println("Bye! See ya!");
                return;
            case ("list"):
                listTasks();
                break;
            case ("mark"):
                int markIndex = Integer.parseInt(words[1]);
                if (!isIndexValid(markIndex)) {
                    System.out.println("Invalid task index!");
                }
                else {
                    tasks.get(markIndex - 1).setIsDone(true);
                    System.out.println("Set task number " + markIndex + ": " + tasks.get(markIndex - 1).getName() + " as done.");
                }
                break;
            case ("unmark"):
                int unmarkIndex = Integer.parseInt(words[1]);
                if (!isIndexValid(unmarkIndex)) {
                    System.out.println("Invalid task index!");
                }
                else {
                    tasks.get(unmarkIndex - 1).setIsDone(false);
                    System.out.println("Set task number " + unmarkIndex + ": " + tasks.get(unmarkIndex - 1).getName() + " as not done.");
                }
                break;
            default:
                addTask(inputCommand);
                break;
            }



        }
    }

    public static void main(String[] args) {
        String botName = "Huan";
        System.out.println("Hello! I'm " + botName + ", a chat bot");

        readProcessCommand();
    }
}
