# User Guide for Bob
Bob is a one-stop solution to track your daily tasks!

## Quick Start
1.Install [Java 11](https://www.oracle.com/java/technologies/downloads/#java11).

2.Download the jar file [here](https://github.com/EdmundTangg/ip/releases/tag/A-Release).

3.Copy the file to the folder you want to use as the home folder for the jar file.

4.Open a command terminal, cd into the folder you put the jar file in

5.Run this command: `java -jar ip.jar`


## Features 

Notes about the command format:

- Commands are all case-sensitive and must strictly follow case specified.
- Words in `UPPER_CASE` are the parameters to be supplied by the user.

<br/>


### Adding a todo task: `todo`
Add a todo task to the list of tasks.

Format: `todo TASK` 

Example: `todo read book `

<br/>

### Adding a deadline task: `deadline`
Add a deadline task to the list of tasks.

Format: `deadline TASK /by TIME`
- Not allowed to use `/by` in any parameters. 

Example: `deadline return book /by June 6th`

<br/>

### Adding an event task: `event`
Add an event task to the list of tasks.

Format: `event TASK /from TIME /to TIME`
- Not allowed to use `/from` and `/to` in any parameters.

Example: `event project meeting /from Aug 6th 2pm /to 4pm`

<br/>

### Deleting a task: `delete`
Delete a task from the list of tasks.

Format: `delete INDEX`
- Edits the task at the specified INDEX. 
- The index must be an integer shown in the displayed task list.

Example: `delete 1`

<br/>

### Marking a task: `mark`
Mark a task as done.

Format: `mark INDEX`
- marks the task at the specified INDEX.
- The index must be an integer shown in the displayed task list.

Example: `mark 1`

<br/>

### Unmarking a task: `unmark`
Unmarking a task as not done.

Format: `unmark INDEX`
- Unmarks the task at the specified INDEX.
- The index must be an integer shown in the displayed task list.

Example: `unmark 1`

<br/>

### Listing all tasks: `list`
Shows a list of tasks that we are tracking.

Format: `list`

<br/>

### Finding a task: `find`
Shows a list of tasks which contain a keyword.

Format: `find KEYWORD`
- the search is case-sensitive. eg `book` will not match `BOOK`

Example: `find book`

<br/>

### Exiting the program: `bye`
Exits the program.

Format: `bye`

<br/>

### Saving the data
Tasks' data are saved in the local file `data.txt` automatically after any command that changes the data. There is no need to save manually.

Do not tamper with the format of the file, else Bob will be unable to read the data as intended.

