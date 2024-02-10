import java.util.Scanner;
public class NyanBot {
    private static String LINE = "____________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void runNyan() {
        Scanner scanner = new Scanner(System.in);
        String[] splitInputs = new String[2];
        while (true) {
            String userInput = scanner.nextLine();
            splitInputs = userInput.split(" ");

            switch (Command.parseInput(splitInputs[0])) {
                case Command.BYE_COMMAND:
                    scanner.close();
                    return;
                case Command.LIST_COMMAND:
                    System.out.println(LINE);

                    for (int i = 0; i < taskCount; i++) {
                        System.out.print(i + 1 + ". " + tasks[i].getStatusIcon() + " ");
                        System.out.println(tasks[i]);
                    }
                    break;
                case Command.MARK_COMMAND:
                    int markIndex = Integer.parseInt(splitInputs[1]) - 1;
                    tasks[markIndex].markAsDone();
                    System.out.println(LINE);
                    System.out.println("はい、markしました！");
                    break;
                case Command.UNMARK_COMMAND:
                    markIndex = Integer.parseInt(splitInputs[1]) - 1;
                    tasks[markIndex].unmarkAsDone();
                    System.out.println(LINE);
                    System.out.println("はい、unmarkしました！");
                    break;
                case Command.TODO_COMMAND:
                    Todo newTodo = new Todo(userInput.substring(5));
                    addTask(newTodo);
                    break;
                case Command.DEADLINE_COMMAND:
                    splitInputs = userInput.split("/");
                    Deadline newDeadline = new Deadline(splitInputs[0].substring(9), splitInputs[1]);
                    addTask(newDeadline);
                    break;
                case Command.EVENT_COMMAND:
                    splitInputs = userInput.split("/");
                    Event newEvent = new Event(splitInputs[0].substring(6), splitInputs[1], splitInputs[2]);
                    addTask(newEvent);
                    break;
                default:
                    System.out.println("あの。。。puriisu taipu input ne \uD83D\uDC31\uD83C\uDF08");
                    System.out.println("try \"help\" desu？");
            }
        }
    }

    public static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println(LINE);
        System.out.println("Added " + task);
    }

    public static void main(String[] args) {
        String GREET = "お帰りなさいませ、ご主人様。ニャンー♡♡";
        String PROMPT = "なにをしたいの？";
        String BYE = "じゃー、またね〜！！";

        System.out.println(LINE);
        System.out.println(GREET + "\n" + PROMPT);

        runNyan();

        System.out.println(LINE);
        System.out.println(BYE);
    }
}
