public class List {
    private Task[] list;
    private int itemCount;

    public List() {
        itemCount = 0;
        list = new Task[100];
    }
    public void add (String input) {
        Task newTask = new Task(input);
        list[itemCount] = newTask;
        itemCount++;
        System.out.println(String.format("\t added: %s", input));
    }
    public void mark (int index) {
        list[index - 1].mark();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println(String.format("\t\t%s %s",list[index - 1].getStatus(), list[index - 1].getName()));
    }
    public void unmark (int index) {
        list[index - 1].unmark();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println(String.format("\t\t%s %s", list[index - 1].getStatus(), list[index - 1].getName()));
    }
    public void listAll() {
        for (int i = 0 ; i < itemCount; i++) {
            System.out.println(String.format("\t %01d.%s %s",i + 1,list[i].getStatus(), list[i].getName()));
        }
    }
}
