# Humi User Guide

## Features

### List all tasks: list

Prints all saved tasks in the list

Format: `list`


### Add Todo task: todo

Adds a todo task to the task list

Format: `todo TASK`

Example: `todo Shopping`


### Add Deadline task: deadline

Adds a deadline task to the task list<br/>
Dates in the format of yyyy-mm-dd hh:mm will be reformatted into MMM-dd-yyyy hh:mm AM/PM

Format: `deadline TASK /by DEADLINE`

Examples: 
- `deadline Assignment /by 2024-10-15 18:30`
- `deadline Lab report /by Tuesday`


### Adding a Event: event

Adds an event task to the task list<br/>
Dates in the format of yyyy-mm-dd hh:mm will be reformatted into MMM-dd-yyyy hh:mm AM/PM

Format: `event TASK /from START /to END`

Examples:
- `event PGP Party /from 2024-10-15 18:30 /to 19:30`
- `event Career Fair /from Tuesday /to Friday`


### Mark task: mark

Sets a particular task in the task list as done

Format: `mark INDEX`

Example: `mark 4`


### Unmark task: unmark

Sets a particular task in the task list as not done

Format: `unmark INDEX`

Example: `unmark 3`


### Delete task: delete

Deletes a particular task from the task list

Format: `delete INDEX`

Example: `delete 2`


### Find task: find

Finds all tasks that contain a particular keyword from the task list

Format: `find KEYWORD`

Example: `find assignment`
