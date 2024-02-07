// Assuming other class definitions remain the same

public class Duke {
    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            printLineSeparator();

            if ("bye".equalsIgnoreCase(input)) {
                isRunning = false;
            } else if ("list".equalsIgnoreCase(input)) {
                listTasks();
            } else {
                handleTaskInput(input);
            }

            printLineSeparator();
        }

        printGoodbyeMessage();
        scanner.close();
    }

    private static void printWelcomeMessage() {
        printLineSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void handleTaskInput(String input) {
        if (input.startsWith("todo")) {
            addTask(new Todo(input.substring(5)));
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split(" /by ");
            addTask(new Deadline(parts[0], parts[1]));
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(6).split(" /from ");
            addTask(new Event(parts[0], parts[1]));
        } else if (input.startsWith("mark")) {
            markTask(Integer.parseInt(input.substring(5)) - 1);
        } else if (input.startsWith("unmark")) {
            unmarkTask(Integer.parseInt(input.substring(7)) - 1);
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    private static void unmarkTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    private static void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }
}
// Testing 1