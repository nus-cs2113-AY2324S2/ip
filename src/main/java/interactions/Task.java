package interactions;
import java.util.Scanner;
import interactions.Chatbot;
public class Task {
    protected String task;
    protected boolean isMarked;

    public boolean HaveToDo() {
        return haveToDo;
    }

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    protected boolean haveToDo;
    Task[] list = new Task[100];
    protected int currSize = 0;
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
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
        System.out.print("[" + (isMarked() ? "T" : " ") + "] ");
        System.out.print("[" + (isMarked() ? "X" : " ") + "] ");
        System.out.println(getTask());
    }
}