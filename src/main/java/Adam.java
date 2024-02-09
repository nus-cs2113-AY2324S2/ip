import java.util.NoSuchElementException;
import java.util.Scanner;

public class Adam {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean exit = false;

        System.out.println(Message.GREETING_MESSAGE);

        while (!exit) {
            input = scanner.nextLine();

            System.out.println(Message.DELIMITER);

            try {
                exit = CommandGenerator.generate(input)
                        .orElseThrow()
                        .execute(tasks); // return true if it's exitCommand; false otherwise
            } catch (NoSuchElementException error) {
                System.out.println(Message.ERROR_MESSAGE);
            }

            System.out.println(Message.DELIMITER);
        }

        scanner.close();
    }
}
