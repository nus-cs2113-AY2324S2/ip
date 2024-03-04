# Jake: Your Personal Assistant
### Introducing Jake! Ever needed help tracking your tasks? Jake is designed to make task management easy and efficient, right from within your CLI

## Feature Overview
1. Add a task: [`toDo`](#todo)
2. Add a deadline: [`deadline`](#deadline)
3. Add an event: [`event`](#event)
4. Mark a task: [`mark`](#mark)
5. Unmark a task: [`unmark`](#unmark)
6. List all tasks: [`list`](#list)
7. Find relevant tasks: [`find`](#find)
8. Delete a task: [`delete`](#delete)
9. Exit the program: [`bye`](#bye)
10. Saving the data (#save)
<br>

## Add a task: `toDo` <a name="todo"></a>
Adds a task to your task list

Format: `todo TASK`

Examples:
- `todo clean the kitchen`
- `todo restock the pantry`
<br>

## Add a deadline: `deadline` <a name="deadline"></a>
Adds a deadline to your task list

Format: `deadline TASK by ENDDATE`
- The format of ENDDATE needs to be strictly followed. It allows for 2 options: Either `YYYY-MM-DD HH:MM` or `YYYY-MM-DD`

Examples:
- `deadline math assignment by 2024-04-04 23:59`
- `deadline proposal submission by 2024-04-17`
<br>

## Add an event: `event` <a name="event"></a>
Adds an event to your task list

Format: `event TASK from STARTDATE to ENDDATE`
- The format of STARTDATE and ENDDATE needs to be strictly followed. It allows for 2 options: Either `YYYY-MM-DD HH:MM` or `YYYY-MM-DD`

Examples:
- `event Japan vacation from 2024-02-24 to 2024-03-01`
- `event swimming comps from 2024-03-04 12:00 to 2024-03-04 14:00`
<br>

## Mark a task: `mark` <a name="mark"></a>
Marks a task as completed

Format: `mark task INDEX`

Examples:
- `mark task 1`
- `mark task 3`
<br>

## Unmark a task: `unmark` <a name="unmark"></a>
Marks a task as uncompleted

Format: `unmark task INDEX`

Examples:
- `unmark task 1`
- `unmark task 3`
<br>

## List all tasks: `list` <a name="list"></a>
List out all the tasks in your task list

Format: `list`
<br>

## Find relevant tasks: `find` <a name="find"></a>
Find all tasks in your task list containing a specified keyword

Format: `find KEYWORD`
- The search is case-sensitive. For example, `books` will not match with `Books`
- The order of keywords do matter. `Book 1` will not match with `1 Book` 

Examples:
- `find assignment`
- `find math tutorial`
<br>

## Delete a task: `delete` <a name="delete"></a>
Deletes a task from your task list

Format: `delete INDEX`
- Deletes the person at the specified index
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer

Examples:
- `delete 2`
- `delete 4`
<br>

## Exit the program: `bye` <a name="bye"></a>
Exits the program

Format: `bye`
<br>

## Saving the data <a name="save"></a>
TaskList data will be saved automatically after any command that changes the data. There is no need to save manually