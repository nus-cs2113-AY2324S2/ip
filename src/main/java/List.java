import java.util.ArrayList;

public class List {
    //Task[] tasks = new Task[100];
    ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    public void addTask(String userInput) throws InvalidArgumentException {
        String taskType = "";
        if (userInput.startsWith("todo")){
            taskType = "todo";
        } else if(userInput.startsWith("deadline")){
            taskType = "deadline";
        } else if (userInput.startsWith("event")) {
            taskType = "event";
        }
        String description;
        switch (taskType){
        case "todo":
            if(5 > userInput.length()){
                throw new InvalidArgumentException();
            }
            description = userInput.substring(userInput.indexOf(" ") + 1);
            tasks.add(new Todo(description));
            break;
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            tasks.add(new Deadline(description, by));
            break;
        case "event":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/from") - 1);
            String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") -1);
            String to = userInput.substring(userInput.indexOf("/to") + 4);
            tasks.add(new Event(description, from, to));
            break;
        }
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + tasks.get(taskCount));
        taskCount++;
        System.out.println("\t Now you have " + taskCount + " tasks in the list.");
    }

    public void listTasks() {
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
    }

    public void setAsDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }

    public void setAsNotDone(int taskNumber){
        tasks.get(taskNumber).setDone(false);
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks.get(taskNumber));
    }
}
