# Beefy User Guide

---
## Table of Content
1. Introduction
2. Quick Start 
3. Features
    - Command format
    - `todo`
    - `deadline`
    - `event`
    - `list`
    - `mark`
    - `unmark`
    - `delete`
    - `find`

---
## Introduction
Beefy is a task tracker chatbot that allows for users to keep track of tasks that they need to do.

---
## Quick Start
### Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click File > Close Project to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click Open. 
   2. Select the project directory, and click OK. 
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use JDK 11 (not other versions) as explained in here. 
   In the same dialog, set the Project language level field to the SDK default option. 
4. After that, locate the src/main/java/Duke.java file, right-click it, and choose Run Duke.main() 
(if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see 
something like the below as the output:
   ```angular2html
    ____________________________________________________________________
    BEEFY
    Hello there, I'm Beefy, what can I do for you?
    ____________________________________________________________________
   ```
---
## Features

### Command Format
- Command has a general structure of:  
    ```
    <mainCommand> <param1> /<secondaryCommand> <param2>
    ```
### 1. Add a new todo task: `todo`

- Format:  
`todo [TASK_DESCRIPTION]`
- Example of usage:  
`todo learn cs2113`
- Expected outcome:  
    ```angular2html
    ____________________________________________________________________
    BEEFY
    ---learn cs2113 has been added to task list!---
    ---Number of Tasks in List: 1---
    ____________________________________________________________________
    ```

### 2. Add a new deadline task: `deadline`

- Format:  
  `deadline [TASK_DESCRIPTION] /by [TASK_DEADLINE]`
- Example of usage:  
  `deadline learn cs2113 /by 2024-02-26T12:00`
- Expected outcome:
    ```angular2html
    ____________________________________________________________________
    BEEFY
    ---learn cs2113 has been added to task list!---
    ---Number of Tasks in List: 1---
    ____________________________________________________________________
    ```

### 3. Add a new event task: `event`

- Format:  
  `event [TASK_DESCRIPTION] /from [TASK_START_DATE] /to [TASK_END_DATE]`
- Example of usage:  
  `event learn cs2113 /from 2024-02-26T12:00 /to 2024-02-26T12:30`
- Expected outcome:
    ```angular2html
    ____________________________________________________________________
    BEEFY
    ---learn cs2113 has been added to task list!---
    ---Number of Tasks in List: 1---
    ____________________________________________________________________
    ```

### 4. List out all tasks: `list`

- Format:  
  `list`
- Example of usage:  
  `list`
- Expected outcome:
  ```angular2html
  ____________________________________________________________________
  Here are the tasks in your list:
  1. [T][ ] learn cs2113
  2. [D][ ] learn cs2113 (by: Feb 26 2024 , 1200)
  3. [E][ ] learn cs2113 (from: Feb 26 2024, 1200 to: Feb 26 2024, 1230)
  ____________________________________________________________________
  ```

### 5. Mark task: `mark`

- Format:  
  `mark [TASK_ID]`
- Example of usage:  
  `mark 1`
- Expected outcome:
  ```angular2html
  ____________________________________________________________________
  Nice one mate! I've marked this task as done:
  [T][X] learn cs2113
  ____________________________________________________________________
  ```

### 6. Unmark task: `unmark`

- Format:  
  `unmark [TASK_ID]`
- Example of usage:  
  `unmark 1`
- Expected outcome:
  ```angular2html
  ____________________________________________________________________
  Why?! I've marked this task as not done:
  [T][ ] learn cs2113
  ____________________________________________________________________
  ```

### 7. Delete task: `delete`

- Format:  
  `delete [TASK_ID]`
- Example of usage:  
  `delete 1`
- Expected outcome:
  ```angular2html
  ____________________________________________________________________
  ---learn cs2113 has been removed from task list!---
  ---Number of Tasks in List: 2---
  ____________________________________________________________________
  ```

### 8. Find all related tasks: `find`

- Format:  
  `find [TASK_DESCRIPTION]`
- Example of usage:  
  `find learn`
- Expected outcome:
  ```angular2html
  ____________________________________________________________________
  Here are the matching tasks in your list: 
  1. [T][ ] learn cs2113
  2. [D][ ] learn cs2113 (by: Feb 26 2024 , 1200)
  3. [E][ ] learn cs2113 (from: Feb 26 2024, 1200 to: Feb 26 2024, 1230)
  
  ____________________________________________________________________
  ```
