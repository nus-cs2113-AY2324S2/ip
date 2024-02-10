public class Deadline extends Task {
        public Deadline(String description) {
            super(description);
        }

        @Override
        public String getDescription() {
            String realDescription = description.split("/by ")[0];
            return realDescription;
        }

        @Override
        public String getTimespan() {
            String date = "(by: " + description.split("/by ")[1] + ")";
            return date;
        }

        @Override
        public String getTaskIcon() {
            return "D";
        }
}

