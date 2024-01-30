import java.util.Scanner;

public class Adam {
    public static void main(String[] args) {
        System.out.println(Messages.GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String input;
        Command command;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.print(Messages.HORIZONTAL_LINE);

            switch (input) {
            case "bye":
                command = new ExitCommand();
                sc.close();
                break;

            case "list":
                command = new ListCommand();
                break;

            default:
                if (input.matches("^(mark [0-9]|unmark [0-9]).*")) {
                    command = new ToggleStatusCommand(input);
                } else {
                    command = new AddTaskCommand(input);
                }
                break;
            }

            command.execute(tasks);
            System.out.println(Messages.HORIZONTAL_LINE);
        }

    }
}
