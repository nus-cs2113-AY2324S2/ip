# Lotes User Guide

Lotes is a command line interface (CLI) application where you can use to **manage your tasks** with just commands through the interface.

## Requirements
1. Ensure that you have `Java 11` installed in your Windows computer.
2. Download the latest `lotes.jar` from [here](https://github.com/e0958902/ip/releases).
3. Copy `lotes.jar` to a directory you want Lotes to run.
4. `Press and hold down shift` then `right-click` the directory and click `Open in Terminal`.
5. Type `java -jar lotes.jar` and press `Enter`.<br>
   If your program is running correctly, you should see an output like this:
   ```
   Hello! I'm  #        ####  ##### ######  ####
               #       #    #   #   #      #
               #       #    #   #   #####   ####
               #       #    #   #   #           #
               #######  ####    #   ######  ####
   What can I do for you?
   ```

## Commands Usage Overview
Type the following commands and press `Enter` to run it:
e.g `list` and press `Enter` will list all the tasks.<br>

* [`todo DESCRIPTION`](#1-add-to-do-to-task-list-todo) : Adds a new to do task with `DESCRIPTION` to the task list.
* [`deadline DESCRIPTION /by BY_DATE_TIME`](#2-add-deadline-to-task-list-deadline) : Adds a new deadline task with `DESCRIPTION` and `BY_DATE_TIME` to the task list.
* [`event DESCRIPTION /from FROM_DATE_TIME /to BY_DATE_TIME`](#3-add-event-to-task-list-event) : Adds a new event task with `DESCRIPTION`, `FROM_DATE_TIME`, and `BY_DATE_TIME` to the task list.
* [`list`](#4-list-all-tasks-list) : Lists all your automatically saved tasks.
* [`mark NUMBER`](#5-mark-a-task-in-task-list-mark) :  Marks the selected task shown in the current task list.
* [`unmark NUMBER`](#6-unmark-a-task-in-task-list-unmark) : Unmarks the selected task shown in the current task list.
* [`find WORD`](#7-find-a-task-in-task-list-find) : Displays your tasks containing `WORD` in your task list.
* [`delete NUMBER`](#8-delete-a-task-in-task-list-delete) : Deletes the selected task shown in the current task list.
* [`bye` or `exit`](#9-exit-program-bye-or-exit) : Exits your program.

**Things to take note about program:**
* Words in UPPER_CASE are the parameters to be supplied by the user.<br>
  e.g For `todo DESCRIPTION`, `DESCRIPTION` can be used as `todo Merida: Answer GDP2021 questions`.
* Parameters must be in order such that `/by` comes before `/to`.<br>
  e.g `event GDP2021 OJT Baiting Challenge /from Friday 1pm /to 6pm`.
* Additional parameters for commands that are not requires will be ignored.<br>
  e.g `list 123`, `bye abc`,`exit xyz`.
* Lotes automatically saves your tasks to a local storage `/data/storage.txt` and reads from it on the next run.

## Features and Usage

### 1. Add To do to task list: `todo`
Adds a new task to do to your task list.<br>
* Format: `todo DESCRIPTION`.<br>
  Example of usage: `todo Merida: Answer GDP2021 questions`.<br>
* Expected outcome:
   ```
     Got it. I've added this task: 
       [T][ ] Merida: Answer GDP2021 questions
     Now you have 5 tasks in the list
   ```

### 2. Add deadline to task list: `deadline`
Adds a new deadline task with `/by` to your task list..<br>
* Format: `deadline DESCRIPTION /by BY_DATE_TIME`.<br>
  Example of usage: `deadline Weekly GDP2021 Routine Dog Bath /by Monday 11:59pm`.<br>
* Expected outcome:
   ```
     Got it. I've added this task: 
       [D][ ] Weekly GDP2021 Routine Dog Bath (by: Monday 11:59pm)
     Now you have 6 tasks in the list
   ```

[Back to Commands Usage Overview](#commands-usage-overview)

### 3. Add event to task list: `event`
Adds a new event task with `/from` and `/to` to your task list..<br>
* Format: `event DESCRIPTION /from FROM_DATE_TIME /to BY_DATE_TIME`.<br>
  Example of usage: `event GDP2021 OJT Baiting Challenge /from Friday 1pm /to 6pm`.<br>
* Expected outcome:
   ```
     Got it. I've added this task: 
       [E][ ] GDP2021 OJT Baiting Challenge (from: Friday 1pm to: 6pm)
     Now you have 7 tasks in the list
   ```

### 4. List all tasks: `list`
Lists all tasks your task list.
* Format: `list`.
  Example of usage: `list`.
* Expected outcome:
   ```
     Got it. I've added this task: 
       [E][ ] GDP2021 OJT Baiting Challenge (from: Friday 1pm to: 6pm)
     Now you have 7 tasks in the list
   ```

[Back to Commands Usage Overview](#commands-usage-overview)

### 5. Mark a task in task list: `mark`
Marks the selected task shown in the current task list.<br>
* Format: `mark NUMBER`.<br>
  Example of usage: `mark 1`.<br>
* Expected outcome:
   ```
    ____________________________________________________
     Nice! I've marked this task as done:
     [D][X] Weekly GDP2021 Routine Dog Bath (by: Monday 11:59pm)
    ____________________________________________________
   ```

### 6. Unmark a task in task list: `unmark`
Unmarks the selected task shown in the current task list.<br>
* Format: `unmark NUMBER`.<br>
  Example of usage: `unmark 2`.<br>
* Expected outcome:
   ```
    ____________________________________________________
     OK, I've marked this task as not done yet:
     [D][ ] Weekly GDP2021 Routine Dog Bath (by: Monday 11:59pm)
    ____________________________________________________
   ```

[Back to Commands Usage Overview](#commands-usage-overview)

### 7. Find a task in task list: `find`
Displays your tasks containing `WORD` in your task list.<br>
* Format: `find WORD`.<br>
  Example of usage: `find GDP2021`.<br>
* Expected outcome:
   ```
    ____________________________________________________
     OK, I've marked this task as not done yet:
     [D][ ] Weekly GDP2021 Routine Dog Bath (by: Monday 11:59pm)
    ____________________________________________________
   ```

### 8. Delete a task in task list: `delete`
Deletes the selected task shown in the current task list.<br>
* Format: `delete NUMBER`.<br>
  Example of usage: `delete 1`.<br>
* Expected outcome:
   ```
    ____________________________________________________
     Noted. I've removed this task:
     [T][ ] Merida: Answer GDP2021 questions
    ____________________________________________________
   ```

[Back to Commands Usage Overview](#commands-usage-overview)

### 9. Exit program: `bye` or `exit`
Exits your program.<br>
* Format: `bye` or `exit`.<br>
  Example of usage: `bye` or `exit`.<br>
* Expected outcome:
   ```
    ____________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________
   ```

[Back to top](#lotes-user-guide)