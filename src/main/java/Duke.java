import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void main(String[] args) {
        String botName = "Battch";

        PrintText.printWithHorizon("Hello! I'm " + botName + "\n" +
                "What can I do for you?");

        Task[] tasks = new Task[100];

        AddTask.addTask(tasks);

        PrintText.printWithHorizon("Bye. Hope to see you again soon!");

    }
}
