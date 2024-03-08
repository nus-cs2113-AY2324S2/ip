# User Guide

## Features 

### Feature- add task

Qchat can add 3 different types of tasks into the list

### Feature- save 

Qchat can save your current tasks and read it next time user uses

### Feature- find

Qchat find your task that contains a given keyword

### Feature- mark as done

Qchat can mark users task as done or not done


## Usage




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

