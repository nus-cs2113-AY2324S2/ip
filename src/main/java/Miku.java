import java.util.Scanner;

public class Miku {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("______________________");
        System.out.println("Hello! I'm Miku!\n" + "What can I do for you?");
        System.out.println("______________________");

        line = in.nextLine();
        String echo = line;
        String[] storedList = new String[100];
        int numberOfListItems = 0;

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("______________________");
                for (int i = 0; i < numberOfListItems; i++) {
                System.out.println(i+1 + ". " + storedList[i] + "\n");
                }
                System.out.println("______________________");
            } else {
                System.out.println("______________________");
                System.out.println("added: " + echo);
                storedList[numberOfListItems] = echo;
                numberOfListItems++;
                System.out.println("______________________");
            }
            line = in.nextLine();
            echo = line;
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________");
    }
}
