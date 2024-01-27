import java.util.Scanner;
public class Yuki {

    public static void printLine() {
        System.out.println("---------------------------------------------");
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I am Yuki, your personal chat bot.");
        System.out.println("What can I do for you?");
        printLine();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        String[] tasks = new String[100];
        int count = 0;

        while (!line.equals("bye")) {
            printLine();
            if (line.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(Integer.toString(i+1) + ". " + tasks[i]);
                }
            } else {
                tasks[count] = line;
                System.out.println("added: " + line);
                count++;
            }
            printLine();
            line = in.nextLine();
        }

        printLine();
        System.out.println("Bye bye!");
        printLine();

    }
}
