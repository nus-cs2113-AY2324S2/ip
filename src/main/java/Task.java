public class Task {
    protected static Task[] tasks;
    public static int totalTasks = 0;
    protected String description;
    protected boolean isDone;

    protected String type;

    public static void initArray() {
        tasks = new Task[100];
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
    }


    public static void getAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int serialNumber = 1;
        for (int i = 0; i < totalTasks; i++) {
            System.out.print(serialNumber
                    + ".[" + tasks[i].type + "]"
                    + "[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());

            if (tasks[i].type.equals("D")) {
                //type casting required to access by member
                Deadline deadlineTask = (Deadline) tasks[i];
                System.out.print(" (by: " + deadlineTask.by + ")");
            } else if (tasks[i].type.equals("E")) {
                //type casting required to access from and to members
                Event eventTask = (Event) tasks[i];
                System.out.print(" (from: " + eventTask.from + " to: " + eventTask.to + ")");
            }
            System.out.println();
            serialNumber += 1;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //method that extracts the task number to mark or unmark
    public static int extractTaskNumber(String input) {
        String keyword;
        if (input.startsWith("mark")) {
            keyword = "mark";
        } else {
            keyword = "unmark";
        }
        //checks if theres even characters after mark/unmark
        if (input.length() > keyword.length()) {
            String numberString = input.substring(keyword.length()).trim();
            int taskNumber = Integer.parseInt(numberString);
            //checks if taskNumber is valid
            if (taskNumber <= 0) {
                return -1;
            } else {
                return taskNumber;
            }
        } else {
            return -1;
        }
    }


    //method that modifies whether task is done or not done, depending on keyword mark or unmark detected
    public static void modifyDoneState(int taskNumber, String input) {
        //only executes if taskNumber is valid
        if (taskNumber != -1) {
            if (input.startsWith("mark")) {
                tasks[taskNumber - 1].isDone = true;
            } else {
                tasks[taskNumber - 1].isDone = false;
            }
        }
    }


    //method that prints out the task that has been marked done or unmarked
    public static void printMarkTask(int index, String input) {
        Omoh.printHorizontalLine();
        if (input.startsWith("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked his task as not done yet:");
        }
        System.out.println("[" + tasks[index - 1].getStatusIcon() + "] "
                + tasks[index - 1].description);
        Omoh.printHorizontalLine();
    }

    public static void addTask(String taskDescription) {
        tasks[totalTasks] = new Task(taskDescription);
        totalTasks++;
    }

    public static void printAddedTask() {
        Omoh.printHorizontalLine();
        System.out.println("added: " + tasks[totalTasks - 1].getDescription());
        Omoh.printHorizontalLine();
    }

}


