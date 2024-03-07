# <span style="color:LightBlue">Bobby User Guide

Bobby is a command-line application that allows you to manage your tasks in the form of a To-Do list. It allows you to add/delete various types of tasks, update their statuses and even search for specific tasks via a keyword.
## <span style="color:Orange">Quick Start

Prerequisites: JDK 11, Intellij.

1. Ensure you have Java installed on your computer.
2. Download the latest Bobby.jar file from [here](https://github.com/seandooa/ip/releases).
3. Navigate to the directory you downloaded the file in and open a command prompt.
   - You can do so on windows by typing 'cmd' in the file explorer address bar
4. Run the application using the command `java -jar Floda.jar`.
5. The CLI along with the welcome message should appear.

## <span style="color:Orange">Features
### <span style="color:Orange">Adding a Todo: `todo`

Adds a task with a description.

Format: `todo <description>`

Example Input: `todo Go to school`

Example Output: `Added: [T] [ ] Go to school`

### <span style="color:Orange">Adding a Deadline: `deadline`

Adds a task with a description and a deadline.

Format: `deadline <description> /by <deadline>`

Example Input: `deadline Go to school /by 9am`

Example Output: `Added: [D] [ ] Go to school (by: 9am)`

###  <span style="color:Orange">Adding a Event: `event`

Adds a task with a description, a start time and an end time.

Format: `event <description> /from <start time> /to <end time>`

Example Input: `event Go to school /from 9am /to 9pm`

Example Output: `Added: [E] [ ] Go to school (from: 9am to: 9pm)`

###  <span style="color:Orange">Listing all tasks: `list`

Shows a list of all tasks currently added.

###  <span style="color:Orange">Deleting a Task: `delete`

Deletes the specified task.

Format: `delete <task number>`

> Tip: Use the command `list` to find the `<task number>` of the task you wish you delete

###  <span style="color:Orange">Marking a Task as Done: `mark`

Marks a specific task as done.

Format: `mark <task number>`

> Tip: Use the command `list` to find the `<task number>` of the task you wish to mark as done.

Example Input: `mark 1`

###  <span style="color:Orange">Unmarking a Task: `unmark`

Unmarks a task as done.

Format: `unmark <task number>`

Replace `<task number>` with the index of the task you want to unmark.
> Tip: Use the command `list` to find the `<task number>` of the task you wish to unmark.

Example Input: `unmark 1`

Example Output: `I have marked this task as not done: [T] [ ] Buy Groceries`


###  <span style="color:Orange">Finding Tasks: `find`

Searches for tasks containing a specific keyword in their descriptions.

Format: `find <keyword>`

Example Input: `find school`

###  <span style="color:Orange">Exiting the Application: `bye`

Exits the chatbot Bobby.

Format: `bye`