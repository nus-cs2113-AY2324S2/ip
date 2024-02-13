package errorhandle;
import format.Formatting;
public class UserInputErrorHandle {

    protected Formatting format;

    public UserInputErrorHandle(){
        format = new Formatting();
    }
    public void undefinedTaskError(){
        format.dividingLine();
        System.out.println("\tUnknown Command!");
        format.dividingLine();
    }

    public void noTaskContentError(String taskType){
        format.dividingLine();
        System.out.println("\tOh nooooo!! The description of " + taskType + "is missing!!");
        format.dividingLine();
    }
}
