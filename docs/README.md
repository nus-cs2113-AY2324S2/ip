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

Expected output:

`Got it. I've added this task: + [description]`

### Add a deadline task: deadline

Add a task with deadline to the list of tasks.

Format: `deadline [description of deadline task] /by [time]`

Example of usage:

`deadline quiz /by 5pm`

Expected output:

`Got it. I've added this task: + [description]`

### Add a event task: event

Add a task of event with starting time and end time to the list of tasks.

Format: `event [description of event task] /from [time] /to [time]`

Example of usage:

`event lecture /from 4pm /to 6pm`

Expected output:

`Got it. I've added this task: + [description]`

### Mark a task: mark

Mark a task in the list of tasks as done.

Format: `mark [index of the task]`

Example of usage:

`mark 1`

Expected output:

`Nice! I've marked this task as done: [despription of the task]`

### Mark a task: unmark

Mark a task in the list of tasks as not done.

Format: `unmark [index of the task]`

Example of usage:

`unmark 1`

Expected output:

`Nice! I've marked this task as not done: [despription of the task]`

### Delete a task: delete

Delete a task in the list of tasks.

Format: `delete [index of the task]`

Example of usage:

`delete 1`

Expected output:

`Ok! Task [index of the task] is removed from the list`

### Find a task: find

Find for a keyword in the list of tasks.

Format: `find [keyword]`

Example of usage:

`find lec`

Expected output:

`Here are the matching tasks in your list:`

