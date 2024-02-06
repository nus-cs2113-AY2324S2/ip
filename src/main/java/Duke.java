import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int counter = 0;
        boolean isExit = false;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        System.out.println("Hello I'm Bobby\n" + "What can I do for you?");

        while (!isExit) {
            String input = in.nextLine();
            String command;
            if (input.indexOf(' ') > 0) {
                command = input.substring(0, input.indexOf(' '));
            } else {
                command = input;
            }
            int entry;
            switch (command) {
            case "bye":
                System.out.println("See you again soon!");
                isExit = true;
                break;
            case "mark":
                entry = Integer.parseInt(input.substring(5));
                if (entry > 0 && entry <= counter) {
                    tasks[entry - 1].setDone(true);
                    System.out.println("Marked as done");
                    System.out.println(entry + "." + tasks[entry - 1]);
                }
                break;
            case "unmark":
                entry = Integer.parseInt(input.substring(7));
                if (entry > 0 && entry <= counter) {
                    tasks[entry - 1].setDone(false);
                    System.out.println("Unmarked");
                    System.out.println(entry + "." + tasks[entry - 1]);
                }
                break;
            case "list":
                for (int j = 0; j < counter; j += 1) {
                    System.out.print((j + 1) + ".[" + tasks[j].getStatusIcon() + "] ");
                    tasks[j].printDescription();
                }
                break;
            default:
                tasks[counter] = new Task(input);
                System.out.println("added: " + tasks[counter].description);
                counter += 1;
                //System.out.println(counter);
                break;
            }
        }
    }
}