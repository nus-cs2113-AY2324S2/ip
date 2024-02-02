import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + "_________________________";
        System.out.println(chatBot);
        Task[] toDo = new Task[100];
        int count = 0;
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String task = userInput.nextLine();
            if(task.equals("bye")){
                System.out.println(
                        "_________________________\n"
                                + "Bye. Hope to see you again soon!\n"
                                + "_________________________\n");
                return;
            } else if(task.equals("list")) {
                System.out.println("_________________________");
                System.out.println("\tYour List:");
                for(int i = 0; i < count; i++) {
                    System.out.println("\t" + (i+1) + ". ["
                            + toDo[i].getStatusIcon()
                            + "] "
                            + toDo[i].description);
                }
                System.out.println("_________________________");
            } else if(task.contains("mark")) {
                String[] subCommand = task.split(" ");
                int num = Integer.parseInt(subCommand[1]);
                if(num > count) {
                    System.out.println("Suggest a number available in the list!");
                    continue;
                }
                if(subCommand[0].equals("mark")) {
                    toDo[num-1].markDone();
                    System.out.println(
                            "_________________________\n"
                                    + "\tNice! I've marked this task as done:\n"
                                    + "[" + toDo[num-1].getStatusIcon() + "] "
                                    + toDo[num-1].description
                                    + "\n_________________________");
                } else {
                    toDo[num-1].markNotDone();
                    System.out.println(
                            "_________________________\n"
                                    + "\tOK, I've marked this task as not done yet:\n"
                                    + "[" + toDo[num-1].getStatusIcon() + "] "
                                    + toDo[num-1].description
                                    + "\n_________________________");
                }
            } else {
                Task tasks = new Task(task);
                toDo[count] = tasks;
                System.out.println(
                        "_________________________\n"
                                + "\tadded: " + task
                                + "\n"
                                + "_________________________\n");
                count++;
            }
        }

    }
}
