import java.util.Scanner;

public class Adam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String input;
        Command command;

        System.out.println(Messages.GREETING_MESSAGE);

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.print(Messages.HORIZONTAL_LINE);

            if (input.equals("bye")) {
                command = new ExitCommand();
                sc.close();
            } else if (input.equals("list")) {
                command = new ListCommand();
            } else if (input.matches("^(mark [0-9]|unmark [0-9])")) {
                command = new ToggleStatusCommand(input);
            } else {
                command = new AddTaskCommand(input);
            }

            command.execute(tasks);
            System.out.println(Messages.HORIZONTAL_LINE);
        }

    }
}
