# Kvothe (Chatbot) User Guide

Kvothe is a personal assistant chatbot that helps a person to keep track of various tasks.
It is a desktop app based on Java that works through commands.
The tasks supported are: todo, deadline and event.
Furthermore, the application supports persistent storage of the tasks.
                    
                    ██╗░░██╗██╗░░░██╗░█████╗░████████╗██╗░░██╗███████╗
                    ██║░██╔╝██║░░░██║██╔══██╗╚══██╔══╝██║░░██║██╔════╝
                    █████═╝░╚██╗░██╔╝██║░░██║░░░██║░░░███████║█████╗░░
                    ██╔═██╗░░╚████╔╝░██║░░██║░░░██║░░░██╔══██║██╔══╝░░
                    ██║░╚██╗░░╚██╔╝░░╚█████╔╝░░░██║░░░██║░░██║███████╗
                    ╚═╝░░╚═╝░░░╚═╝░░░░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    - [Add task](#add-task)
        - [Add Todo Task: `todo`](#add-todo-task-todo)
        - [Add Deadline Task: `deadline`](#add-deadline-task-deadline)
        - [Add Event Task: `event`](#add-event-task-event)
    - [List tasks: `list`](#list-tasks-list)
    - [Mark task as done: `done`](#mark-task-as-done-done)
    - [Unmark task as done: `unmark`](#unmark-task-as-done-unmark)
    - [Delete task: `delete`](#delete-task-delete)
    - [Find task: `find`](#find-task-find)
    - [Exit the program: `bye`](#exit-the-program-bye)

  

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `kvothe.jar` from [here](
3. Copy the file to the folder you want to use as the home folder for your Kvothe.
4. Execute the jar using the following command: `java -jar kvothe.jar`

## Features 

All the actions are performed through commands. In case a command is not recognized, the application will notify you.

````
cook pasta
		____________________________________________________________
		Invalid command
		____________________________________________________________
````

### Add task

For adding a new task to the list, you only need to specify the type of task and its arguments.

In case any argument is missing or the format is incorrect, the application will notify you.

````
event part /from 5pm /until 6
		____________________________________________________________
		Invalid argument /until. Expected /to
		____________________________________________________________
event /from 5pm /to /6pm
		____________________________________________________________
````

Note: Dates can be specified in any format. Also, no checking is done on the validity of the date.

The supported tasks are:
- `todo`
- `deadline`
- `event`


#### Add Todo Task: `todo`

Adds a new todo task to the list.

Format: `todo DESCRIPTION`

Example of usage:

````
todo study for the exam
		____________________________________________________________
		added: [T][ ] study for the exam
		now you have 1 tasks in the list
		____________________________________________________________

````

#### Add Deadline Task: `deadline`

Adds a new deadline task to the list.

Format: `deadline DESCRIPTION /by DATE`

Example of usage:

````
deadline return book /by Sep 30 2021
        ____________________________________________________________
        added: [D][ ] return book (by: Sep 30 2021)
        now you have 2 tasks in the list
        ____________________________________________________________
````

#### Add Event Task: `event`

Adds a new event task to the list.

Format: `event DESCRIPTION /from DATE /to DATE`

Example of usage:

````
event project meeting /from Sep 30 2021 /to Oct 1 2021
        ____________________________________________________________
        added: [E][ ] project meeting (from: Sep 30 2021 to: Oct 1 2021)
        now you have 3 tasks in the list
        ____________________________________________________________
````

### List tasks: `list`

Lists all the tasks in the list.

Format: `list`

Example of usage:

````
list
        ____________________________________________________________
        Here are the tasks in your list:
        1. [T][ ] study for the exam
        2. [D][ ] return book (by: Sep 30 2021)
        3. [E][ ] project meeting (from: Sep 30 2021 to: Oct 1 2021)
        ____________________________________________________________
````

### Mark task as done: `done`

Marks a task as done.

Format: `done INDEX`

Example of usage:

````
done 1
        ____________________________________________________________
        Nice! I've marked this task as done:
        [T][X] study for the exam
        ____________________________________________________________
````
### Unmark task as done: `unmark`

Unmark a task as done.

Format: `unmark INDEX`

Example of usage:

````
unmark 1
        ____________________________________________________________
        Nice! I've unmarked this task:
        [T][ ] study for the exam
        ____________________________________________________________
````

### Delete task: `delete`

Deletes a task from the list.

Format: `delete INDEX`

Example of usage:

````
delete 1
        ____________________________________________________________
        Noted. I've removed this task:
        [T][ ] study for the exam
        now you have 2 tasks in the list
        ____________________________________________________________
````

### Find task: `find`

Finds tasks that contain the given keyword.

Format: `find KEYWORD`

Example of usage:

````
find book
        ____________________________________________________________
        Here are the matching tasks in your list:
        1. [D][ ] return book (by: Sep 30 2021)
        ____________________________________________________________
````

### Exit the program: `bye`

Exits the program.

Format: `bye`

Example of usage:

````
bye
        ____________________________________________________________
        Bye. Hope to see you again soon!
        ____________________________________________________________
````
