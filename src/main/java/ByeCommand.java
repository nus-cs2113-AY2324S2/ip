public class ByeCommand implements Command {

    @Override
    public void execute (String input, Task[] taskList) {
        Gab.isExit = true;
        System.out.println("All the best!");
    }
}
