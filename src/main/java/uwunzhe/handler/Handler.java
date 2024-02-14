package uwunzhe.handler;

import uwunzhe.exceptions.UwunzheException;

public abstract class Handler {
    public abstract void handle() throws UwunzheException;
}
