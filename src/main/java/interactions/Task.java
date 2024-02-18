package interactions;
import java.util.Scanner;
import interactions.Chatbot;
public class Task {
    private String task;
    private boolean isMarked;

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    protected boolean haveToDo;
    public String getTask() {
        return task;
    }
    public boolean isMarked() {
        return isMarked;
    }
    public void setMarked(boolean marked) {
        isMarked = marked;
    }
    public Task(String task) {
        this.task = task;
        this.isMarked = false;
    }
    public void print() {
        System.out.print("[" + (isMarked() ? "X" : " ") + "] ");
        System.out.println(getTask());
    }
}