import java.util.Scanner;

public class Cody {

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    " " + input + "\n" +
                    "_____________________________________________________________");
            input = in.nextLine();
        }
    }

    private static void createList() {
        String[] list = new String[100];
        int listSize = 0;
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < listSize; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                System.out.println("_____________________________________________________________");
            } else {
                list[listSize] = input;
                listSize++;
                System.out.println("______________________________________________________________\n" +
                        " added: " + input + "\n" +
                        "_____________________________________________________________");
            }
            input = in.nextLine();
        }
    }

    private static void greet() {
        String greet = " ____________________________________________________________\n" +
                " Hello! I'm Cody\n" +
                " Tell me your tasks and I will create a task list for you\n" +
                "_____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void exit() {
        String exit = "_____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "_____________________________________________________________";
        System.out.println(exit);
    }

    public static void main(String[] args) {
        greet();
        createList();
        exit();
    }
}

