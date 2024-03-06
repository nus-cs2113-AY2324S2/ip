# Adam the Chat Bot

Adam the Chat Bot is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Adam can get your tasks managed faster than traditional GUI apps.
A complete user guide can be found [here](https://adamzzq.github.io/ip/).

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `adam.jar` from [here](https://github.com/adamzzq/ip/releases/download/A-UserGuide/adam.jar)
3. Copy the file to the folder you want to use as the home folder for Adam the Chat Bot.
4. Start a terminal and navigate to the folder where `adam.jar` is located.
5. Run the command `java -jar adam.jar` to start the app. The app should start with the following output displayed:
   ```
   Hello from
                 _
        /\      | |
       /  \   __| | __ _ _ __ ___
      / /\ \ / _` |/ _` | '_ ` _ \
     / ____ \ (_| | (_| | | | | | |
    /_/    \_\__,_|\__,_|_| |_| |_|
   
   ```
6. Type the command in the command box and press Enter to execute it. Some example commands you can try:
   - `list` : Lists all tasks.
   - `todo read book` : Adds a todo task with the description "read book".
   - `delete 1` : Deletes the 1st task shown in the current list.
   - `bye` : Exits the app.

## Command Summary

| Action                     | Format                                                  | Examples                                                  |
|----------------------------|---------------------------------------------------------|-----------------------------------------------------------|
| Add Todo                   | `todo <description>`                                    | `todo read book`                                          |
| Add Deadline               | `deadline <description> /by <date>`                     | `deadline return book /by 2024-04-30`                     |
| Add Event                  | `event <description> /from <start date> /to <end date>` | `event prepare for exams /from 2024-03-01 /to 2024-03-02` |
| List                       | `list` or `ls`                                          | `ls`                                                      |
| Mark/Unmark<br>a task as done | `mark <index>`                                          | `mark 1`, `unmark 2`                                      |
| Delete                     | `delete <index>`                                        | `delete 3`                                                |
| Find                       | `find <keyword>`                                        | `find book`                                               |
| Exit                       | `bye` or `ex` or `q`                                    | `q`                                                       |
| Help                       | `help` or `h`                                           | `h`                                                       |

