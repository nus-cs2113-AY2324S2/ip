import java.util.Scanner;

public class Adam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String input;

        System.out.println(Message.GREETING_MESSAGE);

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.print(Message.DELIMITER);

            if (new CommandGenerator().apply(input).isPresent()) {
                Command command = new CommandGenerator().apply(input).get();
                command.execute(tasks);
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
