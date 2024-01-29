import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Lotes {
    public static String lineSeparator = System.lineSeparator();
    public static String underscore = "    ____________________________________________________________";
    public static void printUserList(List<String> list){
        //System.out.println("List: " + list.get(0));
        System.out.println(underscore);
        if(list.isEmpty()){
            System.out.println("    List is empty, please enter some text to add to list.");
        }
        int wordCount = 0;
        for(String items : list){
            wordCount++;
            System.out.print(wordCount + ". " + items + lineSeparator);
        }
        System.out.println(underscore);
    }
    public static void main(String[] args) {
        System.out.println(underscore + lineSeparator + "    Hello! I'm Lotes" + lineSeparator +
                "    What can I do for you?" + lineSeparator + underscore);
        String userInput;

        List<String> list = new ArrayList<>();

        while(true){
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                System.out.println(underscore + lineSeparator
                        + "    Bye. Hope to see you again soon!\n" + underscore);
                break;
            }else if(userInput.equals("list")){
                printUserList(list);
            }else{
                list.add(userInput);
                System.out.print(underscore + lineSeparator + "     added: " + userInput
                        + lineSeparator + underscore + lineSeparator);
            }
        }
    }
}
