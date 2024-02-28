# ThawBot User Guide
Welcome to ThawBot! This tool helps you keep track of your tasks, deadlines, and events, making your daily task management more efficient and straightforward.

## Features
**Save Data** <br>
All your tasks are saved automatically and loaded when you start the program again. <br><br>

**Add Task** <br>
You can add three types of tasks: Todos, Deadlines, and Events. <br><br>

**List Tasks** <br>
Displays all the tasks which you have added and whether they are completed or not. <br><br>

**Mark the Task as Done** <br>
Mark any task as done based on its associated number in the task list. This feature helps you keep track of your task completion.<br><br>

**Unmark Task as Undone** <br>
Unmark any task as undone based on its associated number in the task list. This feature helps you keep track of your task completion. <br><br>

**Delete Task** <br>
Remove tasks that are no longer relevant or were added by mistake. <br><br>

**Find Task** <br>
Search for tasks using keywords to quickly find specific tasks in your list. <br><br>



## Installation (Prerequisite Java 11 or later)
1. Download the latest release of ThawBot from the [releases page] 
2. Extract the downloaded zip file to a folder on your computer. 
3. Open a terminal or command prompt and navigate to the folder where you extracted the zip file. 
4. Run the following command to start ThawBot:

```bash
java -jar ThawBot.jar
```

## Usage
#### Adding task:
1. Todo: todo <br>
     Format: todo NAME <br>
     Example: <br>
     ```bash
     todo eat
     ```
     Output: <br>
     ```bash
     Got it. I've added this task:
     [T][ ] eat
     Now you have 1 task in the list.
     ```
    
3. Deadline: deadline <br>
     Format: deadline NAME /by DUE_DATE DUE_TIME <br>
     DUE_DATE is in mm-dd-yy (e.g. 01-04-2024 is displayed as Jan 4 2024. Note cannot replace the "-" with "/"). <br>
     DUE_TIME is in HHMM (e.g. 2359 is displayed as 11:59pm). <br>
     Example: <br>
     ```bash
     deadline submit assignment /by 01-04-2024 2359
      ```
     Output: <br>
     ```bash
     Got it. I've added this task:
     [D][ ] submit assignment (by: Jan 4, 2024, 11:59PM)
     Now you have 2 task in the list.
     ```
   
5. Event: event <br>
     Format: event NAME /from START_DATE START_TIME /to END_DATE END_TIME <br>
     START_DATE and END_DATE is in mm-dd-yy (e.g. 01-04-2024 is displayed as Jan 4 2024. Note cannot replace the "-" with "/"). <br>
     START_TIME and END_TIME is in HHMM (e.g. 2359 is displayed as 11:59pm). <br>
     Example: <br>
     ```bash
     event CG2023 exam /from 01-01-2100 1500 /to 01-01-2100 1700
     ```
     Output: <br>
     ```bash
     [E][ ] CG2023 exam (from: Jan 1 2100 3:00PM to: Jan 1 2100 5:00PM)
     Now you have 3 task in the list.
     ```bash

#### Deleting task:
**delete: Deletes a task** <br>
     Format: delete NUMBER <br>
     NUMBER is the task index or the number that the task is labelled with which can be seen when using the list function. <br>
     Example: delete 2 (it will delete the second tasks in the list and shift all the tasks at the bottom up). <br><br>
     ```bash
     delete 2
     ```
     Output: <br>
     ```bash
     [D][ ] submit assignment (by: Jan 4 2024 11:59PM)
     Noted. I've removed this task:
     ```

#### Looking at the tasks 
**list: List out all the added tasks** <br>
     Format: list <br>
     Will list out all the added tasks. <br>
     Example: <br>
     ```bash
     list
     ```
     Output: <br>
     ```bash
     1. [T][ ] eat
     2. [E][ ] CG2023 exam (from: Jan 1 2100 3:00PM to: Jan 1 2100 5:00PM)
     ```
     

#### Finding specific task: 
**find: Find a task using a keyword** <br>
     Format: find KEYWORD <br> 
     Will find the task in the task list that contains the particular KEYWORD. <br>
     Example: <br>
     ```bash
     find eat
     ```
     Output: <br>
     ``` bash
     Here are the matching tasks in your list:
     1. [T][ ] eat
     ```

#### Marking certain task as complete 
1. **mark: Mark a task as done using the associated number in the tasklist** <br>
     Format: mark NUMBER <br>
     Will mark the task with the associated number as done. <br>
     Example:  <br>
     ```bash
     mark 1
     ```
     Output: <br>
     ```bash
     Nice! I've marked this task as done:
     [T][X] eat
     ```

2. **unmark: unmark a task as not done using the associated number in the tasklist** <br>
     Format: unmark NUMBER <br>
     Will unmark the task with the associated number as done. <br>
     Example: <br>
     ```bash
     unmark 1
     ```
     Output: <br>
     ```bash
     Nice! I've unmarked this task as not done:
     [T][ ] eat
     ```
     #### Leaving the program
     **bye: terminate the program** <br>
     Format: bye <br>
     Will terminate the program but saves the file before it does that. <br>
     Example: <br>
     ```bash
     bye
     ```

     Output: <br>
     ```bash
     Bye. Hope to see you again soon!
     ```

