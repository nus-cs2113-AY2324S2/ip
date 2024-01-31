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

    /* Function for greeting and farewell messages*/
    private static void sayHi() {
        String line = "-----------------------------------\n";
        String greet = "Hello! I'm Winter!\nWhat can I do for you?";
        System.out.print(line);
        System.out.println(greet);
        System.out.print(line);
    }
    private static void sayBye() {
        String line = "-----------------------------------\n";
        String farewell = "Bye. Hope to see you again soon!";
        System.out.print(line);
        System.out.println(farewell);
        System.out.print(line);
    }

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
    private static void addTasks() {
        String line = "-----------------------------------\n";
        String indent = "   ";
        Task[] taskList = new Task[100];
        int taskIndex = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            String command = input.nextLine();
            Task newTask = new Task(taskIndex+1,false,command);
            taskList[taskIndex] = newTask;
            switch(command) {
            case "bye":
                break;
            case "Bye":
                break;
            case "BYE":
                break;
            case "list":
                displayList(taskList,taskIndex);
                continue;
            case "List":
                displayList(taskList,taskIndex);
                continue;
            case "LIST":
                displayList(taskList,taskIndex);
                continue;

            }
            switch(command.substring(0,command.indexOf(" "))) {
            case "mark":
                taskList[Integer.parseInt(command.substring(5,command.length()))-1].mark();
                continue;
            case "unmark":
                taskList[Integer.parseInt(command.substring(7,command.length()))-1].unmark();
                continue;
            }

            taskIndex++;
            System.out.print(line);
            System.out.print(indent);
            System.out.println("added: " + command);
            System.out.print(line);

        }
    }
    private static void displayList(Task[] taskList, int taskIndex) {
        String line = "-----------------------------------\n";
        String indent = "   ";
        for (int i = 0; i < taskIndex; i++) {
            String status;
            if (taskList[i].isMarked()) {
                status = "[X]";
            }
            else {
                status = "[ ]";
            }
            System.out.print(indent);
            System.out.println(taskList[i].getOrder() + ". " + status + " " + taskList[i].getTaskName());
        }
        System.out.print(line);
    }
    private static class Task {
        boolean marked;
        int order;
        String taskName;

        Task(int order ,boolean marked,String taskName) {
            this.order = order;
            this.marked = marked;
            this.taskName = taskName;
        }
        private void mark() {
            String line = "-----------------------------------\n";
            String indent = "   ";
            this.marked = true;
            System.out.println("Woohoo! I've marked this task as done:");
            System.out.print(indent);
            System.out.println("[X] "+ this.taskName);
            System.out.print(line);
        }
        private void unmark() {
            String line = "-----------------------------------\n";
            String indent = "   ";
            this.marked = false;
            System.out.println("Alright! I've marked this task as incomplete:");
            System.out.print(indent);
            System.out.println("[ ] "+ this.taskName);
            System.out.print(line);
        }

        public int getOrder() {
            return order;
        }

        public String getTaskName() {
            return taskName;
        }

        public boolean isMarked() {
            return marked;
        }
    }
}
