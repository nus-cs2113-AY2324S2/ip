import java.util.Scanner;

public class Gab {

    private static Task[] taskList = new Task[100]; //array of Task instances

    public static void getTask() {
        String taskDescription;
        Scanner in = new Scanner(System.in);
        System.out.println("\tWhat do you want to do?: ");
        taskDescription = in.nextLine();
        checkKeywords(taskDescription); //check if its un mark/mark/exit/list
    }

    public static void checkKeywords (String task) { //going to input task description from getTask here
        String[] taskAction = task.split(" ");
        String action = taskAction[0];

        if (action.equals("mark") || action.equals("unmark")) {
            CommandHandler.markTask(task, taskList); //need display as [ ][X] task name
            getTask();
        } else {
            switch (action) {
            case "bye":
                System.out.println("I hope you complete them!");
                System.exit(0);
                break;
            case "list":
                Ui.listTask(taskList);
                break;
            case "todo": //need display [T][ ] name
                CommandHandler.setToDo(task, taskList);
                break;
            case "deadline":
                CommandHandler.setDeadline(task, taskList);
                break;
            case "event":
                CommandHandler.setEvent(task, taskList);
                break;
            }
            getTask();
        }
    }


    public static void main(String[] args) {
        String logo =
                          "  _____           __ \n"
                        + "/  ____|         |  |\n"
                        + "|  |  __   ____  |  |__\n"
                        + "|  | |_  |/  _   |  -   \\ \n"
                        + "|  |__|  |  (_|  | |_)   |\n"
                        + "\\_______ |__ ,_ |_.___ /\n";

        System.out.println(logo);
        System.out.println("\tI am Gab the Bot! Nice to meet you!");
        System.out.println("\tAnything I can help you with?");

        Gab.getTask();
    }
}


