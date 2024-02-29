# CheeseBot User Guide

CheeseBot is an automated task tracking bot, optimised for use in a command-line interface environment.
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a task:](#adding-a-task)
    - [To-do task with no deadline: `todo`](#adding-a-to-do-task-todo)
    - [To-do task with a deadline: `deadline`](#adding-a-to-do-task-with-deadline-deadline)
    - [Event with start and end time: `event`](#adding-an-event-event)
  - [Listing tasks: `list`](#listing-tasks-list)
  - [Deleting tasks: `delete`](#deleting-tasks-delete)
  - [Searching tasks by keyword: `find`](#searching-tasks-by-keyword-find)
  - [Marking a task as completed: `mark`](#marking-a-task-as-completed-mark)
  - [Marking a task as uncompleted: `unmark`](#marking-a-task-as-uncompleted-unmark)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
  
## Quick Start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest CheeseBot.jar from [here]()
3. Copy the file to the folder you want to use as the home folder for CheeseBot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the following command:
`java -jar CheeseBot.jar`. A command-line interface should appear in a few seconds.
5. Type a command into the command-line interface and press Enter to execute the command. For example, try typing `help`
to bring up the list of commands available to you. You can also obtain more information on the commands
[here](#features).


## Features

> <img class="emoji" title=":information_source:" alt=":information_source:" src="https://github.githubassets.com/images/icons/emoji/unicode/2139.png" height="20" width="20">
> <strong>Notes on command formatting </strong>  
>
> - Words in `UPPER_CASE` are parameters that need to be supplied by the user.  
> E.g. in `todo TASK_NAME`, `TASK_NAME` is a parameter which should be used like this: `todo reading`.  
> 
> 
> - Parameters ordering should be followed strictly.  
> E.g. in command `event TASK_NAME /from START_TIME /to END_TIME`, users are expected to use the command in the given
> way, and not in like this: `event TASK_NAME /to END_TIME /from START_TIME`.  
> 
> 
> - Certain commands (like `help`, `list` and `bye`) are singular and should not have extra parameters supplied to it.
> Extra parameters will cause the command entered to be rejected.  
> E.g. `help` is the correct format and `help 123` will not be accepted.  

### Viewing help: `help`
Displays a list of commands available to you and how to use them.

Format: `help`

### Adding a task

There are 3 types of tasks you may add:
1. [A to-do task no deadlines](#adding-a-to-do-task-todo)
2. [A to-do task with a deadline](#adding-a-to-do-task-with-deadline-deadline)
3. [An event with a start and end time](#adding-an-event-event)

#### Adding a to-do task: `todo`
Adds a to-do task into your list of tasks.

Format: `todo TASK_NAME`  
Example usage: `todo Revise lecture 1 content`  

> <img class="emoji" title=":information_source:" alt=":information_source:" src="https://github.githubassets.com/images/icons/emoji/unicode/2139.png" height="20" width="20">
> <strong>Note</strong>
> 
> If you are looking to add a task with a specific deadline to adhere to, 
> try [`deadline`](#adding-a-to-do-task-with-deadline-deadline)  

#### Adding a to-do task with deadline: `deadline`
Adds a to-do task with deadline into your list of tasks.

Format: `deadline TASKNAME /by DEADLINE`  
Example usage: `deadline assignment 1 /by 1/2/2024 2359`  

> <img class="emoji" title=":information_source:" alt=":information_source:" src="https://github.githubassets.com/images/icons/emoji/unicode/2139.png" height="20" width="20">
> <strong>Note </strong>
> 
> For `DEADLINE` parameter, ensure that it is fully numeric and in the form `dd/MM/yyyy HHmm`, where `dd` is the date,
> `MM` is the month, `yyyy` is the year, `HHmm` is the time in 24-hour format.  

#### Adding an event: `event`
Adds an event (with a start and end time) into your list of tasks.

Format: `event EVENT_NAME /from START_TIME /to END_TIME`  
Example usage: `event Week 7 CS2113T Lecture /from 8/3/2024 1600 /to 8/3/2024 1800`  

> <img class="emoji" title=":information_source:" alt=":information_source:" src="https://github.githubassets.com/images/icons/emoji/unicode/2139.png" height="20" width="20">
> <strong> Note </strong>
>
> For `START_TIME` and `END_TIME` parameters, ensure that it is fully numeric and in the form `dd/MM/yyyy HHmm`, where 
>`dd` is the date, `MM` is the month, `yyyy` is the year, `HHmm` is the time in 24-hour format.   

> <img class="emoji" title=":information_source:" alt=":information_source:" src="https://github.githubassets.com/images/icons/emoji/unicode/2139.png" height="20" width="20">
> <strong> Note </strong>
> 
> Ensure that `START_TIME` is before `END_TIME`, or your command will get rejected.

### Listing tasks: `list`
Lists all the tasks that you have entered so far.

Format: `list`

### Deleting tasks: `delete`
Deletes a particular task from the list of tasks using its task number. You may obtain a task's task number by using 
[`list`](#listing-tasks-list).  

> <img class="emoji" title=":bulb:" alt=":bulb:" src="https://github.githubassets.com/images/icons/emoji/unicode/1f4a1.png" height="20" width="20">
> <strong> Tip</strong>
>
> A task's task number is not fixed and may be reassigned after `delete`.
> 
> Use [`list`](#listing-tasks-list) after every `delete` to ensure you are deleting the correct task.  

Format: `delete TASK_NUMBER`.  
Example usage: `delete 3`

### Searching tasks by keyword: `find`
Displays all tasks whose names contains the keyword.

Format: `find KEYWORD`  
Example usage: `find assignment`

### Marking a task as completed: `mark`
Marks a particular task as completed. You may obtain a task's task number by using [`list`](#listing-tasks-list).

Format: `mark TASK_NUMBER`  
Example usage: `mark 2`

### Marking a task as uncompleted: `unmark`
Marks a particular task as uncompleted. You may obtain a task's task number by using [`list`](#listing-tasks-list).

Format: `unmark TASK_NUMBER`  
Example usage: `unmark 2`

### Exiting the program: `bye`
Exits the program safely. This will also automatically save your to-do list.

Format: `bye`

### Saving the data
Upon entering `bye`, all data is saved automatically. This includes the tasks' statuses such as their task names, task
types, and their completion status. 

The next time you boot up CheeseBot, the saved data will also automatically be loaded.

### Editing the data file
Saved data are saved automatically as a TXT file `[JAR file location]/data/CheeseBot.txt`.
Advanced users are welcome to update data directly by editing the data file.  

> <img class="emoji" title=":exclamation:" alt=":exclamation:" src="https://github.githubassets.com/images/icons/emoji/unicode/2757.png" height="20" width="20"> 
> <strong> Caution: </strong>
> If the changes you have made to the save file are deemed invalid by the built-in parser, you may lose tasks.
> Hence, it is recommended that your store a backup of the file before editing it.  
>
> Please only use this feature if you are sure with what you are doing. 
> We will not be liable for any data lost.