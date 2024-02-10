import java.util.Scanner;
public class NyanBot {
    static final String LIST_COMMAND = "LIST";
    static final String MARK_COMMAND = "MARK";
    static final String UNMARK_COMMAND = "UNMARK";
    static final String TODO_COMMAND = "TODO";
    static final String DEADLINE_COMMAND = "DEADLINE";
    static final String EVENT_COMMAND = "EVENT";
    static final String BYE_COMMAND = "BYE";
    private static String LINE = "____________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void markTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            tasks[index].markAsDone();
            System.out.println("はい、markしました！");
        } catch (NullPointerException e) {
            System.out.println("item no exist...");
            System.out.println("またして見よう！");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("puriisu use correct number!...");
            System.out.println("またして見よう！");
        } catch (NumberFormatException e) {
            System.out.println("puriisu use number...");
            System.out.println("またして見よう！");
        } finally {
            System.out.println(LINE);
        }
    }

    private static void unmarkTask(String input) {
        try {
            String[] splitInputs = input.split(" ");
            int index = Integer.parseInt(splitInputs[1]) - 1;
            tasks[index].markAsUndone();
            System.out.println("はい、unmarkしました！");
        } catch (NullPointerException e) {
            System.out.println("item no exist...");
            System.out.println("またして見よう！");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("puriisu use correct number!...");
            System.out.println("またして見よう！");
        } catch (NumberFormatException e) {
            System.out.println("puriisu use number...");
            System.out.println("またして見よう！");
        } finally {
            System.out.println(LINE);
        }
    }

    private static void addTodo(String input) {
        try {
            String description = input.substring(5);
            Todo todo = new Todo(description);
            addTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("puriisu add description!...");
            System.out.println("またして見よう！");
        } finally {
            System.out.println(LINE);
        }
    }

    private static void addDeadline(String input) {
        try {
            String[] splitInputs = input.split("/");
            String description = splitInputs[0].substring(9);
            String date = splitInputs[1];
            Deadline deadline = new Deadline(description, date);
            addTask(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("puriisu add description!...");
            System.out.println("使い方：deadline [description] /[date]");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("puriisu add date!");
            System.out.println("使い方：deadline [description] /[date]");
        } finally {
            System.out.println(LINE);
        }
    }

    private static void addEvent(String input) {
        try {
            String[] splitInputs = input.split("/");
            String description = splitInputs[0].substring(6);
            String start = splitInputs[1];
            String end = splitInputs[2];
            Event event = new Event(description, start, end);
            addTask(event);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("puriisu add description!...");
            System.out.println("使い方：event [description] /[date]");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("puriisu add start & end!");
            System.out.println("使い方：event [description] /[start] /[end]");
        } finally {
            System.out.println(LINE);
        }
    }

    private static String parseCommand(String input) {
        String[] splitInputs = input.split(" ");
        return splitInputs[0].toUpperCase();
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        String[] splitInputs = new String[2];

        while (true) {
            String input = scanner.nextLine();
            splitInputs = input.split(" ");

            switch (parseCommand(input)) {
                case BYE_COMMAND:
                    scanner.close();
                    return;
                case LIST_COMMAND:
                    System.out.println(LINE);

                    for (int i = 0; i < taskCount; i++) {
                        System.out.print(i + 1 + ". " + tasks[i].getStatusIcon() + " ");
                        System.out.println(tasks[i]);
                    }
                    break;
                case MARK_COMMAND:
                    int markIndex;
                    markTask(input);
                    break;
                case UNMARK_COMMAND:
                    unmarkTask(input);
                    break;
                case TODO_COMMAND:
                    addTodo(input);
                    break;
                case DEADLINE_COMMAND:
                    addDeadline(input);
                    break;
                case EVENT_COMMAND:
                    addEvent(input);
                    break;
                default:
                    System.out.println("あの。。。puriisu taipu valid input ne \uD83D\uDC31\uD83C\uDF08");
                    System.out.println("try \"help\" desu？");
                    break;
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

        getInput();

        System.out.println(LINE);
        System.out.println(BYE);
    }
}
