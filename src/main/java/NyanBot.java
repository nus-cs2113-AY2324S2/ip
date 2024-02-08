import java.util.Scanner;
public class NyanBot {
    private static String LINE = "____________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String[] splitInputs = new String[2];
        String userInput = in.nextLine();
        splitInputs = userInput.split(" ");

        switch(splitInputs[0]) {
        case "bye":
            return;
        case "list":
            System.out.println(LINE);

            for (int i = 0; i < taskCount; i++) {
                System.out.print(i + 1 + ". " + tasks[i].getStatusIcon() + " ");
                System.out.println(tasks[i]);
            }
            break;
        case "mark":
            int markIndex = Integer.parseInt(splitInputs[1]) - 1;
            tasks[markIndex].markAsDone();
            System.out.println(LINE);
            System.out.println("はい、markしました！");
            break;
        case "unmark":
            markIndex = Integer.parseInt(splitInputs[1]) - 1;
            tasks[markIndex].unmarkAsDone();
            System.out.println(LINE);
            System.out.println("はい、unmarkしました！");
            break;
        case "todo":
            Todo newTodo = new Todo(userInput.substring(5));
            addTask(newTodo);
            break;
        case "deadline":
            splitInputs = userInput.split("/");
            Deadline newDeadline = new Deadline(splitInputs[0].substring(9), splitInputs[1]);
            addTask(newDeadline);
            break;
        case "event":
            splitInputs = userInput.split("/");
            Event newEvent = new Event(splitInputs[0].substring(6), splitInputs[1], splitInputs[2]);
            addTask(newEvent);
            break;
        default:
            Task newTask = new Task(userInput);
            addTask(newTask);
        }
        echo();
    }
    public static void main(String[] args) {
        String GREET = "お帰りなさいませ、ご主人様。ニャンー♡♡";
        String PROMPT = "なにをしたいの？";
        String BYE = "じゃー、またね〜！！";

        System.out.println(LINE);
        System.out.println(GREET + "\n" + PROMPT);

        echo();

        System.out.println(LINE);
        System.out.println(BYE);
    }

    public static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println(LINE);
        System.out.println("Added " + task);
    }
}
