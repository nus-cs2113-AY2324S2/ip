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

        Task t = new Task();
        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            if(input.equals("bye")){
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                t.printTasks();
                System.out.println(line);
            } else if (input.startsWith("mark")){
                System.out.println(line);
                t.markTask(input);
                System.out.println(line);
            } else if (input.startsWith("unmark")){
                System.out.println(line);
                t.unmarkTask(input);
                System.out.println(line);
            }else {
                System.out.println(line);
                t.addTask(input);
                System.out.println(line);
            }
        }


        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.out.println(logo);
    }
}
