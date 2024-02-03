import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class G_one {
    private List<String> textList;

    public G_one() {
        this.textList = new ArrayList<>();
    }

    public void start() {
        System.out.println("Hello! I'm G.one");
        System.out.println("--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.print("Whats up? ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                flag = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTextList();
            } else {
                addText(userInput);
                System.out.println("Well...." + userInput);
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void addText(String text) {
        textList.add(text);
    }

    private void displayTextList() {
        System.out.println("Your Text List:");
        for (int i = 0; i < textList.size(); i++) {
            System.out.println((i + 1) + ". " + textList.get(i));
        }
    }

    public static void main(String[] args) {
        G_one g_one = new G_one();
        g_one.start();
    }
}
