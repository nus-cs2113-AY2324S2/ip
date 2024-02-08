public class Task {
    protected String description;
    protected int index;
    protected boolean isDone;
    protected Type taskType;

    public Task(String description, int arrayIndex) {
        this.description = description.trim();
        this.index = arrayIndex + 1;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getCheckMark() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return this.getIndex()+ ". ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}