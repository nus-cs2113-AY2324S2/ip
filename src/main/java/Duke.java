import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = TaskFile.load();
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
        tasks.add(task);
        TaskFile.save(tasks);
        greet.printFormat();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        greet.printNumTasks(tasks.size());
        greet.printFormat();
    }

    public static void deleteTask(int indexToDelete) {
        tasks.remove(indexToDelete);
        TaskFile.save(tasks);

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
        if(!description.contains("list") && !description.contains("event") && !description.contains("deadline")
                && !description.contains("todo") && !description.contains("mark")
                && !description.contains("delete")) {
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
                if (greet.isWithinBounds(tasks.size(), indexToMark)) {
                    Task taskToMark = tasks.get(indexToMark);
                    greet.printFormat();
                    taskToMark.setIsDone();
                    TaskFile.save(tasks);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToMark);
                    greet.printFormat();
                }
                continue;
            }
            if(line.startsWith("unmark")) {
                int indexToUnmark = t.taskIndex(line);
                if (greet.isWithinBounds(tasks.size(), indexToUnmark)) {
                    Task taskToUnmark = tasks.get(indexToUnmark);
                    greet.printFormat();
                    taskToUnmark.setIsNotDone();
                    TaskFile.save(tasks);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskToUnmark);
                    greet.printFormat();
                }
                continue;
            }
            if(line.startsWith("delete")) {
                int indexToDelete = t.taskIndex(line);
                if (greet.isWithinBounds(tasks.size(), indexToDelete)) {
                    Task taskToDelete = tasks.get(indexToDelete);
                    greet.printFormat();
                    deleteTask(indexToDelete);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskToDelete);
                    greet.printNumTasks(tasks.size());
                    greet.printFormat();
                }
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