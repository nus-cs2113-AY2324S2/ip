import java.util.Scanner;
import java.util.ArrayList;

public class Helpy {
    public static void greetUser() {
        String horizontalLine = "______________________\n";
        String name = "░▒█░▒█░▒█▀▀▀░▒█░░░░▒█▀▀█░▒█░░▒█\n" +
                "░▒█▀▀█░▒█▀▀▀░▒█░░░░▒█▄▄█░▒▀▄▄▄▀\n" +
                "░▒█░▒█░▒█▄▄▄░▒█▄▄█░▒█░░░░░░▒█░░\n";
        System.out.println(horizontalLine
                + "Greetings, I am\n" + name);
        System.out.println("How can I help you?\n" + horizontalLine);
    }

    public static void listTasks(ArrayList<Task> taskList) {
        int label = 1;
        String horizontalLine = "______________________\n";

        System.out.print(horizontalLine);
        for (Task task : taskList) {
            System.out.print(label);
            System.out.println(". " + task.getTaskName());
            label++;
        }
        System.out.println(horizontalLine);
    }

    public static void addTask(String command, ArrayList<Task> taskList) {
        Task newTask = new Task(command);
        taskList.add(newTask);

        String horizontalLine = "______________________\n";
        System.out.print(horizontalLine);
        System.out.println("I have added: " + command);
        System.out.println(horizontalLine);
    }

    public static void goodbyeUser() {
        String horizontalLine = "______________________\n";
        System.out.print(horizontalLine);
        System.out.println("Goodbye, see you next time!");
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        greetUser();

        Scanner in = new Scanner(System.in);
        String command = "";
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!command.equals("bye")) {
            command = in.nextLine();
            switch (command) {
            case "bye":
                goodbyeUser();
                break;
            case "list":
                listTasks(taskList);
                break;
            default:
                addTask(command, taskList);
                break;
            }
        }
    }
}
