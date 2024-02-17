import java.util.Scanner;

public class Duke {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void addTask(String line) {
        Greet greet = new Greet();
        Task task;
        try {
            if(line.startsWith("event")) {
                task = Event.fromString(line);
            } else if(line.startsWith("deadline")) {
                task = Deadline.fromString(line);
            } else {
                task = Todo.fromString(line);
            }
        } catch(MissingParameterException e) {
            greet.printError(e.getMessage());
            return;
        }
        tasks[taskCount] = task;
        taskCount++;

        greet.printFormat();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        greet.printNumTasks(taskCount);
        greet.printFormat();
    }

    public static void printList() {
        int count = 0;
        for(Task t:tasks) {
            if(t == null) {
                return;
            }
            count++;
            System.out.print(count + ".");
            System.out.println(t);
        }
    }

    public static void validate(String description) {
        Greet greet = new Greet();
        try {
            checkForError(description);
        } catch (DukeException e) {
            greet.printInvalidDescription();
        }
    }

    private static void checkForError(String description) throws DukeException {
        if(!description.contains("list") && !description.contains("event") && !description.contains("deadline") && !description.contains("todo") && !description.contains("mark")) {
            throw new DukeException();
        }
        if(description.trim().isEmpty()) {
            throw new DukeException();
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__\n" +
                "    },_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet greet = new Greet();
        greet.sayHello();

        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if(line.equals("bye")) {
                break;
            }
            validate(line);
            if(line.startsWith("list")) {
                greet.printFormat();
                printList();
                greet.printFormat();
                continue;
            }
            Task t = new Task(line);
            if(line.startsWith("mark")) {
                int indexToMark = t.taskIndex(line);
                if (greet.isWithinBounds(taskCount, indexToMark)) {
                    Task taskToMark = tasks[indexToMark];
                    greet.printFormat();
                    taskToMark.setIsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToMark);
                    greet.printFormat();
                }
                continue;
            }
            if(line.startsWith("unmark")) {
                int indexToUnmark = t.taskIndex(line);
                if (greet.isWithinBounds(taskCount, indexToUnmark)) {
                    Task taskToUnmark = tasks[indexToUnmark];
                    greet.printFormat();
                    taskToUnmark.setIsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskToUnmark);
                    greet.printFormat();
                }
                continue;
            }
            if(line.startsWith("todo")) {
                addTask(line);
                continue;
            }
            if(line.startsWith("deadline")) {
                addTask(line);
                continue;
            }
            if(line.startsWith("event")) {
                addTask(line);
            }
        }
        greet.sayBye();
    }
}