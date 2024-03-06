// Handles user interface interactions
class Ui {
	// Prints the greeting message
	public void printGreeting() {
		// Printing the logo and greeting message
		String logo = "   _____\n" +
				"  / ____|\n" +
				" | (___   __ _ _ __ ___\n" +
				"  \\___ \\ / _` | '_ ` _ \\\n" +
				"  ____) | (_| | | | | | |\n" +
				" |_____/ \\__,_|_| |_| |_|\n" +
				"";
		System.out.println("Hello! I'm SAM\n" + logo + "\n" + "What can I do for you?\n");
	}

	// Prints the list of tasks
	public void printTasks(TaskList tasks) {
		// Printing tasks
		System.out.println("Here's what you got saved:");
		tasks.listTasks();
		printLine();
	}

	// Prints an error message
	public void printErrorMessage(String message) {
		// Printing error message
		System.out.println(message);
	}

	// Prints the farewell message
	public void printFarewell() {
		// Printing farewell message
		System.out.println("Hope to see you again soon!");
		System.out.println("  _____             ____            __\n" +
				" / ___/__  ___  ___/ / /  __ _____ / /\n" +
				"/ (_ / _ \\/ _ \\/ _  / _ \\/ // / -_)_/ \n" +
				"\\___/\\___/\\___/\\_,_/_.__/\\_, /\\__(_)  \n" +
				"                        /___/         ");
		printLine();
	}

	// Shows an error message for loading tasks
	public void showLoadingError() {
		// Showing loading error message
		System.out.println("Error loading tasks from file.");
	}

	// Prints a line separator
	public void printLine() {
		System.out.println("____________________________________________________________");
	}
}
