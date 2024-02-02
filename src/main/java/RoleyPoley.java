import java.util.Scanner;

public class RoleyPoley {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        greet();
        echo(taskList);
    }

    public static void displayList(Task[] taskList) {
        for ( int i = 1; i < taskList.length; i++) {
            if (taskList[i] == null) {
                break;
            }
            System.out.println("\t" + i + ".[" + taskList[i].getStatusIcon() + "]" + taskList[i].description);
        }
    }

    public static void createLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void greet() {
        System.out.println("Hello! I'm RoleyPoley \nWhat can I do for you today?");
        createLine();
    }

    public static void echo(Task[] taskList) {
        String line;

        int counter = 1;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                createLine();
                break;
            } else if (line.equals("list")) {
                displayList(taskList);
                createLine();
            } else if (line.startsWith("mark")) {
                String[] words = line.split(" ");
                if (words.length == 2) {
                    try {
                        int taskNum = Integer.parseInt(words[1]);
                        if (taskList[taskNum] == null) {
                            System.out.println("Error! Out of Bounds!");
                        }
                        else {
                            taskList[taskNum].markAsDone();
                        }
                        createLine();
                    } catch (NumberFormatException e) {
                        System.out.println("\tadded : " + line);
                        createLine();
                        taskList[counter] = new Task(line);
                        counter++;
                    }
                }

            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                if (words.length == 2) {
                    try {
                        int taskNum = Integer.parseInt(words[1]);
                        if (taskList[taskNum] == null) {
                            System.out.println("Error! Out of Bounds!");
                        }
                        else {
                            taskList[taskNum].markAsUndone();
                        }
                        createLine();
                    } catch (NumberFormatException e) {
                        System.out.println("\tadded : " + line);
                        createLine();
                        taskList[counter] = new Task(line);
                        counter++;
                    }
                }
            } else {
                System.out.println("\tadded : " + line);
                createLine();
                taskList[counter] = new Task(line);
                counter++;
            }
        }
    }
}

