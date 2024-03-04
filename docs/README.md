# Gandalf User Guide

## Features 

### List of tasks: list

Prints out all the tasks inside the list

Format: `list`

### Adding Todo task: todo

Adds a todo task to the task list

Format: `todo [TASK]`

Example: `todo Do laundry`

### Adding Deadline task: deadline

Adds a deadline task to the task list<br/>
Date must be in the `yyyy-mm-dd` format, otherwise it won't be inputted

Format: `deadline [TASK] /by [DATE]`

Example: `deadline Do CS2113 project /by 2024-03-08`

### Adding Event task: event

Adds an event task to the task list

Format: `event [TASK] /from [START DATE] /to [END DATE]`

Example: `event MCDonald's part time work /from Monday /to Friday`

### Deleting task: delete

Deletes a particular task from the task list</br>
Only a valid integer can be inputted after the delete command

Format: `delete [INDEX]`

Example: `delete 1`

### Marking task: mark

Sets a particular task from the task list as done</br>
Only a valid integer can be inputted after the mark command

Format: `mark [INDEX]`

Example: `mark 2`

### Unmarking task: unmark

Sets a particular task from the task list as not done</br>
Only a valid integer can be inputted after the mark command

Format: `unmark [INDEX]`

Example: `unmark 3`

### Finding task: find

Finds all tasks that contains the inputted keyword from the task list

Format: `find [KEYWORD]`

Example: `find project`