import java.util.Scanner;

public class Sayo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hello! I'm Sayo and it's great to see you! \n" + "What can I do for you today? \n");

        String input = " ";
        do {
            input = scanner.nextLine();
            if(!input.equals("bye")){
                System.out.println(input);
            }
        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
