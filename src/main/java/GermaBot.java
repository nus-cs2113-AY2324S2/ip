import java.util.Scanner;

public class GermaBot {
    static int counter = 0;
    static Task[] toDoList = new Task[100];
    public static int getIdx(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1)) - 1;
    }

    public static void echo(String input) {
        if (input.contains("todo")) {
            System.out.println("Gotcha! Added '" + input + "' to your To Do List!");
        }
        else if (input.contains("deadline")) {
            System.out.println("Gotcha! Added '" + input + "' to your To Do List!" +
                    " You have to finish this by a certain time, so be reminded!");
        }
        else if (input.contains("event")) {
            System.out.println("Gotcha! Added '" + input + "' to your To Do List!" +
                    " This happens and ends at a specific time, so remember to check your calender! (Or ask me!)");
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
        /*Task t = new Task(input);
        toDoList[counter] = t;
        toDoList[counter].setDescription(input);
        System.out.println("Added '" + input + "' to your To Do List!");
        counter++;*/
    }

    public static void main(String[] args) {
        String WelcomeMessage = "____________________ \n"
                + "Hello! GermaBot here! \n"
                + "What may I do for you this fine day? \n"
                + "____________________";
        System.out.println(WelcomeMessage);
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
                    /*System.out.println(printCounter + ". [" + toDoList[j].getStatusIcon() + "] " + toDoList[j].getDescription());*/
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
        String GoodbyeMessage = "____________________ \n"
                + "Thanks for using me! Hope you again soon~! \n"
                + "____________________";
        System.out.println(GoodbyeMessage);
    }
}
