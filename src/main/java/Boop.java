import java.util.Scanner;

public class Boop {
    //public static String[] lst = new String[100];
    //public static boolean[] isMarked = new boolean[100];
    public static Task[] tasks = new Task[100];
    public static int count = 0;

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm BOOP");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner in = new Scanner(System.in);
        String command;
        while(true) {
            System.out.println("____________________________________________________________");
            command= in.nextLine();
            String[] comArr = command.split(" ");
            String curCommand = comArr[0];
            if(curCommand.equals("bye")) {
                break;
            } else if (curCommand.equals("list")) {
                printLst();
            } else if (curCommand.equals("mark")) {
                mark(comArr);
            } else if (curCommand.equals("unmark")) {
                unmark(comArr);
            } else {
                addTask(command);
            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        //System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLst() {
        for(int i = 0; i < count; i += 1) {
            Task t = tasks[i];
            String res = "" + (i + 1) + ".[" + t.getStatus() + "] " + t.getDescription();
            System.out.println(res);
        }
    }

    public static void mark(String[] arr) {
        if(arr.length <= 1) {
            System.out.println("Please specify which item to mark");
            return;
        }
        int markNum;
        try {
            markNum = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a number to mark");
            return;
        }
        if (markNum >= 1 && markNum <= count) {
            tasks[markNum - 1].markTask();
        } else {
            System.out.println("This number is not on the list");
        }
    }

    public static void unmark(String[] arr) {
        if(arr.length <= 1) {
            System.out.println("Please specify which item to unmark");
            return;
        }
        int markNum;
        try {
            markNum = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a number to unmark");
            return;
        }
        if (markNum >= 1 && markNum <= count) {
            tasks[markNum - 1].unmarkTask();
        } else {
            System.out.println("This number is not on the list");
        }
    }

    public static void addTask(String desc) {
        Task t = new Task(desc);
        tasks[count] = t;
        count += 1;
        System.out.println("added: " + desc);

    }

}
