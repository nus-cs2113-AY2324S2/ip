# Aragorn

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest joe.jar release [here](https://github.com/vimalapugazhan/ip/releases).
3. Copy the file to a folder you want as the home directory of the file.
4. From the command terminal, `cd` into the home directory of the jar file and run the command `java -jar Aragorn.jar` to launch the application.
5. Type a command in the command box and press Enter to execute it. Refer to **Features** below for details of each available command.

## Features 

- ### Add task

  - Add 3 different types of tasks; ToDo, Deadline and Event.

- ### Delete  task

  - Remove a task from the list.

- ### Mark and Unmark

  - Mark a task complete.
  - Unmark a completed task.

- ### Find task

  - Find tasks containing inputted keyword.

- ### Save and Load

  - Automatically saves the list when a command is run and loads it the next time the program is launched.

## Commands

### `todo` -  Add ToDo task

Usage:

`todo <description>`

Expected outcome:

```
todo Read book
    __________________________________________________________
    Got it. I've added this task:
    [T][ ] Read book

    You have 1 / 1 remaining tasks in the list.
    __________________________________________________________
```
________________________________________
### `deadline` -  Add Deadline task

Usage:

`deadline <description> /by <deadline>`

Expected outcome:

```
deadline CG2113 Quiz /by Friday 12pm
    __________________________________________________________
    Got it. I've added this task:
    [D][ ] CG2113 Quiz (by: Friday 12pm)

    You have 2 / 2 remaining tasks in the list.
    __________________________________________________________
```
________________________________________
### `event` -  Add Event task

Usage:

`event <description> /from <start> /by <end>`

Expected outcome:

```
event Birthday party /from Sunday 5pm /to 10pm
    __________________________________________________________
    Got it. I've added this task:
    [E][ ] Birthday party (from: Sunday 5pm to: 10pm)

    You have 3 / 3 remaining tasks in the list.
    __________________________________________________________
```
________________________________________
### `list` -  Display list

Usage:

`list`

Expected outcome:

```
list
    __________________________________________________________

    Here are the tasks in your list: 
    1. [T][ ] Read book
    2. [D][ ] CG2113 Quiz (by: Friday 12pm)
    3. [E][ ] Birthday party (from: Sunday 5pm to: 10pm)


    You have 3 / 3 remaining tasks in the list.
    __________________________________________________________
```
________________________________________
### `mark` -  Mark task as complete

Usage:

`mark <index>`

Expected outcome:

```
mark 2
    __________________________________________________________
    Nice! I've marked this task as done:
       [D][X] CG2113 Quiz (by: Friday 12pm)

    You have 2 / 3 remaining tasks in the list.
    __________________________________________________________
```
________________________________________
### `unmark` -  Unmark a completed task

Usage:

`unmark <index>`

Expected outcome:

```
unmark 2
    __________________________________________________________
    OK, I've marked this task as incomplete:
       [D][ ] CG2113 Quiz (by: Friday 12pm)

    You have 3 / 3 remaining tasks in the list.
    __________________________________________________________
```
________________________________________
### `find` -  Display a list of task containing keyword

Usage:

`find <keyword>`

Expected outcome:

```
list
    __________________________________________________________

    Here are the tasks in your list: 
    1. [T][ ] Read book
    2. [D][X] CG2113 Quiz (by: Friday 12pm)
    3. [E][ ] Birthday party (from: Sunday 5pm to: 10pm)
    4. [E][ ] 2113 meeting (from: Tuesday 6pm to: 7pm)


    You have 3 / 4 remaining tasks in the list.
    __________________________________________________________

find 2113
    __________________________________________________________

    Here are the tasks in your list: 
    1. [D][X] CG2113 Quiz (by: Friday 12pm)
    2. [E][ ] 2113 meeting (from: Tuesday 6pm to: 7pm)


    2 matching tasks found!
    __________________________________________________________

```
________________________________________
### `delete` -  Delete task from list

Usage:

`delete <index in list>`

Expected outcome:

```
delete 2
    __________________________________________________________
    I've deleted this task from the list:
       [D][X] CG2113 Quiz (by: Friday 12pm)

    You have 3 / 3 remaining tasks in the list.
    __________________________________________________________

list
    __________________________________________________________

    Here are the tasks in your list: 
    1. [T][ ] Read book
    2. [E][ ] Birthday party (from: Sunday 5pm to: 10pm)
    3. [E][ ] 2113 meeting (from: Tuesday 6pm to: 7pm)


    You have 3 / 3 remaining tasks in the list.
    __________________________________________________________

```
________________________________________
### `help` -  Display command list

Usage:

`help`

Expected outcome:

```
help
    __________________________________________________________
    Here is a list of commands:

    "list": Displays list of tasks.

    "todo <description>": Adds a Todo task to the list.

    "deadline <description> /by <deadline>": Adds a task and its deadline to the list.

    "event <description> /from <start> /to <end>": Adds an event and its start and end conditions to the list.

    "mark <task number>": Marks the corresponding task in the list as completed.

    "unmark <task number>": Marks the corresponding task in the list as incomplete.

    "delete <task number>": Removes the corresponding task from the list.

    "find <keyword>": Displays the tasks containing the keyword.

    "help": Displays this list of commands.

    "bye": Closes the program.
    __________________________________________________________
```
________________________________________
### `bye` -  Exits the program

Example of usage:

`bye`

Expected outcome:

```
bye
    __________________________________________________________
        Farewell. May we meet again!
    __________________________________________________________
```
________________________________________
