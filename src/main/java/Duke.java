import java.util.Scanner;
public class Duke {
    public static void echo(String input){
        System.out.println("____________________________________________________________\n"
                            + input + "\n"
                            + "____________________________________________________________");
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
            if (!line.equals("bye")) {
                echo(line);
            }
            else {
                break;
            }
        }
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +"____________________________________________________________\n");
    }
}

/*
____________________________________________________________
 Hello! I'm [YOUR CHATBOT NAME]
 What can I do for you?
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
 */