public class Query {
    // attributes of query
    // input must always be in lowercase
    private String rawInput;
    private String lowercaseInput;

    // create constructor
    public Query() {
        this("");
    }

    public Query(String s){
        this.changeInput(s);
    }

    public void changeInput(String s) {
        this.rawInput = s;
        this.lowercaseInput = s.toLowerCase();
    }

    public String getInput() {
        return this.rawInput;
    }

    public String getLowercaseInput() {
        return this.lowercaseInput;
    }
}
