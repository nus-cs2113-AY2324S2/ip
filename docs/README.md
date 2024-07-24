# Chris ChatBot User Guide

## Introduction
Chris is a ChatBot that acts as a task handler in CLI(Command Line Interface).

## Table of contents
- [Quick Start](#quick-start)
- [Features Summary](#features-summary)
- [All possible commands](#commands-summary)
- [Usage](#usage)
    - [Add a task](#add-a-task)
    - [Mark or Unmark a task](#mark---mark-task-number)
    - [List all tasks](#list-all-tasks)
    - [Delete a task](#delete-a-task)
    - [Find a task](#find-a-task)
    - [Exit the program](#exit-the-program)
- [Extra Questions](#extra-questions)

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `chris.jar` from [here](https://github.com/CXIA17/ip/releases/download/A-Release/chris.jar).
3. Copy the file to the folder you want to use as the home folder for Chris.
4. Open terminal and navigate to the folder where `chris.jar` is located.
5. Run the command `java -jar chris.jar` to start the ChatBot. The chatbot should start with the following output.
    ```
   Hello from
      ___           ___           ___                       ___
     /\  \         /\__\         /\  \          ___        /\  \
    /::\  \       /:/  /        /::\  \        /\  \      /::\  \
   /:/\:\  \     /:/__/        /:/\:\  \       \:\  \    /:/\ \  \
    /:/  \:\  \   /::\  \ ___   /::\~\:\  \      /::\__\  _\:\~\ \  \
    /:/__/ \:\__\ /:/\:\  /\__\ /:/\:\ \:\__\  __/:/\/__/ /\ \:\ \ \__\
    \:\  \  \/__/ \/__\:\/:/  / \/_|::\/:/  / /\/:/  /    \:\ \:\ \/__/
    \:\  \            \::/  /     |:|::/  /  \::/__/      \:\ \:\__\
    \:\  \           /:/  /      |:|\/__/    \:\__\       \:\/:/  /
    \:\__\         /:/  /       |:|  |       \/__/        \::/  /
    \/__/         \/__/         \|__|                     \/__/
    Your journey has started
    ____________________________________________________________
    Hello, I'm Chris
    What can I do for you?
    ____________________________________________________________
   ```
6. Type the command in the command box and press Enter to execute it. Some example commands you can try:
    - `list`: Lists all tasks in the task list.
    - `bye`: Exits the ChatBot.

## Features Summary

### Feature - Add tasks
- Records your tasks into a task list.
- There are 3 types of tasks: **Todo**, **Deadline**, and **Event**.
    - **Todo**: Tasks that need to be done with no constraints.
    - **Deadline**: Tasks that need to be completed by a specific time.
    - **Event**: Tasks that needs to start from a specific time to another time.

### Feature - Mark or Unmark tasks
- Marks finished tasks as done, user can also unmark tasks as not done.

### Feature - Delete tasks
- Delete a task from task list.

### Feature - List tasks
- Lists all tasks in the task list with their respective type, completion status, and task description.

### Feature - Find tasks
- **Using keyword**: Finds tasks in the task list that contains the keyword in the task description.

### Feature - Save task list into local storage
- Automatically saves the tasks inside the task list into local storage when the ChatBot is exited.
- The task list will be loaded from local storage when the ChatBot starts.

> [Special Notice]
> - There is no need to save the data manually.
> - The task list is saved in the data folder which is the directory as the `chris.jar` file.
> - The task list is saved in the file `chris.txt`.
> - If the file or folder does not exist, it will be created.
> - If the file or folder is deleted, the task list will be lost.
> - The ChatBot will not load the data that are in an incorrect format and this could lead to data loss.

## All possible commands
## Commands Summary

| Description                                   | Command                                         |
|-----------------------------------------------|-------------------------------------------------|
| [Add a todo](#add-a-task)                     | `todo <task description>`                       |
| [Add a deadline](#add-a-task)                 | `deadline <task description> /by <time>`        |
| [Add an event](#add-a-task)                   | `event <task description> /from <time> /to <time>` |
| [Delete a task](#delete-a-task)               | `delete <task number>`                          |
| [Find tasks with keyword](#find-a-task)       | `find <keyword>`                                |
| [Exit](#exit-the-program)                     | `bye`                                           |
| [Mark a task](#mark---mark-task-number)       | `mark <task number>`                            |
| [Unmark a task](#unmark---unmark-task-number) | `unmark <task number>`                          |
| [List all tasks](#list-all-tasks)             | `list`                                          |

> [!NOTE]
> - Words in `<angle brackets>` are user input parameters.
> - When supplying the parameters in the command, do not include the `<>`.
> - Inputs associated with `time` need to be in the format:<br>
>  - `dd/MM/yyyy HHmm` (**Time & date together**)

## Usage

### Add a task

#### Todo - `todo <task description>`
- Example: `todo eat`
    - Expected outcome:
      ```plaintext
      Got it. I've added this task:
      [T][ ] eat
      Now you have 1 tasks in the list.
      ```
#### Deadline - `deadline <task description> /by <time>`
- Example: `deadline submit ip /by 08/03/2024 2359`
    - Expected outcome:
      ```plaintext
      Got it. I've added this task:
      [D][ ] submit ip (by: 08 Mar 2024 23:59)
      Now you have 2 tasks in the list.
      ```
#### Event - `event <task description> /from <time> /to <time>`
- Example: `event cs2113 tutorial /from 20/01/2024 1100 /to 08/03/2024 1200`
    - Expected outcome:
      ```plaintext
      Got it. I've added this task:
      [E][ ] cs2113 ip (from: 20 Jan 2024 11:00 to: 08 Mar 2024 12:00)
      Now you have 3 tasks in the list.
      ```
    
### Indicate completion of a task

#### Mark - `mark <task number>`
- Example: `mark 1`
    - Expected outcome:
      ```plaintext
      Nice! I've marked this task as done:
      [T][X] eat
      ```

#### Unmark - `unmark <task number>`
- Example: `unmark 1`
    - Expected outcome:
      ```plaintext
      OK, I've marked this task as not done yet:
      [T][ ] eat
      ```
      
### List all tasks

#### List - `list`
- Example: `list`
    - Expected outcome:
  
      ```plaintext
      Here are the tasks in your list:
      1. [T][ ] eat
      2. [D][ ] submit ip (by: 08 Mar 2024 23:59)
      3. [E][ ] cs2113 ip (from: 20 Jan 2024 11:00 to: 08 Mar 2024 12:00)
      ```
      
#### Delete - `delete <task number>`
- Example: `delete 1`
    - Expected outcome:
  
      ```plaintext
      Noted. I've removed this task:
      1. [T][ ] eat
      Now you have 2 tasks in the list.
      ```
      
### Find a task

#### Using keyword - `find <keyword>`
- Example: `find ip`
    - Expected outcome:
  
      ```plaintext
      Here are the matching tasks in your list:
      1. [D][X] submit ip (by: 20 Sep 2021 23:59)
      2. [E][ ] cs2113 ip (from: 20 Jan 2024 11:00 to: 08 Mar 2024 12:00)
      ```
      
### Exit the program

#### Bye - `bye`
- Example: `bye`
    - Expected outcome:
      ```plaintext
      Bye. Hope to see you again soon!
      ```

## Extra Questions

#### Q  -  How do I transfer my data to another computer?
- Download the `chris.jar` file in the new computer and copy the `data` folder on your original directory
  to the same directory of the application in the new device. 

