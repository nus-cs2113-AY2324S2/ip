import java.util.Scanner;

public class GermaBot {
    public static int getIdx(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    public static void main(String[] args) {
        String WelcomeMessage = "____________________ \n"
                + "Hello! GermaBot here! \n"
                + "What may I do for you this fine day? \n"
                + "____________________";
        System.out.println(WelcomeMessage);
        Task[] toDoList = new Task[100];
        int counter = 0;
        while (true) {
            String echo;
            Scanner in = new Scanner(System.in);
            echo = in.nextLine();
            if (echo.equals("bye")) {
                break;
            }
            if (echo.equals("list")) {
                int printCounter = 1;
                System.out.println("Gotcha! Here are your tasks:");
                for (int j = 0; j < counter; j++) {
                    if (toDoList[j] == null) {
                        break;
                    }
                    System.out.println(printCounter + ". [" + toDoList[j].getStatusIcon() + "] " + toDoList[j].getDescription());
                    printCounter++;
                }
            } else if (echo.contains("unmark")) {
                int idx = getIdx(echo);
                toDoList[idx].setDone(false);
                System.out.println("Aww, not done? Okay, I'll mark this task as undone: "
                        + "[" + toDoList[idx].getStatusIcon() + "] " + toDoList[idx].getDescription());
            } else if (echo.contains("mark")) {
                int idx = getIdx(echo);
                toDoList[idx].setDone(true);t
                System.out.println("Good job! I'll mark this task as done: "
                        + "[" + toDoList[idx].getStatusIcon() + "] " + toDoList[idx].getDescription());
            } else {
                Task t = new Task(echo);
                toDoList[counter] = t;
                System.out.println("Added '" + echo + "' to the list!");
                counter++;
            }
        }
        String GoodbyeMessage = "____________________ \n"
                + "Thanks for using me! Hope you again soon~! \n"
                + "____________________";
        System.out.println(GoodbyeMessage);
    }
}
