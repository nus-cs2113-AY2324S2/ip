# Project Kyrene - A Task Management ChatBot

Kyrene is a java project run on CLI that manages a personal to-do list. Given below are instructions on how to use it.

## Setting Up in Intellij
>[!OPTIONAL]
> For developers who want to modify Kyrene:
>
> Prerequisites: JDK 11, update Intellij to the most recent version.
>
> 1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
> 2. Open the project into Intellij as follows:
>    1. Click `Open`.
>    2. Select the project directory, and click `OK`.
>    3. If there are any further prompts, accept the defaults.
> 3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
>    In the same dialog, set the **Project language level** field to the `SDK default` option.
> 4. After that, locate the `src/main/java/kyrene/Kyrene.java` file, right-click it, and choose `Run Kyrene.main()` (if the code editor is showing compile errors, try restarting the IDE).

## Quick Guide to Start Kyrene with Executable JAR File

> [!IMPORTANT]
> Prerequisites: Ensure your device is able to run java apps, if not, please install java on your device.

 1. Go to the folder that contains `kyrene.jar` file, or open terminal and `cd` to the target folder.
 2. Launch the `kyrene.jar` file by key in command `java -jar kyrene.jar` in the terminal. If the setup is correct, you should see something like the below as the output:
    ```
    Loading file ./data/Kyrene.txt ...
    File ./data/Kyrene.txt is not found. Trying to create file...
    Folder ./data/ is not found. Trying to create folder...
    Folder ./data/ is successfully created.
    File ./data/Kyrene.txt is successfully created.
    |==========================================================|

    |==========================================================|

    _   _  _    _  ____   ____  _   _  ____
    | | / /\ \  / /|  _ \ | ___|| \ | || ___|
    | |/ /  \ \/ / | |_| || ===||  \| || ===|
    | |\ \   |  |  | |\ / | |__ | |\  || |__
    |_| \_\  |__|  |_| \_\|____||_| \_||____|   by Zhou Junmin

    |==========================================================|

    Hi, I am Kyrene, your private reminder assistant.
    What can I do for you?

    |==========================================================|
    ```
 3. If you see the above displays on your screen, congrats! Kyrene is now successfully launched!

## Features

> [!NOTE]
> All `[` and `]` and `(words within and including bracket)` are to be omitted when key in commands.

> [!IMPORTANT]
> The features below provide format of commands that must be followed to use Kyrene correctly.

### Adding a task: `todo` `deadline` `event`
 Adds a task of desired type to the task list.
 - Adding a to-do task:
   - Format: `todo [task description]`
   - Example:
     - `todo Play baldur's Gate 3` adds a to-do of *Play baldur's Gate 3* to the task list.
 - Adding a deadline task:
   - Format: `deadline [task description] /by [time]`
     - `[time]` must be in the format of: Year-Month-Date HourMinute.
     - Either date or time can be ignored, but not both.
     - If the format of `[time]` entered is invalid, Kyrene would require another input of `[time]`. There is no need to enter `deadline [task description] /by` again.
   - Examples:
     - `deadline Sleep /by 2359` adds a deadline of *Sleep* by *today 23:59* to the task list.
     - `deadline Prepare birthday gift /by 2024-06-06` adds a deadline of *Prepare birthday gift* by *2024.06.06 23:59* to the task list.
     - `deadline Attend online conference /by 2024-05-12-1000` adds a deadline of *Attend online conference* by *2024.05.12 10:00* to the task list.
 - Adding an event task:
   - Format: `event [task description] /from [starting time] /to [ending time]`
     - Both `[starting time]` and `[ending time]` must be in the format of: Year-Month-Date HourMinute.
     - Either date or time can be ignored, but not both.
     - If the format of `[starting time]` and `[ending time]` entered is invalid, Kyrene would require another two separate inputs of `[starting time]` and `[ending time]` respectively. There is no need to enter `event [task description] /from /to` again.
   - Examples:
       - `event Attend final exam /from 2024-05-01 1000 /to 1200` adds an event of *Attend final exam* from *2024.05.01 10:00* to *2024.05.01 12:00* to the task list.
       - `event Trip to JB /from 2024-03-01 /to 2024-03-02` adds an event of *Trip to JB* from *2024.03.01 00:00* to *2024.03.02 23:59* to the task list.

### Listing all tasks: `list`
 Lists out all existing tasks (including finished ones) within the task list.
 - Format: `list` lists all tasks.

### Deleting a task: `delete`
 Deletes the task of target index within the task list.
 - Format: `delete [task index]`
   - `[task index]` must be an integer that is within the range between 1 (if there is at least one task in the task list) and total number of existing tasks in the task list.
 - Example:
   - `delete 1` deletes task indexed 1.

### Marking a task: `mark` `unmark`
 Marks the task of target index as done or not done, depending on the input command.
 - Marking a task as done:
   - Format: `mark [task index]`
     - `[task index]` must be an integer that is within the range between 1 (if there is at least one task in the task list) and total number of existing tasks in the task list.
   - Example:
       - `mark 1` marks task indexed 1 as done.
 - Marking a task as **not** done:
     - Format: `unmark [task index]`
         - `[task index]` must be an integer that is within the range between 1 (if there is at least one task in the task list) and total number of existing tasks in the task list.
     - Example:
         - `unmark 1` marks task indexed 1 as not done.

### Searching tasks: `due` `at` `find`
 Searches tasks that meet the requirement, such as happening at a specific point of time or containing a key word.
 - Searching deadlines that are due before a date:
   - Format: `due [date]`
     - `[date]` must be in the format of: Year-Month-Date.
     - If the format of `[date]` entered is invalid, Kyrene would require another input of `[date]`. There is no need to enter `due` again.
   - Example:
     - `due 2024-04-01` searches deadlines due before 2024.04.01 23:59.
 - Searching events that happen at a point of time:
   - Format: `at [time]`
     - `[time]` must be in the format of: Year-Month-Date HourMinute.
     - Either date or time can be ignored, but not both.
     - If the format of `[time]` entered is invalid, Kyrene would require another input of `[time]`. There is no need to enter `at` again.
   - Examples:
       - `at 1400` searches events start before today 14:00, and end after today 14:00.
       - `at 2024-06-06` searches events start before 2024.06.06 23:59, and end after 2024.06.06 23:59.
       - `at 2024-05-12-1000` searches events start before 2024.05.12 10:00, and end after 2024.05.12 10:00.
 - Searching tasks containing a key word:
   - Format: `find [key word]`
     - `[key word]` must be a single word that does not contain a white space, otherwise Kyrene will take the word before the white space as key word.
     - `[key word]` is case-sensitive.
   - Example:
     - `find attend` searches tasks containing key word *attend*.

### Exiting the programme: `bye`
 Terminates conversations with Kyrene and exits the programme.
 - Format: `bye`
 - You will see the following indicating that you have successfully exited:
```
    |==========================================================|

    Bye! Wish to see you again soon!

    |==========================================================|
```

### Saving the data:
 Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
 
### Editing the data file:
> [!CAUTION]
> This action is only recommended for developers and advanced users only.
> 
> All tasks are stored in the `.txt` file in the following format.
> 
> For to-do tasks: `[task done status (true/false)] todo [task description]`
> 
> For deadline tasks: `[task done status (true/false)] deadline [task description] /by [time]`
> 
> For event tasks: `[task done status (true/false)] event [task description] /from [starting time] /to [ending time]`
> 
> If your changes to the data file makes its format invalid, Kyrene will discard the line of corrupted data. Hence, it is recommended to take a backup of the file before editing it, or to edit the data file only if you are confident that you can update it correctly.

 Task list data are saved automatically as a `.txt` file `[JAR file location]/data/Kyrene.txt`. Developers and advanced users are welcome to update data directly by editing that data file, for easier editing or testing purposes.
