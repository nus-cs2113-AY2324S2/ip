import java.util.Scanner;

import exception.AdamException;
import command.CommandGenerator;
import task.TaskList;
import ui.Message;

public class Adam {
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        boolean exitFlag = false;

        try {
            tasks = FileManager.loadTasks();
        } catch (AdamException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Message.GREETING_MESSAGE);

        while (!exitFlag) {
            System.out.println(Message.DELIMITER);

            try (Scanner input = new Scanner(System.in)) {
                exitFlag = CommandGenerator.generate(input.nextLine()).execute(tasks);
                // return true if it's exitCommand; false otherwise
                FileManager.saveTasks(tasks);
            } catch (AdamException error) {
                System.out.println(error.getMessage());
            }

            System.out.println(Message.DELIMITER);
        }
    }
}
