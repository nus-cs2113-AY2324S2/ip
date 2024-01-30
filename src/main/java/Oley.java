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
        System.out.println("Hello, I'm your cute and lovely friend Oley.");
        System.out.println("What can I do for you?");
        lineBreaker();

    }
    public static void echo(){
        Scanner in = new Scanner(System.in);
        String message;
        message = in.nextLine();
        lineBreaker();
        while (message != null){
            if (message.equals("bye")){
                System.out.println("Bye~ Feel free to talk to me anytime. I will always be here waiting for you. ฅʕ•̫͡•");
                lineBreaker();
                return;
            } else {
                System.out.println(message + " ʕง•ᴥ•ʔง");
                lineBreaker();
                message = in.nextLine();
                lineBreaker();
            }
        }
    }
    public static void lineBreaker(){
        System.out.println(" ");
        System.out.println("~~~~~♥~~~~~♥~~~~~♥~~~~~♥~~~~~");
    }
    public static void main(String[] args) {
        initialise();
        echo();
    }
}
