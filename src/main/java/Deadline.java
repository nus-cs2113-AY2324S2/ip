public class Deadline extends Task {

    public Deadline(String description) {
        super(description.substring(9,description.indexOf("/by"))
                + "(by: " +description.substring(description.indexOf("/by")+4) + ")");
        this.type = "D";

    }

}
