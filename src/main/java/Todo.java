public class Todo extends Task{

        public static final String[] args = {};

        public Todo (String description){
            super(description);
        }

        @Override
        public String toString(){
            return "[T]" + super.toString();
        }
}
