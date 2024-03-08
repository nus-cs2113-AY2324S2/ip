# Zuke Chatbot User Guide

## Introduction

Discover Zuke, your task-management chatbot, thoughtfully crafted for those who appreciate the art of typing! 
Zuke brings the simplicity of managing tasks to a whole new level with its intuitive command line interface (CLI).
Welcome to a world where managing tasks becomes a breeze with the power of Zuke!

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
    - [Ask for help](#ask-for-help)
    - [Exit the program](#exit-the-program)

## Quick Start

1. Ensure you have Java 11 installed on your computer.
2. Download the latest `zuke.jar` from [here](https://github.com/ZMinghuiZ/ip/releases/download/A-Release/zuke.jar).
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
    - `help`: outputs a list of commands and their usage.
    - `list`: Lists all tasks in the task list.
    - `bye`: Exits the chatbot.
7. Refer to the [Commands](#commands-summary) below for quick reference of each command.
8. Refer to the [Usage](#usage) below for details of each command.

## Features Summary

### Feature - Help
- Outputs a list of commands and their usage. **Important for first-time users**.

### Feature - Add tasks
- Records your tasks into a task list.
- There are 3 types of tasks: **Todo**, **Deadline**, and **Event**.
    - **Todo**: Tasks that need to be done with no constraints.
    - **Deadline**: Tasks that need to be done by a specific time.
    - **Event**: Tasks that need to be done within a specific time frame.

### Feature - Mark or Unmark tasks
- Marks finished tasks as done, user can also unmark tasks as not done.

### Feature - Delete tasks
- Deletes a task from the task list.

### Feature - List tasks
- Lists all tasks in the task list with their respective type, finished status, and description.

### Feature - Find tasks
- **Using keyword**: Finds tasks in the task list that contains the keyword in the description.
- **Using time**: Finds tasks occurring on a specific time (*only for deadline or event*).

### Feature - Save task list into local storage
- Automatically saves the task list into local storage when after any command that changes the data
and when the chatbot is exited.
- The task list will be loaded from local storage when the chatbot is started.

> [!NOTE]
> - There is no need to save the data manually.
> - The task list is saved in the data folder which is the directory as the `zuke.jar` file.
> - The task list is saved in the file `zuke.txt`.
> - If the file or folder does not exist, it will be created.
> - If the file or folder is deleted, the task list will be lost.
> - Modifying the file with incorrect format may cause the chatbot to load error data.


## Commands Summary

| Description                                             | Command                                            |
|---------------------------------------------------------|----------------------------------------------------|
| [Help](#feature---help)                                 | `help`                                             |
| [Add a todo task](#add-a-task)                          | `todo <task description>`                          |
| [Add a deadline task](#add-a-task)                      | `deadline <task description> /by <time>`           |
| [Add an event task](#add-a-task)                        | `event <task description> /from <time> /to <time>` |
| [Mark a task](#mark-or-unmark-a-task)                   | `mark <task number>`                               |
| [Unmark a task](#mark-or-unmark-a-task)                 | `unmark <task number>`                             |
| [List all tasks](#list-all-tasks)                       | `list`                                             |
| [Delete a task](#delete-a-task)                         | `delete <task number>`                             |
| [Find tasks with the word](#find-a-task)                | `find /w <keyword>`                                |
| [Find tasks occurring on a specific time](#find-a-task) | `find /t <time>`                                   |
| [Exit](#exit-the-program)                               | `bye`                                              |

> [!NOTE]
> - Words in `<angle brackets>` are the parameters that need to be supplied in by the user.
> - When supplying the parameters in the command, do not include the `<angle brackets>`.
> - The commands are **NOT case-sensitive**, meaning `todo`, `Todo`, and `TODO` are all valid commands.
> - Inputs associated with `time` need to be in the format of any one the three listed:<br>
>  - `dd/MM/yyyy` (**Date only**)
>  - `HHmm` (**Time only**)
>  - `dd/MM/yyyy HHmm` (**Time & date together**)

## Usage

### Add a task

#### Todo - `todo <task description>`
- Example of usage: `todo one hour running`
    - Expected outcome:
      ```plaintext
      Got it. I've added this task:
      [T][ ] one hour running
      Now you have 1 task in the list.
      ```

#### Deadline - `deadline <task description> /by <time>`
- Example of usage: `deadline submit report /by 20/09/2021 2359`
    - Expected outcome:
      ```plaintext
      Got it. I've added this task:
      [D][ ] submit report (by: Sep 20 2021 23:59 pm)
      Now you have 2 tasks in the list.
      ```

#### Event - `event <task description> /from <time> /to <time>`
[!NOTE]
> * The parameter for event can be in any order. <br>E.g. `/from <time> /to <time>` or `/to <time> /from <time>`.

- Example of usage: `event cs2113 iP /from 20/01/2024 /to 08/03/2024`
    - Expected outcome:
      ```plaintext
      Got it. I've added this task:
      [E][ ] cs2113 iP (from: Jan 20 2024 0921 am to: Mar 08 2024 1159 pm)
      Now you have 3 tasks in the list.
      ```
- Example of usage: `event cs2113 tp meeting /from 1900 /to 2100`
  - Expected outcome:
    ```plaintext
    Got it. I've added this task:
    [E][ ] cs2113 tp meeting (from: Mar 08 2024 0700 pm to: Mar 08 2024 0900 pm)
    Now you have 4 tasks in the list.
    ```

### Mark or Unmark a task

#### Mark - `mark <task number>`
- Example of usage: `mark 1`
    - Expected outcome:
      ```plaintext
      Nice! I've marked this task as done:
      [T][X] one hour running
      ```
- Example of usage: `mark 2`
    - Expected outcome:
      ```plaintext
      Nice! I've marked this task as done:
      [D][X] submit report (by: Sep 20 2021 23:59)
      ```

#### Unmark - `unmark <task number>`
- Example of usage: `unmark 1`
    - Expected outcome:
      ```plaintext
      Nice! I've unmarked this task as not done:
      [T][ ] one hour running
      ```

### List all tasks

#### List - `list`
- Example of usage: `list`
  - Expected outcome:
  
    ```plaintext
      Here are the tasks in your list:
      1. [T][X] one hour running
      2. [D][X] submit report (by: Sep 20 2021 23:59)
      3. [E][ ] cs2113 iP (from: Jan 20 2024 0921 am to: Mar 08 2024 1159 pm)
      4. [E][ ] cs2113 tp meeting (from: Mar 08 2024 0700 pm to: Mar 08 2024 0900 pm)
    ```

### Delete a task

#### Delete - `delete <task number>`
- Example of usage: `delete 1`
    - Expected outcome:
      ```plaintext
      Noted. I've removed this task:
      [T][ ] one hour running
      Now you have 3 tasks in the list.
      ```

### Find a task

#### Using keyword - `find /d <keyword>`
- Example of usage: `find report`
    - Expected outcome:
  
      ```plaintext
      Here are the matching tasks in your list:
      1. [D][X] submit report (by: 20 Sep 2021 23:59)
      ```

#### Using time - `find /t <time>`
- Example of usage: `find /t 20/01/2024`
    - Expected outcome:
  
      ```plaintext
      Here are the matching tasks in your list:
      1. [E][ ] cs2113 iP (from: 20 Jan 2024 to: 08 Mar 2024)
      ```
- Example of usage: `find /t 2359`
    - Expected outcome:
  
      ```plaintext
      Here are the matching tasks in your list:
      1. [D][X] submit report (by: 20 Sep 2021 23:59)
      ```
      
### Ask for help
#### Help - `help`
- Example of usage: `help`
    - Expected outcome:
      ```plaintext
      =============================ZUKE   MANUAL==========================
      ADD TODO TASK:                                      todo <task name>
      ADD DEADLINE TASK:                   deadline <task name> /by <date>
      ADD EVENT TASK:            event <task name> /from <time> /to <time>
      LIST ALL TASKS:                                                 list
      MARK/UNMARK A TASKS:                       mark/unmark <task number>
      DELETE A TASK:                                  delete <task number>
      FIND WITH WORD:                                    find /w <keyword>
      FIND WITH TIME:                                       find /t <date>
      EXIT:                                                            bye
      ====================================================================
      ```

### Exit the program

#### Bye - `bye`
- Example of usage: `bye`
    - Expected outcome:
      ```plaintext
      Bye. Hope to see you again soon!
      ```

## FAQ

#### Q  -  How do I transfer my data to another computer?
- Download the `zuke.jar` file in the new computer and copy the `data` folder on your old machine 
to the same folder of the application in the new device. If there is an existing `data` folder, replace it with the copied `data` folder.