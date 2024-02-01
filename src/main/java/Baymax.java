import java.util.Scanner;

public class Baymax {
    public static void main(String[] args) {
        String name = "Baymax";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?\n\n");

        String[] list = new String[100];
        int num = 0;

        while(true) {
            String text;

            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            if (text.equalsIgnoreCase("bye")) {
                break;
            }
            else if (text.equalsIgnoreCase("list")) {
                for (int i = 0; i < 100; i++) {

                    if(list[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ". " + list[i]);
                }
            }
            else {
                System.out.println("added: " + text);
                list[num] = text;
                num++;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
