import java.util.Scanner;

import task.Deadlines;
import task.Events;
import task.TaskLists;
import task.Tasks;
import task.ToDos;
import task.DataManage;
import exceptions.InputException;
public class Chris {
    public static void main(String[] args) throws InputException {
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
        System.out.println("Hello from \n" + logo );
        String dash = "____________________________________________________________";

        Scanner sc = new Scanner(System.in);
        String command = "";
        TaskLists listCommands = new TaskLists();

        DataManage.createFolder();
        DataManage.createText();
        try {
            listCommands = DataManage.readSavedData();
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(dash + "\n" +
                "Hello, I'm Chris\n" +
                "What can I do for you?\n" +
                dash + "\n");
        while (sc.hasNextLine()) {
            DataManage.saveData(listCommands);
            try {
                command = sc.nextLine();
                if (command.equals("bye")) {
                    DataManage.saveText(listCommands);
                    System.out.println(dash + "\n" +
                            "Bye. Hope to see you again soon!\n" +
                            dash);
                    break;
                } else if (command.equals("list")) {
                    System.out.println(dash);
                    System.out.println("Here are the tasks in your list");
                    listCommands.printTasks();
                    System.out.println(dash);
                } else if (command.matches("^(mark [0-9]|unmark [0-9])")) {
                    String[] split = command.split(" ");
                    String option = split[0];
                    String index = split[1];
                    int i = Integer.parseInt(index);
                    if (i > listCommands.tasksSize()) {
                        throw new InputException("index out of bound");
                    }
                    if (option.startsWith("un")) {
                        listCommands.unBox(i);
                    } else {
                        listCommands.box(i);
                    }
                } else if (command.matches("delete [0-9]")) {
                    String[] split = command.split(" ");
                    String index = split[1];
                    int i = Integer.parseInt(index);
                    if (i > listCommands.tasksSize()) {
                        throw new InputException("index out of bound");
                    }
                    listCommands.delete(i);
                } else if (command.matches("todo(.*)")) {
                    if ((command.matches("todo"))) {
                        throw new InputException("Don't have an empty description for todo");
                    }
                    Tasks record = new ToDos(command.substring(5));
                    listCommands.addTask(record);
                    System.out.println(dash);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + record);
                    listCommands.taskNumPrint();
                    System.out.println(dash);
                } else if (command.matches("deadline(.*)")) {
                    String[] deadlines = command.split(" /by ", 2);
                    if (deadlines.length == 2) {
                        if ((command.matches("deadline"))) {
                            throw new InputException("Don't have an empty description for deadline");
                        }
                        Tasks record = new Deadlines(deadlines[0].substring(9), deadlines[1]);
                        listCommands.addTask(record);
                        System.out.println(dash);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + record);
                        listCommands.taskNumPrint();
                        System.out.println(dash);
                    } else {
                        throw new InputException("Wrong input syntax, have a time for deadline");
                    }
                } else if (command.matches("event(.*)")) {
                    String[] events = command.split(" /from ", 2);
                    if (events.length == 2) {
                        if ((command.matches("events"))) {
                            throw new InputException("Don't have an empty description for event");
                        }
                        String[] times = events[1].split(" /to ", 2);
                        if (times.length == 2) {
                            Tasks record = new Events(events[0].substring(6), times[0], times[1]);
                            listCommands.addTask(record);
                            System.out.println(dash);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + record);
                            listCommands.taskNumPrint();
                            System.out.println(dash);
                        } else {
                            throw new InputException("invalid syntax, have a start and end time");
                        }
                    } else {
                        throw new InputException("wrong input syntax, have a description command");
                    }
                } else {
                    throw new InputException("I cannot understand the command");
                }
            } catch (InputException e) {
                System.out.println(dash);
                System.out.println(e.getMessage());
                System.out.println(dash);
            }
        }
        sc.close();
    }
}
