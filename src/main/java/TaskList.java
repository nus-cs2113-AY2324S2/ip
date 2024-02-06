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
        case "mark":
            if (commandParts.length > 1) {
                markTask(Integer.parseInt(commandParts[1]) - 1);
            } else {
                System.out.println("Invalid command format for marking a task. Use 'mark <index>'.");
            }
            break;
        case "unmark":
            if (commandParts.length > 1) {
                unmarkTask(Integer.parseInt(commandParts[1]) - 1);
            } else {
                System.out.println("Invalid command format for unmarking a task. Use 'unmark <index>'.");
            }
            break;
        default:
            addTask(new Task(command)); // Default to addTask if not recognised
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
                System.out.println("  " + (i+1) + ". " + taskList[i]);
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

                switch (command) {
                case "bye":
                    System.out.println("~~~~~~~~~~~~~~~~\nDobby say's BYE!");
                    return;
                case "list":
                    listTasks();
                    break;
                default:
                    processTaskCommand(command);
                    break;
//                if (command.startsWith("add")) {
//                    addTask(command.substring(4).trim());
//                } else if (command.startsWith("mark")) {
//                    int taskIndex = Integer.parseInt(command.split(" ")[1]);
//                    markTask(taskIndex);
//                } else if (command.startsWith("unmark")) {
//                    int taskIndex = Integer.parseInt(command.split(" ")[1]);
//                    unmarkTask(taskIndex);
//                } else {
//                    addTask(command);
//                }
//                break;
                }

            }
        }



}


