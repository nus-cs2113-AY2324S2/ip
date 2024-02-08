import java.util.NoSuchElementException;
import java.util.Scanner;

public class Adam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String input;

        System.out.println(Message.GREETING_MESSAGE);

        while (scanner.hasNextLine()) {
            System.out.print(Message.DELIMITER);

            try {
                CommandGenerator.generate(scanner.nextLine())
                        .orElseThrow()
                        .execute(tasks);
            } catch (NoSuchElementException error) {
                System.out.println(Message.ERROR_MESSAGE);
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
