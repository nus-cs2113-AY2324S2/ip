public class List {
    private int numItems;
    private String[] listContents;

    public List() {
        this.numItems = 0;
        this.listContents = new String[100];
    }

    public void addListContents(String content) {
        this.listContents[numItems] = content;
        this.numItems += 1;
    }

    public void showListContents() {
        if (this.numItems == 0) {
            System.out.println("List is empty. Please enter something first.");
        }
        for (int i = 0; i < numItems; i += 1) {
            System.out.println(i+1 + ". " + this.listContents[i]);
        }
    }
}
