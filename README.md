# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
   1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
      In the same dialog, set the **Project language level** field to the `SDK default` option.
      1. After that, locate the `src/main/java/Chat/tasks/Evelyn.java` file, right-click it, and choose `Run Evelyn.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
         ```
         Hello from
         ------                    ___
         | ___|                    | |
         | |___ __    __  _______  | |  __      __  _________
         |  __| \ \  / /  |  _-_|  | |  \ \    / /  | _____ |
         | |___  \ \/ /   |  |___  | |   \ \  / /   | |   | |
         |_____|  \__/    |______| |_|    \ \/ /    |_|   |_|
                                           \/ /
                                           / /
                                          /_/
         ```
3. Type the actions you want to do, the commands include follows:
   1. *bye*: Exit from the program.
   2. *list*: List out all the tasks in your task list.
   3. *mark*: Mark the task with the index as done. 
      - format: mark (index)
   4. *unmark*: Mark the task with the index as not done. 
      - format: unmark (index)
   5. *todo*: Add a todo task. 
      - format: todo (description)
   6. *deadline*: Add a deadline task with a deadline. 
      - format: deadline (description) /by (time)
   7. *event*: Add a event task with a start time and end time.
      - format: event (description) /from (time) /to (time)
   8. *delete*: delete a task with a specified index from the task list.
      - format: delete (index)
   9. *find*: find tasks that contain a keyword.
      - format: find (keyword)