import java.util.Scanner;
public class TaskList {
        private final Task[] taskList;
        private static final int MAX_TASKS = 100;
        //protected boolean[] isMarked;
        private int taskCount;


        public TaskList() {
            this.taskList = new Task[MAX_TASKS];
            this.taskCount = 0;
        }

    private void processTaskCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        String taskType = commandParts[0];

        switch (taskType) {
        case "todo":
            addTask(new Task(commandParts[1]));
            break;
        case "deadline":
            String[] deadlineParts = commandParts[1].split("/by" , 2);
            if (deadlineParts.length != 2) {
                System.out.println("Invalid deadline format! Use: deadline <<description>> /by <<deadline>>.");
            } else {
                addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
            }
            break;
        case "mark":
            if (commandParts.length == 1 || !isValidIndex(Integer.parseInt(commandParts[1])-1) ) {
                System.out.println("Invalid command format for marking a task. Use 'mark <index>'.");
            } else {
                markTask(Integer.parseInt(commandParts[1]) - 1);
            }
            break;
        case "unmark":
            if (commandParts.length == 1 || !isValidIndex(Integer.parseInt(commandParts[1])-1) ) {
                System.out.println("Invalid command format for marking a task. Use 'mark <index>'.");
            } else {
                unmarkTask(Integer.parseInt(commandParts[1]) - 1);
            }
            break;
        default:
            System.out.println("☹ Dobby does not understand."); // Default to addTask if not recognised
            break;
        }
    }

        /**
         * Adds user input into taskList
         *
         * @param task input given by user
         */
        public void addTask(Task task) {
            taskList[taskCount] = task;
            //isMarked[taskCount] = false;
            taskCount += 1;
            System.out.println("added: " + task);
        }

        /**
         * list out current tasks and displays task status
         */
        public void listTasks() {
            if (taskCount == 0) {
                System.out.println("Dobby has no tasks :(");
                return;
            }
            System.out.println("List\n~~~~~~~~~~~~~~~~");
            for (int i = 0 ; i < taskCount ; i += 1) {
                System.out.println("  " + (i+1) + ". [" + taskList[i].getType() + "]" + taskList[i]);
            }
            System.out.println("~~~~~~~~~~~~~~~~");
        }

        private Boolean isValidIndex(int index) {
            return index >= 0 && index < taskCount;
        }

        /**
         * 1. Checks if user inputs a valid index if valid
         * marks the stated task and shows which task is marked
         *
         * @param taskIndex index of task stored in array
         */
        public void markTask(int taskIndex) {
            if (!isValidIndex(taskIndex)) {
                System.out.println("Invalid number! Please try again");
            } else if (taskList[taskIndex].isDone()) {
                System.out.println("ERROR: task is already marked");
            } else {
                taskList[taskIndex].markTask();
                System.out.println("OK, Dobby has marked this task as done:");
                System.out.println("  " + taskList[taskIndex]);
                System.out.println("~~~~~~~~~~~~~~~~");
            }
        }

        /**
         * Checks if user inputs a valid index
         * if valid, unmarks the stated task and shows the unmarked task
         *
         * @param taskIndex index of task stored in array
         */
        public void unmarkTask(int taskIndex) {
            if (!isValidIndex(taskIndex)) {
                System.out.println("Invalid number> Please try again");
            } else if (!taskList[taskIndex].isDone()) {
                System.out.println("The task is already unmarked");
            } else {
                taskList[taskIndex].unmarkTask();
                System.out.println("OK, Dobby marked this task as not done:");
                System.out.println("  " + taskList[taskIndex]);
                System.out.println("~~~~~~~~~~~~~~~~");
            }
        }

        public void userCommand() {
            while(true) {
                String command;
                Scanner in = new Scanner(System.in);
                command = in.nextLine().toLowerCase();
                String[] commandParts = command.split(" ", 2);

                switch (commandParts[0]) {
                case "bye":
                    System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                    return;
                case "list":
                    listTasks();
                    break;
                default:
                    processTaskCommand(command);
                    break;
                }

            }
        }



}


