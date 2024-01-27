import java.util.Scanner;


public class Sayo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] items = new String[100];
        int itemsCount = 0;
        
        System.out.println("Hello! I'm Sayo and it's great to see you! \n" + "What can I do for you today? \n");

        String input = " ";

        do {
            input = scanner.nextLine().trim();
            if(!input.equals("bye") && !input.equals("list")){
                items[itemsCount] = input;
                itemsCount++;
                System.out.println("added: " + input);
            } else if (input.equals("list")) {
                for (int i = 0; i < itemsCount; i++) {
                    System.out.println((i+1) + ". " + items[i]); 
                }
            }
        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
