import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Wongster {
    public static void main(String[] args) {
        String name = "Wongster";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n");
        Echo();
    }

    public static void Addlist(List<String> userList, String newItem, List<Boolean> markedList) {
        userList.add(newItem);
        markedList.add(false);
        System.out.println("Added: " + newItem);
    }

    public static void Printlist(List<String> userList, List<Boolean> markedList) {
        System.out.println("Here are tasks in your list: \n");
        for (int i = 0; i < userList.size(); i++) {
            String marking = markedList.get(i) ? "[x]" : "[ ]";
            System.out.println((i + 1) + "." + marking + " " + userList.get(i));
        }
    }

    public static void Marklist(List<String> userList, List<Boolean> markedList, String indexStr) {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < userList.size()) {
                markedList.set(index, true);
                System.out.println("Nice! I've marked this task as done: \n");
                System.out.println("[X] " + userList.get(index));
            }
    }

    public static void Unmarklist(List<String> userList, List<Boolean> markedList, String indexStr) {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < userList.size()) {
                markedList.set(index, false);
                System.out.println("OK, I've marked this task as not done yet: \n");
                System.out.println("[ ] " + userList.get(index));
            }
    }

    public static void Echo() {
        List<String> userList = new ArrayList<>();
        List<Boolean> markedList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see  you again soon!");
                break;
            }
            if (userInput.startsWith("mark") && userInput.length() > 5) {
                Marklist(userList, markedList, userInput.substring(5));
            } else if (userInput.startsWith("unmark") && userInput.length() > 7) {
                Unmarklist(userList, markedList, userInput.substring(7));
            } else if (userInput.equalsIgnoreCase("list")) {
                Printlist(userList, markedList);
            } else {
                Addlist(userList, userInput, markedList);
            }

        }
        scanner.close();
    }
}
