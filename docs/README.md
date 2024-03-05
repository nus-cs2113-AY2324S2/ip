# ZORO
## User Guide
Welcome to **Zoro!** This tool is designed to help you manage your tasks efficiently, whether they are to-dos, deadlines, or events.

## Features
### Add Task:
     You can add three types of tasks:
      1. To-dos
      2. Deadlines
      3. Events

Each task type helps you categorize your tasks for better organization.

### List Tasks
Display all your tasks, including to-dos, deadlines, and events, along with their status
(completed or pending) and additional information like timeframe and deadline timing..

### Mark Task as Done
Mark any task as completed based on its list number to track your progress effectively.

### Mark Task as Incomplete
Restore any completed task to pending status based on its list number. This feature helps you manage your tasks easily.

### Delete Task
Remove tasks that are no longer needed or were added by mistake.

### Find Tasks
Search for tasks by keywords to quickly find specific tasks in your list. This feature helps you to find the task to know its deadline or timeframe along with its index to mark or unmark it.

### Save Data
All your tasks are automatically saved and loaded when you start the program again.


## Getting Started
### Prerequisites
Make sure you have Java 11 or later installed on your computer.

### Installation
1.Download the latest release of Zoro from the releases page.
2.Extract the downloaded zip file to a folder on your computer.
3.Navigate to the folder where you extracted the zip file and open a terminal.
4.Run the following command to start the Zoro application:

      java -jar ZORO.jar

If the application started successfully, it will display the following welcome message.

        -----------------------------------------
             Hello I'm ZORO
             What can I do for you?
        -----------------------------------------
   
>-----------------------------------------

## Usage
### `todo` - Add a To-do Task
Adds a simple task with no additional informatiom

**Example of usage:**

      todo Run 10km
**Expected Outcome:**

      -----------------------------------------
         Got it. I've added this task:
         [T][ ] Run 10km
         Now you have 1 tasks in the list.
      -----------------------------------------

### `deadline` - Add a Deadline Task
Adds a task to complete it by specific deadline

**Example of usage:**

      deadline Do 20 Muscle-Ups /by 31/12/2024 11:59
**Expected Outcome:**

      -----------------------------------------
         Got it. I've added this task:
         [D][ ] Do 20 Muscle-Ups (by: Dec 31 2024 11:59)
         Now you have 2 tasks in the list.
      -----------------------------------------

### `event` - Add a Event Task
Adds a task with specific date and time.

**Example of usage:**

      event Build Core Muscles /from 01/01/2025 12:01 /to 21/06/2025 11:59
**Expected Outcome:**

      -----------------------------------------
         Got it. I've added this task:
         [E][ ] Build Core Muscles (from: Jan 01 2025 12:01 to: Jun 21 2025 11:59)
         Now you have 3 tasks in the list.
      -----------------------------------------

### `list` - Lists all Tasks
Display all of your tasks, including To-dos, Deadlines and Events along with their status (completed or pending) and their deadline or timeframe.

**Example of usage:**

      list
**Expected Outcome:**

      -----------------------------------------
         Here are the tasks in your list:
         1.[T][ ] Run 10km
         2.[D][ ] Do 20 Muscle-Ups (by: Dec 31 2024 11:59)
         3.[E][ ] Build Core Muscles (from: Jan 01 2025 12:01 to: Jun 21 2025 11:59)
      -----------------------------------------

### `mark` - Mark Task as Completed
Marks a specific Task as completed based on the list number provided.

**Example of usage:**

      mark 2
**Expected Outcome:**

      -----------------------------------------
         Nice! I've marked this task as done:
         [D][X] Do 20 Muscle-Ups (by: Dec 31 2024 11:59)
      -----------------------------------------

### `unmark` - Mark Completed Task as not done
Unmarks a specific Task based on the list number provided. It helps to revert the task status to not completed.

**Example of usage:**

      unmark 2
**Expected Outcome:**

      -----------------------------------------
         OK, I've marked this task as not done yet:
         [D][ ] Do 20 Muscle-Ups (by: Dec 31 2024 11:59)
      -----------------------------------------

### `delete` - Remove a Task
Removes a specific task from the list based on the list number provided.

**Example of usage:**

      delete 1
**Expected Outcome:**

      -----------------------------------------
         Noted. I've removed this task:
         [T][ ] Run 10km
         Now you have 2 tasks in the list.
      -----------------------------------------

### `find` - Find Tasks
Search for tasks using keywords to quickly find specific tasks in the filtered list.

**Example of usage:**

      find Build
**Expected Outcome:**

      -----------------------------------------
         Here are the matching tasks in your list:
         1[E][ ] Build Core Muscles (from: Jan 01 2025 12:01 to: Jun 21 2025 11:59). (Original Index: 2) 
      -----------------------------------------

### `bye` - Exit the Program
Exits from Zoro program.

**Example of usage:**

      bye
**Expected Outcome:**

      -----------------------------------------
         Bye. Hope to see you again soon!
      -----------------------------------------
> ----------------------------------------- -----------------------------------------
### User Commands:
				
| Name | Format | Command Example |
|--|--|--|
| Todo | todo [name]  | todo Run 10km |
| Deadline | deadline [name] /by [DD/MM/YYYY HH:MM] | deadline Do 20 Muscle-Ups /by 31/12/2024 11:59 |
| Event| deadline [name] /from [DD/MM/YYYY HH:MM] /to [DD/MM/YYYY HH:MM] |event Build Core Muscles /from 01/01/2025 12:01 /to 21/06/2025 11:59 |
| List | list  | list |
| Mark |mark [task index]  | mark 2 |
| Unmark | unmark [task index]  | unmark 2 |
| Delete | delete [task index] | delete 1 |
| Find | find [keyword] | find muscle |
| Exit | bye  | bye |



### Command tips:

 Format | Exception | Taken as |
|--|--|-  |
 todo [name] | nil | nil |
| deadline [name] /by [DD/MM/YYYY **HH:MM**]  | deadline [name] /by [DD/MM/YYYY] | deadline [name] /by [DD/MM/YYYY **00:00**]|
| event [name] /from [DD/MM/YYYY **HH:MM**] /to [DD/MM/YYYY **HH:MM**]  | event [name] /from [DD/MM/YYYY] /to [DD/MM/YYYY] | event [name] /from [DD/MM/YYYY **00:00**] /to [DD/MM/YYYY **00:00**]|




### Other useful tips:

 1. Find keyword usage

     When using `find [keyword]` you will get the real index of the tasks shown on the side. By knowing the real index you can use that in `mark, unmark or delete commands`. This make sures that you don't actually change the status or delete the wrong tasks.

      **Example:**
> 1[E][ ] Build Core Muscles (from: Jan 01 2025 12:01 to: Jun 21 2025 11:59).**(Original Index: 2)**


 2. Missing tasks.txt file or data folder
    
     If you don't have data folder or tasks.txt, the program will warn you and it will create a new file at that location if you continue to add tasks.
	 
	 **Example:**
   
          -----------------------------------------
               Unable to load File. Error loading tasks from file: .\data\tasks.txt (The system cannot find the file specified)
               Please Check your File path.
               Continuing will create a new file in the destination path
          -----------------------------------------
