import java.util.Scanner;

public class Alexis {
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

        TaskList tasks = new TaskList();

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
                tasks.printTasks();
                System.out.println(lineBreak);
            }

            else {
                System.out.println(lineBreak);
                if (line.startsWith("mark")) {
                    tasks.markTask(line);
                }
                else if (line.startsWith("unmark")) {
                    tasks.unmarkTask(line);
                }
                else {
                    tasks.addTask(line);
                }
                System.out.println(lineBreak);

            }
            }

        //Prints goodbye message before terminating.
        System.out.println(lineBreak);
        System.out.println(goodbye);
        System.out.println(lineBreak);
    }
}
