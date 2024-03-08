# Loopy
Loopy is here to help with tracking your tasks!

## How to Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the jar file [here](https://github.com/luozihui2003/Luo-Zi-Hui-ip/releases/tag/v0.2).
3. Copy the file into a folder to set it as home directory.
4. From the terminal, `cd` into home directory of the jar file and run the command `java -jar loopy.txt` to launch Loopy!
5. Type in the commands and press enter to execute.

## Features 

### Add task

Add 3 different types of tasks: ToDo, Deadline, and Event.

### Delete task

Delete a task from the list.

### Mark / Unmark task

Mark a task or unmark a completed task.

### Find

Find tasks containing the keyword.

### Save and Load

Automatically saves the list after every command, and loads it the next time the programme is launched.
## Commands

### `todo` - Add ToDo task

Adds a new ToDo task into the list.

Example of usage: 

`todo <description>`

Expected outcome:

```
todo watch lecture
    
    Got it. I've added this task:
    [T][ ] watch lecture

    Now you have 1 tasks in the list.    
```
### `deadline` - Add Deadline task

Adds a new Deadline task into the list.

Example of usage:

`deadline <description> /by <deadline>`

Expected outcome:

```
deadline CS2113 ip /by 8 Mar
    
    Got it. I've added this task:
    [D][ ] CS2113 ip (by: 8 Mar)

    Now you have 2 tasks in the list.    
```
### `event` - Add Event task

Adds a new Event task into the list.

Example of usage:

`event <description> /from <start> /to <end>`

Expected outcome:

```
event interview /from Friday 2pm /to 3pm

    Got it. I've added this task:
    [E][ ] interview (from: Friday 2pm to: 3pm)

    Now you have 3 tasks in the list.    
```
### `list` - Display list

Shows all existing tasks in the list.

Example of usage:

`list`

Expected outcome:

```
list
    
    Here are the tasks in your list: 
    1. [T][ ] watch lecture
    2. [D][ ] CS2113 ip (by: 8 Mar)
    3. [E][ ] interview (from: Friday 2pm to: 3pm)
```
### `mark` - Marks a task as complete

Marks a task as done according to the index specified by user.
Example of usage:

`mark <index>`

Expected outcome:

```
mark 1
    
    Nice! I've marked this task as done:
    [T][X] watch lecture
```
### `unmark` - Unmark a completed task

Unmarks a task as not done according to the index specified by user.

Example of usage:

`unmark <index>`

Expected outcome:

```
unmark 1

    OK, I've marked this task as not done yet:
       [T][ ] watch lecture
```
### `find` - Dislay a list of tasks containing keyword

Displays a list of tasks that matches the user-inputted keyword.

Example of usage:

`find <keyword>`

Expected outcome:

```
list

   Here are the tasks in your list: 
    1. [T][ ] watch lecture
    2. [D][ ] CS2113 ip (by: 8 Mar)
    3. [E][ ] interview (from: Friday 2pm to: 3pm)
    4. [T][ ] review lecture


find lecture

    Here are the matching tasks in your list: 
    1. [T][ ] watch lecture
    2. [T][ ] review lecture
```
### `delete` - Delete a task from list

Deletes a task according to the index specified by user.

Example of usage:

`delete <index>`

Expected outcome:

```
delete 2

    I have deleted the task:
       [D][ ] CS2113 ip (by: 8 Mar)
       You now have 3 tasks left
       
list

   Here are the tasks in your list: 
    1. [T][ ] watch lecture
    2. [E][ ] interview (from: Friday 2pm to: 3pm)
    3. [T][ ] review lecture
```
### `bye` - Exits the programme

Stops and exits the programme.

Example of usage:

`bye`

Expected outcome:

```
bye

   Bye! Hope to see you again ♡
```
