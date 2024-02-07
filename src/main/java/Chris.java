import java.util.Scanner;
public class Chris {
    public static void main(String[] args) {
        String logo = "      ___           ___           ___                       ___     \n" +
                "     /\\  \\         /\\__\\         /\\  \\          ___        /\\  \\    \n" +
                "    /::\\  \\       /:/  /        /::\\  \\        /\\  \\      /::\\  \\   \n" +
                "   /:/\\:\\  \\     /:/__/        /:/\\:\\  \\       \\:\\  \\    /:/\\ \\  \\  \n" +
                "  /:/  \\:\\  \\   /::\\  \\ ___   /::\\~\\:\\  \\      /::\\__\\  _\\:\\~\\ \\  \\ \n" +
                " /:/__/ \\:\\__\\ /:/\\:\\  /\\__\\ /:/\\:\\ \\:\\__\\  __/:/\\/__/ /\\ \\:\\ \\ \\__\\\n" +
                " \\:\\  \\  \\/__/ \\/__\\:\\/:/  / \\/_|::\\/:/  / /\\/:/  /    \\:\\ \\:\\ \\/__/\n" +
                "  \\:\\  \\            \\::/  /     |:|::/  /  \\::/__/      \\:\\ \\:\\__\\  \n" +
                "   \\:\\  \\           /:/  /      |:|\\/__/    \\:\\__\\       \\:\\/:/  /  \n" +
                "    \\:\\__\\         /:/  /       |:|  |       \\/__/        \\::/  /   \n" +
                "     \\/__/         \\/__/         \\|__|                     \\/__/    ";
        System.out.println("Hello from\n" + logo );

        Scanner sc = new Scanner(System.in);
        String command = "";
        TaskLists listCommands = new TaskLists();
        System.out.println("____________________________________________________________\n" +
                "Hello, I'm Chris\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
        while (sc.hasNextLine()) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list");
                listCommands.printTasks();
                System.out.println("____________________________________________________________");
            } else if (command.matches("^(mark [0-9]|unmark [0-9])")) {
                String[] split = command.split(" ");
                String option = split[0];
                String index = split[1];
                int i = Integer.parseInt(index);
                if (i > listCommands.tasksSize()) {
                    System.out.println("index out of bound");
                    break;
                }
                if (option.startsWith("un")) {
                    listCommands.unBox(i);
                } else {
                    listCommands.box(i);
                }
            } else if (command.matches("todo(.*)")) {
                Tasks record = new ToDos(command.substring(5));
                listCommands.addTask(record);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + record);
                listCommands.taskNum();
                System.out.println("____________________________________________________________");
            } else if (command.matches("deadline(.*)")) {
                String[] deadlines = command.split(" /by ", 2);
                if (deadlines.length == 2) {
                    Tasks record = new Deadlines(deadlines[0].substring(9), deadlines[1]);
                    listCommands.addTask(record);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + record);
                    listCommands.taskNum();
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("wrong input syntax");
                    break;
                }
            } else if (command.matches("event(.*)")) {
                String[] events = command.split(" /from ", 2);
                if (events.length == 2) {
                    String[] times = events[1].split(" /to ", 2);
                    if (times.length == 2) {
                        Tasks record = new Events(events[0].substring(6), times[0], times[1]);
                        listCommands.addTask(record);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + record);
                        listCommands.taskNum();
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("wrong input syntax");
                        break;
                    }
                } else {
                    System.out.println("wrong input syntax");
                    break;
                }
            } else {
                System.out.println("____________________________________________________________\n" +
                        "added: " + command + "\n" +
                        "____________________________________________________________");
                Tasks record = new Tasks(command);
                listCommands.addTask(record);
            }
        }
        sc.close();
    }
}
