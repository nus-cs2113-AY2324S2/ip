import java.util.Scanner;

public class Duke {

    public static void addTask(String line) {
        Ui ui = new Ui();
        Task task;
        try {
            if(Parser.getCommand(line).equals("event")) {
                task = Parser.getEvent(line);
            } else if(Parser.getCommand(line).equals("deadline")) {
                task = Parser.getDeadline(line);
            } else {
                task = Parser.getTodo(line);
            }
        } catch(MissingParameterException e) {
            ui.printError(e.getMessage());
            return;
        }
        TaskList.addTask(task);
        ui.printFormat();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        ui.printNumTasks(TaskList.getSize());
        ui.printFormat();
    }

    public static void validate(String description) {
        Ui ui = new Ui();
        try {
            checkForError(description);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }

    private static void checkForError(String description) throws DukeException {
        if(Parser.isInvalidCommand(description)) {
            throw new DukeException("Hey, the description is invalid!");
        }
        if(Parser.stringIsEmpty(description)) {
            throw new DukeException("Hey, the description is invalid!");
        }
    }


    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printLogo();
        ui.sayHello();

        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                line = in.nextLine();
                if (line.equals("bye")) {
                    break;
                }
                validate(line);
                if (Parser.getCommand(line).equals("list")) {
                    ui.printFormat();
                    TaskList.printList();
                    ui.printFormat();
                    continue;
                }
                if (Parser.getCommand(line).equals("mark")) {
                    int indexToMark = Parser.getTaskIndex(line);
                    if (TaskList.isWithinBounds(TaskList.getSize(), indexToMark) && indexToMark >= 0) {
                        Task taskToMark = TaskList.getTask(indexToMark);
                        ui.printFormat();
                        taskToMark.setIsDone();
                        Storage.save(TaskList.tasks);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskToMark);
                        ui.printFormat();
                    }
                    continue;
                }
                if (Parser.getCommand(line).equals("unmark")) {
                    int indexToUnmark = Parser.getTaskIndex(line);
                    if (TaskList.isWithinBounds(TaskList.getSize(), indexToUnmark) && indexToUnmark >= 0) {
                        Task taskToUnmark = TaskList.getTask(indexToUnmark);
                        ui.printFormat();
                        taskToUnmark.setIsNotDone();
                        Storage.save(TaskList.tasks);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskToUnmark);
                        ui.printFormat();
                    }
                    continue;
                }
                if (Parser.getCommand(line).equals("delete")) {
                    int indexToDelete = Parser.getTaskIndex(line);
                    if (TaskList.isWithinBounds(TaskList.getSize(), indexToDelete) && indexToDelete >= 0) {
                        Task taskToDelete = TaskList.getTask(indexToDelete);
                        ui.printFormat();
                        TaskList.deleteTask(indexToDelete);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskToDelete);
                        ui.printNumTasks(TaskList.getSize());
                        ui.printFormat();
                    }
                    continue;
                }
                if (Parser.getCommand(line).equals("find")) {
                    String stringToFind = Parser.getStringToFind(line);
                    TaskList.searchTasks(stringToFind);
                }
                if (Parser.isTask(line)) {
                    addTask(line);
                }
            } catch(DukeException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.sayBye();
    }
}