import java.util.Scanner;
public class Conversation {
    public void dividingLine(){
        System.out.println("\t________________________________");
    }
    public void communicate(){
        Task[] list = new Task[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine().trim() ;
        while(!line.equals("bye")){
            dividingLine();
            if(line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + list[i].getStatusIcon() + " " + list[i].getContent());
                }
            }
            else if(line.contains("unmark")){
                int spaceIndex = line.indexOf(" ");
                int number = Integer.parseInt(line.substring(spaceIndex + 1));
                if(number > index){
                    System.out.println("Oops, you do not have this task");
                }
                else{
                    System.out.println("OK, I've marked this task as not done yet:");
                    list[number - 1].changeStatus(false);
                    System.out.println("\t" + list[number - 1].getStatusIcon() + " " + list[number - 1].getContent());
                }
            }
            else if(line.contains("mark")){
                int spaceIndex = line.indexOf(" ");
                int number = Integer.parseInt(line.substring(spaceIndex + 1));
                if(number > index){
                    System.out.println("Oops, you do not have this task");
                }
                else{
                    System.out.println("Nice! I've marked this task as done:");
                    list[number - 1].changeStatus(true);
                    System.out.println("\t" + list[number - 1].getStatusIcon() + " " + list[number - 1].getContent());
                }
            }
            else{
                list[index] = new Task(line);
                index++;
                System.out.println("\tadded: " + line);
            }
            dividingLine();
            line = in.nextLine();
        }
    }
    public void describeFunctionality(){
        dividingLine();
        System.out.println("\tI have the following features:");
        System.out.println("\t\t1. Echo and store whatever task entered by you, my user.");
        System.out.println("\t\t2. Type \"list\" to see what I have recorded for you.");
        System.out.println("\t\t3. Type \"mark\" + \"number\" to mark tasks as done");
        System.out.println("\t\t4. Type \"unmark\" + \"number\" to mark tasks as not done");
        System.out.println("\t\t5. Type \"bye\" to say goodbye to me");
    }
    public void startConversation(){
        dividingLine();
        System.out.println("\tHi!, I'm 'Noob'");
        System.out.println("\tWhat can I do for you?");
        describeFunctionality();
        dividingLine();

        communicate();
        dividingLine();
        System.out.println("\tBye. Hope to see you again soon!");
        dividingLine();
    }
}
