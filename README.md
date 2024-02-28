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



#### Installation (Prerequisite Java 11 or later)
1. Download the latest release of ThawBot from the [releases page] 
2. Extract the downloaded zip file to a folder on your computer. 
3. Open a terminal or command prompt and navigate to the folder where you extracted the zip file. 
4. Run the following command to start ThawBot:

```bash
java -jar ThawBot.jar
```

### Usage
#### Adding task:
1. Todo: todo <br>
     Format: todo NAME <br>
     Example: todo sleep. 
2. Deadline: deadline <br>
     Format: deadline NAME /by DUE_DATE DUE_TIME <br>
     DUE_DATE is in mm-dd-yy (e.g. 01-04-2024 is displayed as Jan 4 2024. Note cannot replace the "-" with "/"). <br>
     DUE_TIME is in HHMM (e.g. 2359 is displayed as 11:59pm). <br>
     Example deadline submit assignment /by 01-04-2024 2359. 
4. Event: event <br>
     Format: event NAME /from START_DATE START_TIME /to END_DATE END_TIME <br>
     START_DATE and END_DATE is in mm-dd-yy (e.g. 01-04-2024 is displayed as Jan 4 2024. Note cannot replace the "-" with "/"). <br>
     START_TIME and END_TIME is in HHMM (e.g. 2359 is displayed as 11:59pm). <br>
     Example: event CG2023 exam /from 01-01-2100 1500 /to 01-01-2100 1700. <br> 

#### Deleting task:
**delete: Deletes a task** <br>
     Format: delete NUMBER <br>
     NUMBER is the task index or the number that the task is labelled with which can be seen when using the list function. <br>
     Example: delete 2 (it will delete the second tasks in the list and shift all the tasks at the bottom up). <br><br>

#### Looking at the tasks 
**list: List out all the added tasks** <br>
     Format: list <br>
     Will list out all the added tasks. <br>

#### Finding specific task: 
**find: Find a task using a keyword** <br>
     Format: find KEYWORD <br> 
     Will find the task in the task list that contains the particular KEYWORD. <br>
     Example: find homework. <br>

#### Marking certain task as complete 
1. **mark: Mark a task as done using the associated number in the tasklist** <br>
     Format: mark NUMBER <br>
     Will mark the task with the associated number as done. <br>
     Example: mark 2. <br>
     Will mark the second task in the task list as done (for example finish homework) as done which will be displayed with a cross when the user lists out the tasklist in the future. 

2. **unmark: unmark a task as not done using the associated number in the tasklist** <br>
     Format: unmark NUMBER <br>
     Will unmark the task with the associated number as done. <br>
     Example: unmark 2. <br>
     Will unmark the second task in the task list as not done (for example finish homework) as done which will be displayed without a cross when the user lists out the tasklist in the future. <br>

#### Leaving the program
**bye: terminate the program** <br>
     Format: bye <br>
     Will terminate the program but saves the file before it does that. <br>

