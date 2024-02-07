public class ToDos extends Task{
    public ToDos(String line){
        super(line);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
