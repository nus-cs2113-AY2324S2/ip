# User Guide
Evelyn is a chatbox that helps the user to record tasks, using Command Line Interface(CLI).
## Features and Usage

### Exit the program: exit
Leave the program

Format: `bye`

### View task list: list
List all the current tasks.

Format: `list`

### Add a todo task: todo

Add a todo task to the list of tasks.

Format: `todo [description of todo task]`

Example of usage:

`todo read books`

### Add a deadline task: deadline

Add a task with deadline to the list of tasks.

Format: `deadline [description of todo task] /by [time]`

Example of usage:

`deadline quiz /by 5pm`

### Add a event task: event

Add a task of event with starting time and end time to the list of tasks.

Format: `event [description of todo task] /from [time] /to [time]`

Example of usage:

`event lecture /from 4pm /to 6pm`

### Mark a task: mark

Mark a task in the list of tasks as done.

Format: `mark [index of the task]`

Example of usage:

`mark 1`

### Mark a task: unmark

Mark a task in the list of tasks as not done.

Format: `unmark [index of the task]`

Example of usage:

`unmark 1`

### Delete a task: delete

Delete a task in the list of tasks.

Format: `delete [index of the task]`

Example of usage:

`delete 1`

### Find a task: find

Find for a keyword in the list of tasks.

Format: `find [keyword]`

Example of usage:

`find lec`


