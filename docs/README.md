# User Guide

TimL is a chatbot that functions as a task manager to help you store any task on a list.<br>
It is also optimized for use via Command Line Interface (CLI).

## Quick Start
1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest TimL.jar release [here](https://github.com/TimothyLKM/ip/releases).
3. Copy the file to a folder you want as the home directory of the file.
4. From the command terminal, cd into the home directory of the jar file and run the command `java -jar TimL.jar` to launch the application.
5. Type a command in the command box and press Enter to execute it. Refer to Features below for details of each available command.

## Features 

### **Notes on command and tasks format:**

>Words between dash - are parameters to be input by the user <br>
e.g. in todo -TASK- where TASK is the parameter to be input by the user used as todo finish assignment

>All tasks are displayed in the format INDEX_NUMBER.[TASK_TYPE][MARKED_STATUS] TASK_DESCRIPTION e.g. <br>
Todo: 4: [T][ ] do homework<br>
Deadline: 6: [D][X] math homework (by: 12 feb)<br>
Event: 7: [E][ ] california road trip (from: 2 feb to: 26 feb)


## Adding a Task
**You can add 3 different types of tasks to the list using these 3 different commands.**

### - Adding a 'Todo' type task:
* Adds a Todo task to the task list<br>
* **Format**: `todo -the task to be done-`<br>
* **Example**: `todo wash the dishes`
### - Adding a 'Deadline' type task:
* Adds a Deadline task to the task list <br>
* **Format**: `deadline -the task to be done- /by -the deadline-` <br>
* **Example**: `deadline buy a towel /by 8 feb`
### - Adding an 'Event' type task:
* Adds an Event task to the task list <br>
* **Format**: `event -the task to be done- /from -the start date- /to -the end date-` <br>
* **Example**: `event Japan vacation /from 14 feb /to 20 feb`

## Other Commands

### - View list:
- Displays the full list <br>
- **Example**: `list` 

### - Setting a task's status to be done: 
- Marks a task as completed
- Format: `mark --INDEX--`

>- INDEX must be a positive integer less than or equals to the current number of tasks in the task list<br>
>- If task selected is already marked, it will remain marked

### - Setting a task's status to be incomplete:
- Marks a task as uncompleted
- Format: `unmark --INDEX--`

>- INDEX must be a positive integer less than or equals to the current number of tasks in the task list <br>
>- If task selected is already marked, it will remain marked 


###  - Deleting a task: 
  - Deletes a task from the task list
  - **Format**: delete --INDEX--
  - **Example**: `delete 1`

> **_NOTE:_**  
INDEX must be a positive integer less than or equals to the current number of tasks in the task list

###  - Finding a task:
- Finds all task containing an identical keyword from the task list
- **Format**: find --keyword--
- **Example**: `find homework`

###  - Exiting the Program:
- Exits program with keyword 'bye'
- **Example**: `bye`


### EXPECTED OUTPUTS

#### Input: `todo wash the dishes`
```
    ____________________________________________________________
    Roger that! i've added this task:
    [T][ ] wash the dishes
    Now you have 1 task in the list.
    ____________________________________________________________
```
#### Input: `deadline buy a towel /by 8 feb`
```
    ____________________________________________________________
    Roger that! i've added this task:
    [D][ ] buy a towel (by: 8 feb)
    Now you have 2 task in the list.
    ____________________________________________________________
```

#### Input: `event Japan vacation /from 14 feb /to 20 feb`
```
    ____________________________________________________________
    Roger that! i've added this task:
    [E][ ] Japan vacation (from: 14 feb to: 20 feb)
    Now you have 3 task in the list.
    ____________________________________________________________
```
#### Input: `find Japan`
```
    ____________________________________________________________
     HERE ARE YOUR MATCHING TASKS IN YOUR LIST: 
    1: [E][ ] Japan vacation (from: 14 feb to: 20 feb)
    ____________________________________________________________
```
#### Input: `list`
```
    ____________________________________________________________
     Here's your List DOOD:
    1: [T][ ] wash the dishes
    2: [D][ ] buy a towel (by: 8 feb)
    3: [E][ ] Japan vacation (from: 14 feb to: 20 feb)
    ____________________________________________________________
```
#### Input: `mark 1`
```
    ____________________________________________________________
    Nice!! I've marked this task as done:
    [X] wash the dishes
    ____________________________________________________________
```
#### Input: `bye`
```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
