import java.util.Scanner;

public class ListKeeper {
    private Task[] tasks;
    private int numTaskAdded = 0;

    public ListKeeper(int maxNumOfTasks) {
        this.tasks = new Task[maxNumOfTasks];
    }

    private void addToList(String taskName) {
        if (this.numTaskAdded == tasks.length) {
            System.out.println("No more tasks can be added");
            return;
        }
        tasks[this.numTaskAdded] = new Task(taskName);
        this.numTaskAdded++;
        System.out.println("added: " + taskName);
    }

    private void printList() {
        for (int i = 0; i < this.numTaskAdded; i++) {
            System.out.print(i + 1 + ". ");
            this.tasks[i].printTask();
        }
    }

    // words[0] must be "mark" or "unmark"
    // If words[1] is an invalid task index, the command is ignored
    // If words[i] is not an integer, the entire input string is treated as a task
    private void changeTaskCompletion(String[] words, String inputString) {
        String command = words[0];
        String taskIndexString = words[1];
        try {
            int taskIndex = Integer.parseInt(taskIndexString);
            if (taskIndex <= 0 || taskIndex > this.numTaskAdded) {
                System.out.println("Please specify a valid task");
            } else if (command.equals("mark")) {
                this.tasks[taskIndex - 1].mark();
            } else {
                this.tasks[taskIndex - 1].unmark();
            }
        } catch (NumberFormatException error) {
            this.addToList(inputString);
        }
    }

    // User's command must be of the following forms
    // 1. "list"
    // 2. "mark x" where x must be an integer
    // 3. "unmark x" where x must be an integer
    // 4. any string ( least priority )
    public void manageList() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            while (line.isEmpty()) {
                line = in.nextLine();
            }
            HorizontalGenerator.printHorizontal();
            String[] words = line.split(" ");
            if (words.length == 1 && words[0].equals("list")) {
                this.printList();
            } else if (words.length == 2 && (words[0].equals("mark") || words[0].equals("unmark"))) {
                this.changeTaskCompletion(words, line);
            } else {
                this.addToList(line);
            }
            HorizontalGenerator.printHorizontal();
            line = in.nextLine();
        }
    }
}
