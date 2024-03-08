import java.util.ArrayList;

public class UI {
    static final String  WELCOME_GREETING = new String(
            "Qchat ,A truly humanized intelligent voice assisstant \n"
                    +"knows better about life and better about you\n"
                    +"What can I do for you?\n"
                    +"----------------------------------------------------------------\n") ;
    static final String GOODBYE_GREETING = new String(
            "--------------------------------------------------------\n" +
                    "goodbye\n"+ "Qchat, your life-long trusted companion\n");
    private static final String LIST_FORM=
            "please enter in one of the following form:\n"+
                    "todo 'description' \n" +
                    "deadline 'description' /by 'end time'\n"+
                    "event 'description' /from 'start time' /to 'end time'\n";

    public void Greeting(){
        System.out.print(WELCOME_GREETING);
    }

    public void Goodbye(){
        System.out.print(GOODBYE_GREETING);
    }
    public void PrintListForm(){
        System.out.println(LIST_FORM);
    }
    public static void PrintList(ArrayList todolist, String description ){
        int i=1;
        System.out.print("---------------------------------------------\n");
        System.out.println(description);
        for (Object t :todolist ){
            if(t == null){
                continue;
            }
            System.out.printf("%d. " + t.toString() +"\n",i);
            i++;
        }
        System.out.print("---------------------------------------------\n");
    }

}
