package allez;
public class Allez {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;
    private static Command c;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

//    public static void printList(ArrayList<Task> tasks){
//        int count = 0;
//
//        if(tasks.isEmpty()) {
//            System.out.println("List is currently empty");
//            return;
//        }
//
//        System.out.println("Here are the tasks in your list:");
//        for (Task task : tasks) {
//            System.out.println("\t" + (count+1) + ". " + task.toString());
//            count+=1;
//        }
//
//    }
    public static void main(String[] args) {
        startBot();
        runCommandsUntilExit();
        exit();
    }

    private static void exit() {
        storage.writeSave(tasks.getTasks());
        Ui.printExit();
    }

    private static void runCommandsUntilExit() {
        boolean hasEnded = false;
        while(!hasEnded) {
            String currentInput = ui.getUserCommand();
            String command =  parser.parseCommand(currentInput);
            hasEnded = executeCommands(command, currentInput);
        }
    }

    private static void startBot() {
        ui = new Ui();
        Ui.printGreeting();
        storage = new Storage("./data/data.txt");
        tasks = new TaskList(storage);
        parser = new Parser();
        c = new Command(tasks);
    }

    private static boolean executeCommands(String command, String line) {
        Ui.printHyphens();

        switch(command){
        case "bye":
            return true;
        case "mark":
            c.markTask(line);
            break;
        case "todo":
            c.createTask(line, TaskType.TODO);
            break;
        case "deadline":
            c.createTask(line, TaskType.DEADLINE);
            break;
        case "event":
            c.createTask(line, TaskType.EVENT);
            break;
        case "list":
            c.printList(tasks.getTasks());
            break;
        case "delete":
            c.deleteTask(line);
            break;
        default:
            System.out.println("Invalid command. Please try again.");
            break;
        }

        return false;
    }

//    private static void createTask(String line, TaskType type) {
//        boolean taskCreated;
//
//        switch (type){
//        case TODO:
//            taskCreated = createToDoTask(line);
//            break;
//        case DEADLINE:
//            taskCreated = createDeadlineTask(line);
//            break;
//        case EVENT:
//            taskCreated = createEventTask(line);
//            break;
//        default:
//            System.out.println("Invalid TaskType occurred.");
//            return;
//        }
//
//        if(!taskCreated){
//            return;
//        }
//
//        System.out.println("Got it. I've added this task:");
//        System.out.println("\t" + tasks.get(taskCount).toString());
//        taskCount +=1;
//        System.out.println("Now you have " + taskCount + " tasks in the list.");
//    }

//    private static boolean createEventTask(String line) {
//        String description;
//        String to;
//        String[] lineSegment;
//        String from;
//        try {
//            lineSegment = Parser.parseEventInput(line);
//            description = lineSegment[0].trim();
//            from = lineSegment[1].trim();
//            to = lineSegment[2].trim();
//            tasks.add(new Event(description, from, to));
//            return true;
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Please input event for the task in a format as shown below");
//            System.out.println("event <description> /from <start> /to <end>");
//        } catch (MissingDetailsException e) {
//            System.out.println("Please input something for description, from and to");
//        } catch (Exception e) {
//            System.out.println("Error encountered");
//        }
//        return false;
//    }

//    private static boolean createDeadlineTask(String line) {
//        String description;
//        String[] lineSegment;
//        String by;
//        try {
//            lineSegment = Parser.parseDeadlineInput(line);
//            description = lineSegment[0].trim();
//            by = lineSegment[1].trim();
//
//            tasks.add(new Deadline(description,by));
//            return true;
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Please input deadline for the task in a format as shown below");
//            System.out.println("deadline <description> /by <deadline>");
//        } catch (MissingDetailsException e) {
//            System.out.println("Please input something for description and deadline");
//        } catch (Exception e) {
//            System.out.println("Error encountered");
//        }
//        return false;
//    }

//    private static boolean createToDoTask(String line) {
//        String description;
//        description = line.substring(4).trim();
//        if (description.isEmpty()) {
//            System.out.println("Please add a description of your task");
//            return false;
//        }
//        tasks.add(new ToDo(description));
//        return true;
//    }

//    private static void markTask(String line) {
//        int toMark;
//        try {
//            toMark = Integer.parseInt(line.substring(4).trim()) -1;
//            tasks.get(toMark).markDone();
//            System.out.println("Nice! I've marked this task as done:");
//            System.out.println("\t" + tasks.get(toMark).toString());
//        } catch (NumberFormatException e) {
//            System.out.println("Please input a number only");
//        } catch (NullPointerException | IndexOutOfBoundsException e) {
//            System.out.println("Please input a number within current number of tasks");
//        }
//    }

//    private static void deleteTask(String line) {
//        int toDelete;
//        Task temp;
//        try {
//            toDelete = Integer.parseInt(line.substring(6).trim()) -1;
//            temp = tasks.getSpecficTask(toDelete);
//            tasks.deleteTask(toDelete);
//            System.out.println("Aight. I've removed this task:");
//            System.out.println("\t" + temp.toString());
//            System.out.println("Now you have " + tasks.getNumberOfTasks() + " tasks in the list.");
//        } catch (NumberFormatException e) {
//            System.out.println("Please input a number only");
//        } catch (NullPointerException | IndexOutOfBoundsException e) {
//            System.out.println("Please input a number within current number of tasks");
//        }
//    }


}
