import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatBot = "Hello! I'm TONY\n"
                + "What can I do for you?\n"
                + "_________________________";
        System.out.println(chatBot);
        Task[] tasks = new Task[100];
        int count = 0;
        Scanner userInput = new Scanner(System.in);
        while(userInput.hasNextLine()) {
            String line = userInput.nextLine();
            if(line.equals("bye")){
                System.out.println(
                        "_________________________\n"
                                + "Bye. Hope to see you again soon!\n"
                                + "_________________________\n");
                return;
            } else if(line.equals("list")) {
                System.out.println("_________________________");
                System.out.println("\tHere are the tasks in your list:");
                for(int i = 0; i < count; i++) {
                    Task task = tasks[i];
                    System.out.println("\t" + (i+1) + "."
                            + task);
                }
                System.out.println("_________________________");
            } else if(line.startsWith("todo")) {
                String[] toDoTask = line.split("todo");
                Todo todo = new Todo(toDoTask[1]);
                tasks[count] = todo;
                count++;
                System.out.println(
                        "_________________________\n"
                                + "\t Got it. I've added this task:"
                                + System.lineSeparator()
                                + "\t\t " + todo
                                + System.lineSeparator()
                                + "\t Now you have " + count + " tasks in the list."
                                + System.lineSeparator()
                                + "_________________________"
                                + System.lineSeparator());
            } else if(line.startsWith("deadline")) {
                String[] deadlineTask = line.split("deadline");
                String[] description = deadlineTask[1].split("/by");
                Deadline deadline = new Deadline(description[0], description[1]);
                tasks[count] = deadline;
                count++;
                System.out.println(
                        "_________________________\n"
                                + "\t Got it. I've added this task:"
                                + System.lineSeparator()
                                + "\t\t " + deadline
                                + System.lineSeparator()
                                + "\t Now you have " + count + " tasks in the list."
                                + System.lineSeparator()
                                + "_________________________"
                                + System.lineSeparator());
            } else if(line.startsWith("event")) {
                String[] eventTask = line.split("event");
                String[] description = eventTask[1].split("/from | /to");
                Event event = new Event(description[0], description[1], description[2]);
                tasks[count] = event;
                count++;
                System.out.println(
                        "_________________________\n"
                                + "\t Got it. I've added this task:"
                                + System.lineSeparator()
                                + "\t\t " + event
                                + System.lineSeparator()
                                + "\t Now you have " + count + " tasks in the list."
                                + System.lineSeparator()
                                + "_________________________"
                                + System.lineSeparator());
            }

            else if(line.contains("mark")) {
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
                                    + tasks[num-1]
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
                count++;
            }
        }

    }
}
