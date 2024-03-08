# User Guide
**MOBY** is a chatbot that aids with **task management**, optimally used via a **Command Line Interface** (CLI).

```
Hello from
 _____ ______   ________  ________      ___    ___
|\   _ \  _   \|\   __  \|\   __  \    |\  \  /  /|
\ \  \\\__\ \  \ \  \ \  \ \  \_\ /_   \ \  \/  / /
 \ \  \\|__| \  \ \  \ \  \ \   __  \   \ \    / /
  \ \  \    \ \  \ \  \_\  \ \  \_\  \   \/   / /
   \ \__\    \ \__\ \_______\ \_______\__/   / /
    \|__|     \|__|\|_______|\|_______|\____/ /
                                      \|____|/
```

Get the latest release [here](https://github.com/ChongXern/ip/releases).

## Features
- [Adding new task](#adding-new-task): ```todo```, ```deadline``` or ```event```
- [Deleting task](#deleting-a-task): ```delete```
- [Showing task list](#show-list): ```list```
- [Marking or unmarking a task](#mark-or-unmark-a-task): ```mark``` or ```unmark```
- [Finding for task](#finding-for-a-task): ```find```
- [Renaming chatbot](#rename-chatbot): ```rename```
- [Repeating user input](#repeating-user-input): ```echo```
- [Saving to file](#saving-to-file)
- [Command Typo detection](#command-typo-detection)
- [Exiting the program](#exiting-the-program): ```bye```
- Undo previous command: ```Upcoming in v2.0```

### Adding new task

Adds a new task to the list of tasks. There are three types of tasks available: ```todo```, ```deadline```, and ```event```. After adding the new task, the user will be updated with the number of tasks in the task list.

1. **Todo**

   The most basic type of task with no attached dates.

   **Format**: ```todo TASK_DESCRIPTION```

   Examples:
   + ```todo buy bananas```
   + ```todo attend classes```


_Example of usage_:

`todo buy bread`

_Expected outcome_:

   ```
   MOBY: Got it. I've added this task:
         [T][ ] buy bread
         Now you have 1 task in the list
   ```


2. **Deadline**

   Task with only one date, which is the deadline. The deadline date should be in ```dd-MM-yyyy``` format, but other date inputs such as ```today```, ```tomorrow```, ```tmr```, ```next week```, ```next month``` are supported.

   **Format**: ```deadline TASK_DESCRIPTION by DEADLINE```
   > If the format is not followed, the chatbot will print out a confused message asking to finish the sentence

   Examples:
   + ```deadline exam by 14-03-2024```
   + ```deadline homework by tmrw```
   + ```deadline group project by next week```

   _Example of usage_:

   `deadline bread expires by next week`

   _Expected outcome_:

   ```
   MOBY: Got it. I've added this task:
         [D][ ] bread expires (by: 15 Mar 2024)
         Now you have 3 tasks in the list
   ```

3. **Event**

   Task with two dates, the event start date and the event end date. The dates should also be in ```dd-MM-yyyy``` format, but the aforementioned date formats are supported as well.

   **Format**: ```event TASK_DESCRIPTION from START_DATE to END_DATE```
   > If this format is not followed, the chatbot will print out a confused message asking to finish the sentence

   Examples:
   + ```event Chinese New Year from 10-02-2024 to 11-02-2024```
   + ```event hackathon from today to next month```
   + ```event recess week from tmr to next week```

   _Example of usage_:

   `event classes from 02-02-2024 to 04-02-2024`

   _Expected outcome_:

   ```
   MOBY: Got it. I've added this task:
         [E][ ] classes (from: 2 Feb 2024 to: 4 Feb 2024)
         Now you have 4 tasks in the list
   ```

### Deleting a task
Deletes a task from the task list based on the index.

**Format**: ```delete INDEX```
> If an index wasn't inputted or the index exceeds the file size, the chatbot will print this out as a message and ask to type again.

Examples:
+ `delete 1`
+ `delete 2`

_Example of usage_:

`mark 7`

_Expected outcome_:

   ```
   MOBY: : Noted. I've removed this task:
          [T][ ] play football
          Now you have 6 tasks in the list
   ```

### Mark or unmark a task
Checks or unchecks any of the tasks in the task list based on the integer index. If the task is already marked or unmarked, the chatbot will say so as a response.

**Format**: ```mark INDEX``` or ```unmark INDEX```
> If an integer is not inputted, a warning message will be printed asking to input an integer instead.
> If the index is larger than the size of the list, a message will be printed asking to input a valid index.

Examples:
+ ```mark 2```
+ ```unmark 6```

_Example of usage_:

`mark 2`

_Expected outcome_:

   ```
   MOBY: Nice! I've marked this task as done:
        [T][X] buy kaya
   ```

### Show list

Shows the list of items in a numbered order.

**Format**: ```list```

> If the list is empty, nothing will be shown and a message will be printed instead.

_Example of usage_:

`list`

_Expected outcome_:

   ```
   MOBY: Here are the tasks in your list:
         1.[T][X] buy bread
         2.[T][X] buy kaya
         3.[D][ ] bread expires (by: 15 Mar 2024)
         4.[E][ ] classes (from: 2 Feb 2024 to: 4 Feb 2024)
         5.[E][ ] learn bread making (from: 9 Mar 2024 to: 8 Apr 2024)
   ```

### Finding for a task
Finds for any tasks that contain a keyword. Prints all the tasks from the task list that correspond to the keyword.

**Format**: ```find KEYWORD```
> Prints out how many tasks with the keyword are found.

Examples:
+ ```find CS2113```
+ ```find money```

_Example of usage_:

`find bread`

_Expected outcome_:

   ```
   MOBY: Here are the matching tasks in your list:
         1.[T][X] buy bread
         2.[T][X] buy kaya
         There are 2 tasks corresponding to your search term buy.
   ```

### Rename chatbot
Renames the UI chatbot's name from MOBY to a new name, which will be capitalised.

**Format**: ```rename NEW_NAME```

Examples:
+ ```rename Duke```
+ ```rename chatgpt```

_Example of usage_:

`rename pete`

_Expected outcome_:
   ```
   MOBY: Renamed chatbot from MOBY to PETE
   ```
> The name change is only evident in the chatbox when more commands are performed

### Repeating user input
Repeats what the user prompts by echoing.

**Format**: ```echo TEXT```

Examples:
+ ```echo abcdefg```
+ ```echo echo```

_Example of usage_:

`echo hello world`

_Expected outcome_:
   ```
      PETE: hello world
   ```

### Saving to file
Saves the task list to a txt file `list.txt`. The file is written every time a task is added, marked or deleted. This file can be accessed next to the app or jar file used.

_Example of usage_:

`deadline assignment by today`

_Expected outcome in text file_:
   ```
   T | [/] | buy bread
   T | [/] | buy kaya
   D | [ ] | bread expires | 15 Mar 2024
   E | [ ] | classes | 2 Feb 2024 -> 4 Feb 2024
   E | [ ] | learn bread making | 9 Mar 2024 -> 8 Apr 2024
   D | [ ] | assignment | 8 Mar 2024
   ```

### Command typo detection
When an unknown command is identified, the chatbot will attempt to correct it if it determines that it is only a typo. Shows up after prompt line with typo in the command is inputted and identified.
> If it is not a typo, a confusion message will be printed saying it does not understand.

_Example of usage_:

`deadlin project by 26-03-2024`

_Expected outcome_:
   ```
   PETE: Did you mean: deadline?
   ```

### Exiting the program
Exits the chatbot session.

**Format**: ```bye```

_Example of usage_:

`bye`

_Expected outcome_:
   ```
   PETE: Bye. Hope to see you again soon!
   ```
