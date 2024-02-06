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

        System.out.println(name);
        System.out.println(lineBreak);
        System.out.println(greeting);
        System.out.println(lineBreak);

        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                System.out.println(lineBreak);
                tasks.printTasks();
                System.out.println(lineBreak);
            } else {
                System.out.println(lineBreak);
                if (line.startsWith("mark")) {
                    tasks.markTask(line);
                } else if (line.startsWith("unmark")) {
                    tasks.unmarkTask(line);
                } else {
                    int firstSpace = line.indexOf(" ");
                    String firstWord = line.substring(0, firstSpace);
                    String description = line.substring(firstSpace);
                    TaskType taskType = TaskType.getTaskType(firstWord);
                    switch (taskType) {
                    case TODO:
                    default:
                        tasks.addTask(TaskType.TODO, description);
                        break;
                    case DEADLINE:
                        tasks.addTask(TaskType.DEADLINE, description);
                        break;
                    case EVENT:
                        tasks.addTask(TaskType.EVENT, description);
                        break;
                    }
                }
                System.out.println(lineBreak);
            }
        }
        System.out.println(lineBreak);
        System.out.println(goodbye);
        System.out.println(lineBreak);
    }
}
