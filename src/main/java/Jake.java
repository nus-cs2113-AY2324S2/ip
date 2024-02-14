import java.util.Scanner;

public class Jake {

    static final String LINE_STRING ="____________________________________________________________";

    // List out all tasks
    private static void listTask(Task[] commands, int totalTasks) {
        System.out.println("Current commands being executed: ");
        for (int i = 0; i < totalTasks; i++){
            System.out.println(Integer.toString(i+1) + "." + commands[i].toString());
        }
        System.out.println(LINE_STRING);
    }

    // Mark or Unmark respective task.
    private static void toggleTask(Task[] commands, int totalTasks, 
            String userInput, String taskType) throws JakeException {
        int taskNumber = Integer.parseInt(userInput.substring(userInput.lastIndexOf(" ")+1));
        if (taskNumber>totalTasks){
            System.out.println("Task does not exist!");
        } else if (taskType.equals("unmark")){
            System.out.println("Successfully unmarked");
            commands[taskNumber-1].markTask(false);
        } else {
            System.out.println("Successfully marked");
            commands[taskNumber-1].markTask(true);
        }
        System.out.println(LINE_STRING);
    }

    // Add ToDos, Deadlines, and Events
    private static void addTask(Task[] commands, int totalTasks, String userInput, String taskType) {
        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new ToDo(userInput);
                break;
            case "deadline":
                String[] deadlineSections = userInput.split(" /by ");
                newTask = new Deadline(deadlineSections[0], deadlineSections[1]);
                break;
            case "event":
                String[] eventSections = userInput.split(" /from ");
                String[] eventTimings = eventSections[1].split(" /to ");
                newTask = new Event(eventSections[0], eventTimings[0], eventTimings[1]);
                break;
            default:
                return;
        }
        
        commands[totalTasks] = newTask;
        System.out.println(LINE_STRING);
        System.out.println("Got it! I have successfully added: \n" + "    " + newTask.toString());
        System.out.printf("You have a total of %d tasks in the list \n", totalTasks+1);
        System.out.println(LINE_STRING);
    }


    public static void main(String[] args) throws JakeException {
        System.out.println("Hello! I'm Jake\n" + "What can I do for you? \n" + LINE_STRING);
        Scanner myScanner = new Scanner(System.in);
        Task[] commands = new Task[100];
        int totalTasks = 0;
        String userInput= "";

        do {
            userInput = myScanner.nextLine();
            String taskType; 
            // Check if the userInput contains a space, to handle inputs like "list" and "bye";
            if (userInput.contains(" ")) {
                taskType = userInput.substring(0, userInput.indexOf(" "));
            } else {
                taskType = userInput;
            }

            switch (taskType) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    listTask(commands, totalTasks);
                    break;
                case "mark":
                case "unmark":
                    toggleTask(commands, totalTasks, userInput, taskType);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    try {
                        addTask(commands, totalTasks, userInput, taskType);
                        totalTasks++;
                        break;
                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                        //throw new JakeException("Invalid task! Please try again!", e);
                        System.out.println("Invalid task! Please check your formatting!");
                        break;
                    } 
                default:
                    System.out.println("Command not recognised! Please try again!");
            }

        } while (!userInput.equals("bye"));

        myScanner.close();
    }
}