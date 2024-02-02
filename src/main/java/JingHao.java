import java.util.Scanner;
public class JingHao {
    protected Task[] taskList;
    protected int numberOfTask;

    public JingHao() {
        this.numberOfTask = 0;
        this.taskList = new Task[100];
    }
    public void Start(){
        String divider = "____________________________________________________________";
        String name = "JingHao";
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        while (true) {
            // Reads user input
            line = in.nextLine().toLowerCase();
            System.out.println(divider);
            // Check if the user wants to exit
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            else if(line.equalsIgnoreCase("taskList")) {
                if (numberOfTask == 0) {
                    System.out.println("No task");
                } else {
                    for (int i = 0; i < numberOfTask; i++) {
                        System.out.println(i+1 + ".[" +taskList[i].getStatusIcon()+ "] " + taskList[i].description);
                    }
                }
            }
            else if(line.startsWith("mark") || line.startsWith("unmark")){
                String[] words = line.split(" ");
                int index = Integer.parseInt(words[1]);
                if(index > numberOfTask || index < 1){
                    System.out.println("Unable to mark due to invalid index");
                }
                else{
                    index -= 1;
                    if(line.startsWith("mark")) {
                        taskList[index].check();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  [" + taskList[index].getStatusIcon() + "] " + taskList[index].description);
                    }
                    else{
                        taskList[index].uncheck();
                        System.out.println("OK, I've marked this task as not done yet::");
                        System.out.println("  [" + taskList[index].getStatusIcon() + "] " + taskList[index].description);
                    }
                }
            }
            else{
                Task taskDescription = new Task(line);
                taskList[numberOfTask]=taskDescription;
                numberOfTask++;
                System.out.println("added: " + line);
                System.out.println(divider);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }

}
