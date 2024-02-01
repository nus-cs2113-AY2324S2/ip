import java.util.Scanner;

public class Winter {
    public static void main(String[] args) {
        String logo = "  __      __.__        __                \n" +
                "/  \\    /  \\__| _____/  |_  ___________ \n" +
                "\\   \\/\\/   /  |/    \\   __\\/ __ \\_  __ \\\n" +
                " \\        /|  |   |  \\  | \\  ___/|  | \\/\n" +
                "  \\__/\\  / |__|___|  /__|  \\___  >__|   \n" +
                "       \\/          \\/          \\/    ";

        System.out.println("Hello from\n" + logo);
        sayHi();
        //echo();
        addTasks();
        sayBye();

    }

    // Method for greeting message
    private static void sayHi() {
        String line = "-----------------------------------\n";
        String greet = "Hello! I'm Winter!\nWhat can I do for you?";
        System.out.print(line);
        System.out.println(greet);
        System.out.print(line);
    }
    // Method for farewell message
    private static void sayBye() {
        String line = "-----------------------------------\n";
        String farewell = "Farewell. Hope to see you again soon!";
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);
    }

    // Method for echo, not used after Level-1
    private static void echo() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        String echoLine;
        Scanner input = new Scanner(System.in);
        echoLine = input.nextLine();
        while (true) {
            if(echoLine.equals("bye") || echoLine.equals("Bye") || echoLine.equals("BYE")) {
                break;
            }
            System.out.print(line);
            System.out.print(indent);
            System.out.println(echoLine);
            System.out.print(line);
            echoLine = input.nextLine();
        }

    }
    // Method for adding tasks by handling user commands
    private static void addTasks() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        String[] taskList = new String[100];
        int taskIndex = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            String command = input.nextLine();
<<<<<<< HEAD
            Task newTask = new Task(taskIndex+1,false,command);
            taskList[taskIndex] = newTask;

            switch(command) {
            // Cases include farewell and list commands
            case "bye":
=======
            if(command.equals("bye") || command.equals("Bye") || command.equals("BYE")) {
>>>>>>> c42d0f18454ee2c796b585ca41b0aa9f74b16ba7
                break;
            }
<<<<<<< HEAD
            switch(command.substring(0,command.indexOf(" "))) {
            // Cases for marking tasks
            case "mark":
                taskList[Integer.parseInt(command.substring(5,command.length()))-1].mark();
                continue;
            case "unmark":
                taskList[Integer.parseInt(command.substring(7,command.length()))-1].unmark();
=======
            else if (command.equals("list") || command.equals("List") || command.equals("LIST")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.print(indent);
                    System.out.println(taskList[i]);
                }
                System.out.print(line);
>>>>>>> c42d0f18454ee2c796b585ca41b0aa9f74b16ba7
                continue;
            }
            taskList[taskIndex] = Integer.toString(taskIndex+1) + ". " + command;
            taskIndex++;
            System.out.print(line);
            System.out.print(indent);
            System.out.println("added: " + command);
            System.out.print(line);

        }
    }
<<<<<<< HEAD
    // Method for displaying list
    private static void displayList(Task[] taskList, int taskIndex) {
        String line = "-----------------------------------\n";
        String indent = "   ";
        for (int i = 0; i < taskIndex; i++) {
            String status;
            if (taskList[i].isMarked()) {
                status = "[X]";
            } else {
                status = "[ ]";
            }
            System.out.print(indent);
            System.out.println(taskList[i].getOrder() + ". " + status + " " + taskList[i].getTaskName());
        }
        System.out.print(line);
    }
    // Class for Task object
    private static class Task {
        boolean marked;
        int order;
        String taskName;

        Task(int order ,boolean marked,String taskName) {
            this.order = order;
            this.marked = marked;
            this.taskName = taskName;
        }
        // Method for marking a task complete
        private void mark() {
            String line = "-----------------------------------\n";
            String indent = "   ";
            this.marked = true;
            System.out.println("Woohoo! I've marked this task as done:");
            System.out.print(indent);
            System.out.println("[X] "+ this.taskName);
            System.out.print(line);
        }
        // Method for unmarking a class
        private void unmark() {
            String line = "-----------------------------------\n";
            String indent = "   ";
            this.marked = false;
            System.out.println("Alright! I've marked this task as incomplete:");
            System.out.print(indent);
            System.out.println("[ ] "+ this.taskName);
            System.out.print(line);
        }
        // Getter method for order
        public int getOrder() {
            return order;
        }
        // Getter method for taskName
        public String getTaskName() {
            return taskName;
        }
        // Getter method for marked
        public boolean isMarked() {
            return marked;
        }
    }
=======
>>>>>>> c42d0f18454ee2c796b585ca41b0aa9f74b16ba7
}
