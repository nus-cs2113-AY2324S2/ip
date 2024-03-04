# Horizon

Horizon is a chatbot that can help store your tasks, if you don't mind her attitude.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Main.java` file, right-click it, and run it. If the setup is correct, you should see something like the below as the output:
   ```
   Hello. You may call me Horizon. 
   Looks like you need me to remember things for you. Again.
   ------------------------------
   ```

## Features and usage
1. 'todo [task description]': Adds a todo with a description.
2. 'deadline [task description] /by [date YYYY-MM-DD]': Adds a deadline with a description and date.
3. 'event [task description] /from [date YYYY-MM-DD] /to [date YYYY-MM-DD]': Adds a event with a description and dates.
4. 'list': Lists current tasks.
5. 'mark [num]': Mark the selected task as done.
6. 'unmark [num]': Mark the selected task as not done.
7. 'delete [num]': Delete the selected task.
8. 'find [description]': Find tasks with the description.
9. 'bye': Exits the chatbot, saving the data to a file.