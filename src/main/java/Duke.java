import java.util.Scanner;

public class Duke {

    public static void greet(){
        System.out.printf("%s%n", "    ____________________________________________________________");
        System.out.printf("%s%n", "     Hello! I'm CHAT-MAN");
        System.out.printf("%s%n", "     What can I do for you?");

    }
    public static void exit(){
        System.out.printf("%s%n","    ____________________________________________________________");
        System.out.printf("%s%n","     Bye. Hope to see you again soon!");
        System.out.printf("%s%n","    ____________________________________________________________");
    }

    public static void getUserCommands(){
        boolean shouldExit=false;
        Scanner commandReader= new Scanner(System.in);
        String userCommand;

        while(!shouldExit){
            System.out.printf("%s%n%n","    ____________________________________________________________");
            userCommand=commandReader.nextLine();
            shouldExit=echoUserCommand(userCommand);
        }


    }
    public static boolean echoUserCommand(String userCommand){
        if(userCommand.equalsIgnoreCase("bye")){
            exit();
            return true;
        }
        else{
            System.out.printf("%s%n","    ____________________________________________________________");
            System.out.printf("     %s%n",userCommand);
            return false;
        }

    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        getUserCommands();

    }
}
