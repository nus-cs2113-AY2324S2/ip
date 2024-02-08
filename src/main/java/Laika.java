import java.util.Scanner;

public class Laika {

    public static void modifyTask(Task[] taskList, String line){
        String[] words = line.split(" ");
        int taskNumber = Integer.parseInt(words[1]);
        if (words[0].equals("mark")) {
            taskList[taskNumber - 1].markAsDone();
            System.out.println("Laika: Good job! Task has been marked as done.");
        } else {
            taskList[taskNumber - 1].markAsUndone();
            System.out.println("Laika: Alright, task has been marked as undone.");
        }
        System.out.println(taskList[taskNumber-1]);
    }

    public static void addTask(Task[] taskList, String line, int count){
        if(line.startsWith("todo")){
            taskList[count] = new Todo(line.replaceFirst("todo ",""));
        }
        else if (line.startsWith("deadline")) {
            String[] words = line.split("/");
            taskList[count] = new Deadline(words[0].replaceFirst("deadline ",""),
                                           words[1].replaceFirst("by ",""));
        }
        else if (line.startsWith("event")) {
            String[] words = line.split("/");
            taskList[count] = new Event(words[0].replaceFirst("event ",""),
                                        words[1].replaceFirst("from ",""),
                                        words[2].replaceFirst("to ",""));
        }
        System.out.println("Laika: Gotcha! I've added the task for you\n  "
                + taskList[count] + System.lineSeparator()
                + "You have " + (count+1) + " tasks in your list. :P" );
    }

    public static void displayTasks(Task[] taskList,int count){
        System.out.println("Task List:");
        for (int i = 0; i<count;i++){
            System.out.println((i + 1) + ") " + taskList[i]);
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int count = 0;
        boolean isConvoOngoing = true;
        String logo = " ^..^      /\n"
                + " /_/\\_____/\n"
                + "    /\\   /\\\n"
                + "   /  \\ /  \\\n";
        System.out.println("Laika: Hi! My name is Laika!\n\n" + logo + "Laika: How can I help you?");

        while(isConvoOngoing){
            line = in.nextLine();
            if (line.startsWith("mark") || line.startsWith("unmark")) {
                modifyTask(taskList,line);
                continue;
            }

            switch(line){
                case "list":
                    displayTasks(taskList,count);
                    break;
                case "bye":
                    isConvoOngoing = false;
                    break;
                default:
                    addTask(taskList, line, count);
                    count++;
            }
        }

        System.out.println("Laika: Bye! Have a nice day!");
    }
}
