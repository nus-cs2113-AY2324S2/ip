package taskPackage;

import taskPackage.Task;

public class ToDos extends Task {


    public ToDos(String description, boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String getStatusIcon() {

        return "[T]" + super.getStatusIcon();
    }

}
