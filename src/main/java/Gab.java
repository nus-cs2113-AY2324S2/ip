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
            markTask(task); //need display as [ ][X] task name
            getTask();
        } else {
            switch (action) {
            case "bye":
                System.out.println("I hope you complete them!");
                System.exit(0);
                break;
            case "list":
                listTask();
                break;
            case "todo": //need display [T][ ] name
                setToDo(task);
                break;
            case "deadline":
                setDeadline(task);
                break;
            case "event":
                setEvent(task);
                break;
            }
            getTask();
        }
    }

    public static void listTask() { //using the array to list the tasks
        System.out.println("All your tasks are here");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            //Task task = taskList[i]; //instance of task in the display list
            //String taskName = task.getDescription();
            //String status = task.getStatusIcon(); //getting the status from the instance
            System.out.println((i+1) + "." + taskList[i].toString());
            //System.out.println((i + 1) + ". " + status + " " + taskName);
        }
    }

    public static void setToDo(String task) {
        String todoName = task.substring(5);
        Todo newToDo = new Todo(todoName);
        taskList[Task.getTaskCount() - 1] = newToDo; //creating new class(to do) in the array
        System.out.println("_______________________");
        System.out.println("Oh no! One new task added...");
        System.out.println(newToDo.toString());
        System.out.println("Now you have " + Task.getTaskCount() + " task(s)");
        System.out.println("_______________________");
    }

    public static void setDeadline (String task) {
        int deadlineIndex = task.indexOf("/by"); //gives index of /by
        String deadline = task.substring(deadlineIndex + 4);
        String deadlineTask = task.substring(9, deadlineIndex - 1);
        Deadline newDeadline = new Deadline(deadlineTask, deadline);
        taskList[Task.getTaskCount() - 1] = newDeadline;
        System.out.println("_______________________");
        System.out.println("Ok! Watch the deadline!");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + Task.getTaskCount() + " task(s)");
        System.out.println("_______________________");
    }

    public static void setEvent (String task) {
        int eventStartIndex = task.indexOf("/from"); //returns index of '/' of /from
        int eventEndIndex = task.indexOf("/to");
        String eventName = task.substring(6, eventStartIndex - 1);
        String eventStartDate = task.substring(eventStartIndex + 6, eventEndIndex - 1);
        String eventEndDate = task.substring(eventEndIndex + 3);
        Event newEvent = new Event(eventName, eventStartDate, eventEndDate);
        taskList[Task.getTaskCount() - 1] = newEvent;
        System.out.println("_______________________");
        System.out.println("Weehoo! Enjoy the event!");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + Task.getTaskCount() + " task(s)");
        System.out.println("_______________________");
    }

    public static void markTask (String tasks) {
        String[] task = tasks.split(" ");
        String taskIndex = task[1]; //getting the index task to mark or un mark
        int index = Integer.parseInt(taskIndex) - 1;

        Task taskToMark = taskList[index];
        String taskName = taskToMark.getDescription();
        if (task[0].equals("mark")) {
            taskToMark.markAsDone();
            System.out.println("Ok! One task down!");
        } else {
            taskToMark.markAsNotDone();
            System.out.println("Oh no! More tasks!");
        }
        String status = taskToMark.getStatusIcon();
        System.out.println(taskToMark.toString());
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


