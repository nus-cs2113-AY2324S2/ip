# User Guide

## Feature List
1. Add Tasks
   Users can add various types of tasks such as to-do, deadline, and event tasks to the task list.
   Command: `add`
2. View Task List
   Users can view all tasks currently in the task list.
   Command: `list`
3. Mark Task as Done
   Users can mark a task as done.
   Command: `mark`
4. Mark Task as Undone
   Users can mark a completed task as not done.
   Command: `unmark`
5. Delete Task
   Users can delete a task from the task list.
   Command: `delete`
6. Clear Task List
   Users can clear all tasks from the task list.
   Command: `clear`
7. Search Tasks
   Users can search for tasks containing specific keywords.
   Command: `find`
8. Save Task List
   Users can save the current task list to a file.
   Command: `save`
9. Help
   Users can view a list of supported commands for using the task manager.
   Command: `help`
10. Quit Task Manager
    Users can exit the task manager application.
    Command: `quit`




## Commands

### `list` - enter the list mode
Example of usage:

```
list
```

Expected outcome:
```
---------------------------------------------
Here are your current tasks in your list:

---------------------------------------------
```
### `Bye` - Quit the Qchat bot

### list mode commads :

### `add` - Add a task

Adds a new task to the to-do list.

Example of usage:

```
add
todo go to class
```
Expected outcome:
```
---------------------------------------------
Here are your current tasks in your list:
1. [T][ ]  go to class
---------------------------------------------
```


### `clear` - Clear all tasks

Example of usage:

```
clear
```
Expected outcome:
```
list cleared
```

### `list` - list all current tasks

Example of usage:

```
list
```
Expected outcome:
```
---------------------------------------------
Here are your current tasks in your list:
---------------------------------------------
```
### `save` - save current tasks into a file

Example of usage:

```
save 
```
Expected outcome:
```
File exists, overwriting file
```


### `mark & unmark` - set a task as done or not done

Example of usage:

```
mark
1
```
Expected outcome:
```
I've marked this task as  done:[T][x]  go to class
```

### `quit` - quit list mode

Example of usage:

```
quit
```
Expected outcome:
```
Task change complete
```
### `help` - get help of other commands

Example of usage:

```
help
```
Expected outcome:
```
supported commands: add , quit , help , mark, unmark ,clear, list, delete, find ,save
```

### `find` - find task which contains a given keyword

Example of usage:

```
find
class
```
Expected outcome:
```
---------------------------------------------
Here are the matching tasks in your list:
1. [T][x]  go to class
---------------------------------------------
```

### `delete` - delete a task in the list

Example of usage:

```
delete 
1
```
Expected outcome:
```
---------------------------------------------
Here are your current tasks in your list:
---------------------------------------------

```

