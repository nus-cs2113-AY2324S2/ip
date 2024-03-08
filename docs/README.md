# Soot
***
## Project Introduction
Soot is a Chatbot that uses the CLI to interact with its user, allowing its users to create a list of tasks that
they have to complete.

This is an individual Java project that was done for the module, CS2113 Software Engineering & Object-Oriented
Programming.

## Table of Contents
<!-- TOC -->
* [Project Introduction](#project-introduction)
* [Booting Soot Up](#booting-soot-up)
* [What Soot can do for YOU](#what-soot-can-do-for-you)
  * [View Soot's commands](#view-soots-commands)
  * [Type of Tasks](#type-of-tasks)
  * [Modifying your Task List](#modifying-your-task-list)
* [Closing Soot](#closing-soot)
<!-- TOC -->

***
## Booting Soot Up
1. Download the latest `Soot.jar` file **[here](https://github.com/claribelho/ip/releases)**
2. Copy the file to the folder you want to use as the main Soot folder
3. Easily boot up Soot by navigating to the folder with the .jar file and run `java -jar ip.jar`

***

## What Soot can do for YOU
```
TIPS for understanding how to use the commands:
- Variables in CAPITAL_LETTERS are parameters to be supplied by you, 
  otherwise copy the command format exactly
- An example input and output is provided for each command.
  Use this as a reference if you are not sure how to use the command.
```

### View Soot's commands
If you are lost? Just key in one word and Soot will be there for you.

> **_View all valid commands_**: `help`

Soot will let you know all commands I recognise!   
**Command Format**: `help`

| _Input_ | _Output_    |
|---------|-------------|
| help    | (see below) |   

_full output_ >>>
```
here's all the commands i recognise:
    ~~~    
to add a task, remember to specify the task type
1. todo TASK_NAME
2. deadline TASK_NAME /by DUE_DATE
3. event TASK_NAME /from START_DATE /to END_DATE
    ~~~    
to modify ur task list:
- list (to view you list)
- done TASK_INDEX_IN_LIST (mark a task as done)
- unmark TASK_INDEX_IN_LIST (mark a task as undone)
- delete TASK_INDEX_IN_LIST (delete a task from your list)
- find KEYWORD_TO_FIND (find a specific word in your task list)
    ~~~    
still lost? don't worry, visit this link for more info
https://claribelho.github.io/ip/
```

### Type of Tasks
Soot recognises 3 types of tasks - Todo, Deadline and Event. These tasks can be added to the list using the following
commands:
> 1) _**Add a Todo task**_: `todo`

Soot will add a new task of type Todo to your task list.  
**Command Format**: `todo TASK_NAME`

| _Input_        | _Output_       |
|--------------|--------------|
| todo math | Okay! i've added to ur tasklist:<br> >> [T][ ] math <br> you currently have a total of 1 tasks in your list :) |

> 2) **_Add a Deadline task_**: `deadline`

Soot will add a new task of type Deadline to your task list.  
**Command Format**: `deadline TASK_NAME /by DUE_DATE`

| _Input_        | _Output_       |
|--------------|--------------|
| deadline math /by tmr| Okay! i've added to ur tasklist:<br> >> [D][ ] math (by: tmr!) <br> you currently have a total of 1 tasks in your list :) |

> 3) **_Add an Event task_**: `event`

Soot will add a new task of type Event to your task list.  
**Command Format**: `event TASK_NAME /from START_DATE /to END_DATE`

| _Input_        | _Output_       |
|--------------|--------------|
| event math /from tues /to wed| Okay! i've added to ur tasklist:<br> >> [E][ ] math (from: tues ~~ to: wed!) <br> you currently have a total of 1 tasks in your list :) |

### Modifying your Task List
Using Soot, you can do the following to your list:
> **_List all your tasks out_**: `list`

Soot will look at the tasks you have added and lets you view it as a list.  
**Command Format**: `list`

| _Input_ | _Output_                              |
|---------|---------------------------------------|
| list    | tasks to be done: <br> 1. [T][ ] math |

> **_Mark a task as completed_**: `done`

Soot will find the task you are specifying and will mark it done.  
**Command Format**: `done TASK_INDEX_IN_LIST`

| _Input_ | _Output_                                                |
|---------|---------------------------------------------------------|
| done 1  | good job! this task is marked as done now: <br> >> math |

> **_Mark a task as uncompleted_**: `unmark`

Soot will find the task you are specifying and will mark it undone.  
**Command Format**: `unmark TASK_INDEX_IN_LIST`

| _Input_ | _Output_                                                |
|---------|---------------------------------------------------------|
| unmark 1  | This task is now marked undone:  <br> >> math |
> **_Delete a task_**: `delete`

Soot will find the task you are specifying and will delete it from your list.  
**Command Format**: `delete TASK_INDEX_IN_LIST`

| _Input_  | _Output_                                                                                                                    |
|----------|-----------------------------------------------------------------------------------------------------------------------------|
| delete 1 | okay, i will remove this task from your list: <br> >> [T][ ] math <br>you currently have a total of 2 tasks in your list :) |

> **_Find a keyword in your tasks_**: `find`

Soot will find the keyword you are specifying within the task names of your list and will list these tasks to you.  
**Command Format**: `find KEYWORD_TO_FIND`

| _Input_   | _Output_                                                                                      |
|-----------|-----------------------------------------------------------------------------------------------|
| find math | i found your word in these tasks: <br> 1. [T][ ] math assignment <br> 2. [T][ ] math problems |

***

## Closing Soot
Don't worry, whenever you are ready to go, Soot will save your task list to your device for future use.

All you have to say is bye :)
> **_Closing Soot_**: `bye`

**Command Format**: `bye`

| _Input_ | _Output_       |
|---------|--------------|
| bye     | Bye! Till the next time we meet... <br> i've saved your list for future use <3 |

We hope to see you back on Soot soon.