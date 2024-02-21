import java.util.Scanner;
import java.util.ArrayList;

// I am using the Google Java Style Guide https://google.github.io/styleguide/javaguide.html

public class Duke {

  static ArrayList<Task> enteredCommands=new ArrayList<>();

  public static void greet() {
    System.out.printf("%s%n", "    ____________________________________________________________");
    System.out.printf("%s%n", "     Hello! I'm CHAT-MAN");
    System.out.printf("%s%n", "     What can I do for you?");
  }

  public static void exit() {
    System.out.printf("%s%n","    ____________________________________________________________");
    System.out.printf("%s%n","     Bye. Hope to see you again soon!");
    System.out.printf("%s%n","    ____________________________________________________________");
  }

  public static void replyAddedTask(){
    Task addedTask = enteredCommands.get(enteredCommands.size() - 1);
    int size=enteredCommands.size();
    System.out.printf("     Got it. I've added this task:%n      %s%n     Now you have %d tasks in the list.%n",
        addedTask.toString(),size);
  }

  public static void addTask(String userCommand) {

    System.out.printf("%s%n","    ____________________________________________________________");
    String taskType=userCommand.split(" ")[0].toUpperCase();

    switch (taskType) {
      default:
        break;

      case "TODO":
        String toDoDesc=userCommand.replaceAll("(?i)TODO","");
        enteredCommands.add(new Todo(toDoDesc));
        replyAddedTask();
        break;

      case "DEADLINE":
        String[] deadLine=userCommand.split("/");
        String deadLineDesc=deadLine[0].replaceAll("(?i)DEADLINE","");
        String by=deadLine[1].replaceAll("(?i)BY","").stripLeading();
        enteredCommands.add(new Deadline(deadLineDesc,by));
        replyAddedTask();
        break;

      case "EVENT":
        String[] event=userCommand.split("/");
        String eventDesc=event[0].replaceAll("(?i)EVENT","");
        String from=event[1].replaceAll("(?i)FROM","").stripLeading();
        String to=event[2].replaceAll("(?i)TO","").stripLeading();
        enteredCommands.add(new Event(eventDesc,from, to));
        replyAddedTask();
        break;
    }

  }

  public static void list () {
    System.out.printf("%s%n","    ____________________________________________________________");
    for(int i=0;i< enteredCommands.size();i++) {
      System.out.printf("     %d.%s %n",(i+1),enteredCommands.get(i).toString());
    }
  }

  public static void markUnmark(String userCommand,String markChoice) {

    switch (markChoice) {

      case "MARK":
        try{
          int toMark=Integer.parseInt(userCommand.replaceAll("[^0-9]", ""))-1;
          enteredCommands.get(toMark).markAsDone();
          System.out.printf("%s%n","    ____________________________________________________________");
          System.out.printf("     Nice! I've marked this task as done:%n       %s%n",
              enteredCommands.get(toMark).toString());
        } catch(Exception e){
          System.out.printf("%s%n","    ____________________________________________________________");
          System.out.printf("     Could not complete mark request.%n");
        }
        break;

      case "UNMARK":
        try{
          int toMark=Integer.parseInt(userCommand.replaceAll("[^0-9]", ""))-1;
          enteredCommands.get(toMark).markAsNotDone();
          System.out.printf("%s%n","    ____________________________________________________________");
          System.out.printf("     OK, I've marked this task as not done yet:%n       %s%n",
              enteredCommands.get(toMark).toString());
        } catch(Exception E){
          System.out.printf("%s%n","    ____________________________________________________________");
          System.out.printf("     Could not complete unmark request.%n");
        }
        break;

    }

  }

  public static boolean executesCommand(String userCommand) {

    String[] separatedCommand=userCommand.split(" ");

    switch (separatedCommand[0].toUpperCase()) {

      default:
        addTask(userCommand);
        return false;

      case "BYE":
        exit();
        return true;

      case "LIST":
        list();
        return false;

      case "MARK":
        markUnmark(userCommand,"MARK");
        return false;

      case "UNMARK":
        markUnmark(userCommand,"UNMARK");
        return false;

    }

  }

  public static void getUserCommands() {
    Scanner commandReader= new Scanner(System.in);
    String userCommand;
    boolean shouldExitLoop=false;

    while (!shouldExitLoop) {
      if (enteredCommands.size()>=100) {
        exit();
        shouldExitLoop=true;
        continue;
      }

      System.out.printf("%s%n%n", "    ____________________________________________________________");
      userCommand = commandReader.nextLine();
      shouldExitLoop = executesCommand(userCommand);

    }

  }


  public static void main(String[] args) {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    greet();
    getUserCommands();

  }
}
