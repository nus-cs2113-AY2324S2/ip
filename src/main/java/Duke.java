import java.util.Scanner;

public class Duke {
  public static void main(String[] args) {
    Task[] list = new Task[100];



    //greeting
    System.out.println("Hello! I'm Apple");
    System.out.println("What can I do for you?");

    int index = 0;//number of items in the list
    String line =" ";

    while (!line.equals("bye")) {
      Scanner input = new Scanner(System.in);
      line = input.nextLine();

      Task t = new Task(line);
      String[] inputs = line.split(" ");

      if (inputs[0].equals("mark")){//mark as done
        int idx = Integer.parseInt(inputs[1]);
        list[idx - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + list[idx - 1].getStatusIcon()+ "]" + list[idx - 1].description);
      } else if(inputs[0].equals("unmark")){//unmark done
        int idx = Integer.parseInt(inputs[1]);
        list[idx - 1].unmarkDone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + list[idx - 1].getStatusIcon()+ "]" + list[idx - 1].description);
      }else if (line.equals("list")) {//lists items
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < index; i++) {
          System.out.println((i + 1) + ". [" + list[i].getStatusIcon()+ "]" + list[i].description);
        }
      }else if (line.equals("bye")) {//exit chat
        break;
      }else {//add items
        list[index] = t;
        System.out.println("added: " + t.description);
        index ++;
      }

    }
    System.out.println("Bye. Hope to see you again soon!");
  }

}
