# Tickles User Guide

## Adding a todo task: `todo`

Adds a todo task to the list.  
Format: `todo [task description]`  
Example: `todo shop for socks`

## Adding a deadline task: `deadline`

Adds a deadline task to the list.  
Format: `todo [task description] /by [deadline]`  
Example: `deadline finish homework /by someday`

## Adding an event task: `event`

Adds an event task to the list.  
Format: `event [task description] /from [start] /to [finish]`  
Example: `event wedding for rats /from friday 5pm /to 8pm`  

## Marking a task as complete: `mark`

Denotes this task number has been completed.   
Format: `mark [number]`  
Example: `mark 2` (list must have at least 2 tasks)

## Marking a task an incomplete: `unmark`

Unmarks a task.   
Format: `unmark [number]`  
Example: `unmark 4` (list must have at least 4 tasks)

## Deleting a task: `delete`

Deletes the task from the list.   
Format: `delete [number]`  
Example: `delete 3` (list must have at least 3 tasks)

## Searching the list of tasks by word(s): `find`

Searches through the list for matches, and prints the list of tasks matching the search.  
Format: `find [word(s)]`  
Example: `find birthday`  

## Listing all tasks: `list`

Prints all tasks added to list, along with whether they are marked/unmarked, along with their task type: [T], [D], [E] denotes Todo, Deadline, and Event, respectively.  
Format: `list`  
Example: `list`

## Exiting the program: `bye`

Terminates the program, and saves the list of tasks for later use.  
Format: `bye`  
Example: `bye`


