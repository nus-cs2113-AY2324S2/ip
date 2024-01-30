import java.util.Scanner;

public class Kyrene {
    final static String LOGO = "    _   _  _    _  ____   ____  _   _  ____\n"
            + "    | | / /\\ \\  / /|  _ \\ | ___|| \\ | || ___|\n"
            + "    | |/ /  \\ \\/ / | |_| || ===||  \\| || ===|\n"
            + "    | |\\ \\   |  |  | |\\ / | |__ | |\\  || |__\n"
            + "    |_| \\_\\  |__|  |_| \\_\\|____||_| \\_||____|   by Zhou Junmin\n";
    final static String DIVIDER = "    ⭐__________________________________________________________⭐\n";
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

    public static boolean converseKyrene(String sentence){
        System.out.println(DIVIDER);
        if(sentence.equals("bye")){
            exitKyrene();
            return true;
        }
        else if(sentence.equals("list")){
            Task.printTaskList();
            System.out.println(DIVIDER);
        }
        else{
            Task newTask = new Task(sentence);
            System.out.println("    Task has been added: " + sentence);
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
            exitFlag = converseKyrene(line);
        }
    }

}
