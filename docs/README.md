# User Guide

Oley is a Java project for **task management**, optimized for use via a Command Line Interface (CLI). Given below are instructions on how to use it.

## Quick Start
> [!IMPORTANT] 
> Ensure you have **Java 11** or above installed in your Computer.

1. Download the latest `Oley.jar` from [here](https://github.com/ZhangWenyue3325/ip/releases).
2. Copy the file to the folder you want to use as the _home folder_ for your Oley chatbot.
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Oley.jar` command to run the application.
4. If the setup is correct, you will see the below on your screen:
   ```
   Greetings from
     _____  __       
    /  _  \|  | ____ ___  ___
   |  | |  |  |/ ___ \  \/  /
   |  |_|  |  |  ____/\    /
    \_____/|__|\_____|/   /
                     /___/

    *~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*
    Hello, I'm your cute and lovely friend Oley.
    What can I do for you?
 
    *~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*
    OOPS, the file does not exist! I will now create one for you~ (*o*)
 
    *~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*
   ```
5. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
- `todo do homework`: Adds _do homework_ as a todo type task to the task list.
- `delete 3`: Deletes the third task in the current task list.
- `list`: Lists all tasks.
- `mark 1`: Marks the first task in the list as done.
- `before 2023-02-01-1222`: Finds all tasks that end before _12:23 Feb 01 2023_.
- `find book`: Finds all tasks that contain the keyword _book_.
- `bye`: Exits the chatbot.
6. Refer to the **Features** below for details of each command.


   
## Features 

> [!NOTE]
> Words in `UPPER_CASE` are the parameters to be supplied by the user.   
> e.g. In `todo TASK_NAME`, `TASK_NAME` is a parameter which can be used as `todo go to sleep`.

### Adding a task: `todo` `deadline` `event`
Adds a new task to the task list.
- Adding a **to-do** task:
   - Format: `todo TASK_NAME`
   - Example: `todo walk` adds _walk_ as a to-do task to the task list.
- Adding a **deadline** task:
   - Format: `deadline TASK_NAME /by TIME`
      - `/by` must be used to indicate the due time
      - `TIME` must be in the format of `yyyy-MMM-dd-HHmm`, else Oley will ask you to re-enter `TIME` without `deadline TASK_NAME /by`
      - `TIME` should be **before the current time** as it is meaningless to add a passed task to the task list. If a passed deadline is entered, Oley will ask you to re-enter `TIME`.
   - Example: `deadline finish iP /by 2024-03-08-2359` adds _finish iP_ as a deadline task, which dues at 23:59 Mar 8th 2024.
- Adding a **event** task:
   - Format: `event TASK_NAME /from TIME /to TIME`
      - `/from` `/to` must be used to indicate the start and end time respectively
      - `TIME` must be in the format of `yyyy-MMM-dd-HHmm`, else Oley will first ask you to re-enter the start `TIME`, then the end `TIME`
      - The end `TIME` should be **after** the start `TIME`, else Oley will ask you to re-enter the end `TIME`.
   - Example: `event sleep /from 2024-03-05-2359 /to 2024-03-06-0800` adds an event _sleep_ that starts from 23:59 Mar 5th 2024, ends at 08:00 Mar 6th 2024.

### Deleting a task: `delete`
Deletes a task from the task list.
- Format: `delete INDEX`
   - `INDEX` must be an integer within the size of the current task list.
- Example: `delete 2` removes the second task from the task list.

### Listing all tasks: `list`
Lists out all existing tasks in the current task list.
- Format: `list`

### Marking / Unmarking a task: `mark` `unmark`
Marks a task as done or not done.
- Marking a task:
  - Format: `mark INDEX`
    - `INDEX` must be an integer within the size of the current task list.
    - If the task to be marked has been marked before, a warning will be given by Oley.
  - Example: `mark 4` marks task 4 as done.
- Unmarking a task:
  - Format: `unmark INDEX`
    - `INDEX` must be an integer within the size of the current task list.
    - If the task to be unmarked has not been marked before, a warning will be given by Oley.
  - Example: `unmark 2` marks task 2 as not done yet.
  
### Searching for tasks: `before` `find`
Searches for tasks that meet specific requirements.
- Searching for tasks that end before a certain time:
  - Format: `before TIME`
    - `TIME` must be in the format of `yyyy-MMM-dd-HHmm`.
  - Example: `before 2023-09-08-1222` searches for all tasks that end before 12:22 Sep 8th 2023, i.e. deadlines that due before the time, events that end before the time.
- Searching for tasks that contain a certain keyword:
  - Format: `find KEYWORD`
    - `KEYWORD` must be a single word. If multiple words are entered as `KEYWORD`, the first word will be recognised as the keyword to search for.
    - `KEYWORD` is case-sensitive.
  - Example: `find do` searches for all tasks that contain the keyword _do_.

### Exiting the chatbot: `bye`
Terminates the conversation with Oley.
- Format: `bye`
- The following will be shown on the screen:
```
    *~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*
bye
 
    *~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*~~~~~*
    Bye~ Feel free to talk to me anytime. I will always be here waiting for you. (0~0)
```

### Saving the data
Oley data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
> [!CAUTION]
> - Oley data are saved automatically as a `.txt` file: `[JAR file location]/data/Oley.txt`.  
> - Advanced users are welcome to update data directly by editing that data file.

