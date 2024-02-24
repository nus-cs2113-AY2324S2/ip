import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Alice {
    private static final String LINE = "____________________________________________________________";
    private static final String GREETING_MESSAGE = "yoo i'm alice! ur virtual bestie here to keep track of ur vibes";
    private static final String FAREWELL_MESSAGE = "bye bestie! catch ya later, and remember to stay hydrated <3";
    private static final String HELP_MESSAGE = "Feelin' lost? Just type 'help' and I'll be right here with the deets again!";
    private static final String INVALID_INPUT_MESSAGE = "ayo my bad i can't seem to understand ya, try saying smt valid, or type 'help'";

    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File("./data/alice.txt");
        file.getParentFile().mkdirs();
        try(PrintWriter writer = new PrintWriter(file)){
            for(Task task:tasks){
                writer.println(task.toSaveFormat());
            }
        }
    }

    // Loads tasks which are saved in alice.txt file
    private static void loadTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File("./data/alice.txt");

        // Skip loading if file doesn't exist
        if(!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                Task task = parseTask(line);
                tasks.add(task);
            }
        }
    }


    // Helps to convert a line from the alice.txt file and convert it into
    // appropriate "Task" object
    private static Task parseTask(String line){
        String[] parts = line.split(" \\| ");
        Task task;
        boolean isDone = parts[1].equals("1");

        switch (parts[0]){
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")){
                    event.markAsDone();
                }
                return event;
            default:
                throw new IllegalArgumentException("Unknown task type in the save file");
        }
        if (isDone){
            task.markAsDone();
        }
        return task;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Used Java collection class ArrayList<Task>
        ArrayList<Task> tasks = new ArrayList<>();

        // Load tasks from file
        try {
            loadTasks(tasks);
        } catch (IOException e) {
            System.out.println("Could not load tasks from file: " + e.getMessage());
        }

        System.out.println(LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println("what's poppin? u can tell me stuff to remember, or type 'bye' to dip");
        System.out.println(LINE);

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println(LINE);
                    System.out.println(FAREWELL_MESSAGE);
                    System.out.println(LINE);
                    break;
                } else if(input.startsWith("delete ")){
                    handleDelete(input, tasks, LINE);
                }
                else if (input.equals("help")) {
                    // Respond to the help command by listing available commands
                    System.out.println(LINE);
                    System.out.println("okay bestie, here's what u can throw at me to help ya <3 :");
                    System.out.println("  - 'list': Peek at all that you gotta get done");
                    System.out.println("  - 'todo [description]': Got something new to remember? I gotchu girlie!");
                    System.out.println("  - 'deadline [description] /by [date]': Racing against the clock? I'll keep ya accountable");
                    System.out.println("  - 'event [description] /from [start time] /to [end time]': Got new plans? Lemme know so u don't miss out any of them");
                    System.out.println("  - 'mark [task number]': Finshed a task? YAY good job bestie ill help mark it off for ya");
                    System.out.println("  - 'unmark [task number]': Took a lil step back? No worries, we can get back to it later u got this!");
                    System.out.println("  - 'bye': Need to head off somewhere? I'll catch ya later! Rememeber that ur amazing bestie :)");
                    System.out.println(HELP_MESSAGE);
                    System.out.println(LINE);
                } else if (input.startsWith("list")) {
                    System.out.println(LINE);
                    if (tasks.isEmpty()) {
                        System.out.println("this list is emptier than my motivation on a monday morning lol");
                    } else {
                        System.out.println("aight here's what's up:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).toString());
                        }
                    }
                    System.out.println(LINE);
                } else if (input.startsWith("todo ")) {
                    handleTodo(input, tasks, LINE);
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    handleMarkUnmark(input, tasks, LINE);
                } else if (input.startsWith("deadline ")) {
                    handleDeadline(input, tasks, LINE);
                } else if (input.startsWith("event ")) {
                    handleEvent(input, tasks, LINE);
                } else {
                    throw new AliceException(INVALID_INPUT_MESSAGE);
                }
            } catch (AliceException e){
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        scanner.close();
    }

    private static void handleDelete(String input, ArrayList<Task> tasks, String line) throws AliceException{
        try{
            // Removes non-digits and parse into int, then subtracts 1 for zero-based index
            int taskNumber = Integer.parseInt(input.replaceAll("\\D", "")) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()){
                throw new AliceException("ayo i cant seem to find that task, try a valid task number yea");
            }

            // Remove task from list
            Task removedTask = tasks.remove(taskNumber);
            System.out.println(line);
            System.out.println("gotcha, ive made task "+ (taskNumber + 1) + " go poof!");
            System.out.println(" " + removedTask);
            System.out.println("now u got " + tasks.size() + " tasks in ur list.");
            System.out.println(line);
            saveTasks(tasks);
        } catch (NumberFormatException e){
            throw new AliceException("ayo do tell me the specific task number to be deleted yea?");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void handleTodo(String input, ArrayList<Task> tasks, String line) throws AliceException, IOException {
        String description = input.substring(5).trim();
        Todo newTask = new Todo(description);
        if (description.isEmpty()){
            throw new AliceException("hey u cant leave the description empty :(");
        }
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("aight i gotchu bestie, added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
        saveTasks(tasks);
    }

    private static void handleMarkUnmark(String input, ArrayList<Task> tasks, String line) throws AliceException, IOException {
        int taskIndex = Integer.parseInt(input.replaceAll("\\D", "")) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            if (input.startsWith("mark")) {
                task.markAsDone();
                System.out.println(line);
                System.out.println("yas queen, marked it off! we are crushing it frfr:");
            } else {
                task.markAsUndone();
                System.out.println(line);
                System.out.println("aight, took a lil step back, unmarked this one:");
            }
            System.out.println(task);
        } else {
            throw new AliceException("aw man that task doesn't seem to exist :( try using a valid task number yea");
        }
        System.out.println(line);
        saveTasks(tasks);
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks, String line) throws AliceException, IOException {

        if(!input.contains("/by")){
            throw new AliceException("ayo deadline inputs must include '/by' so i'll know the deadline yea :)");
        }

        String[] parts = input.substring(9).split(" /by ", 2);
        Task newTask = new Deadline(parts[0], parts[1]);

        if (parts[1].isEmpty()){
            throw new AliceException("hey do tell me when ur deadline is yea?");
        }

        tasks.add(newTask);
        System.out.println(line);
        System.out.println("ooh we're on a deadline kinda vibe? aight added it in:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
        saveTasks(tasks);
    }

    private static EventTime parseEventTimes(String input) throws AliceException {
        if (!input.contains("/to") || !input.contains("/from")) {
            throw new AliceException("ayo event commands must include '/from' and '/to' so that i'll know when your event starts and ends yea?");
        }

        String[] parts = input.split(" /from ", 2);

        if (!parts[1].contains(" /to ")) {
            throw new AliceException("ayo the event time must be specified with '/from' followed by '/to' yea?");
        }

        String[] timeParts = parts[1].split(" /to ", 2);

        if (timeParts.length < 2) {
            throw new AliceException("ayo do specify both the start and end times for your event yea?");
        }

        return new EventTime(parts[0], timeParts[0], timeParts[1]);
    }

    private static class EventTime {
        String description;
        String startTime;
        String endTime;

        EventTime(String description, String startTime, String endTime) {
            this.description = description;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    private static void handleEvent(String input, ArrayList<Task> tasks, String line) throws AliceException, IOException {
        EventTime eventTime = parseEventTimes(input.substring(6));

        Task newTask = new Event(eventTime.description, eventTime.startTime, eventTime.endTime);
        tasks.add(newTask);
        System.out.println(line);
        System.out.println("ayy noted down an event, don't forget girlie: ");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(line);
        saveTasks(tasks);
    }

}