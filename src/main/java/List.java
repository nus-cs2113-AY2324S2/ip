public class List {
    private String[] list;
    private int itemCount;

    public List() {
        itemCount = 0;
        list = new String[100];
    }
    public void add (String input) {
        list[itemCount] = input;
        itemCount++;
        System.out.println(String.format("\t added: %s", input));
    }

    public void listAll() {
        for (int i = 0 ; i < itemCount; i++) {
            System.out.println(String.format("\t %01d. %s",i + 1, list[i]));
        }
    }
}
