import java.util.Scanner;
public class KuroBot {
    public static void main(String[] args) {
        String logo =
                " ___   ___    ___    ___ \n"
                        + "|   |/   /   |  |   |  | \n"
                        + "|       /    |  |   |  | \n"
                        + "|   |\\   \\   |_ |___| _| \n"
                        + "|___| \\___\\    |_____|   \n";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm KuroBot\n" + "What can I do for you?");
        System.out.println(line);
        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            if(input.equals("bye")){
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.out.println(logo);
    }
}
