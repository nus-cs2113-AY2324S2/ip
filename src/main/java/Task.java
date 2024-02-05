public class Task {
<<<<<<< HEAD
				protected String description;
				protected boolean isDone;

				public Task(String description) {
								this.description = description;
								this.isDone = false;
				}

				public String getStatusIcon() {
								return (isDone ? "[X]" : "[ ]");
				}

				public void markAsDone() {
								this.isDone = true;
				}

				public void unmarkAsDone() {
								this.isDone = false;
				}

				public String getDescription() {
								return description;
				}
=======
	protected String description;
	protected boolean isDone;

	public Task(String description) {
					this.description = description;
					this.isDone = false;
	}

	public String getStatusIcon() {
					return (isDone ? "[X]" : "[ ]");
	}

	public void markAsDone() {
					this.isDone = true;
	}

	public void unmarkAsDone() {
					this.isDone = false;
	}

	public String getDescription() {
					return description;
	}
>>>>>>> parent of 0daec96 (Task Indentation Error)
}

