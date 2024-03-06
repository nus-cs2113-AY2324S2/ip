# Davvy User Guide

*Davvy* is the individual project component for CS2113, AY23/24 Semester 2.

```
 Hello! I'm Davvy
 What can I do for you?
```
The chatbot aims to be your personal task tracker for your daily needs!

## Table of Contents
- [Davvy User Guide](#Davvy-User-Guide)
    - [Launching Davvy](#launching-davvy)
    - [Features](#features)
      - [Task Creation](#task-creation)
      - [Task Management](#task-management)
      - [Persistent State](#persistent-state)
    - [Commands](#commands)
      - [`todo`](#todo---create-and-add-a-todo-task)
      - [`deadline`](#deadline---create-and-add-a-deadline-task)
      - [`event`](#event---create-and-add-a-event-task)
      - [`list`](#list---list-out-all-saved-tasks)
      - [`mark`](#mark---tags-a-task-as-completed)
      - [`unmark`](#unmark---un-tag-a-task-if-incomplete)
      - [`delete`](#delete---deletes-a-specified-task)
      - [`find`](#find---searches-for-a-task)
      - [`bye`](#bye---end-the-chatbot)

## Launching Davvy

To start the chatbot, go to the src folder containing the JAR file. Then, on a terminal of your choice, run:

```
-$ java -jar ip.jar
```
*This program is best executed with the Java-8 Runtime Environment*

## Features
*Davvy* comes jam-packed with features that allow you to use CLI to manage your upcoming tasks 

### Task Creation
Manage your upcoming tasks by adding and creating tasks:
1. `todo` for your everyday general tasks
2. `deadline` for tasks with a date goal in mind
3. `event` for time critical tasks and appointments

### Task Management
Track, edit and mark all of your tasks with ease!
- *Davvy* allows you to `mark` and `unmark` tasks, so you can focus on what is not done!
- *Davvy* allows you to `list` out all the tasks you have added so far.
- Made a wrong entry? Just `delete` and be on your way!

### Persistent State
*Davvy* helps you track even when the PC is shut! Data is saved to the local drive with every added task, and loaded
automatically on startup!

## Commands

### `todo` - Create and add a TODO task
Create a task of todo type for *Davvy* to track.

**Syntax**:
```
todo <task_description>
```

**Example of usage**:
```
todo homework
```

**Expected outcome**:
*Davvy* will read your todo description, and echo it back to you via console after adding it to your task list.
```
 Got it. I've added this task:
 [T][ ] hi
 Now you have 4 tasks in the list.
```

### `deadline` - Create and add a Deadline task
Create a task of deadline type for *Davvy* to track. <br />
Note that a "due by" parameter is needed.

**Syntax**:
```
deadline <task_description> /by <task_deadline>
```

**Example of usage**:
```
deadline wash clothes /by tonight
```

**Expected outcome**:
*Davvy* will read your deadline description, and echo it back to you via console after adding it to your task list.
```
 Got it. I've added this task:
 [D][ ] wash clothes (by: tonight)
 Now you have 5 tasks in the list.
```
### `event` - Create and add a Event task
Create a task of event type for *Davvy* to track. <br />
Note that a "from" and "to" parameter is needed.

**Syntax**:
```
event <task_description> /from <start_date_or_time> /to <end_date_or_time>
```

**Example of usage**:
```
event attend class /from 9am /to 10am
```

**Expected outcome**:
*Davvy* will read your event description, and echo it back to you via console after adding it to your task list.
```
 Got it. I've added this task:
 [E][ ] attend class (from: 9am to: 10am)
 Now you have 6 tasks in the list.
```
### `list` - List out all saved tasks
Shows all stored tasks that the user has added into the chatbot

**Example of usage**:
```
list
```

**Expected outcome**:
A list of all tasks will be shown. The first box indicates the type of task shown, the second box indicates the 
completion status of the task.
```
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][X] return book (by: June 6th )
 3.[E][X] project meeting(from: Aug 6th 2pm to: 4pm)
```
### `mark` - Tags a task as completed
Tag a selected task as completed. Can be seen with an 'X'. <br />
Note that task number here refers to the index of the item of the list

**Syntax**:
```
mark <task_number>
```

**Example of usage**:
```
mark 6
```

**Expected outcome**:
*Davvy* will store the new status of the task, and echos the tagged task on the console for the user to verify
```
 Nice! I've marked this task as done:
 [E][X] attend class(from: 9am to: 10am)
```
### `unmark` - Un-Tag a task if incomplete
Tag a selected task as not completed. Can be seen with the absence of an 'X' in the second box of the task. <br />
Note that task number here refers to the index of the item of the list

**Syntax**:
```
unmark <task_number>
```

**Example of usage**:
```
unmark 6
```

**Expected outcome**:
*Davvy* will store the new status of the task, and echos the un-tagged task on the console for the user to verify
```
 OK, I've marked this task as not done yet:
 [E][ ] attend class(from: 9am to: 10am)
```
### `delete` - Deletes a specified task
Removes a selected task from storage. <br />
Note that task number here refers to the index of the item of the list.

**Syntax**:
```
delete <task_number>
```

**Example of usage**:
```
delete 6
```

**Expected outcome**:
*Davvy* will remove the task from storage, and echos the deleted task on the console for the user to verify
```
 Noted. I've removed this task:
 [E][ ] attend class(from: 9am to: 10am)
 Now you have 5 tasks in the list.
```
### `find` - Searches for a task
Searches everything in the list for tasks that match the search description

**Syntax**:
```
find <search_description>
```

**Example of usage**:
```
find clothes
```

**Expected outcome**:
*Davvy* will query for the task with the user specified description, and returns the tasks with the description, should
it exist
```
 Here are the matching tasks in your list:
 1.[D][ ] wash clothes (by: tonight )
```
### `bye` - End the chatbot
Done with the chatbot? End the bot manually to prevent potential saving issues :(

**Example of usage**:
```
bye
```

**Expected outcome**:
*Davvy* will terminate the chatbot program
```
 Bye. Hope to see you again soon!
```
