import java.util.Scanner;

public class Alexis {
    //Declares array list of size 100 to store list
    public static String[] list = new String[100];
    public static int listIndex = 0;

    public static void addToList(String input){
        list[listIndex] = input;
        listIndex++;
    }
    public static void printList(){
        int i = 0;
        int itemIndex = 1;
        for (String item: list) {
            if (item != null) {
                System.out.printf("%d. %s\n", itemIndex, list[i]);
                i++;
                itemIndex++;
            }
        }
    }
    public static void main(String[] args) {
        String name = "   ('-.                 ('-.  ) (`-.               .-')    \n"
                + "  ( OO ).-.           _(  OO)  ( OO ).            ( OO ).  \n"
                + "  / . --. / ,--.     (,------.(_/.  \\_)-. ,-.-') (_)---\\_) \n"
                + "  | \\-.  \\  |  |.-')  |  .---' \\  \\/  /  |  |OO)/    _ |  \n"
                + ".-'-'  |  | |  | OO ) |  |      \\  ; /    |  |  \\\\  :` `.  \n"
                + " \\| |_.'  | |  |`-' |(|  '--.    \\   \\ |  |  |(_/ '..`''.) \n"
                + "  |  .-.  |(|  '---.' |  .--'   .' ,  \\_),|  |_.'.-._)   \\ \n"
                + "  |  | |  | |      |  |  `---. /  .''  \\(_|  |   \\       / \n"
                + "  `--' `--' `------'  `------''--'   '--' `--'    `-----'  \n";

        String lineBreak = "____________________________________________________________";
        String greeting = "Hello, I'm Alexis.\n"
                + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";

        //Prints name and greeting.
        System.out.println(name);
        System.out.println(lineBreak);
        System.out.println(greeting);
        System.out.println(lineBreak);

        //Loops the echo so users can input multiple lines. Breaks when users enter `bye`.
        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            //Terminates on command `bye`.
            if (line.equals("bye")) {
                break;
            }

            //Prints list on command `list`
            else if (line.equals("list")) {
                System.out.println(lineBreak);
                printList();
                System.out.println(lineBreak);
            }

            else {
                addToList(line);
                System.out.println(lineBreak);
                System.out.printf("Added: %s\n", line);
                System.out.println(lineBreak);
            }
        }

        //Prints goodbye message before terminating.
        System.out.println(lineBreak);
        System.out.println(goodbye);
        System.out.println(lineBreak);
    }
}
