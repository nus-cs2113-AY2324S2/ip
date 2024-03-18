# Baymax - User Guide
Baymax is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
## Features
### ToDo, Deadline and Event tasks
Baymax offers 3 types of tasks: ToDo, Deadline and Event.
- ToDo contains a description
- Deadline contains a description and dateline.
- Event contains a description and from and to.

They can be added into the task list using these starting commands: `todo`, `deadline` and `event`, respectively.

To view all the tasks from the task list, the `list` command can be executed. The list assigns each task an index which allows for more manipulation of the tasks, such as the `delete`, `mark`, `unmark` features.

### Mark/Unmark as done
Completion of tasks can be tracked by Baymax.
Initially, all tasks are marked as not done. Users may use the `mark` command to mark the task as done, or reverse the change using `unmark`.

### Keyword finder
User can input a keyword and Baymax will list out the tasks that contains the keyword, providing great convenience to the user. Just simply use the `find` command with the keyword.

### Loading and Saving of data
Baymax offers an auto-load and auto-save feature which ensures that the user's tasks and progress is retained.

## Usage

> **Note:**
> - Words in <code>UPPER_CASE</code> are the parameters to be supplied by the user.  (e.g. in <code>todo DESCRIPTION</code>, <code>DESCRIPTION</code>  is the parameter which can be used as <code>todo Homework</code>.)
> -  `INDEX` refers to the task's number with reference to the list.

### Adding ToDo task: `todo`
Adds a Todo task into the task list. 
Format: `todo DESCRIPTION`
Example of usage:
`todo wash the dishes`
Expected Outcome:
Creates the todo task with the description of "wash the dishes".
```
~------------------------------------------------------------~
    Bala-lalala... I've added this task:
    [T] [ ] wash the dishes
    You have a total of 1 tasks now. :)
~------------------------------------------------------------~
```

### Adding Deadline task: `deadline`
Adds a deadline task into the task list.
Format: `deadline DESCRIPTION /by DATELINE`
Example of usage:
`deadline submit assignment /by Saturday`
Expected Outcome:
Creates the deadline task with the description of "submit assignment (by: Saturday)".
```
~------------------------------------------------------------~
    Bala-lalala... I've added this task:
    [D] [ ] submit assignment (by: Saturday)
    You have a total of 2 tasks now. :)
~------------------------------------------------------------~
```

### Adding Event task: `event`
Adds an event task into the task list.
Format: `event DESCRIPTION /from FROM /to TO`
Example of usage:
`event John's Party /from 10th March 5pm /to 11th March 12pm`
Expected Outcome:
Creates the event task with the description of "John's Party (from: 10th March 5pm to: 11th March 12pm)".
```
~------------------------------------------------------------~
    Bala-lalala... I've added this task:
    [E] [ ] John's Party (from: 10th March 5pm to: 11th March 12pm)
    You have a total of 3 tasks now. :)
~------------------------------------------------------------~
```

### Listing all tasks: `list`
Shows a list of all tasks in the task list.
Format: `list`
Example of usage:
`list`
Expected outcome:
Displays all tasks in the task list.
```
~------------------------------------------------------------~
    Bala-lalala... Displaying your tasks: 
     1. [T] [ ] wash the dishes
     2. [D] [ ] submit assignment (by: Saturday)
     3. [E] [ ] John's Party (from: 10th March 5pm to: 11th March 12pm)
     You have a total of 3 tasks now. :)
~------------------------------------------------------------~
```

### Marking a task: `mark`
Marks a task as done.
Format: `mark INDEX`
Example of usage:
`mark 2`
Expected outcome:
Marks the second task in the list as done.
```
~------------------------------------------------------------~
     Bala-lalala... I've marked this task:
     [D] [X] submit assignment (by: Saturday)
~------------------------------------------------------------~
```

### Unmarking a task: `unmark`
Unmarks done from a task.
Format: `unmark INDEX`
Example of usage:
`unmark 2`
Expected outcome:
Unmarks the second task from the task list.
```
~------------------------------------------------------------~
     Bala-lalala... I've unmarked this task:
     [D] [ ] submit assignment (by: Saturday)
~------------------------------------------------------------~
``` 

### Deleting a task: `delete`
Deletes a task from the task list.
Format: `delete INDEX`
Example of usage:
`delete 2`
Expected outcome:
Deletes the second task from the task list.
```
~------------------------------------------------------------~
     Bala-lalala... I've deleted this task:
     [D] [ ] submit assignment (by: Saturday)
~------------------------------------------------------------~
```

### Finding a task: `find`
Lists all the tasks from the task list which contains the keyword.
Format: `find KEYWORD`
Example of usage:
`find wash`
Expected outcome:
Lists tasks that contains "wash".
```
~------------------------------------------------------------~
     Bala-lalala... Here are the tasks that contains your keyword:
     1. [T] [ ] wash the dishes
~------------------------------------------------------------~
```

### Exiting the program: `bye`
Exits the program.
Format: `bye`
Example of usage:
`bye`
Expected outcome:
Auto-saves the task list and exits the program.
```
~------------------------------------------------------------~
     I hope you are satisfied with my service! Goodbye. :)
~------------------------------------------------------------~
```