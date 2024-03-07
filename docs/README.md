# Zuke Chatbot User Guide


## Introduction


Zuke is a chatbot that helps you to manage your tasks in a command line interface (CLI). It is optimised for users who prefer to type.


## Table of Contents


- [Quick Start](#quick-start)
- [Features](#features-summary)
- [Commands](#commands-summary)
- [Usage](#usage)
  - [Add a task](#add-a-task)
  - [Mark or Unmark a task](#mark-or-unmark-a-task)
  - [List all tasks](#list-all-tasks)
  - [Delete a task](#delete-a-task)
  - [Find a task](#find-a-task)
  - [Exit the program](#exit-the-program)


## Quick Start


1. Ensure you have Java 11 installed in your Computer.
2. Download the latest `zuke.jar` from [here](
3. Copy the file to the folder you want to use as the home folder for Zuke.
4. Open a terminal and navigate to the folder where `zuke.jar` is located.
5. Run the command `java -jar zuke.jar` to start the chatbot. The chatbot should start and greet you with a welcome message.
```
Hello from
███████╗██╗   ██╗██╗  ██╗███████╗
╚══███╔╝██║   ██║██║ ██╔╝██╔════╝
  ███╔╝ ██║   ██║█████╔╝ █████╗  
 ███╔╝  ██║   ██║██╔═██╗ ██╔══╝  
███████╗╚██████╔╝██║  ██╗███████╗
╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝

```
6. Type the command in the command box and press Enter to execute it. Some example commands you can try:
   - `help` : outputs a list of commands and their usage.
   - `list` : Lists all tasks in the task list.
   - `bye` : Exits the chatbot.
7. Refer to the [Commands](#commands-summary) below for quick reference of each command.
8. Refer to the [Usage](#usage) below for details of each command.


## Features Summary


### Feature - Add tasks
> Records your tasks into a task list.<br>
  There are 3 types of tasks:
  **Todo**, **Deadline**, and **Event**.
> * `Todo` <br> Tasks needs to be done with no constraints
> * `Deadline` <br> Tasks needs to be done by a specific time
> * `Event` <br> Tasks needs to be done within a specific time frame

### Feature - Mark tasks
> Marks finished tasks as done, user can also unmark tasks as not done.

### Feature - Delete tasks
> Deletes a task from the task list.

### Feature - List tasks
> Lists all tasks in the task list with their respective type, finished status, and description.

### Feature - Find tasks
#### - Using keyword
> Finds tasks in the task list that contains the keyword in the description.
#### - Using time
> Finds tasks occurring on a specific time (**only for deadline or event**).

### Feature - Save task list into local storage
> Automatically saves the task list into local storage when the chatbot is exited.<br>
> The task list will be loaded from local storage when the chatbot is started.<br>

> [!NOTE]
> * The task list is saved in the data folder which is the directory as the `zuke.jar` file.
> * The task list is saved in the file `zuke.txt`.
> * If the file or folder does not exist, it will be created.
> * If the file or folder is deleted, the task list will be lost.
> * Modifying the file with incorrect format may cause the chatbot to load error data.


## Commands Summary


| Command                                            | Description                                     |
|----------------------------------------------------|-------------------------------------------------|
| `todo <task description>`                          | Adds a todo task                                |
| `deadline <task description> /by <time>`           | Adds a deadline task                            |
| `event <task description> /from <time> /to <time>` | Adds an event task                              |
| `mark <task number>`                               | Marks a task as done                            |
| `unmark <task number>`                             | Unmarks a task as not done                      |
| `list`                                             | Lists all tasks in the task list                |
| `delete <task number>`                             | Deletes a task from the task list               |
| `find /d <keyword>`                                | Finds tasks with the keyword in the description |
| `find /t <time>`                                   | Finds tasks occurring on a specific time        |
| `bye`                                              | Exits the chatbot                               |


## Usage


[!NOTE]
> * Inputs associate with `time` need to be in the format <br>
    `dd/MM/yyyy` *Date only* <br>
    `HHmm` *Time only* <br>
    `dd/MM/yyyy HHmm` *Time & date together*<br>

### **Add a task**

#### Todo - `todo <task description>`<br>
* Example of usage: `todo one hour runing`<br>
> Expected outcome: <br>
> ```
> Got it. I've added this task:
> [T][ ] one hour running
> Now you have 1 task in the list.
> ```

#### Deadline - `deadline <task description> /by <time>`<br>
[!NOTE]
> * The time can be in the format `dd/MM/yyyy` **Date only** <br> `HHmm` **Time only** <br> `dd/MM/yyyy HHmm`<br>

Example of usage: `deadline submit report /by 20/09/2021 2359`<br>
> Expected outcome: <br>
> ```
> Got it. I've added this task:
> [D][ ] submit report (by: 20 Sep 2021 23:59)
> Now you have 2 tasks in the list.
> ```

#### Event - `event <task description> /at <time>`<br>

Example of usage: `event cs2113 iP /from 20/01/2024 /to 08/03/2024`<br>
> Expected outcome: <br>
> ```
> Got it. I've added this task:
> [E][ ] cs2113 iP (from: 20 Jan 2024 to: 08 Mar 2024)
> Now you have 3 tasks in the list.
> ```

### **Mark or Unmark a task**

#### Mark - `mark <task number>`<br>
Example of usage: `mark 1`<br>
> Expected outcome: <br>
> ```
> Nice! I've marked this task as done:
> [T][X] one hour running
> ```
Example of usage: `mark 2`<br>
> Expected outcome: <br>
> ```
> Nice! I've marked this task as done:
> [D][X] submit report (by: 20 Sep 2021 23:59)
> ```

#### Unmark - `unmark <task number>`<br>
Example of usage: `unmark 1`<br>
> Expected outcome: <br>
> ```
> Nice! I've unmarked this task as not done:
> [T][ ] one hour running
> ```


### List all tasks

#### List - `list`<br>
Example of usage: `list`<br>
> Expected outcome: <br>
> ```
> Here are the tasks in your list:
> 1. [T][ ] one hour running
> 2. [D][X] submit report (by: 20 Sep 2021 23:59)
> 3. [E][ ] cs2113 iP (from: 20 Jan 2024 to: 08 Mar 2024)
> ```

### Delete a task

#### Delete - `delete <task number>`<br>
Example of usage: `delete 1`<br>
> Expected outcome: <br>
> ```
> Noted. I've removed this task:
> [T][ ] one hour running
> Now you have 2 tasks in the list.
> ```

### Find a task

#### Using keyword - `find /d <keyword>`<br>
Example of usage: `find report`<br>
> Expected outcome: <br>
> ```
> Here are the matching tasks in your list:
> 1. [D][X] submit report (by: 20 Sep 2021 23:59)
> ```
#### Using time - `find /t <time>`<br>
Example of usage: `find /t 20/01/2024`<br>
> Expected outcome: <br>
> ```
> Here are the matching tasks in your list:
> 1. [E][ ] cs2113 iP (from: 20 Jan 2024 to: 08 Mar 2024)
> ```
Example of usage: `find /t 2359`<br>
> Expected outcome: <br>
> ```
> Here are the matching tasks in your list:
> 1. [D][X] submit report (by: 20 Sep 2021 23:59)
> ```

### Exit the program

#### Bye - `bye`<br>
Example of usage: `bye`<br>
> Expected outcome: <br>
> ```
> Bye. Hope to see you again soon!
> ```