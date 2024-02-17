package interactions;
public class Chatbot {
    protected String name;
    protected String INDENT = "      ";
    public Chatbot() {
        name = "MOBY";
    }
    public void printIndent() {
        System.out.print(INDENT);
    }
    public void rename(String name) {
        this.name = name.toUpperCase();
    }
    public void greet() { 
        System.out.println(name + ": Hello! I'm " + name + "!");
        System.out.println(name + ": What can I do for you?");
    }
    public void exit() {
        System.out.println(name + ": Bye. Hope to see you again soon!");
    }
}
