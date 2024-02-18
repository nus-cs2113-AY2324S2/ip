public class Todo extends Task {
Todo(String task){
    super(task);
}
@Override
public String toString(){
    if (this.isDone){
        return("[T][X] " + this.taskName);
    }
    else{
        return("[T][ ] " + this.taskName);
    }
}
}
