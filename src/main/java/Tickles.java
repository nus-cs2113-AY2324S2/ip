
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Tickles {

    private static boolean stillGoing = true;
    private static List<String> list = new ArrayList<>();
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
        } else if (prompt.equals("list")) {
            printList();
        }
        else {
            addToList(prompt);
            printThis("added: " + prompt);
        }

    }

    public static void printList() {
        int counter = 1;
        for (String s : list) {
            System.out.println(counter + ". " + s);
            counter += 1;
        }
    }

    public static void addToList(String str) {
        list.add(str);
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
