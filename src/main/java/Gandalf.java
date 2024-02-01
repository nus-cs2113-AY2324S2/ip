import java.util.Scanner;

public class Gandalf {
    public static void makeList() {
        //Array of class Task to store all in to do lists
        Task[] listTasks = new Task[100];
        Scanner in = new Scanner(System.in);
        final String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("What would you like to be added to the list?");
        System.out.println(line);
        String text = in.nextLine();

        int insertIndex = 0;

        while (true) {
            if (text.equals("bye")) {
                System.out.println(line);
                return;
            } else if (text.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listTasks.length; i++) {
                    if (listTasks[i] != null) {
                        System.out.println((i + 1) + "." + " " + listTasks[i].getStatusIcon() + " " + listTasks[i].getDescription());
                    }
                }
                System.out.println(line);
            } else if (text.startsWith("mark ")) {
                int indexToMark = Integer.parseInt(text.substring(5).trim());
                if (indexToMark >= 1 && indexToMark <= listTasks.length && listTasks[indexToMark - 1] != null) {
                    listTasks[indexToMark - 1].markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listTasks[indexToMark - 1].getStatusIcon() + " " + listTasks[indexToMark - 1].getDescription());
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Invalid task index. Please try again.");
                    System.out.println(line);
                }
            } else if (text.startsWith("unmark ")) {
                int indexToUnmark = Integer.parseInt(text.substring(7).trim());
                if (indexToUnmark >= 1 && indexToUnmark <= listTasks.length && listTasks[indexToUnmark - 1] != null) {
                    listTasks[indexToUnmark - 1].unmarkAsDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(listTasks[indexToUnmark - 1].getStatusIcon() + " " + listTasks[indexToUnmark - 1].getDescription());
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Invalid task index. Please try again.");
                    System.out.println(line);
                }
            } else {
                if (insertIndex < listTasks.length) {
                    listTasks[insertIndex] = new Task(text);
                    insertIndex += 1;
                    System.out.println(line);
                    System.out.println("added: " + text);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("List is full. Cannot add more items.");
                    System.out.println(line);
                }
            }
            text = in.nextLine();
        }
    }

    public static void startEcho() {
        Scanner in = new Scanner(System.in);
        final String line = "____________________________________________________________";
        String byeStatement = "bye";
        String makeListStatement = "make list";
        String text = in.nextLine();
        boolean hasSaidBye = byeStatement.equals(text);
        boolean isMakeList = text.equals(makeListStatement);

        if (isMakeList) {
            makeList();
            return;
        }
        if (text.equals(byeStatement)) {
            System.out.println(line);
            return;
        }

        System.out.println(line);
        System.out.println(text);
        System.out.println(line);

        while (!hasSaidBye) {
            text = in.nextLine();
            System.out.println(line);
            if (text.equals(byeStatement)) {
                hasSaidBye = true;
            } else if (text.equals(makeListStatement)) {
                makeList();
            } else {
                System.out.println(text);
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Gandalf");
        System.out.println("What can I do for you?");
        System.out.println(line);
        startEcho();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
