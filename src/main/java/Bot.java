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
            echo(input);
            Task task = new Task(input);
            addList(task);
        }
        sayBye();
    }


}
