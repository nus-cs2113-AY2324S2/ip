import java.util.Scanner;
public class NyanBot {
    private static String line = "____________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String[] splitInput = new String[2];
        String userInput = in.nextLine();
        splitInput = userInput.split(" ");

        switch(splitInput[0]) {
        case "bye":
            return;
        case "list":
            System.out.println(line);

            for (int i = 0; i < taskCount; i++) {
                System.out.print(i + 1 + ". " + tasks[i].getStatusIcon() + " ");
                System.out.println(tasks[i]);
            }
            break;
        case "mark":
            int markIndex = Integer.parseInt(splitInput[1]) - 1;
            tasks[markIndex].markAsDone();
            System.out.println(line);
            System.out.println("はい、markしました！");
            break;
        case "unmark":
            markIndex = Integer.parseInt(splitInput[1]) - 1;
            tasks[markIndex].unmarkAsDone();
            System.out.println(line);
            System.out.println("はい、unmarkしました！");
            break;
        case "todo":
            Todo newTodo = new Todo(userInput.substring(5));
            addTask(newTodo);
            break;
        case "deadline":
            splitInput = userInput.split("/");
            Deadline newDeadline = new Deadline(splitInput[0].substring(9), splitInput[1]);
            addTask(newDeadline);
            break;
        case "event":
            splitInput = userInput.split("/");
            Event newEvent = new Event(splitInput[0].substring(6), splitInput[1], splitInput[2]);
            addTask(newEvent);
            break;
        default:
            Task newTask = new Task(userInput);
            addTask(newTask);
        }
        echo();
    }
    public static void main(String[] args) {
        String greet = "お帰りなさいませ、ご主人様。ニャンー♡♡";
        String prompt = "なにをしたいの？";
        String bye = "じゃー、またね〜！！";

        System.out.println(line);
        System.out.println(greet + "\n" + prompt);

        echo();

        System.out.println(line);
        System.out.println(bye);
    }

    public static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println(line);
        System.out.println("Added " + task);
    }
}
