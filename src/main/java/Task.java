import java.util.Arrays;

public class Task {
//    private String[] tasks;
//    private Boolean[] isMarked;
//    private int taskCount;
//
//    public void addTask(String task){
//        System.out.println("added: " + task);
//        tasks[taskCount++] = task;
//    }
//
//    public void printTasks() {
//        String[] validList = Arrays.copyOf(tasks, taskCount);
//        int j = 1;
//        for (String item : validList) {
//            String marking;
//            if (isMarked[j-1]){
//                marking = "X";
//            } else {
//                marking = " ";
//            }
//            System.out.println(j + ". " +"["  + marking + "] " + item);
//            j++;
//        }
//    }
//
//    public void markTask(String input){
//        String[] words = input.split(" ");
//        int taskNum = Integer.parseInt(words[1]);
//        isMarked[taskNum-1] = true;
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println("[X] " + tasks[taskNum-1]);
//    }
//
//    public void unmarkTask(String input){
//        String[] words = input.split(" ");
//        int taskNum = Integer.parseInt(words[1]);
//        isMarked[taskNum-1] = false;
//        System.out.println("OK, I've marked this task as not done yet:");
//        System.out.println("[ ] " + tasks[taskNum-1]);
//    }
//
//    public Task(){
//        this.tasks = new String[100];
//        this.isMarked = new Boolean[100];
//        Arrays.fill(isMarked, false);
//        this.taskCount = 0;
//    }

    protected String taskType;
    protected boolean isMarked;

    protected String taskName;

    public Task(String taskType, String taskName){
        this.taskType = taskType;
        this.isMarked = false;
        this.taskName = taskName;
    }

    protected String lineBreak = "____________________________________________________________";


    public void mark(){
        this.isMarked = true;
        System.out.println(lineBreak);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    public void unmark(){
        this.isMarked = false;
        System.out.println(lineBreak);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    public String markStatus(){
        if(isMarked){
            return "X";
        } else {
            return " ";
        }
    }

    public String printTask(){
        return "[" + this.taskType+ "][" +markStatus()+ "] " + this.taskName;
    }
}
