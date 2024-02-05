import java.util.Scanner;
public class Cody {

    private static void greet() {
        String greet = " ____________________________________________________________\n"
                + " Hey there! I'm Cody, your personal task manager\n"
                + " Tell me your tasks and I will create a task list for you\n"
                + "_____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void exit() {
        String exit = "_____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "_____________________________________________________________";
        System.out.println(exit);
    }

    public static void main(String[] args) {
        greet();
        TaskManager taskManager = new TaskManager();
        exit();
    }
}

