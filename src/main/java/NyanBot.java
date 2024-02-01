import java.util.Scanner;
public class NyanBot {
    private static String line = "____________";
    private static Task[] input = new Task[100];
    private static int inputCount = 0;

    private static void echo() {
        Scanner in = new Scanner(System.in);
        String[] splitInput = new String[2];
        String userInput = in.nextLine();
        splitInput = userInput.split(" ");

        switch(splitInput[0]) {
        case "bye":
            return;
        case "list":
            System.out.println(line);
            for (int i = 0; i < inputCount; i++) {
                System.out.println(i + 1 + ". " +
                        input[i].getStatusIcon() + " " + input[i].getDescription());
            }
            break;
        case "mark":
            int markIndex = Integer.parseInt(splitInput[1]) - 1;
            input[markIndex].markAsDone();
            System.out.println(line);
            System.out.println("はい、markしました！");
            break;
        case "unmark":
            markIndex = Integer.parseInt(splitInput[1]) - 1;
            input[markIndex].unmarkAsDone();
            System.out.println(line);
            System.out.println("はい、unmarkしました！");
            break;
        default:
            System.out.println(line);
            Task newTask = new Task(userInput);
            input[inputCount] = newTask;
            inputCount++;
            System.out.println("Added: " + userInput);
        }
        echo();
    }
    public static void main(String[] args) {
        String greet = "お帰りなさいませ、ご主人様。ニャンー♡♡";
        String prompt = "なにをしたいの？";
        String bye = "じゃー、またね〜！！";

        System.out.println(line);
        System.out.println(greet + "\n" + prompt);

        echo();

        System.out.println(line);
        System.out.println(bye);
    }
}
