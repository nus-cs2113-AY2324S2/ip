public class List {
    Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(String userInput) {
        String taskType = userInput.toLowerCase().substring(0, userInput.indexOf(" "));
        String description;
        switch (taskType){
        case "todo":
            description = userInput.substring(userInput.indexOf(" ") + 1);
            tasks[taskCount] = new Todo(description);
            break;
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            tasks[taskCount] = new Deadline(description, by);
            break;
        }
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + tasks[taskCount]);
        taskCount++;
        System.out.println("\t Now you have " + taskCount + " tasks in the list.");
    }

    public void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks[i]);
        }
    }

    public void setAsDone(int taskNumber) {
        tasks[taskNumber].setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks[taskNumber]);
    }

    public void setAsNotDone(int taskNumber){
        tasks[taskNumber].setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks[taskNumber]);
    }
}
