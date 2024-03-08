# User Guide
**MOBY** is a chatbot that aids with **task management**, optimally used via a **Command Line Interface** (CLI). 

## Features  
- Adding new task: ```todo```, ```deadline``` or ```event```
- Deleting task: ```delete```
- Showing task list: ```list```
- Marking or unmarking a task: ```mark``` or ```unmark```
- Renaming chatbot: ```rename```
- Repeating user input: ```echo```
- Saving to file
- Command Typo detection
- Exiting the program: ```bye```

### Adding new task

Adds a new task to the list of tasks. There are three types of tasks available: ```todo```, ```deadline```, and ```event```. After adding the new task, the user will be updated with the number of tasks in the task list. 

1. **Todo**
   
   The most basic type of task with no attached dates.
   
   **Format**: ```todo TASK_DESCRIPTION```


   Examples:
   + ```todo buy bananas```
   + ```todo attend classes```

3. **Deadline**
   
   Task with only one date, which is the deadline. The deadline date should be in ```dd-MM-yyyy``` format, but other date inputs such as ```today```, ```tomorrow```, ```tmr```, ```next week```, ```next month``` are supported.
   
   **Format**: ```deadline TASK_DESCRIPTION by DEADLINE```
  > If the format is not followed, the chatbot will print out a confused message asking to finish the sentence

   Examples:
   + ```deadline exam by 14-03-2024```
   + ```deadline homework by tmrw```
   + ```deadline group project by next week```
    
5. **Event**
   
   Task with two dates, the event start date and the event end date. The dates should also be in ```dd-MM-yyyy``` format, but the aforementioned date formats are supported as well.
   
   **Format**: ```event TASK_DESCRIPTION from START_DATE to END_DATE"
> If this format is not followed, the chatbot will print out a confused message asking to finish the sentence

   Examples:
   + ```event Chinese New Year from 10-02-2024 to 11-02-2024```
   + ```event hackathon from today to next month```
   + ```event recess week from tmr to next week```

### Show list

Shows the list of items in a numbered order. 

Format: ```list```

> If the list is empty, nothing will be shown and a message will be printed instead.

### Mark or unmark a task
Check or uncheck any of the tasks in the task list based on the integer index. If the task is already marked or unmarked, the chatbot will say so as a response. 

**Format**: ```mark INDEX``` or ```unmark INDEX```
> If an integer is not inputted, a warning message will be printed asking to input an integer instead.
> If the index is larger than the size of the list, a message will be printed asking to input a valid index. 

  Examples:
  + ```mark 2```
  + ```unmark 6```

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
