import java.util.Scanner;


public class Allez {
    public static void main(String[] args) {

        System.out.println("_________________________");
        System.out.println("Hello! I'm Allez");
        System.out.println("What can I do for you?");
        System.out.println("_________________________");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")){
            System.out.println(line);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
