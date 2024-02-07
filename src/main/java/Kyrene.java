import java.util.Scanner;

public class Kyrene {
    final static String LOGO = "    _   _  _    _  ____   ____  _   _  ____\n"
            + "    | | / /\\ \\  / /|  _ \\ | ___|| \\ | || ___|\n"
            + "    | |/ /  \\ \\/ / | |_| || ===||  \\| || ===|\n"
            + "    | |\\ \\   |  |  | |\\ / | |__ | |\\  || |__\n"
            + "    |_| \\_\\  |__|  |_| \\_\\|____||_| \\_||____|   by Zhou Junmin\n";
    final static String DIVIDER = "    ⭐__________________________________________________________⭐\n";
    final static String GREETING = "    Hi, I am Kyrene, your private reminder assistant.\n"
            + "    What can I do for you?\n";
    final static String BYE = "    Bye! Wish to see you again soon!\n";
    final static String ERROR_TASK_NOT_EXIST = "    Sorry! This task does not exist.\n";

    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void initKyrene(){
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public static void exitKyrene(){
        System.out.println(BYE);
        System.out.println(DIVIDER);
    }

    public static void addTask(String sentence){

        if(sentence.startsWith("todo ")){
            tasks[taskCount] = new Todo(sentence.substring(5));
        }
        else if(sentence.startsWith("deadline ")){
            tasks[taskCount] = new Deadline(sentence.substring(9));
        }
        else if(sentence.startsWith("event ")){
            tasks[taskCount] = new Event(sentence.substring(6));
        }
        else{
            tasks[taskCount] = new Task(sentence);
        }

        System.out.println("    Task has been successfully added: " + tasks[taskCount].toString());
        taskCount++;
        if(taskCount == 1){
            System.out.printf("    Now you have %d task(including finished ones) in your list.\n\n", taskCount);
        }
        else{
            System.out.printf("    Now you have %d tasks(including finished ones) in your list.\n\n", taskCount);
        }
        printDivider();

    }

    public static void markTask(int taskNumber){
        if(taskNumber < 1 || taskNumber > taskCount){
            System.out.println(ERROR_TASK_NOT_EXIST);
            printDivider();
            return;
        }
        tasks[taskNumber - 1].setDone(true);
        System.out.println("    Congrats! Task " + taskNumber + " is done!\n");
        printDivider();
    }

    public static void unmarkTask(int taskNumber){
        if(taskNumber < 1 || taskNumber > taskCount){
            System.out.println(ERROR_TASK_NOT_EXIST);
            printDivider();
            return;
        }
        tasks[taskNumber - 1].setDone(false);
        System.out.println("    Task " + taskNumber + " is marked as not done.\n");
        printDivider();
    }

    public static void printDivider(){
        System.out.println(DIVIDER);
    }

    public static void printTasks() {
        System.out.println("    Here is your to-do list:");
        for(int i = 0; i < taskCount; i++){
            System.out.printf("        %d.%s\n", i + 1, tasks[i].toString());
        }
        System.out.print("\n");
    }

    public static boolean converseKyrene(String sentence){
        boolean isEnd = false;
        printDivider();
        if(sentence.equals("bye")){
            isEnd = true;
            exitKyrene();
            return isEnd;
        }
        else if(sentence.equals("list")){
            printTasks();
            printDivider();
        }
        else if(sentence.startsWith("mark ")){
            int taskNumber = Integer.parseInt(sentence.substring(5));
            markTask(taskNumber);
        }
        else if(sentence.startsWith("unmark ")){
            int taskNumber = Integer.parseInt(sentence.substring(7));
            unmarkTask(taskNumber);
        }
        else{
            addTask(sentence);
        }
        return isEnd;
    }

    public static void main(String[] args) {
        initKyrene();
        Scanner input = new Scanner(System.in);
        String line;
        boolean exitFlag = false;
        while (!exitFlag){
            line = input.nextLine();
            exitFlag = converseKyrene(line);
        }
    }

}
