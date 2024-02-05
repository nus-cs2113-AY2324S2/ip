public class Duke {
    public static void displayLogo() {
        String logo = 
                " _    _                          _          \n"
                + "| |  | |                        | |         \n"
                + "| |  | |_      ___   _ _ __  ___| |__   ___ \n"
                + "| |  | \\ \\ /\\ / / | | | '_ \\|_  / '_ \\ / _ \\\n"
                + "| |__| |\\ V  V /| |_| | | | |/ /| | | |  __/\n"
                + " \\____/  \\_/\\_/  \\__,_|_| |_/___|_| |_|\\___|\n";

        System.out.println(logo);
    }

    public static void addLineBreak() {
        String lineString = "____________________________________________________________";
        System.out.println(lineString);
    }

    public static void main(String[] args) {
        String botName = "Uwunzhe";
        displayLogo();
        
        addLineBreak();

        System.out.println("HELLO MY POSITIVE MENTALITY FLEN!! MY NAME IS " + botName.toUpperCase() + "!!!");
        System.out.println("Actually uh... What even do you want me to do?");

        addLineBreak();

        System.out.println("Good night my positive mentality flen, "
                + "it is time for me to take my happy pills. Bye Bye!");
        addLineBreak();
    }
}
