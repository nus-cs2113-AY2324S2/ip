import java.util.NoSuchElementException;
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

            try {
                Command command = new CommandGenerator().apply(input).orElseThrow();
                command.execute(tasks);
            } catch (NoSuchElementException error) {
                System.out.println(Message.ERROR_MESSAGE);
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
