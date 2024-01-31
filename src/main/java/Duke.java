import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /** Creates a class of TaskList */
        TaskList taskList = new TaskList(100);
        String line;
        Scanner in = new Scanner(System.in);
        boolean userSaidBye = false;
        // Welcomes user
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm PoratoBot");
        System.out.println("    How can I assist you mortal?");
        System.out.println("    ____________________________________________________________");
        while (!userSaidBye) {
            // Takes in user input
            line = in.nextLine();
            if (line.equals("bye")) {
                userSaidBye = true;
            } else if(line.equals("list")) {
                // Prints the all the tasks in the list
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                taskList.listTasks();
                System.out.println(" ");
                System.out.println("    ____________________________________________________________");
            } else if (line.startsWith("mark")){
                // Marks the task in the list
                String[] sentence = line.split(" ");
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                taskList.checkTask(Integer.parseInt(sentence[1]));
                System.out.println(" ");
                System.out.println("    ____________________________________________________________");
            } else if (line.startsWith("unmark")){
                // Unmarks the task in the list
                String[] sentence = line.split(" ");
                System.out.println("     OK, I've marked this task as not done yet:");
                taskList.uncheckTask(Integer.parseInt(sentence[1]));
                System.out.println("    ____________________________________________________________");
            } else {
                // Adds a new task to the list
                Task newTask = new Task(line);
                taskList.addTask(newTask);
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + " added: " + line);
                System.out.println("    ____________________________________________________________");
            }
        }
        // Exits program when user says bye
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

