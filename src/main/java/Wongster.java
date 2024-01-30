import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Wongster {
    public static void main(String[] args) {
        String name = "Wongster";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n");
        Echo();
    }

    public static void Addlist(List<String> userList, String newItem) {
        userList.add(newItem);
        System.out.println("Added: " + newItem);
    }

    public static void Printlist(List<String> userList) {
        System.out.println("list \n");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + ". " + userList.get(i));
        }
    }

    public static void Echo() {
        List<String> userList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see  you again soon!");
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                Printlist(userList);
            } else {
                Addlist(userList, userInput);
            }

        }
        scanner.close();
    }
}
