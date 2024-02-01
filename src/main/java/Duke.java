import java.util.Scanner;
public class Duke {
    public static String[] list = new String[100];
    public static int listLength = 0;
    /*
    public static void echo(String input){
        System.out.println("____________________________________________________________\n"
                            + input + "\n"
                            + "____________________________________________________________");
    }
    */
    public static void add(String input){
        System.out.println("____________________________________________________________\n"
                + "added: " + input + "\n"
                + "____________________________________________________________");
        list[listLength] = input;
        listLength++;
    }
    public static void main(String[] args) {
        String chat_name = "Sigma";
        String output = "____________________________________________________________\n"
                + " Hello! I'm " + chat_name + "\n"
                + " What can I do for you?\n"
                +"____________________________________________________________\n";
        System.out.println(output);
        String line;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < listLength; i++){
                    System.out.print(i + ". ");
                    System.out.println(list[i] + '.');
                }
                System.out.println("____________________________________________________________");
            }
            else {
                add(line);
            }
        }
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +"____________________________________________________________\n");
    }
}

