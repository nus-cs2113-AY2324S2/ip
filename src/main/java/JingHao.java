import java.util.Scanner;
public class JingHao {
    protected Task[] list;
    protected int numberOfTask;

    public JingHao() {
        this.numberOfTask = 0;
        this.list = new Task[100];
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
            line = in.nextLine();
            System.out.println(divider);
            // Check if the user wants to exit
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            else if(line.equalsIgnoreCase("list")) {
                if (numberOfTask == 0) {
                    System.out.println("No task");
                } else {
                    for (int i = 0; i < numberOfTask; i++) {
                        System.out.println(i+1 + ".[" +list[i].getStatusIcon()+ "] " + list[i].description);
                    }
                }
            }
            else{
                Task taskDescription = new Task(line);
                list[numberOfTask]=taskDescription;
                numberOfTask++;
                System.out.println("added: " + line);
                System.out.println(divider);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }

}
