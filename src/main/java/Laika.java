import java.util.Scanner;

public class Laika {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int count = 0;
        boolean continueConvo = true;
        String logo = " ^..^      /\n"
                + " /_/\\_____/\n"
                + "    /\\   /\\\n"
                + "   /  \\ /  \\\n";
        System.out.println("Laika: Hi! My name is Laika!\n\n" + logo + "Laika: How can I help you?");
        while(continueConvo){
            System.out.print("User: ");
            line = in.nextLine();
            if (line.startsWith("mark")){
                String[] words = line.split(" ");
                int taskNumber = Integer.parseInt(words[1]);
                taskList[taskNumber-1].markAsDone();
                System.out.println("Laika: Good job! Task has been marked as done.");
                System.out.println(taskList[taskNumber-1].getStatusIcon() + " " + taskList[taskNumber-1].description);
                continue;
            } else if (line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int taskNumber = Integer.parseInt(words[1]);
                taskList[taskNumber-1].markAsUndone();
                System.out.println("Laika: Alright, task has been marked as undone.");
                System.out.println(taskList[taskNumber-1].getStatusIcon() + " " + taskList[taskNumber-1].description);
                continue;
            }
            switch(line){
                case "list":
                    System.out.println("Task List:");
                    for (int i = 0; i<count;i++){
                        System.out.println((i + 1) + ") " + taskList[i].getStatusIcon() + " " + taskList[i].description);
                    }
                    break;
                case "bye":
                    continueConvo = false;
                    break;
                default:
                    taskList[count] = new Task(line);
                    count++;
                    System.out.println("Laika: Task \"" + line + "\" has been added!" );

            }
        }

        System.out.println("Laika: Bye! Have a nice day!");
    }
}
