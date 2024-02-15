import java.util.Scanner;

public class GermaBot {
    static int counter = 0;
    static final String LINE= "____________________________________________";
    static Task[] toDoList = new Task[100];
    public static int getIdx(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    public static void echo(String input) {
        if (input.contains("todo")) {
            String toDoTask = input.substring(input.indexOf("todo ") + 5);
            System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!");
        }
        else if (input.contains("deadline")) {
            String description = input.replaceFirst("deadline ", "");
            int idxOfEndDate = description.indexOf("/");
            String toDoTask = description.substring(0, idxOfEndDate - 1);
            String Date = description.substring(idxOfEndDate + 4);
            System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!" +
                    " You have to finish this by "  + Date + ", so be reminded!");
        }
        else if (input.contains("event")) {
            String description = input.replaceFirst("event ", "");
            int idxOfFrom = description.indexOf("/from");
            int idxOfBy = description.indexOf("/to");
            String startDate = description.substring(idxOfFrom + 6, idxOfBy - 1);
            String endDate = description.substring(idxOfBy + 4);
            String toDoTask = description.substring(0, idxOfFrom - 1);
            System.out.println("Gotcha! Added '" + toDoTask + "' to your To Do List!" +
                    " This will happen from " + startDate + " to " + endDate + ", so please remember to mark it" +
                    " on your calender! (Or ask me!)");
        }
        else {
            System.out.println("Uhh.. I'm sorry but I'm not quite sure what '" + input + "' means..." +
                    " Remember to include the Task Type in front of the input!!");
        }
    }

    public static void createTask(String input) {
        if (input.contains("todo")) {
            toDoList[counter] = new ToDo(input.substring(input.indexOf("todo ") + 5));
            counter++;
            echo(input);
        }
        else if (input.contains("deadline")) {
            String description = input.replaceFirst("deadline ", "");
            int idxOfEndDate = description.indexOf("/");
            String Date = description.substring( idxOfEndDate + 4);
            toDoList[counter] = new Deadline(description.substring(0, idxOfEndDate - 1), Date);
            counter++;
            echo(input);
        }
        else if(input.contains("event")) {
            String description = input.replaceFirst("event ", "");
            int idxOfFrom = description.indexOf("/from");
            int idxOfBy = description.indexOf("/to");
            String startDate = description.substring(idxOfFrom + 6, idxOfBy - 1);
            String endDate = description.substring(idxOfBy + 4);
            toDoList[counter] = new Event(description.substring(0, idxOfFrom - 1), startDate, endDate);
            counter++;
            echo(input);
        } else {
            echo(input);
        }
    }

    public static void main(String[] args) {
        String WelcomeMessage = "Hello! GermaBot here! \n"
                + "What may I do for you this fine day?";
        System.out.println(LINE);
        System.out.println(WelcomeMessage);
        System.out.println(LINE);
        while (true) {
            String input;
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                int printCounter = 1;
                System.out.println("Gotcha! Here are your tasks:");
                for (int i = 0; i < counter; i++) {
                    if (toDoList[i] == null) {
                        break;
                    }
                    System.out.println(printCounter + ". " + toDoList[i].toString());
                    printCounter++;
                }
            } else if (input.contains("unmark")) {
                int idx = getIdx(input);
                toDoList[idx].setDone(false);
                System.out.println("Aww, not done? Okay, I'll mark this task as undone: "
                        + "[" + toDoList[idx].getStatusIcon() + "] " + toDoList[idx].getDescription());
            } else if (input.contains("mark")) {
                int idx = getIdx(input);
                toDoList[idx].setDone(true);
                System.out.println("Good job! I'll mark this task as done: "
                        + "[" + toDoList[idx].getStatusIcon() + "] " + toDoList[idx].getDescription());
            } else {
                createTask(input);
            }
        }
        String GoodbyeMessage = "Thanks for using me! Hope to see you again soon~!";
        System.out.println(LINE);
        System.out.println(GoodbyeMessage);
        System.out.println(LINE);
    }
}
