public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list. Please input at least one!");
        } else {
            System.out.println("Here are tasks in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i));
            }
        }
    }
}

