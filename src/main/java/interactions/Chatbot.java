package interactions;
public class Chatbot {
    private String[] instructions = {"todo", "deadline", "event", "list", "bye"};
    public String getName() {
        return name;
    }
    private String name;
    public Chatbot() {
        name = "MOBY";
    }
    public void rename(String name) {
        this.name = name.toUpperCase();
    }
    public void greet() { 
        System.out.println(name + ": Hello! I'm " + name + "!");
        System.out.println(name + ": What can I do for you?");
    }
    public void echo(String line) {
        System.out.println(line);
    }
    // identify if its a typo, otherwise just echo
    public void didYouMean(String line) {
        String[] parts = line.split(" ", 2);
        String typo = parts[0];
        int typoSize = typo.length();
        for (String instruction : instructions) {
            if (typo.equals(instruction.substring(0, typoSize))) {
                System.out.println("Did you mean: " + instruction + "?");
                return;
            }
        }
        echo(line);
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
