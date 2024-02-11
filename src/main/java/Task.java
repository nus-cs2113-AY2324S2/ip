public class Task {

  protected String description;
  protected boolean isDone;

  public Task(String description) {
        setDescription(description);
        markAsNotDone();
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description=description;
  }

  public boolean getIsDone() {
        return this.isDone;
  }

  public void markAsDone() {
    this.isDone=true;
  }

  public void markAsNotDone() {
    this.isDone=false;
  }

  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

}