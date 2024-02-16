public class Query {
    // attributes of query
    // input must always be in lowercase
    private String rawInput;
    private String lowercaseInput;
    private String command;
    private String argument;

    // create constructor
    public Query() {
        this("");
    }

    public Query(String s) {
        this.changeInput(s);
    }

    /**
     * Parse string s to update attributes of Query.
     * @param s
     */
    public void changeInput(String s) {
        this.rawInput = s;
        this.lowercaseInput = rawInput.trim().toLowerCase();

        // Separate command and argument
        int index = this.lowercaseInput.indexOf(" ");
        if (index == -1) {
            // No Argument
            this.command = this.lowercaseInput;
            this.argument = null;
        } else {
            // There is an Argument
            this.command = s.substring(0,index).toLowerCase();
            this.argument = s.substring(index + 1);
        }
    }


    /**
     *
     * @return actual inputted query
     */
    public String getInput() {
        return this.rawInput;
    }

    /**
     *
     * @return actual inputted query but all in lowercase
     */
    public String getLowercaseInput() {
        return this.lowercaseInput;
    }

    /**
     *
     * @return command of query
     */
    public String getCommand() {
        return this.command;
    }

    /**
     *
     * @return argument of query (not in lowercase)
     */
    public String getArgument() {
        return this.argument;
    }
}
