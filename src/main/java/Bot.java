import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
    ArrayList<Task> taskList;
    public Bot()
    {
        taskList=new ArrayList<Task>();
    }
    private void echo(String st)
    {
        System.out.println("added: "+st);
        printLine();
    }
    private void printLine()
    {
        System.out.println("____________________________________________________________");
    }
    private void addList(Task t)
    {
        this.taskList.add(t);
    }
    private void sayBye()
    {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    private void printList()
    {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<this.taskList.size();i++)
        {
            System.out.println((i+1)+". "+this.taskList.get(i).toString());
        }
        printLine();
    }
    private void markAsDone(int index)
    {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.get(index).markTaskAsDone();
        } else {
            System.out.println("Invalid task index.");
        }
    }
    private void unmarkAsDone(int index)
    {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.get(index).unmarkTaskAsDone();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    protected void run()
    {
        Scanner sc = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Venti.");
        System.out.println("What can I do for you?");
        printLine();

        while(true)
        {
            String input=sc.nextLine();
            if(input.equals("bye")) break;
            if(input.equals("list"))
            {
                printList();
                continue;
            }
            if(input.startsWith("mark"))
            {
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                this.markAsDone(index);
                System.out.println(this.taskList.get(index).toString());
                continue;
            }
            if(input.startsWith("unmark"))
            {
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                this.unmarkAsDone(index);
                System.out.println(this.taskList.get(index).toString());
                continue;
            }
            if(input.startsWith("todo"))
            {
                printLine();
                System.out.println("Got it. I've added this task:");
                String taskDescription = input.substring(5);
                Todo todo = new Todo(taskDescription);
                addList(todo);
                System.out.println(todo);
                printLine();
                continue;
            }
            if(input.startsWith("deadline")) {
                String[] parts = input.split(" /by ");
                String taskDescription = parts[0].substring(9);
                String by = parts[1];
                printLine();
                System.out.println("Got it. I've added this task:");
                Deadline deadline = new Deadline(taskDescription, by);
                addList(deadline);
                System.out.println(deadline);
                printLine();
                continue;
            }
            if(input.startsWith("event")) {
                String[] parts = input.split(" /from | /to ");
                String taskDescription = parts[0].substring(6);
                String from = parts[1];
                String to = parts[2];
                printLine();
                System.out.println("Got it. I've added this task:");
                Event event = new Event(taskDescription, from, to);
                addList(event);
                System.out.println(event);
                printLine();
                continue;
            }
        }
        sayBye();
    }


}
