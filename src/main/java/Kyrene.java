import java.util.Scanner;

public class Kyrene{
    final static String LOGO = "    _   _  _    _  ____   ____  _   _  ____\n"
            + "    | | / /\\ \\  / /|  _ \\ | ___|| \\ | || ___|\n"
            + "    | |/ /  \\ \\/ / | |_| || ===||  \\| || ===|\n"
            + "    | |\\ \\   |  |  | |\\ / | |__ | |\\  || |__\n"
            + "    |_| \\_\\  |__|  |_| \\_\\|____||_| \\_||____|   by Zhou Junmin\n";
    final static String DIVIDER = "    ____________________________________________________________\n";
    final static String GREETING = "    Hi, I am Kyrene, your private reminder assistant.\n"
            + "    What can I do for you?\n";
    final static String BYE = "    Bye! Wish to see you again soon!\n";

    public static void initKyrene(){
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public static void exitKyrene(){
        System.out.println(BYE);
        System.out.println(DIVIDER);
    }

    public static boolean echo(String sentence){
        if(sentence.equals("bye")){
            exitKyrene();
            return true;
        }
        else{
            System.out.println("    " + sentence);
            System.out.println(DIVIDER);
        }
        return false;
    }

    public static void main(String[] args) {
        initKyrene();
        Scanner input = new Scanner(System.in);
        String line;
        boolean exitFlag = false;
        while (!exitFlag){
            line = input.nextLine();
            exitFlag = echo(line);
        }
    }

}
