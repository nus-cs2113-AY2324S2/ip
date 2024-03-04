package kvothe.task;
import kvothe.exception.WrongArgumentsException;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task; something to be done.
 */
public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //TODO: keep track of the received args
    /**
     * Parses the line and returns the arguments to create a new task.
     * @param line the line to parse
     * @param validArgs expected arguments for the task.
     * @return the values for the arguments.
     * @throws WrongArgumentsException if the arguments in the input are invalid
     */

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        String status = this.isDone ? "1" : "0";
        return  "|" + status + "|" + this.description + "|";
    }



}



