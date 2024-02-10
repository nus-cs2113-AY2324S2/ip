import java.util.Scanner;

public class Duke  {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
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
            if(line.startsWith("list")) {
                greet.printHyphen();
                System.out.println();
                printList();
                greet.printHyphen();
                System.out.println();
                continue;
            }
            Task t = new Task(line);
            if(line.startsWith("mark")) {
                int indexToMark = t.taskIndex(line);
                Task taskToMark = tasks[indexToMark];
                greet.printHyphen();
                System.out.println();
                taskToMark.setIsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskToMark);
                greet.printHyphen();
                System.out.println();
                continue;
            }
            if(line.startsWith("unmark")) {
                int indexToUnmark = t.taskIndex(line);
                Task taskToUnmark = tasks[indexToUnmark];
                greet.printHyphen();
                System.out.println();
                taskToUnmark.setIsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskToUnmark);
                greet.printHyphen();
                System.out.println();
                continue;
            }
            if(line.startsWith("todo")) {
                Todo todo = new Todo(line);
                addTask(todo);
                greet.printHyphen();
                System.out.println();
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                greet.printNumTasks(taskCount);
                greet.printHyphen();
                System.out.println();
                continue;
            }
            if(line.startsWith("deadline")) {
                String[] segments = line.split("/");
                String by = segments[segments.length-1];
                Deadline d = new Deadline(line, by.replace("by", ""));
                addTask(d);
                greet.printHyphen();
                System.out.println();
                System.out.println("Got it. I've added this task:");
                System.out.println(d);
                greet.printNumTasks(taskCount);
                greet.printHyphen();
                System.out.println();
                continue;
            }
            if(line.startsWith("event")) {
                String[] segments = line.split("/");
                String from = segments[segments.length-2].replace("from", "");
                String to = segments[segments.length-1].replace("to", "");
                Event e = new Event(line, from, to);
                addTask(e);
                greet.printHyphen();
                System.out.println();
                System.out.println("Got it. I've added this task:");
                System.out.println(e);
                greet.printNumTasks(taskCount);
                greet.printHyphen();
                System.out.println();
            }
        }
        greet.sayBye();
    }
}