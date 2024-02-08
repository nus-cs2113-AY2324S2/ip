// Subclass of Task
public class Deadline extends Task{

    public Deadline(String description, int index) throws DukeException {
        super(description, index); // Automatically invokes the constructor of Task
        toPrint();
    }
    public String getBy() throws DukeException{

        String[] splitLine = description.split("/"); // Split input from / onwards
        String getby = null;
        for (String s : splitLine) {
            if (s.startsWith("by")) {
                getby = s.substring(2);
                return getby;
            }

        }
        throw new DukeException();
        //return splitLine[1].substring(3); // Return date

    }

    // Override task's toString() to add [D] and the deadline timing
    @Override
    public String toString() {
        //printHeaders();
        try {
            return "[D]" + super.toString() + " (by: " + getBy() + ")";
        } catch (DukeException e) {
            throw new RuntimeException();
        }
    }

    public void toPrint() throws DukeException {
        if (getBy() != null) {
            System.out.println(toString());
        }
    }


}