# User Guide

Nehsik is a CLI-based personal assistant chatbot that helps the user to keep track of their daily tasks

## Installation
1. Ensure that Java 11 or above is installed in your computer
2. Download "Nehsik.jar" from this [link](https://github.com/Kishen271828/ip/releases/tag/A-Release)
3. Open the command terminal, and `cd` to the directory where the "Nehsik.jar" file is present
4. Run the command `java -jar Nehsik.jar`. If the app has started successfully, you will see the welcome message:
```
________________________________________________________________________________________________
Hala habibi! Shlonik? Shakhbarak?
I'm Nehsik, What can I do for you?
________________________________________________________________________________________________
```
5. You can start using Nehsik by typing the commands in the format as described in the Features section. 


## Features 

### Add tasks : `todo`, `deadline`, `event`

Tasks of the following types can be added:
- **Todo**: Contains task description only
- **Deadline**: Contains task description and the deadline time
- **Event**: Contains task description and the duration of the event
  
Format: 

`todo TASK_DESCRIPTION` 

`deadline TASK_DESCRIPTION /by DEADLINE`

`event TASK_DESCRIPTION /from START_TIME /to END_TIME`

### List tasks : `list`

Displays all the tasks present in the task list, along with the task type and status. 

Format: `list`

### Delete task : `delete` 

Deletes the required task based on the task number of the task to be deleted in the latest task list.

Format: `delete TASK_NUM`

### Mark task : `mark`

Marks the required task as done based on the task number of the task to be marked as done in the latest task list.

Format: `mark TASK_NUM`

### Unmark task : `unmark`

Marks the required task as not done based on the task number of the task to be marked as not done in the latest task list.

Format: `unmark TASK_NUM`

### Find task : `find`

Displays the list of matching tasks which contain the keyword in their descriptions.

Format: `find KEYWORD`

### Exit : `bye`
Displays bye message and exits from the program.

Format: `bye`

### Saving the data
The data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
Your task list can be found in the file "nehsik.txt" created in the same directory where the "Nehsik.jar" file is present.

