import java.util.ArrayList;

class Ui {
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

	public void printTasks(ArrayList<Task> tasks) {
		// Printing tasks
		System.out.println("Here's what you got saved:");
		for (int j = 0; j < tasks.size(); j++) {
			System.out.println((j + 1) + "." + tasks.get(j));
		}
		printLine();
	}

	public void printErrorMessage(String message) {
		// Printing error message
		System.out.println(message);
	}

	public void printFarewell() {
		// Printing farewell message
		System.out.println("Bye. Hope to see you again soon!");
		printLine();
	}

	public void showLoadingError() {
		// Showing loading error message
		System.out.println("Error loading tasks from file.");
	}

	public void printLine() {
		System.out.println("____________________________________________________________");
	}
}
