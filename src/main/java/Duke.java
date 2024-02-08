public class Duke {

    public static void main(String[] args) {
        String botName = "Battch";

        PrintText.printWithLinebreak("Hello! I'm " + botName + "\n" +
                "What can I do for you?");

        Task[] tasks = new Task[100];

        AddTask.taskListManager(tasks);

        PrintText.printWithLinebreak("Bye. Hope to see you again soon!");

    }
}
