
import java.util.Scanner;


public class Eln {
    private static final String LINE = "____________________________________________________________";
    private static Task[] tasksList = new Task[100]; // max number of tasks set to 100
    private static int numOfTasks = 0;

    private static void greeting() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Eln");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void showList() {
        System.out.println(LINE);
        if(numOfTasks == 0) {
            System.out.println("there are no pending tasks");
        }
        for(int i = 0; i < numOfTasks; i++) {
            System.out.println((i+1) + ". " + tasksList[i].toString());
        }
        System.out.println(LINE);
    }

    private static void addTask(String taskToAdd) {

        Task newTask = new Todo(taskToAdd);

        tasksList[numOfTasks] = newTask;
        numOfTasks++;

        //addTaskResponse
        System.out.println(LINE);
        System.out.println("The following task has been added as task number " + numOfTasks);
        System.out.println(newTask.toString());
        System.out.println(LINE);
    }

    private static void mark(String taskToMark) {
        int taskNumToMark = Integer.parseInt(taskToMark.substring("mark".length()).trim()) - 1;
        tasksList[taskNumToMark].markAsDone();

        //markAsDone response
        System.out.println(LINE);
        System.out.println("The following task was marked");
        System.out.println(tasksList[taskNumToMark].toString());
        System.out.println(LINE);
    }

    private static void unmark(String taskToUnmark) {
        int taskNumToUnmark = Integer.parseInt(taskToUnmark.substring("unmark".length()).trim()) - 1;
        tasksList[taskNumToUnmark].markAsUndone();

        //markAsDone response
        System.out.println(LINE);
        System.out.println("The following task was unmarked");
        System.out.println(tasksList[taskNumToUnmark].toString());
        System.out.println(LINE);
    }



    private static void taskManager() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine(); // read user input

        while (!userInput.equalsIgnoreCase("bye")){
            if (userInput.equalsIgnoreCase("list")) {
                showList();
            } else if (userInput.startsWith("mark")) {
                mark(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmark(userInput);
            } else {
                addTask(userInput);
            }
            userInput = in.nextLine();
        }

    }
    public static void main(String[] args) {
        greeting();
        taskManager(); // taskManager ends when user inputs "bye"
        farewell();
    }
}



    /*private static void echoUser() {
        Scanner scan = new Scanner(System.in);
        String input = "";
        String[] list = new String[20];
        int index = 0;

        while(!input.equals("bye")) {
            input = scan.nextLine();

            if(input.equals("list")) {
                System.out.println(LINE);
                if(index == 0) {
                    System.out.println("list is empty");
                }
                for(int i = 0; i < index; i++) {
                    System.out.println((i+1) + ". " + list[i]);
                }
                System.out.println(LINE);
            } else {
                list[index] = input;
                System.out.println(LINE);
                System.out.println("added: " + input);
                System.out.println(LINE);
                index++;
            }
        }
    }*/