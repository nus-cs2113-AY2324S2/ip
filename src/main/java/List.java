
public class List {
    private String[] list;
    private int itemCount = 0;

    public List() {
        this.list = new String[100];
    }

    public void getList() {
        Binks.createLineSpacing();
        for (int i = 0; i < itemCount; i++){
            System.out.println( (i + 1) + ". " + list[i]);
        }
        Binks.createLineSpacing();
    }

    public void addItem(String item){
        Binks.createLineSpacing();
        list[itemCount] = item;
        itemCount++;
        System.out.println("added: " + item);
        Binks.createLineSpacing();
    }

}
