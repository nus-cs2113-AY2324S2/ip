public class Deadline extends Task {
        public Deadline(String description) {
            super(description);
        }

        @Override
        public String getDescription() {
            String realDescription = description.split("(?i)/by ")[0].split("(?i)deadline")[1];
            return realDescription;
        }

        @Override
        public String getTimespan() {
            String date = "(by: " + description.split("(?i)/by ")[1] + ")";
            return date;
        }

        @Override
        public String getTaskIcon() {
            return "D";
        }

        @Override
        public String toString() {
            return "D | " + (isDone ? "1 | " : "0 | ") + getDescription() + " |" + getTimespan();
        }

}

