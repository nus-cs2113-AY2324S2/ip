import java.util.Scanner;


public class ControlPanel {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How may I assist you today?");
        System.out.println("1. List tasks");
        System.out.println("2. Add a task");
        System.out.println("3. Add a deadline task");
        System.out.println("4. Add an event task");
        System.out.println("5. Mark a task as done");
        System.out.println("6. Exit");
        System.out.print("Enter the number corresponding to your choice: ");

        while (scanner.hasNextLine()) {
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listTasks(taskManager);
                    break;
                case "2":
                    System.out.println("Please enter the task description and priority (e.g., slain a dragon /S):");
                    String inputAddTask = scanner.nextLine().trim();
                    addTodoTask(inputAddTask, taskManager);
                    break;
                case "3":
                    System.out.println("Please enter the deadline task description, deadline date and priority (e.g., submit report /by 2021-09-30 /SS):");
                    String inputAddDeadline = scanner.nextLine().trim();
                    addDeadlineTask(inputAddDeadline, taskManager);
                    break;
                case "4":
                    System.out.println("Please enter the event description, start date, end date and priority (e.g., project meeting /from 2021-09-30 /to 2021-10-01 /S):");
                    String inputAddEvent = scanner.nextLine().trim();
                    addEventTask(inputAddEvent, taskManager);
                    break;
                case "5":
                    System.out.println("Please enter the task number to mark as done:");
                    String inputMark = scanner.nextLine().trim();
                    markTaskAsDone(inputMark, taskManager);
                    break;
                case "6":
                    String address = getAddress();
                    System.out.println("Pleased to serve you, " + address+"." + UserDetails.getUserName());
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
            System.out.println("Enter next command:");
        }
        scanner.close();
    }
    
    private static void listTasks(TaskManager taskManager) {
        taskManager.listTasks();
    }
    
    private static void addTodoTask(String input, TaskManager taskManager) {
        // Splitting the input at " /" to separate description and priority
        String[] parts = input.split(" ?/ ?");
        if (parts.length < 2 || parts[1].isEmpty()) {
            System.out.println("\"Incorrect format. Please ensure the task description is followed by '/' and a priority value (e.g., 'slain a dragon /SS').\"");
            return;
        }
        String description = parts[0];
        TaskManager.Priority priority;
        try{
            priority = TaskManager.Priority.valueOf(parts[1].trim().toUpperCase());
        }catch(IllegalArgumentException e){
            System.out.println("Invalid priority. Please enter a valid priority value (SS, S, A, B, C, D, E).");
            return;
        }
        // Assuming the name and description of the task are the same for simplicity
        taskManager.addTask(taskManager.new Todo("Todo", description, priority));

    }
    
    private static void addDeadlineTask(String input, TaskManager taskManager) {
        String[] parts = input.split(" ?/by |  ?/ ?");
        if(parts.length < 3 || parts[1].isEmpty() || parts[2].isEmpty()){
            System.out.println("Incorrect format. Please ensure the task description is followed by '/by' and a deadline date, and then a priority value (e.g., 'submit report /by 2021-09-30 /SS').");
            return;
        }
        String description = parts[0];
        String by = parts[1];
        TaskManager.Priority priority;
        try{
            priority = TaskManager.Priority.valueOf(parts[2].trim().toUpperCase());
        }catch(IllegalArgumentException e){
            System.out.println("Invalid priority. Please enter a valid priority value (SS, S, A, B, C, D, E).");
            return;
        }
        taskManager.addTask(taskManager.new Deadline("Deadline", description, by, priority));
    }
    
    private static void addEventTask(String input, TaskManager taskManager) {
        String[] parts = input.split(" /from | /to |  ?/ ?");
        if (parts.length < 4 || parts[1].isEmpty() || parts[2].isEmpty() || parts[3].isEmpty()) {
            System.out.println("Incorrect format. Please ensure the task description is followed by '/from', a start date, '/to', an end date, and then a priority value (e.g., 'project meeting /from 2021-09-30 /to 2021-10-01 /S').");
            return;
        }
        String description = parts[0];
        String start = parts[1];
        String end = parts[2];
        TaskManager.Priority priority;
        try{
            priority = TaskManager.Priority.valueOf(parts[3].trim().toUpperCase());
        }catch(IllegalArgumentException e){
            System.out.println("Invalid priority. Please enter a valid priority value (SS, S, A, B, C, D, E).");
            return;
        }
        taskManager.addTask(taskManager.new Event("Event", description, start, end, priority));
    }
    
    private static void markTaskAsDone(String input, TaskManager taskManager) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5)) - 1; // Adjust for zero-based index
            if (taskNumber < 0 || taskNumber >= taskManager.listSize()) {
                System.out.println("Task number is out of range. Please enter a valid task number.");
                System.out.println("Current number of tasks: " + taskManager.listSize());
                return;
            }
            taskManager.markTaskAsDone(taskNumber);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }

    private static String getAddress() {
        String address = "";
        if (UserDetails.getUserGender().equals("Male")) {
            address = "Mr";
        } else if (UserDetails.getUserGender().equals("Female")) {
            address = "Ms";
        } else {
            address = "Mx";
        }
        return address;
    }
}



