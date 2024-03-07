# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project`
   to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in
   [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default`
   option.
4. After that, locate the `src/main/java/RecrBad.java` file, right-click it, and
   choose `Run RecrBad.main()` (if the code editor is showing compile errors, try
   restarting the IDE). If the setup is correct, you should see something like the
   below as the output:

```
   Hello! I'm
 ____   ___    ___   ____    ___
| __/  / _ \  | _ \  | __|  / _ \
| |    ||_||  | / /  | |_   | | |
| |_   | _ |  | _/   |_  /  | | |
|___\  // \\  |_|    /__/ . \___/

****************************************
What can I do for you?
```

5. Ctrl D to FC (compare files)

## Possible user commands
1. `todo <task>`                            : Add todo task
2. `deadline <task> /<deadline>`            : Add deadline
3. `event <task> /<startTime> /<endTime>`   : Add event
4. `list`                                   : List all tasks
5. `mark/unmark <taskIndex>`                : Mark task <index> as done/ undone
6. `find <keyword>`                         : Finds keyword in list of tasks
7. `bye`                                    : Exit Program
