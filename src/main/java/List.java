import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class List {
    Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(String userInput) throws InvalidArgumentException {
//        String taskType = userInput.toLowerCase().substring(0, userInput.indexOf(" ") -1);
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
            addTodo(description);
            break;
        case "deadline":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);
            addDeadline(description, by);
            break;
        case "event":
            description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/from") - 1);
            String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") -1);
            String to = userInput.substring(userInput.indexOf("/to") + 4);
            addEvent(description, from, to);
            break;
        }
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + tasks[taskCount]);
        taskCount++;
        System.out.println("\t Now you have " + taskCount + " tasks in the list.");
    }

    private void addEvent(String description, String from, String to) {
        tasks[taskCount] = new Event(description, from, to);
    }

    private void addDeadline(String description, String by) {
        tasks[taskCount] = new Deadline(description, by);
    }

    private void addTodo(String description){
        tasks[taskCount] = new Todo(description);
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

    public void loadTasks() throws FileNotFoundException {
        File f = new File("./src/data/vibes.txt");
        Scanner s  = new Scanner(f);
        while (s.hasNext()){
            readTask(s.nextLine());
        }
    }

    private void readTask(String textLine){
        char taskType = textLine.charAt(0);
        boolean isMarked = (textLine.charAt(4) == '1');
        String description;

        switch (taskType){
        case 'T':
            description = textLine.substring(8).trim();
            addTodo(description);
            if (isMarked){
                tasks[taskCount].setDone(true);
            }
            taskCount++;
            break;
        case 'D':
            description = textLine.substring(8, textLine.indexOf('|',8)).trim();
            String by = textLine.substring(textLine.indexOf('|',8) + 1).trim();
            addDeadline(description, by);
            if (isMarked){
                tasks[taskCount].setDone(true);
            }
            taskCount++;
            break;
        case 'E':
            description = textLine.substring(8, textLine.indexOf('|',8)).trim();
            String from = textLine.substring(textLine.indexOf('|',8) + 1, textLine.lastIndexOf('|'))
                    .trim();
            String to = textLine.substring(textLine.lastIndexOf('|') + 1).trim();
            addEvent(description, from, to);
            if (isMarked){
                tasks[taskCount].setDone(true);
            }
            taskCount++;
            break;
        }
    }
}
