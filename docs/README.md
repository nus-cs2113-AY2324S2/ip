# KuroBot User Guide

## Features 

### Feature-adding tasks

Add tasks of type todo, deadline, or event.

### Feature-marking tasks

Mark the specified task as done or undone.

### Feature-deleting tasks

Remove the specified task from the list.

## Usage
> [!NOTE]
> Words in UPPER_CASE are the parameters to be supplied by the user.


### `todo` - Adding a todo

Adds a task of type todo to the task list.

Format:
`todo TASK_NAME`

Example of usage: 

`todo borrow book`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 5 tasks in the list.
    ____________________________________________________________
```
### `deadline` - Adding a deadline

Adds a task of type deadline to the task list.

Format:
`todo borrow book`

Example of usage:

`deadline TASK_NAME /by DEADLINE`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.
    ____________________________________________________________
```

### `event` - Adding a event

Adds a task of type event to the task list.

Format:
`event TASK_NAME /from START_TIME /to END_TIME`

Example of usage:

`event project meeting /from Mon 2pm /to 4pm`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (from: Mon 2pm to: 4pm)
     Now you have 7 tasks in the list.
    ____________________________________________________________
```

### `list` - Viewing all tasks

Shows a list of all the added tasks.

Format:
`list`

Example of usage:

`list`

Expected outcome:

```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[X] read book
     2.[ ] return book
     3.[ ] buy bread
    ____________________________________________________________
```

### `mark` - Mark a task as done

Marks the specified task as completed.

Format:
`mark TASK_INDEX`

Example of usage:

`mark 2`

Expected outcome:

```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [X] return book
    ____________________________________________________________

```

### `unmark` - Mark a task as undone

Marks the specified task as uncompleted.

Format:
`mark TASK_INDEX`

Example of usage:

`mark 2`

Expected outcome:

```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [ ] return book
    ____________________________________________________________

``` 

### `delete` - Delete a task from list

Removes the specified task from the list.

Format:
`delete TASK_INDEX`

Example of usage:

`delete 3`

Expected outcome:

```
    ____________________________________________________________
     Noted. I've removed this task:
       [ ] buy bread
     Now you have 2 tasks in the list.
    ____________________________________________________________

```

### `bye` - End the session

Exits the program.

Format:
`bye`

Example of usage:

`delete 3`

Expected outcome:

```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
 ___   ___    ___    ___ 
|   |/   /   |  |   |  | 
|       /    |  |   |  | 
|   |\   \   |_ |___| _| 
|___| \___\    |_____|   

```


> [!TIP]
> Tasks data are saved in the hard disk automatically after any command that changes the data. \
> There is no need to save manually.
