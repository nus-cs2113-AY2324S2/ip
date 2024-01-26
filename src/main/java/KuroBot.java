import java.util.Scanner;
import java.util.Arrays;
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

        String[] lists = new String[100];
        int i = 0;
        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            if(input.equals("bye")){
                break;
            } else if (input.equals("list")){
                String[] validList = Arrays.copyOf(lists,i);
                int j = 1;
                System.out.println(line);
                for(String item: validList){
                    System.out.println(j + ". " + item);
                    j ++;
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
                lists[i++] = input;
            }
        }


        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.out.println(logo);
    }
}
