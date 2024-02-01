import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Stella";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------");
        String[] list = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")){
            if (!line.equals("list")) {
                System.out.println("-----------------------------------");
                System.out.println("added: " + line);
                list[index] = line;
                index++;
                System.out.println("-----------------------------------");
            } else {
                System.out.println("-----------------------------------");
                for (int i = 0; i < index; i++){
                    System.out.println((i + 1) + ". " + list[i]);
                }
                System.out.println("-----------------------------------");
            }
            line = in.nextLine();
        }
        System.out.println("-----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------------------");
    }
}
