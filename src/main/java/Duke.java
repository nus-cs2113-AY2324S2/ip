import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static Boolean isIndexValid(int index) {
        return index >= 1 && index <= tasks.size();
    }
    public static void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public static void listTasks() {
        int cnt = 0;
        System.out.println("You have a total of " + tasks.size() + " tasks.");
        for (Task task : tasks) {
            cnt += 1;
            System.out.printf(cnt + ". ");

            switch (task.getTaskType()) {
            case (1):
                TodoTask todoTask = (TodoTask)task;
                todoTask.printTask();
                break;
            case (2):
                EventTask eventTask = (EventTask)task;
                eventTask.printTask();
                break;
            case (3):
                DeadlineTask deadlineTask = (DeadlineTask)task;
                deadlineTask.printTask();
                break;
            default:
                task.printTask();
                break;
            }
        }
    }
    public static void readProcessCommand() {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------");
            String inputCommand = scanner.nextLine();
            String[] words = inputCommand.split(" ");
            String firstWord = words[0];
            String suffixWord;
            if(words.length > 1) {
                suffixWord = inputCommand.substring(words[0].length() + 1);
            }
            else {
                suffixWord = "";
            }
            switch (firstWord) {
            case ("bye"):
                System.out.println("Bye! See ya!");
                return;
            case ("list"):
                listTasks();
                break;
            case ("mark"):
                int markIndex = Integer.parseInt(suffixWord);
                if (!isIndexValid(markIndex)) {
                    System.out.println("Invalid task index!");
                }
                else {
                    tasks.get(markIndex - 1).setIsDone(true);
                    System.out.println("Set task number " + markIndex + ": " + tasks.get(markIndex - 1).getName() + " as done.");
                }
                break;
            case ("unmark"):
                int unmarkIndex = Integer.parseInt(suffixWord);
                if (!isIndexValid(unmarkIndex)) {
                    System.out.println("Invalid task index!");
                }
                else {
                    tasks.get(unmarkIndex - 1).setIsDone(false);
                    System.out.println("Set task number " + unmarkIndex + ": " + tasks.get(unmarkIndex - 1).getName() + " as not done.");
                }
                break;
            case ("todo"):
                TodoTask todoTask = new TodoTask(suffixWord, false);
                addTask(todoTask);
                System.out.println("Added todo type task with name: " + todoTask.getName());
                break;
            case ("event"):
                EventTask eventTask = new EventTask(suffixWord, false);
                addTask(eventTask);
                System.out.println("Added event type task with name: " + eventTask.getName());
                break;
            case ("deadline"):
                DeadlineTask deadlineTask = new DeadlineTask(suffixWord, false);
                addTask(deadlineTask);
                System.out.println("Added deadline type task with name: " + deadlineTask.getName());
                break;
            default:
                Task task = new Task(inputCommand, false);
                addTask(task);
                System.out.println("Added task with name: " + task.getName());
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
