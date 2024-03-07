# SPIKE User Guide

Welcome to **SPIKE!** - Your personal chatbot designed to help you manage tasks efficiently, including to-dos, deadlines, and events.

## Features

### Add Task
You can add three types of tasks:
1. To-dos
2. Deadlines
3. Events

Each task type helps you categorize tasks for better organization.

### List Tasks
Display all tasks, including to-dos, deadlines, and events, with their status (completed or pending) and additional information like timeframe and deadline timing.

### Mark Task as Done
Mark any task as completed based on its list number to track progress effectively.

### Mark Task as Incomplete
Restore any completed task to pending status based on its list number for easy task management.

### Delete Task
Remove tasks that are no longer needed or were added by mistake.

### Find Tasks
Search for tasks by keywords to quickly find specific tasks in your list. This feature helps you find tasks and know their deadlines, timeframes, and indices for marking or unmarking.

### Save Data
All tasks are automatically saved and loaded when you start the program again.

## Getting Started

### Prerequisites
Make sure you have Java 11 or later installed on your computer.

### Installation
1. Download the latest release of SPIKE from the releases page.
2. Extract the downloaded zip file to a folder on your computer.
3. Navigate to the folder where you extracted the zip file and open a terminal.
4. Run the following command to start the SPIKE application:

    ```bash
    java -jar SPIKE.jar
    ```

If the application starts successfully, it will display the following welcome message:

```plaintext
-----------------------------------------
     Hello, I'm SPIKE
     What can I do for you?
-----------------------------------------
```
## Usage
### `todo` - Add a To-do Task
Adds a simple task with no additional informatiom

**Example of usage:**

      todo Clean the Room
**Expected Outcome:**

      -----------------------------------------
         Got it. I've added this task:
         [T][ ] Clean the Room
         Now you have 1 tasks in the list.
      -----------------------------------------

### `deadline` - Add a Deadline Task
Adds a task to complete it by specific deadline

**Example of usage:**

      deadline Submit Assignment /by Mar 23
**Expected Outcome:**

      -----------------------------------------
         Got it. I've added this task:
         [D][ ] Submit Assignment (by: Mar 23)
         Now you have 2 tasks in the list.
      -----------------------------------------

### `event` - Add a Event Task
Adds a task with specific date and time.

**Example of usage:**

      event Project Meeting /from Sunday 5pm /to 7pm
**Expected Outcome:**

      -----------------------------------------
         Got it. I've added this task:
         [E][ ] Project Meeting (from: Sunday 5pm to: 7pm)
         Now you have 3 tasks in the list.
      -----------------------------------------

### `list` - Lists all Tasks
Display all of your tasks, including To-dos, Deadlines and Events along with their status (completed or pending) and their deadline or timeframe.

**Example of usage:**

      list
**Expected Outcome:**

      -----------------------------------------
         Here are the tasks in your list:
         1.[T][ ] Clean the Room
         2.[D][ ] Submit Assignment (by: Mar 23)
         3.[E][ ] Project Meeting (from: Sunday 5pm to: 7pm)
      -----------------------------------------

### `mark` - Mark Task as Completed
Marks a specific Task as completed based on the list number provided.

**Example of usage:**

      mark 2
**Expected Outcome:**

      -----------------------------------------
         Nice! I've marked this task as done:
         [D][X] Submit Assignment (by: Mar 23)
      -----------------------------------------

### `unmark` - Mark Completed Task as not done
Unmarks a specific Task based on the list number provided. It helps to revert the task status to not completed.

**Example of usage:**

      unmark 2
**Expected Outcome:**

      -----------------------------------------
         OK, I've marked this task as not done yet:
         [D][ ] Submit Assignment (by: Mar 23)
      -----------------------------------------

### `delete` - Remove a Task
Removes a specific task from the list based on the list number provided.

**Example of usage:**

      delete 1
**Expected Outcome:**

      -----------------------------------------
         Noted. I've removed this task:
         [T][ ] Clean the Room
         Now you have 2 tasks in the list.
      -----------------------------------------

### `find` - Find Tasks
Search for tasks using keywords to quickly find specific tasks in the filtered list.

**Example of usage:**

      find Project
**Expected Outcome:**

      -----------------------------------------
      Here are the matching tasks in your list:
      1.[T][ ] Project Meeting (from: Sunday 5pm to: 7pm) 
      -----------------------------------------

### `bye` - Exit the Program
Exits from the chatbot.

**Example of usage:**

      bye
**Expected Outcome:**

      -----------------------------------------
         Bye. Hope to see you again soon!
      -----------------------------------------
### User Commands:

| Name | Format | Command Example                                                      |
|--|--|----------------------------------------------------------------------|
| Todo | todo [name]  | todo Clean the Room                                                  |
| Deadline | deadline [name] /by [DD/MM/YYYY HH:MM] | deadline Submit Assignment (by: Mar 23)                       |
| Event| deadline [name] /from [DD/MM/YYYY HH:MM] /to [DD/MM/YYYY HH:MM] | event Project Meeting (from: Sunday 5pm to: 7pm) |
| List | list  | list                                                                 |
| Mark |mark [task index]  | mark 2                                                               |
| Unmark | unmark [task index]  | unmark 2                                                             |
| Delete | delete [task index] | delete 1                                                             |
| Find | find [keyword] | find muscle                                                          |
| Exit | bye  | bye                                                                  |



