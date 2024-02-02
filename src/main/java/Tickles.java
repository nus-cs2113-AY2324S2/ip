
import java.util.Scanner;
public class Tickles {

    private static boolean stillGoing = true;
    public static void main(String[] args) {
        String logo =
            "╱╭╮╱╱╱╱╭╮╱╭╮\n" +
            "╭╯╰╮╱╱╱┃┃╱┃┃\n" +
            "╰╮╭╋┳━━┫┃╭┫┃╭━━┳━━╮\n" +
            "╱┃┃┣┫╭━┫╰╯┫┃┃┃━┫━━┫\n" +
            "╱┃╰┫┃╰━┫╭╮┫╰┫┃━╋━━┃\n" +
            "╱╰━┻┻━━┻╯╰┻━┻━━┻━━╯\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");
        while (stillGoing) {
            promptUser();
        }
    }

    public static void promptUser() {
        Scanner in = new Scanner(System.in);
        String prompt = in.nextLine();
        if (prompt.equals("bye")) {
            stillGoing = false;
            printThis("Bye. Hope to see you again soon! Mr. Tickles will miss you.");
        } else {
            printThis(prompt);
        }

    }

    public static void printThis(String str) {
        printLine();
        System.out.println(str);
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
