# Welcome to BobBot: Your CLI Taskmaster Extraordinaire! 🤖✨
Meet BobBot, your CLI-based task management sidekick designed for speedy typing enthusiasts. BobBot is here to make your to-do list fun, turning mundane task management into an engaging experience. Ready to boost productivity and add a dash of adventure to your daily routines? Dive into BobBot and transform your to-dos into triumphs!

## Contents
- [Quick Start](#quick-start)
- [Features](#features)

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `BobBot.jar` from [here]()
3. Copy the file to the folder you want to use as the home folder for your BobBot.
4. In the terminal, navigate to the folder where `BobBot.jar` is located.
5. Run the command `java -jar BobBot.jar` to start the app. 
6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will display the list of commands available.

## Features 

### Viewing help: `help`
This command displays the list of commands available.

### Adding a task: `todo`, `deadline`, `event`
Adds a task to the task list. 
- `todo` adds a general task without any date/time attached to it 
- `deadline` adds a task that needs to be done before a specific date/time 
- `event` adds a task that starts at a specific date/time and ends at a specific date/time.

Example of usage and corresponding output:
```
todo bake cake
        ______________________________________________________________________________
        Got it. I've added this task:
          [T][ ] bake cake
        Now you have 1 tasks in the list.
        ______________________________________________________________________________
```
```
deadline try this /by 2019-12-03 1400
        ______________________________________________________________________________
        Got it. I've added this task:
          [D][ ] try this (by: Dec 3 2019 1400)
        Now you have 2 tasks in the list.
        ______________________________________________________________________________
```
```
event try this /at 2019-12-03 1400 to 2019-12-03 1500
        ______________________________________________________________________________
        Got it. I've added this task:
          [E][ ] try this (at: Dec 3 2019 2pm to Dec 3 2019 3pm)
        Now you have 3 tasks in the list.
        ______________________________________________________________________________
```

### Listing all tasks: `list`
Displays all the tasks in the task list. <br>
Usage: `list` <br>
Example of usage and corresponding output:
```
list
        ______________________________________________________________________________
        Your task list currently has 6 items!

        1. [T][ ] make cake
        2. [D][ ] try this (by: Dec 3 2019 2pm)
        3. [D][X] make bread (by: tuesday 4pm)
        4. [D][ ] cook rice (by: 2021/10/11)
        5. [D][X] cook rice (by: Mar 11 2022 1800)
        6. [D][ ] eat bread (by: Mar 4 2019 1700)
        ______________________________________________________________________________
```

### Finding a task: `find`
Finds tasks whose descriptions contain the given keyword. <br>
Usage: `find <keyword>` <br>
Example of usage and corresponding output:
```
find make
        ______________________________________________________________________________
        Here are the matching tasks in your list:
        1. [T][ ] make cake
        2. [D][ ] make bread (by: tuesday 4pm)
        ______________________________________________________________________________
```

### Marking a task as done: `mark`
Marks the specified task as done. <br>
Usage: `mark <task number>` <br>
Example of usage and corresponding output:
```
mark 2
        ______________________________________________________________________________
        Got it! I've marked this task as done:
          [D][X] bake cake (by: monday 2pm)
        ______________________________________________________________________________
```

### Marking a task as done: `unmark`
Unmarks the specified task, making it not done. <br>
Usage: `unmark <task number>` <br>
Example of usage and corresponding output:
```
unmark 2
        ______________________________________________________________________________
        Got it! I've unmarked this task as not done:
          [D][ ] bake cake (by: monday 2pm)
        ______________________________________________________________________________
```

### Deleting a task: `delete`
Deletes the specified task number from the task list. <br>
Usage: `delete <task number>` <br>
Example of usage and corresponding output:
```
delete 2
        ______________________________________________________________________________
        Got it! Deleting this task:
          [D][ ] bake cake (by: monday 2pm)
        ______________________________________________________________________________
```

### Exiting the program: `bye`
Exits the program. <br>
Usage: `bye`
Example of usage and corresponding output:
```
bye
        ______________________________________________________________________________
        Bye. Hope to see you again soon!
        ______________________________________________________________________________
```