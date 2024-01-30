import java.util.Scanner;

class Task {
    private String description;
    private boolean isDone;
    public static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void printTask() {
        if (this.isDone) {
            System.out.println("[X] " + this.description);
        } else {
            System.out.println("[ ] " + this.description);
        }
    }
}


enum Commands {
    list, mark, unmark, add, bye
}


public class Asuka {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Asuka\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        // Read input
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        Task[] tasks = new Task[100];
        int count = 0;
        int i;
        boolean toDo = false;
        boolean toUndo = false;
        //Mark which task to do or undo
        while (myObj != null) {
            for (i = 1; i <= count; i++) {
                if (input.equals("mark " + i)) {
                    toDo = true;
                    break;
                } else if (input.equals("unmark " + i)) {
                    toUndo = true;
                    break;
                }
            }
            // Determine command
            Commands command;
            if (input.equals("bye")) {
                command = Commands.bye;
            } else if (input.equals("list")) {
                command = Commands.list;
            } else if (toDo) {
                command = Commands.mark;
            } else if (toUndo) {
                command = Commands.unmark;
            } else {
                command = Commands.add;
            }
            // Execute command
            switch (command) {
            case mark:
                System.out.println(
                        "____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                tasks[i - 1].markAsDone();
                tasks[i - 1].printTask();
                System.out.println(
                        "____________________________________________________________");
                toDo = false;
                input = myObj.nextLine();
                break;
            case unmark:
                System.out.println(
                        "____________________________________________________________");
                System.out.println("Nice! I've marked this task as undone: ");
                tasks[i - 1].markAsUndone();
                tasks[i - 1].printTask();
                System.out.println(
                        "____________________________________________________________");
                toUndo = false;
                input = myObj.nextLine();
                break;
            case list:
                System.out.println(
                        "____________________________________________________________");
                for (int j = 0; j < count; j++) {
                    System.out.print(j + 1 + ". ");
                    tasks[j].printTask();
                }
                System.out.println(
                        "____________________________________________________________");
                input = myObj.nextLine();
                break;
            case add:
                System.out.println(
                        "____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println(
                        "____________________________________________________________");
                tasks[count] = new Task(input);
                count++;
                input = myObj.nextLine();
                break;
            case bye:
                System.out.println(
                        "____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(
                        "____________________________________________________________");
                myObj.close();
                myObj = null;
                break;
            }
        }
    }
}
