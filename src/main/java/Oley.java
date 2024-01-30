import java.util.Scanner;
public class Oley {
    public static void initialise(){
        String logo = "  _____  __       \n"
                + " /  _  \\|  | ____ ___  ___ \n"
                + "|  | |  |  |/ ___ \\  \\/  /\n"
                + "|  |_|  |  |  ____/\\    /\n"
                + " \\_____/|__|\\_____|/   /\n"
                + "                  /___/";
        System.out.println("Greetings from\n" + logo);
        lineBreaker();
        System.out.println("    Hello, I'm your cute and lovely friend Oley.");
        System.out.println("    What can I do for you?");
        lineBreaker();

    }
    public static void exist(){
        System.out.println("    Bye~ Feel free to talk to me anytime. I will always be here waiting for you. ฅʕ•̫͡•");
    }
    public static void lineBreaker(){
        System.out.println(" ");
        System.out.println("    🌙~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~🌙");
    }

    public static void instructions(){
        Scanner in = new Scanner(System.in);
        String message;
        message = in.nextLine();
        lineBreaker();
        while (message != null){
            if (message.equals("bye")){
                exist();
                return;
            }
            else if (message.equals("list")){
                Task.printTask();
                lineBreaker();
                message = in.nextLine();
                lineBreaker();
            } else if (message.contains("unmark") || message.contains("mark")){
                Task.mark(message);
                lineBreaker();
                message = in.nextLine();
                lineBreaker();
            } else {
                new Task(message);
                System.out.println("    " + "added: " + message);
                lineBreaker();
                message = in.nextLine();
                lineBreaker();
            }
        }
    }
    public static void main(String[] args) {
        initialise();
        instructions();
    }
}
