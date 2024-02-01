import java.util.Scanner;

public class Gandalf {
    public static void makeList() {
        Scanner in = new Scanner(System.in);
        String[] listActivities = new String[100];
        String byeStatement = "bye";
        final String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("What would you like to be added to the list?");
        System.out.println(line);
        String text = in.nextLine();

        int printIndex = 1;
        int insertIndex = 0;

        while (true) {
            printIndex = 1;
            if (text.equals(byeStatement)) {
                System.out.println(line);
                return;
            } else if (text.equals("list")) {
                for (String activity : listActivities) {
                    if (activity != null) {
                        System.out.println(printIndex + "." + " " + activity);
                        printIndex += 1;
                    }
                }
                System.out.println(line);
            } else {
                listActivities[insertIndex] = text;
                insertIndex += 1;
                System.out.println(line);
                System.out.println("added: " + text);
                System.out.println(line);
            }
            text = in.nextLine();
        }
    }

    public static void startEcho() {
        Scanner in = new Scanner(System.in);
        final String line = "____________________________________________________________";
        String byeStatement = "bye";
        String makeListStatement = "make list";
        String text = in.nextLine();
        boolean hasSaidBye = byeStatement.equals(text);
        boolean isMakeList = text.equals(makeListStatement);

        if (isMakeList) {
            makeList();
            return;
        }
        if (text.equals(byeStatement)) {
            System.out.println(line);
            return;
        }

        System.out.println(line);
        System.out.println(text);
        System.out.println(line);

        while (!hasSaidBye) {
            text = in.nextLine();
            System.out.println(line); //not being printed
            if (text.equals(byeStatement)) {
                hasSaidBye = true;
            } else if (text.equals(makeListStatement)) {
                makeList();
                return;
            } else {
                System.out.println(text);
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        final String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Gandalf");
        System.out.println("What can I do for you?");
        System.out.println(line);
        startEcho();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
