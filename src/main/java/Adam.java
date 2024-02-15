import java.util.Scanner;

import exception.AdamException;
import command.CommandGenerator;
import task.TaskList;
import ui.Message;

public class Adam {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean exitFlag = false;

        System.out.println(Message.GREETING_MESSAGE);

        while (!exitFlag) {
            input = scanner.nextLine();

            System.out.println(Message.DELIMITER);

            try {
                exitFlag = CommandGenerator.generate(input).execute(tasks);
                // return true if it's exitCommand; false otherwise
            } catch (AdamException error) {
                System.out.println(error.getMessage());
            }

            System.out.println(Message.DELIMITER);
        }

        scanner.close();
    }
}
