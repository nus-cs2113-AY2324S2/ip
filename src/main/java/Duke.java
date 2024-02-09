import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<String> enteredCommands=new ArrayList<String>();

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
            if(enteredCommands.size()<100) {
                System.out.printf("%s%n%n", "    ____________________________________________________________");
                userCommand = commandReader.nextLine();
                shouldExit = executesCommand(userCommand);
            }
            else{
                exit();
                shouldExit=true;
            }
        }



    }
    public static boolean executesCommand(String userCommand){
        if(userCommand.equalsIgnoreCase("bye")){
            exit();
            return true;
        }

        else if(userCommand.equalsIgnoreCase("list")){
            System.out.printf("%s%n","    ____________________________________________________________");
            for(int i=0;i< enteredCommands.size();i++){
                System.out.printf("     %d.%s%n",(i+1),enteredCommands.get(i));
            }
            return false;
        }

        else{
            System.out.printf("%s%n","    ____________________________________________________________");
            System.out.printf("     added: %s%n",userCommand);
            enteredCommands.add(userCommand);
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
