import java.util.Scanner;

public class Boop {
    public static String[] lst = new String[100];
    public static boolean[] isMarked = new boolean[100];
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
                if (comArr.length < 1) {
                    System.out.println("Please specify which item to mark");
                } else {
                    int markNum = Integer.parseInt(comArr[1]);
                    if (markNum > 1 && markNum <= count) {
                        isMarked[markNum - 1] = true;
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("[X] " + lst[markNum -1]);
                    } else {
                        System.out.println("This number is not on the list");
                    }
                }
            } else if (curCommand.equals("unmark")) {
                if (comArr.length < 1) {
                    System.out.println("Please specify which item to unmark");
                } else {
                    int markNum = Integer.parseInt(comArr[1]);
                    if (markNum > 1 && markNum <= count) {
                        isMarked[markNum - 1] = false;
                        System.out.println("Ok, I've marked this task as not done yet: ");
                        System.out.println("[] " + lst[markNum -1]);
                    } else {
                        System.out.println("This number is not on the list");
                    }
                }
            } else {
                lst[count] = curCommand;
                count += 1;
                System.out.println("added: " + curCommand);

            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        //System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLst() {
        for(int i = 0; i < count; i+= 1) {
            String res = "";
            if (isMarked[i]) {
                res += "[X] ";
            } else {
                res += "[ ] ";
            }
            System.out.println("" + (i+1) + "." + res + lst[i]);
        }

    }
}
