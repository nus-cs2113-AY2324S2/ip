public class FindCommand extends Command{
    private String searchTerm;
    FindCommand(String searchTerm){
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws OGFException {
        System.out.println("Here are your search results: ");
        int numFound = 1;
        for (Task task : taskList.getTaskList()){
            if (task.getTaskName().contains(searchTerm) ){
                System.out.println(numFound++ + ". " + task.toString());

            }
        }
        ui.printBreakLine();
        return true;
    }
}
