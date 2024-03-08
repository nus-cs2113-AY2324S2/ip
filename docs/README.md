# GermaBot User Guide

GermaBot is a task list bot. You can add, delete, find and list the stored tasks. GermaBot recognizes 3 different type 
of tasks: Todo tasks, deadline tasks and event tasks. 
GermaBot is also capable of saving files to your local hard disk, to the `/src/GermaBotData.txt` file.

## Features 

> [!NOTE]
> ## Notes about the command format.
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> - Extra parameters that do not take in any parameters (such as `list` and `bye`) will be ignored. 
> e.g. if the input is `list 123`, it will be interpreted as `list`.

    
### Adding Todo tasks : `todo`
Adds a todo task to the task list.

Format: `todo TASK_NAME`

### Adding Deadline tasks : `deadline` 
Adds a deadline task to the task list. 

Format: `deadline TASK_NAME /by DEADLINE`

Example:
```
deadline Read a book /by 4th July
```

### Adding Event tasks : `event`
Adds an event task to the task list. 

Format: `event TASK_NAME /from STARTDATE /to ENDDATE`

Example:
```
event Dad's party /from 6pm /to 12am
```

### Listing all tasks : `list`
Shows the list of the current tasks, including the details of the task.

Example input:
```
list 
```
Example output:
```
Gotcha! Here are your tasks:
1. [T][X] Read a book
2. [D][ ] Finish IP (by: 23rd November)
3. [E][X] Dad's birthday party (from: 6pm to: 12am)
```

### Deleting tasks : `delete`
Deletes a task from the task list.

Format: `delete TASK_NUMBER`

Example:
```
delete 2
```
Expected output:
```
Okay! I've removed this task from your To Do List:
[D][X] Finish IP (by: tmr)
Now you have, let me count... 4 items left in your To Do List!
```

### Finding tasks : `find`
Finds a task containing certain keywords.

Format: `find KEYWORDS`

Example:
```
find book
```
Expected output:
```
Gotcha! Finding tasks containing 'book'...
1. [T][X] Read a book
```

### Marking tasks as done : `mark`
Marks a task as done. 

Format: `mark TASK_NUMBER`

Example: 
```
mark 2
```
Expected output:
```
Good job! I'll mark this task as done: [X] Finish IP
```

### Marking tasks as undone : `unmark`
Marks a task as undone.

Format: `unmark TASK_NUMBER`

Example
```
unmark 1
```
Expected output:
```
Aww, not done? Okay, I'll mark this task as undone: [ ] Read a book
```

### Exiting the program: `bye`
Exits the program.

Format: `bye`
