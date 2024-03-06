package duke;

import java.util.ArrayList;

public class UnparsedTaskList {
    private static ArrayList<String> unparsedTaskList = new ArrayList<>(2);

    public String get(int i) {
        return unparsedTaskList.get(i);
    }

    public void add(String input) {
        unparsedTaskList.add(input);
    }

    public void remove(String input) {
        unparsedTaskList.remove(input);
    }

    public int size() {
        return unparsedTaskList.size();
    }
}
