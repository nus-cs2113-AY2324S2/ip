import java.util.NoSuchElementException;
import java.util.Scanner;

public class Adam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList tasks = new TaskList();

        System.out.println(Message.GREETING_MESSAGE);

        while (input.hasNextLine()) {
            System.out.print(Message.DELIMITER);

            try {
                CommandGenerator.generate(input.nextLine())
                        .orElseThrow()
                        .execute(tasks);
            } catch (NoSuchElementException error) {
                System.out.println(Message.ERROR_MESSAGE);
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
