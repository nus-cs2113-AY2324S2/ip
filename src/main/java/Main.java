import duke.*;

import java.io.IOException;

import static duke.list.*;

public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.greet();
        startList();
        ui.goodbye();
    }
}