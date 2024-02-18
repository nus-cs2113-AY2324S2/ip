import java.util.Scanner;

import exception.AdamException;
import command.CommandGenerator;
import task.TaskList;
import ui.Message;

public class Adam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String input;
        boolean exitFlag = false;

        try {
            tasks = FileManager.loadTasks();
        } catch (AdamException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Message.GREETING_MESSAGE);

        while (!exitFlag) {
            input = scanner.nextLine();
            System.out.println(Message.DELIMITER);

            try {
                exitFlag = CommandGenerator.generate(input).execute(tasks);
                // return true if it's exitCommand; false otherwise
                FileManager.saveTasks(tasks);
            } catch (AdamException error) {
                System.out.println(error.getMessage());
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
