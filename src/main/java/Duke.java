import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name ="Altria";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm "+name);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String instruction = in.nextLine();
        while(!instruction.equals("bye")) //main loop of the chat bot
        {
            System.out.println("\n"+"\t"+instruction+"\n");
            instruction = in.nextLine();
        }
        System.out.println("\n"+"\t"+"Bye.Hope to see you again soon!");
    }
}
