import java.util.NoSuchElementException;

public class Adam {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();

        System.out.println(Message.GREETING_MESSAGE);

        while (true) {
            System.out.print(Message.DELIMITER);

            try {
                CommandGenerator.generate()
                        .orElseThrow()
                        .execute(tasks);
            } catch (NoSuchElementException error) {
                System.out.println(Message.ERROR_MESSAGE);
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
