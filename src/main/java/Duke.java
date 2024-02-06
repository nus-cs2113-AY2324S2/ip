import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + "_________________________";
        System.out.println(chatBot);
        Task[] tasks = new Task[100];
        int count = 0;
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String line = userInput.nextLine();
            if(line.equals("bye")){
                System.out.println(
                        "_________________________\n"
                                + "Bye. Hope to see you again soon!\n"
                                + "_________________________\n");
                return;
            } else if(line.equals("list")) {
                System.out.println("_________________________");
                System.out.println("\tYour List:");
                for(int i = 0; i < count; i++) {
                    System.out.println("\t" + (i+1) + ". ["
                            + tasks[i].getStatusIcon()
                            + "] "
                            + tasks[i].description);
                }
                System.out.println("_________________________");
            } else if(line.contains("mark")) {
                String[] subCommand = line.split(" ");
                int num = Integer.parseInt(subCommand[1]);
                if(num > count) {
                    System.out.println("Suggest a number available in the list!");
                    continue;
                }
                if(subCommand[0].equals("mark")) {
                    tasks[num-1].markDone();
                    System.out.println(
                            "_________________________\n"
                                    + "\tNice! I've marked this task as done:\n"
                                    + "[" + tasks[num-1].getStatusIcon() + "] "
                                    + tasks[num-1].description
                                    + "\n_________________________");
                } else {
                    tasks[num-1].markNotDone();
                    System.out.println(
                            "_________________________\n"
                                    + "\tOK, I've marked this task as not done yet:\n"
                                    + "[" + tasks[num-1].getStatusIcon() + "] "
                                    + tasks[num-1].description
                                    + "\n_________________________");
                }
            } else {
                Task task = new Task(line);
                tasks[count] = task;
                System.out.println(
                        "_________________________\n"
                                + "\tadded: " + line
                                + "\n"
                                + "_________________________\n");
                count++;
            }
        }

    }
}
