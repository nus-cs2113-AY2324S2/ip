# User Guide for Bob
Bob is a CLI app for tracking 3 types of tasks: todo, deadline and event.


## Features 

Notes about the command format:

- Commands are all case-sensitive and must strictly follow case specified.
- Words in `UPPER_CASE` are the parameters to be supplied by the user.


### Adding a todo task: `todo`
Add a todo task to the list of tasks.

Format: `todo TASK` 

Example: todo read book 


### Adding a deadline task: `deadline`
Add a deadline task to the list of tasks.

Format: `deadline TASK /by TIME`
- Not allowed to use `/by` in any parameters. 

Example: deadline return book /by June 6th



### Adding an event task: `event`
Add an event task to the list of tasks.

Format: `event TASK /from TIME /to TIME`
- Not allowed to use `/from` and `/to` in any parameters.

Example: event project meeting /from Aug 6th 2pm /to 4pm



### Deleting a task: `delete`
Delete a task from the list of tasks.

Format: `delete INDEX`
- Edits the task at the specified INDEX. 
- The index must be an integer shown in the displayed task list.

Example: delete 1


### Marking a task: `mark`
Mark a task as done.

Format: `mark INDEX`
- marks the task at the specified INDEX.
- The index must be an integer shown in the displayed task list.

Example: mark 1

### Unmarking a task: `unmark`
Unmarking a task as not done.

Format: `unmark INDEX`
- Unmarks the task at the specified INDEX.
- The index must be an integer shown in the displayed task list.

Example: unmark 1


### Listing all tasks: `list`
Shows a list of tasks that we are tracking.

Format: `list`


### Finding a task: `find KEYWORD`
Shows a list of tasks which contain a keyword.

Format: `find KEYWORD`
- the search is case-sensitive. eg `book` will not match `BOOK`


Example: find book


### Exiting the program: `bye`
Exits the program.

Format: `bye`


### Saving the data
Tasks' data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.



