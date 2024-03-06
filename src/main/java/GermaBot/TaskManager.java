package GermaBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import Tasks.*;

public class TaskManager {
    private String filePath;

    public TaskManager(String filePath) {
        this.filePath = filePath;
    }

}
