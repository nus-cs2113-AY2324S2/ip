# Eln: A chatbot

# User Guide

Eln is a Command Line Interface (CLI) optimised app for creating lists of tasks and managing them 
while also providing similar benefits to a Graphical User Interface (GUI)

- [Quick Start](https://nigelheng.github.io/ip/#quick-start)
- [Features](https://nigelheng.github.io/ip/#features)
    - [Getting Help: `help`](https://nigelheng.github.io/ip/#getting-help-help)
    - [Adding Tasks: `todo` `deadline` `event`](https://nigelheng.github.io/ip/#adding-tasks-todo-deadline-event)
    - [Listing Tasks: `list`](https://nigelheng.github.io/ip/#listing-tasks-list)
    - [Deleting a Task: `delete`](https://nigelheng.github.io/ip/#deleting-a-task-delete)
    - [Marking and Unmarking a Task: `mark` `unmark`](https://nigelheng.github.io/ip/#marking-and-unmarking-a-task-mark-unmark)
    - [Finding Tasks: `find`](https://nigelheng.github.io/ip/#finding-tasks-find)
    - [Exit from Eln: `bye`](https://nigelheng.github.io/ip/#exit-from-eln-bye)

### Quick Start

1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest ip.jar from [here]().
3. Copy the file to the folder you want to use as the home folder for your Bart.
4. Open a command terminal, cd into the folder you put the jar file in, and input java -jar ip.jar to run the application.

Refer to the Features list below for information on the commands accepted by Eln

### Features

#### Getting Help: `/help`

Shows a list of available commands.

Format: `/help`

<br>

#### Adding Tasks: `todo` `deadline` `event`

Adds a task into the list. A new save file is created if no existing file is found.
The save file can be found under `./ip/src/storage/saveFile.txt` 
and will be automatically opened on next start up of Eln.

Todo Format: `todo [TASK]`

Deadline Format: `deadline [TASK] /by [END]`

Event Format: `event [TASK] /from [START] /to [END]`

Examples:
- `todo wake up`
- `deadline shower /by 7.30am`
- `event lecture /from 9am /to 11am`

```
_______________________________________________
The following task has been added

1 [][T] wake up
...
```

<br>

#### Listing Tasks: `list`

Shows the full task list

Format: `list`

```
1 [][T] wake up
2 [][D] shower (by: 7.30am)
3 [][E] lecture (from: 9am) (to: 11am)
```

<br>

#### Deleting a Task: `delete`

Deletes the task at the given index

Format: `delete [INDEX]`

Example:
- `delete 1` Deletes the 1st task in the list

```
1 [][D] shower (by: 7.30am)
2 [][E] lecture (from: 9am) (to: 11am)
```

<br>

#### Marking and Unmarking a Task: `mark` `unmark`

Marks a task as done or not done based on whether its status box has a tick

Mark Format: `mark [INDEX]`

Unmark Format: `unmark [INDEX]`

Examples:
- `mark 2` Marks the 2nd task as done
- `unmark 2` Removes the mark from the 2nd task

```
1 [][D] shower (by: 7.30am)
2 [][E] lecture (from: 9am) (to: 11am)
```

<br>

#### Finding Tasks: `find`

Searches for a specified keyword in the task list and displays 
a new list with tasks containing that keyword

Find Format: 'find shower'

```
1 [][D] shower (by: 7.30am)
```
<br>

#### Exit from Eln: `bye`

Say bye to exit the program
